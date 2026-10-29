package py.com.zeus.web;

import com.fasterxml.jackson.databind.JsonNode;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;
import py.com.zeus.config.Usuario;
import py.com.zeus.db.ConectarDB;
import py.com.zeus.db.ZeusException;

/** Movimientos (Mod. Compra, Mod. Servicio, Mod. Gest. Cobros) y consultas de las ventanas. */
@Controller
class MovController {

    private static final Pattern PARAM = Pattern.compile("(?<!:):(_?[a-z]+)");

    private final ConectarDB db;

    MovController(ConectarDB db) {
        this.db = db;
    }

    @GetMapping("/mov/{key}")
    String ventana(@PathVariable String key, @AuthenticationPrincipal Usuario u, Model m) {
        Movimientos.Mov mov = Movimientos.MOVS.get(key);
        if (mov == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        m.addAttribute("mov", mov);
        m.addAttribute("u", u);
        return "mov/" + key;
    }

    @PostMapping("/mov/{key}/save")
    @ResponseBody
    Map<String, Object> guardar(@PathVariable String key, @RequestBody JsonNode body, @AuthenticationPrincipal Usuario u) {
        Movimientos.Mov mov = Movimientos.MOVS.get(key);
        if (mov == null || mov.funcion() == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ventana inexistente");
        }
        int ban = body.path("ban").asInt();
        if (!mov.banderas().contains(ban)) {
            throw new ZeusException("Operación no válida en esta ventana");
        }
        if (mov.banderasAprob().contains(ban) && !u.isAprobador()) {
            throw new ZeusException("No posee los permisos necesarios", "Acceso Denegado");
        }
        String cod = RefController.texto(body.path("cod"));
        int res = db.mov(mov.funcion(), ban, cod == null ? null : Integer.valueOf(cod), u,
                body.path("cab"), body.path("det"));
        return Map.of("ok", true, "cod", res);
    }

    /** consultas de la lista blanca (ver Consultas) */
    @GetMapping("/q/{nombre}")
    @ResponseBody
    List<Map<String, Object>> consulta(@PathVariable String nombre, @RequestParam Map<String, String> req,
                                       @AuthenticationPrincipal Usuario u) {
        String sql = Consultas.SQL.get(nombre);
        if (sql == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Consulta inexistente: " + nombre);
        }
        List<Object> args = new ArrayList<>();
        Matcher m = PARAM.matcher(sql);
        StringBuilder sb = new StringBuilder();
        while (m.find()) {
            String p = m.group(1);
            m.appendReplacement(sb, "?");
            args.add(switch (p) {
                case "_suc" -> u.getSucCod();
                case "_emp" -> u.getEmpCi();
                default -> {
                    String v = req.get(p);
                    v = v == null || v.isBlank() ? null : v.trim();
                    yield switch (Consultas.TIPOS.getOrDefault(p, "s")) {
                        case "i" -> new SqlParameterValue(Types.INTEGER, v == null ? null : Integer.valueOf(v));
                        default -> new SqlParameterValue(Types.VARCHAR, v);
                    };
                }
            });
        }
        m.appendTail(sb);
        return db.list(sb.toString(), args.toArray());
    }
}
