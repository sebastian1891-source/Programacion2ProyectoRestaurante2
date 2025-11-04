package exception;

// Excepción general para validar entradas incorrectas (ej: precio <= 0, nombre vacío)
public class DatoInvalidoException extends Exception {

    public DatoInvalidoException(String mensaje) {
        super(mensaje);
    }
}
