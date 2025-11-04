package service;

import exception.StockInsuficienteException;
import model.Pedido;
import model.Producto;
import repository.PedidoRepository;

import java.util.ArrayList;
import java.util.List;

// Implementa la lógica de los pedidos
public class PedidoService implements IPedidoService {

    private final PedidoRepository repo = new PedidoRepository();
    private final List<Pedido> pedidos;

    public PedidoService() {
        this.pedidos = repo.cargarPedidos();
    }

    @Override
    public void agregarProductoAlPedido(Pedido pedido, Producto producto, int cantidad) throws StockInsuficienteException {
        if (producto.getStock() < cantidad)
            throw new StockInsuficienteException("No hay stock suficiente para " + producto.getNombre());

        producto.setStock(producto.getStock() - cantidad);
        pedido.agregarProducto(producto, cantidad);
        repo.guardarPedidos(pedidos);
    }

    @Override
    public double calcularTotal(Pedido pedido) {
        return pedido.getTotal();
    }

    @Override
    public List<Pedido> listarPedidos() {
        return new ArrayList<>(pedidos);
    }

    @Override
    public void cerrarPedido(Pedido pedido) {
        pedido.cerrarPedido();
        repo.guardarPedidos(pedidos);
    }
}
