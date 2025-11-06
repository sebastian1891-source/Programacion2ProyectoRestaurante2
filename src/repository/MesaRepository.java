package repository;

import model.Mesa;
import java.util.List;

/**
 * Repositorio específico para la gestión de Mesas.
 * Se encarga de guardar y cargar los datos de las mesas desde un archivo binario.
 */
public class MesaRepository extends BaseRepository<Mesa> {

    private static final String ARCHIVO = "mesas.dat";

    /**
     * Guarda la lista completa de mesas en el archivo.
     *
     * @param mesas Lista de mesas a guardar
     */
    public void guardarMesas(List<Mesa> mesas) {
        if (mesas == null) return; // evita errores si la lista es nula
        guardar(ARCHIVO, mesas);
    }

    /**
     * Carga la lista de mesas desde el archivo.
     *
     * @return Lista de mesas cargadas; una lista vacía si el archivo no existe o está vacío
     */
    public List<Mesa> cargarMesas() {
        return cargar(ARCHIVO);
    }
}

