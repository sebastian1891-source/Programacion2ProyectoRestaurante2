package exception;

// Excepción personalizada para cuando no hay suficiente stock de un producto
public class StockInsuficienteException extends Exception {

    // Constructor con mensaje personalizado
    public StockInsuficienteException(String mensaje) {
        super(mensaje);
    }
}
