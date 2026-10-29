package py.com.zeus.web;

import com.fasterxml.jackson.databind.JsonNode;
import java.math.BigDecimal;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.jdbc.core.SqlParameterValue;
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
import py.com.zeus.web.Referenciales.Campo;
import py.com.zeus.web.Referenciales.Ref;

/** Ventanas "Mantener ..." (una sola plantilla para todos los referenciales). */
@Controller
@org.springframework.web.bind.annotation.RequestMapping("/ref/{key}")
class RefController {

    private final ConectarDB db;

    RefController(ConectarDB db) {
        this.db = db;
    }

    private static Ref ref(String key, Usuario u) {
        Ref r = Referenciales.get(key);
        if (r == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ventana inexistente");
        }
        if (r.soloAdmin() && !u.isAdmin()) {
            throw new ZeusException("No posee los permisos necesarios", "Acceso Denegado");
        }
        return r;
    }

    @GetMapping
    String ventana(@PathVariable String key, @AuthenticationPrincipal Usuario u, Model m) {
        Ref r = Referenciales.get(key);
        if (r == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        m.addAttribute("ref", r);
        m.addAttribute("permitido", !r.soloAdmin() || u.isAdmin());
        return "ref";
    }

    /** listado (pestaña Listado): ver Vigentes/Anulados + busqueda por descripcion o codigo */
    @GetMapping("/list")
    @ResponseBody
    List<Map<String, Object>> listar(@PathVariable String key, @RequestParam(defaultValue = "VIG") String est,
                                     @RequestParam(defaultValue = "") String q, @AuthenticationPrincipal Usuario u) {
        Ref r = ref(key, u);
        String cols = r.cols().stream().map(Referenciales.Col::col).collect(Collectors.joining(", "));
        return db.list("SELECT " + cols + " FROM " + r.vista()
                + " WHERE est_cod = ? AND (" + r.busqueda() + "::text ILIKE '%' || ? || '%' OR " + r.codCol() + "::text = ?)"
                + " ORDER BY 1 LIMIT 1000", "ANU".equals(est) ? "ANU" : "VIG", q.trim(), q.trim());
    }

    /** datos para la pestaña Edicion */
    @GetMapping("/get")
    @ResponseBody
    Map<String, Object> obtener(@PathVariable String key, @RequestParam int cod, @AuthenticationPrincipal Usuario u) {
        Ref r = ref(key, u);
        String cols = r.campos().stream().filter(c -> !"p".equals(c.tipo())).map(Campo::col)
                .collect(Collectors.joining(", "));
        Map<String, Object> d = db.one("SELECT " + r.codCol() + ", " + cols + " FROM " + r.vista()
                + " WHERE " + r.codCol() + " = ?", cod);
        if (d == null) {
            throw new ZeusException("El registro " + cod + " no existe");
        }
        return d;
    }

    /** Guardar / Anular / Reactivar: abm_x(bandera, cod, campos...) */
    @PostMapping("/save")
    @ResponseBody
    Map<String, Object> guardar(@PathVariable String key, @RequestBody JsonNode body, @AuthenticationPrincipal Usuario u) {
        Ref r = ref(key, u);
        int ban = body.path("ban").asInt();
        if (ban < 1 || ban > 4) {
            throw new ZeusException("Operación no válida");
        }
        JsonNode cod = body.path("cod");
        JsonNode f = body.path("f");
        List<SqlParameterValue> p = new ArrayList<>();
        p.add(new SqlParameterValue(Types.INTEGER, ban));
        p.add(new SqlParameterValue(Types.INTEGER, texto(cod) == null ? null : Integer.valueOf(texto(cod))));
        for (Campo c : r.campos()) {
            String v = ban <= 2 ? texto(f.path(c.col())) : null;
            if (ban <= 2 && c.req() && v == null) {
                throw new ZeusException("Debe completar los campos Obligatorios(*) para proceder\nCampo: " + c.label());
            }
            if (v != null && c.max() > 0 && v.length() > c.max()) {
                throw new ZeusException("El campo " + c.label() + " admite como máximo " + c.max() + " caracteres");
            }
            p.add(parametro(c.tipo(), v));
        }
        if (r.codManual() && ban == 1 && p.get(1).getValue() == null) {
            throw new ZeusException("Debe ingresar el " + r.codLabel());
        }
        int res = db.abm(r.funcion(), p);
        return Map.of("ok", true, "cod", res, "msg", mensaje(ban));
    }

    static String texto(JsonNode n) {
        if (n == null || n.isNull() || n.isMissingNode()) {
            return null;
        }
        String s = n.asText().trim();
        return s.isEmpty() ? null : s;
    }

    static SqlParameterValue parametro(String tipo, String v) {
        return switch (tipo) {
            case "i", "c" -> new SqlParameterValue(Types.INTEGER, v == null ? null : Integer.valueOf(v));
            case "n" -> new SqlParameterValue(Types.NUMERIC, v == null ? null : new BigDecimal(v.replace(",", ".")));
            case "d" -> new SqlParameterValue(Types.DATE, v == null ? null : java.sql.Date.valueOf(LocalDate.parse(v)));
            case "b" -> new SqlParameterValue(Types.BOOLEAN, v == null ? Boolean.FALSE : Boolean.valueOf(v));
            default -> new SqlParameterValue(Types.VARCHAR, v);
        };
    }

    /** mismos mensajes que conectarDB.EmitirMensaje del legacy */
    static String mensaje(int ban) {
        return switch (ban) {
            case 1 -> "Registro Guardado con Éxito";
            case 2 -> "Registro Modificado con Éxito";
            case 3 -> "Registro Anulado con Éxito";
            default -> "Operación realizada con Éxito";
        };
    }
}
