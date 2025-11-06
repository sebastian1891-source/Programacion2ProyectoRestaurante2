package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// Representa un pedido realizado por una mesa
public class Pedido implements Serializable {

    private int numero;
    private Mesa mesa;
    private List<ItemPedido> items;

    // Clase interna para almacenar producto + cantidad
    public static class ItemPedido implements Serializable {
        private Producto producto;
        private int cantidad;

        public ItemPedido(Producto producto, int cantidad) {
            this.producto = producto;
            this.cantidad = cantidad;
        }

        public Producto getProducto() { return producto; }
        public int getCantidad() { return cantidad; }

        public double getSubtotal() {
            return producto.getPrecio() * cantidad;
        }
    }

    // Constructor principal (coherente con PedidoUI y PedidoService)
    public Pedido(int numero, Mesa mesa) {
        this.numero = numero;
        this.mesa = mesa;
        this.items = new ArrayList<>();
    }

    // Getters
    public int getNumero() { return numero; }
    public Mesa getMesa() { return mesa; }
    public List<ItemPedido> getItems() { return items; }

    // Agrega un producto al pedido
    public void agregarProducto(Producto producto, int cantidad) {
        items.add(new ItemPedido(producto, cantidad));
    }

    // Calcula el total del pedido
    public double calcularTotal() {
        double total = 0;
        for (ItemPedido item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Pedido #" + numero +
                " | Mesa: " + mesa.getNumero() +
                " | Total: $" + calcularTotal();
    }
}

