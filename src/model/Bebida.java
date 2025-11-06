package model;

// Subclase concreta para bebidas
public class Bebida extends Producto {

    // Constructor sin imagen
    public Bebida(int id, String nombre, double precio, int stock, boolean disponible) {
        super(id, nombre, precio, stock, disponible);
    }

    // Nuevo constructor con imagen
    public Bebida(int id, String nombre, double precio, int stock, boolean disponible, String imagenPath) {
        super(id, nombre, precio, stock, disponible, imagenPath);
    }

    @Override
    public String getTipo() {
        return "Bebida";
    }
}


