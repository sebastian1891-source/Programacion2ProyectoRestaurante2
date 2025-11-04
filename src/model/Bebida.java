package model;

// Subclase concreta para bebidas
public class Bebida extends Producto {
    public Bebida(int id, String nombre, double precio, int stock, boolean disponible) {
        super(id, nombre, precio, stock, disponible);
    }

    @Override
    public String getTipo() { return "Bebida"; }
}

