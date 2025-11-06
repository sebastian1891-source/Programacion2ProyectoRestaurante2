package model;

// Subclase concreta para postres
public class Postre extends Producto {

    // Constructor original (sin imagen)
    public Postre(int id, String nombre, double precio, int stock, boolean disponible) {
        super(id, nombre, precio, stock, disponible);
    }

    // Nuevo constructor con imagen
    public Postre(int id, String nombre, double precio, int stock, boolean disponible, String imagenPath) {
        super(id, nombre, precio, stock, disponible, imagenPath);
    }

    @Override
    public String getTipo() {
        return "Postre";
    }
}


