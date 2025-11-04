package repository;

import model.Producto;

import java.util.List;

// Repositorio específico para productos
public class ProductoRepository extends BaseRepository<Producto> {

    private static final String ARCHIVO = "productos.dat";

    // Guarda la lista de productos
    public void guardarProductos(List<Producto> productos) {
        guardar(ARCHIVO, productos);
    }

    // Carga la lista de productos
    public List<Producto> cargarProductos() {
        return cargar(ARCHIVO);
    }
}
