package repository;

import model.Pedido;

import java.util.List;

// Repositorio específico para pedidos
public class PedidoRepository extends BaseRepository<Pedido> {

    private static final String ARCHIVO = "pedidos.dat";

    // Guarda los pedidos
    public void guardarPedidos(List<Pedido> pedidos) {
        guardar(ARCHIVO, pedidos);
    }

    // Carga los pedidos
    public List<Pedido> cargarPedidos() {
        return cargar(ARCHIVO);
    }
}

