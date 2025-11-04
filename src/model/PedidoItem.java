package model;

import java.io.Serializable;

// Relación entre producto y cantidad pedida
public class PedidoItem implements Serializable {
    private Producto producto;
    private int cantidad;

    public PedidoItem(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() { return producto; }
    public int getCantidad() { return cantidad; }

    // Subtotal = precio * cantidad
    public double getSubtotal() {
        return producto.getPrecio() * cantidad;
    }

    @Override
    public String toString() {
        return producto.getNombre() + " x" + cantidad + " = $" + getSubtotal();
    }
}

