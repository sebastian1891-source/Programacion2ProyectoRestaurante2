package model;

// Subclase concreta para comidas
public class Comida extends Producto {
    public Comida(int id, String nombre, double precio, int stock, boolean disponible) {
        super(id, nombre, precio, stock, disponible);
    }

    @Override
    public String getTipo() { return "Comida"; }
}

