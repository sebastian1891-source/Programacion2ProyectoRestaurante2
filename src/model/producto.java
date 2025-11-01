package model;

import java.io.Serializable;

// Clase abstracta base de todos los productos del restaurante
public abstract class Producto implements Serializable {
    private int id;
    private String nombre;
    private double precio;
    private int stock;
    private boolean disponible;

    // Constructor base
    public Producto(int id, String nombre, double precio, int stock, boolean disponible) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.disponible = disponible;
    }

    // Getters y Setters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }
    public boolean isDisponible() { return disponible; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPrecio(double precio) { this.precio = precio; }
    public void setStock(int stock) { this.stock = stock; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    // Método para reducir el stock al vender
    public void reducirStock(int cantidad) { this.stock -= cantidad; }

    // Cada subclase define su tipo
    public abstract String getTipo();

    @Override
    public String toString() {
        return nombre + " ($" + precio + ") - Stock: " + stock;
    }
}
