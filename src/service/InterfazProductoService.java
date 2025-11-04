package service;

import exception.DatoInvalidoException;
import exception.StockInsuficienteException;
import model.Producto;

import java.util.List;

// Interface para la gestión de productos
interface IProductoService {

    void agregarProducto(Producto producto) throws DatoInvalidoException;

    void actualizarStock(String nombre, int nuevoStock) throws DatoInvalidoException;

    Producto buscarProductoPorNombre(String nombre);

    List<Producto> listarProductos();

    void descontarStock(String nombre, int cantidad) throws StockInsuficienteException;
}
