package util;

import exception.DatoInvalidoException;

// Clase con métodos de validación reutilizables
public class ValidacionUtil {

    // Verifica que un string no esté vacío
    public static void validarTexto(String texto, String campo) throws DatoInvalidoException {
        if (texto == null || texto.isBlank()) {
            throw new DatoInvalidoException("El campo '" + campo + "' no puede estar vacío.");
        }
    }

    // Verifica que un número sea mayor a 0
    public static void validarPositivo(double valor, String campo) throws DatoInvalidoException {
        if (valor <= 0) {
            throw new DatoInvalidoException("El campo '" + campo + "' debe ser mayor que 0.");
        }
    }

    // Verifica que un stock no sea negativo
    public static void validarStock(int stock) throws DatoInvalidoException {
        if (stock < 0) {
            throw new DatoInvalidoException("El stock no puede ser negativo.");
        }
    }
}
