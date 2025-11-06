package repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase genérica para guardar y cargar listas en archivos binarios (.dat)
 */
public class BaseRepository<T> {

    /**
     Guarda una lista de objetos en un archivo binario

     */
    public synchronized void guardar(String nombreArchivo, List<T> lista) {
        if (lista == null) return;

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))) {
            oos.writeObject(lista);
        } catch (IOException e) {
            System.err.println("⚠️ Error al guardar datos en " + nombreArchivo + ": " + e.getMessage());
        }
    }

    /**
     * Carga una lista de objetos desde un archivo binario.
     *
     * @param nombreArchivo Nombre del archivo desde donde se cargarán los datos
     * @return Lista de objetos cargados (vacía si ocurre un error)
     */
    @SuppressWarnings("unchecked")
    public synchronized List<T> cargar(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        if (!archivo.exists()) {
            return new ArrayList<>(); // Si no existe, devolver lista vacía
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            return (List<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("⚠️ Error al cargar datos de " + nombreArchivo + ": " + e.getMessage());
            return new ArrayList<>();
        }
    }
}

