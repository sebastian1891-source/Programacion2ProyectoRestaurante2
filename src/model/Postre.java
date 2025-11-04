package model;

// Subclase concreta para postres
public class Postre extends Producto {
    public Postre(int id, String nombre, double precio, int stock, boolean disponible) {
        super(id, nombre, precio, stock, disponible);
    }

    @Override
    public String getTipo() { return "Postre"; }
}

