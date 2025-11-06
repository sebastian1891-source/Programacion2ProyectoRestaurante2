package model;

import java.io.Serializable;

// Clase abstracta base de todos los productos del restaurante
public abstract class Producto implements Serializable {

    private static final long serialVersionUID = 1L; // Recomendado para clases serializables

    private int id;
    private String nombre;
    private double precio;
    private int stock;
    private boolean disponible;
    private String imagenPath; // Ruta de la imagen del producto

    // Constructor base completo (para nuevos productos con imagen)
    public Producto(int id, String nombre, double precio, int stock, boolean disponible, String imagenPath) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.disponible = disponible;
        this.imagenPath = imagenPath;
    }

    // Constructor alternativo sin imagen (compatibilidad con datos viejos)
    public Producto(int id, String nombre, double precio, int stock, boolean disponible) {
        this(id, nombre, precio, stock, disponible, null);
    }

    // Getters y Setters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }
    public boolean isDisponible() { return disponible; }
    public String getImagenPath() { return imagenPath; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPrecio(double precio) { this.precio = precio; }
    public void setStock(int stock) { this.stock = stock; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
    public void setImagenPath(String imagenPath) { this.imagenPath = imagenPath; }

    // Método para reducir stock
    public void reducirStock(int cantidad) {
        this.stock = Math.max(0, this.stock - cantidad); // Evita que quede negativo
    }

    // Cada subclase define su tipo (Comida, Bebida, Postre)
    public abstract String getTipo();

    @Override
    public String toString() {
        String base = nombre + " ($" + precio + ") - Stock: " + stock;
        if (imagenPath != null && !imagenPath.isBlank()) {
            base += " [Imagen: " + imagenPath + "]";
        }
        return base;
    }
}



