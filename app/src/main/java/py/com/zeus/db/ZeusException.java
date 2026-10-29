package py.com.zeus.db;

/** Error de negocio que se muestra al usuario en un dialogo (JOptionPane del legacy). */
public class ZeusException extends RuntimeException {

    private final String titulo;

    public ZeusException(String mensaje) {
        this(mensaje, "Atención");
    }

    public ZeusException(String mensaje, String titulo) {
        super(mensaje);
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }
}
