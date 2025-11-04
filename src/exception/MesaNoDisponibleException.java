package exception;

// Excepción personalizada para cuando se intenta abrir una mesa ocupada o reservada
public class MesaNoDisponibleException extends Exception {

    // Constructor con mensaje personalizado
    public MesaNoDisponibleException(String mensaje) {
        super(mensaje);
    }
}
