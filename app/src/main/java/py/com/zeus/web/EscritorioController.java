package py.com.zeus.web;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.servlet.http.HttpSession;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import py.com.zeus.config.Usuario;
import py.com.zeus.db.ConectarDB;
import py.com.zeus.db.ZeusException;

/** Login, escritorio (frames.Menu), menu Sistema/Ayuda e informes. */
@Controller
class EscritorioController {

    private static final Set<String> PAGINAS_SIS = Set.of("calendario", "calculadora", "acerca", "ayuda", "pass", "backup");
    private static final Set<String> PAGINAS_INF = Set.of("stock", "ref", "mov");

    private final ConectarDB db;

    @Value("${zeus.bloqueo-minutos}") int bloqueoMinutos;
    @Value("${zeus.db.host}") String dbHost;
    @Value("${zeus.db.port}") String dbPort;
    @Value("${zeus.db.name}") String dbName;
    @Value("${spring.datasource.username}") String dbUser;
    @Value("${spring.datasource.password}") String dbPass;

    EscritorioController(ConectarDB db) {
        this.db = db;
    }

    @GetMapping("/login")
    String login(HttpSession s, Model m) {
        Object err = s.getAttribute("loginError");
        if (err != null) {
            m.addAttribute("error", err);
            s.removeAttribute("loginError");
        }
        return "login";
    }

    @GetMapping("/")
    String escritorio(@AuthenticationPrincipal Usuario u, Model m) {
        m.addAttribute("u", u);
        m.addAttribute("menu", Menu.BARRA);
        m.addAttribute("bloqueoMinutos", bloqueoMinutos);
        return "menu";
    }

    /** desbloqueo de pantalla (menu Sistema > Bloquear y bloqueo por inactividad) */
    @PostMapping("/api/desbloquear")
    @ResponseBody
    Map<String, Object> desbloquear(@RequestBody JsonNode body, @AuthenticationPrincipal Usuario u) {
        if (db.one("SELECT 1 FROM fn_login(?, ?)", u.getUser(), body.path("pass").asText("")) == null) {
            throw new ZeusException("Contraseña incorrecta", "Bloqueo de Seguridad");
        }
        return Map.of("ok", true);
    }

    @PostMapping("/api/pass")
    @ResponseBody
    Map<String, Object> cambiarPass(@RequestBody JsonNode body, @AuthenticationPrincipal Usuario u) {
        String nueva = body.path("nueva").asText("");
        if (!nueva.equals(body.path("repetir").asText())) {
            throw new ZeusException("La nueva contraseña y su confirmación no coinciden");
        }
        db.one("SELECT fn_cambiar_pass(?, ?, ?)", u.getUsrCod(), body.path("actual").asText(""), nueva);
        return Map.of("ok", true, "msg", "Contraseña modificada con Éxito");
    }

    @GetMapping("/sis/{pagina}")
    String sistema(@PathVariable String pagina, @AuthenticationPrincipal Usuario u, Model m) {
        if (!PAGINAS_SIS.contains(pagina)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        m.addAttribute("u", u);
        return "sis/" + pagina;
    }

    /** Copia de Seguridad (frames.BackUp): descarga un pg_dump de la base */
    @GetMapping("/sis/backup/descargar")
    ResponseEntity<StreamingResponseBody> backup(@AuthenticationPrincipal Usuario u) {
        if (!u.isAdmin()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "No posee los permisos necesarios");
        }
        String archivo = "zeus_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmm")) + ".sql";
        StreamingResponseBody cuerpo = out -> {
            ProcessBuilder pb = new ProcessBuilder("pg_dump", "-h", dbHost, "-p", dbPort, "-U", dbUser,
                    "-d", dbName, "--clean", "--if-exists");
            pb.environment().put("PGPASSWORD", dbPass);
            pb.redirectError(ProcessBuilder.Redirect.DISCARD);
            Process p = pb.start();
            try (InputStream in = p.getInputStream()) {
                in.transferTo(out);
            }
        };
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + archivo + "\"")
                .contentType(MediaType.parseMediaType("application/sql"))
                .body(cuerpo);
    }

    @GetMapping("/inf/{pagina}")
    String informe(@PathVariable String pagina, @AuthenticationPrincipal Usuario u, Model m) {
        if (!PAGINAS_INF.contains(pagina)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        m.addAttribute("u", u);
        m.addAttribute("refs", Referenciales.todos().values().stream().filter(r -> !r.soloAdmin() || u.isAdmin()).toList());
        return "inf/" + pagina;
    }
}
