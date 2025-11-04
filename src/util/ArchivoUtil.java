package util;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Clase de utilidad para escribir tickets o reportes simples en archivos de texto
public class ArchivoUtil {

    // Guarda un texto en un archivo .txt
    public static void guardarEnArchivo(String nombreArchivo, String contenido) {
        try (FileWriter writer = new FileWriter(nombreArchivo)) {
            writer.write(contenido);
            System.out.println("Archivo guardado correctamente: " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al guardar archivo: " + e.getMessage());
        }
    }

    // Crea un ticket básico con fecha y total
    public static void generarTicket(String nombreCliente, double total) {
        String fecha = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        String ticket = "=== TICKET DE COMPRA ===\n"
                + "Cliente: " + nombreCliente + "\n"
                + "Fecha: " + fecha + "\n"
                + "Total: $" + total + "\n"
                + "=========================\n";

        guardarEnArchivo("ticket_" + nombreCliente + ".txt", ticket);
    }
}
