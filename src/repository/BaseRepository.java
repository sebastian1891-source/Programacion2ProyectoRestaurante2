package repository;

import javax.swing.*;
import java.io.*;
import java.util.List;

// Clase genérica para guardar y cargar listas en archivos binarios (.dat)
public class BaseRepository<T> {

    // Guarda una lista de objetos en un archivo binario
    public void guardar(String nombreArchivo, List<T> lista) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    "Error al guardar datos en " + nombreArchivo,
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Carga una lista de objetos desde un archivo binario
    @SuppressWarnings("unchecked")
    public List<T> cargar(String nombreArchivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            return (List<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "Advertencia: no se pudieron cargar datos de " + nombreArchivo +
                            ". Se iniciará una lista vacía.",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
            return new java.util.ArrayList<>();
        }
    }
}
