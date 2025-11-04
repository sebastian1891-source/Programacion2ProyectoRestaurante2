package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Representa un pedido realizado en una mesa
public class Pedido implements Serializable {
    private int id;
    private Mesa mesa;
    private List<PedidoItem> items;
    private LocalDateTime fecha;
    private boolean cobrado;

    public Pedido(int id, Mesa mesa) {
        this.id = id;
        this.mesa = mesa;
        this.items = new ArrayList<>();
        this.fecha = LocalDateTime.now();
        this.cobrado = false;
    }

    public int getId() { return id; }
    public Mesa getMesa() { return mesa; }
    public List<PedidoItem> getItems() { return items; }
    public LocalDateTime getFecha() { return fecha; }
    public boolean isCobrado() { return cobrado; }
    public void setCobrado(boolean cobrado) { this.cobrado = cobrado; }

    // Agregar un producto al pedido
    public void agregarItem(Producto producto, int cantidad) {
        items.add(new PedidoItem(producto, cantidad));
    }

    // Quitar producto del pedido
    public void eliminarItem(Producto producto) {
        items.removeIf(item -> item.getProducto().equals(producto));
    }

    // Calcular total del pedido
    public double calcularTotal() {
        double total = 0;
        for (PedidoItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Pedido #" + id + " - Mesa " + mesa.getNumero() + " - Total: $" + calcularTotal();
    }
}
