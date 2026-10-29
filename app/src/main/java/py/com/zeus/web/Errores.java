package py.com.zeus.web;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import py.com.zeus.db.ZeusException;

/** Los errores vuelven como {ok:false, titulo, msg} y la ventana los muestra en un dialogo. */
@RestControllerAdvice(assignableTypes = {RefController.class, MovController.class, EscritorioController.class})
class Errores {

    private static final Logger log = LoggerFactory.getLogger(Errores.class);

    @ExceptionHandler(ZeusException.class)
    ResponseEntity<Map<String, Object>> zeus(ZeusException e) {
        return ResponseEntity.unprocessableEntity()
                .body(Map.of("ok", false, "titulo", e.getTitulo(), "msg", e.getMessage()));
    }

    @ExceptionHandler({NumberFormatException.class, IllegalArgumentException.class})
    ResponseEntity<Map<String, Object>> formato(RuntimeException e) {
        return ResponseEntity.badRequest()
                .body(Map.of("ok", false, "titulo", "Atención", "msg", "Dato con formato inválido: " + e.getMessage()));
    }

    @ExceptionHandler(org.springframework.web.server.ResponseStatusException.class)
    ResponseEntity<Map<String, Object>> estado(org.springframework.web.server.ResponseStatusException e) {
        return ResponseEntity.status(e.getStatusCode())
                .body(Map.of("ok", false, "titulo", "Atención", "msg", String.valueOf(e.getReason())));
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<Map<String, Object>> otro(Exception e) {
        log.error("Error no controlado", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("ok", false, "titulo", "Error del Sistema", "msg", "Ocurrió un error inesperado. Contacte con el Administrador."));
    }
}
