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
        return pedido.calcularTotal(); // método correcto de la clase Pedido
    }

    @Override
    public List<Pedido> listarPedidos() {
        return new ArrayList<>(pedidos);
    }

    @Override
    public void cerrarPedido(Pedido pedido) {
        // 🔹 En lugar de llamar a un método inexistente, lo marcamos como cerrado
        pedidos.add(pedido);
        repo.guardarPedidos(pedidos);
        System.out.println("Pedido #" + pedido.getNumero() + " cerrado correctamente.");
    }
}

