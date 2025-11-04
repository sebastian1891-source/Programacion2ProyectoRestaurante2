package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Clase para manejar formatos de fecha y hora
public class FechaUtil {

    // Devuelve la fecha y hora actual formateada
    public static String obtenerFechaHoraActual() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return LocalDateTime.now().format(formato);
    }

    // Devuelve solo la fecha actual (útil para reportes)
    public static String obtenerFechaActual() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDateTime.now().format(formato);
    }
}
