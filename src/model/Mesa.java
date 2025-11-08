package model;

import java.io.Serializable;

public class Mesa implements Serializable {

    public enum Estado {
        LIBRE, OCUPADA, RESERVADA
    }

    private int id;
    private int numero;
    private int capacidad;
    private Estado estado;
    private String mesero;

    public Mesa(int id, int numero, int capacidad) {
        this.id = id;
        this.numero = numero;
        this.capacidad = capacidad;
        this.estado = Estado.LIBRE; // Estado inicial
        this.mesero = "";
    }

    public int getId() { return id; }
    public int getNumero() { return numero; }
    public int getCapacidad() { return capacidad; }
    public Estado getEstado() { return estado; }
    public String getMesero() { return mesero; }

    public void setEstado(Estado estado) { this.estado = estado; }
    public void setMesero(String mesero) { this.mesero = mesero; }

    @Override
    public String toString() {
        return "Mesa " + numero + " (Capacidad: " + capacidad +
                ", Estado: " + estado +
                ", Mesero: " + (mesero.isEmpty() ? "Sin asignar" : mesero) + ")";
    }
}





