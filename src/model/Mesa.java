package model;

import java.io.Serializable;

// Representa una mesa del restaurante
public class Mesa implements Serializable {
    public enum Estado { LIBRE, OCUPADA, RESERVADA }

    private int numero;
    private Estado estado;
    private String mesero;

    public Mesa(int numero, Estado estado, String mesero) {
        this.numero = numero;
        this.estado = estado;
        this.mesero = mesero;
    }

    // Getters y setters
    public int getNumero() { return numero; }
    public Estado getEstado() { return estado; }
    public String getMesero() { return mesero; }

    public void setEstado(Estado estado) { this.estado = estado; }
    public void setMesero(String mesero) { this.mesero = mesero; }

    @Override
    public String toString() {
        return "Mesa " + numero + " (" + estado + ") - Mesero: " + mesero;
    }
}

