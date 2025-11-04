package service;

import exception.DatoInvalidoException;
import exception.StockInsuficienteException;
import model.Producto;
import repository.ProductoRepository;

import java.util.ArrayList;
import java.util.List;

// Implementa la lógica de los productos
public class ProductoService implements IProductoService {

    private final ProductoRepository repo = new ProductoRepository();
    private final List<Producto> productos;

    public ProductoService() {
        this.productos = repo.cargarProductos();
    }

    @Override
    public void agregarProducto(Producto producto) throws DatoInvalidoException {
        if (producto.getNombre() == null || producto.getNombre().isBlank())
            throw new DatoInvalidoException("El nombre del producto no puede estar vacío.");
        if (producto.getPrecio() <= 0)
            throw new DatoInvalidoException("El precio debe ser mayor que cero.");

        productos.add(producto);
        repo.guardarProductos(productos);
    }

    @Override
    public void actualizarStock(String nombre, int nuevoStock) throws DatoInvalidoException {
        Producto p = buscarProductoPorNombre(nombre);
        if (p == null) throw new DatoInvalidoException("Producto no encontrado.");
        if (nuevoStock < 0) throw new DatoInvalidoException("El stock no puede ser negativo.");
        p.setStock(nuevoStock);
        repo.guardarProductos(productos);
    }

    @Override
    public Producto buscarProductoPorNombre(String nombre) {
        return productos.stream()
                .filter(p -> p.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Producto> listarProductos() {
        return new ArrayList<>(productos);
    }

    @Override
    public void descontarStock(String nombre, int cantidad) throws StockInsuficienteException {
        Producto p = buscarProductoPorNombre(nombre);
        if (p == null) throw new StockInsuficienteException("Producto no encontrado.");
        if (p.getStock() < cantidad)
            throw new StockInsuficienteException("Stock insuficiente para " + nombre + ".");
        p.setStock(p.getStock() - cantidad);
        repo.guardarProductos(productos);
    }

    // 🔧 NUEVO MÉTODO: genera un ID único incremental
    public int generarNuevoId() {
        if (productos.isEmpty()) {
            return 1;
        } else {
            return productos.stream()
                    .mapToInt(Producto::getId)
                    .max()
                    .orElse(0) + 1;
        }
    }
}

