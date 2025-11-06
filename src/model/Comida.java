package model;

// Subclase concreta para comidas
public class Comida extends Producto {

    // Constructor
    public Comida(int id, String nombre, double precio, int stock, boolean disponible) {
        super(id, nombre, precio, stock, disponible);
    }

    // Nuevo constructor con imagen
    public Comida(int id, String nombre, double precio, int stock, boolean disponible, String imagenPath) {
        super(id, nombre, precio, stock, disponible, imagenPath);
    }

    @Override
    public String getTipo() {
        return "Comida";
    }
}


