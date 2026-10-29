package py.com.zeus.db;

import com.fasterxml.jackson.databind.JsonNode;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;
import org.postgresql.util.PSQLException;
import org.postgresql.util.ServerErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameterValue;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import py.com.zeus.config.Usuario;

/**
 * Equivalente al config.conectarDB del legacy, sobre un pool de conexiones.
 * Toda consulta va parametrizada (el legacy concatenaba texto del usuario en el SQL)
 * y los errores se traducen a mensajes en lugar de hacer System.exit(1).
 */
@Component
public class ConectarDB {

    private static final Logger log = LoggerFactory.getLogger(ConectarDB.class);

    private final JdbcTemplate jdbc;

    public ConectarDB(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Map<String, Object>> list(String sql, Object... args) {
        try {
            List<Map<String, Object>> l = jdbc.queryForList(sql, args);
            // fechas en formato fijo para las ventanas (yyyy-MM-dd para los campos de fecha)
            for (Map<String, Object> r : l) {
                r.replaceAll((k, v) -> v instanceof java.sql.Date d ? d.toLocalDate().toString()
                        : v instanceof java.sql.Timestamp t ? t.toLocalDateTime().format(FECHA_HORA) : v);
            }
            return l;
        } catch (DataAccessException e) {
            throw traducir(e, "Error al Consultar");
        }
    }

    private static final java.time.format.DateTimeFormatter FECHA_HORA =
            java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public Map<String, Object> one(String sql, Object... args) {
        List<Map<String, Object>> l = list(sql, args);
        return l.isEmpty() ? null : l.get(0);
    }

    /** Ejecuta una funcion abm_* de referencial: abm_x(ban, cod, campos...). Devuelve el codigo. */
    @Transactional
    public int abm(String funcion, List<SqlParameterValue> params) {
        String sql = "SELECT " + funcion + "(" + "?,".repeat(params.size()).replaceAll(",$", "") + ")";
        log.info("EXECUTE {} {}", funcion, params.stream().map(SqlParameterValue::getValue).toList());
        try {
            Integer r = jdbc.queryForObject(sql, Integer.class, params.toArray());
            return r == null ? 0 : r;
        } catch (DataAccessException e) {
            throw traducir(e, "Error en la interacción con la BD");
        }
    }

    /**
     * Ejecuta un movimiento: abm_x(ban, cod, emp_ci, suc_cod, cab, det).
     * Cabecera y detalles van en una sola llamada atomica
     * (el legacy los mandaba por separado con executeBatch y podia quedar a medias).
     */
    @Transactional
    public int mov(String funcion, int ban, Integer cod, Usuario u, JsonNode cab, JsonNode det) {
        log.info("EXECUTE {} ban={} cod={} usr={} cab={} det={}", funcion, ban, cod, u.getUser(), cab, det);
        try {
            Integer r = jdbc.queryForObject(
                    "SELECT " + funcion + "(?, ?, ?, ?, ?::jsonb, ?::jsonb)", Integer.class,
                    ban,
                    new SqlParameterValue(Types.INTEGER, cod),
                    u.getEmpCi(),
                    u.getSucCod(),
                    json(cab),
                    json(det));
            return r == null ? 0 : r;
        } catch (DataAccessException e) {
            throw traducir(e, "Error en la interacción con la BD");
        }
    }

    private static String json(JsonNode n) {
        return n == null || n.isNull() || n.isMissingNode() ? null : n.toString();
    }

    private ZeusException traducir(DataAccessException e, String titulo) {
        Throwable t = e.getMostSpecificCause();
        if (t instanceof PSQLException p && p.getServerErrorMessage() != null) {
            ServerErrorMessage m = p.getServerErrorMessage();
            String estado = p.getSQLState();
            switch (estado == null ? "" : estado) {
                case "P0001": // RAISE EXCEPTION de las funciones abm_*
                    return new ZeusException(m.getMessage(), "Atención");
                case "23505":
                    return new ZeusException("Ya existe un registro con esos datos.\n" + detalle(m), "Registro duplicado");
                case "23503":
                    return new ZeusException("El dato seleccionado no existe o el registro está siendo utilizado.\n" + detalle(m), titulo);
                case "23514":
                    return new ZeusException("Uno de los valores ingresados no es válido (" + m.getConstraint() + ")", titulo);
                case "23502":
                    return new ZeusException("Debe completar el campo obligatorio: " + m.getColumn(), titulo);
                case "22P02", "22007", "22008", "22003":
                    return new ZeusException("Formato de dato inválido: " + m.getMessage(), titulo);
                default:
                    break;
            }
        }
        log.error("Error de base de datos", e);
        String msg = t instanceof SQLException s ? s.getMessage() : e.getMessage();
        return new ZeusException(msg, titulo);
    }

    private static String detalle(ServerErrorMessage m) {
        return m.getDetail() == null ? "" : m.getDetail()
                .replace("Key ", "Clave ").replace("already exists", "ya existe")
                .replace("is not present in table", "no existe en la tabla")
                .replace("is still referenced from table", "está referenciado desde la tabla");
    }
}
