package model;

import java.io.Serializable;

/**
 * Representa una mesa del restaurante.
 * Implementa Serializable para permitir persistencia en archivos.
 */
public class Mesa implements Serializable {

    private static final long serialVersionUID = 1L; // buena práctica para Serializable

    public enum Estado {
        LIBRE, OCUPADA, RESERVADA
    }

    private int numero;
    private Estado estado;
    private String mesero;

    // Constructor completo
    public Mesa(int numero, Estado estado, String mesero) {
        this.numero = numero;
        this.estado = estado != null ? estado : Estado.LIBRE;
        this.mesero = mesero != null ? mesero : "Sin asignar";
    }

    // Constructor alternativo (estado por defecto LIBRE)
    public Mesa(int numero, String mesero) {
        this(numero, Estado.LIBRE, mesero);
    }

    // Getters
    public int getNumero() {
        return numero;
    }

    public Estado getEstado() {
        return estado;
    }

    public String getMesero() {
        return mesero;
    }

    // Setters
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setMesero(String mesero) {
        this.mesero = mesero;
    }

    @Override
    public String toString() {
        return "Mesa " + numero + " - Estado: " + estado + " - Mesero: " + mesero;
    }
}





