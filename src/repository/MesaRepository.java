package repository;

import model.Mesa;

import java.util.List;

// Repositorio específico para mesas
public class MesaRepository extends BaseRepository<Mesa> {

    private static final String ARCHIVO = "mesas.dat";

    // Guarda las mesas
    public void guardarMesas(List<Mesa> mesas) {
        guardar(ARCHIVO, mesas);
    }

    // Carga las mesas
    public List<Mesa> cargarMesas() {
        return cargar(ARCHIVO);
    }
}
