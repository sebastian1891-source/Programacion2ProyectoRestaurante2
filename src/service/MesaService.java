package service;

import exception.MesaNoDisponibleException;
import model.Mesa;

import java.util.ArrayList;
import java.util.List;

public class MesaService {

    private final List<Mesa> mesas = new ArrayList<>();

    public MesaService() {
        inicializarMesas();
    }

    private void inicializarMesas() {
        mesas.clear();
        // Crear 12 mesas con capacidad genérica (por ejemplo, 4 personas)
        for (int i = 1; i <= 12; i++) {
            Mesa mesa = new Mesa(i, i, 4); // id = i, número = i, capacidad = 4
            mesa.setEstado(Mesa.Estado.LIBRE);
            mesa.setMesero("Sin asignar mesero");
            mesas.add(mesa);
        }
    }

    public List<Mesa> listarMesas() {
        return mesas;
    }

    public List<Mesa> obtenerMesas() {
        return listarMesas();
    }

    public Mesa buscarPorNumero(int numeroMesa) {
        for (Mesa m : mesas) {
            if (m.getNumero() == numeroMesa) {
                return m;
            }
        }
        return null;
    }

    // Método para abrir una mesa (no permite abrir una ya ocupada)
    public void abrirMesa(int numeroMesa) throws MesaNoDisponibleException {
        Mesa mesa = buscarPorNumero(numeroMesa);

        if (mesa == null) {
            throw new MesaNoDisponibleException("La mesa no existe.");
        }

        if (mesa.getEstado() == Mesa.Estado.OCUPADA) {
            throw new MesaNoDisponibleException("La mesa " + numeroMesa + " ya está abierta.");
        }

        mesa.setEstado(Mesa.Estado.OCUPADA);
        System.out.println("Mesa " + numeroMesa + " abierta correctamente.");
    }

    // Método para cerrar una mesa
    public void cerrarMesa(int numeroMesa) {
        Mesa mesa = buscarPorNumero(numeroMesa);
        if (mesa != null && mesa.getEstado() == Mesa.Estado.OCUPADA) {
            mesa.setEstado(Mesa.Estado.LIBRE);
            mesa.setMesero("Sin asignar mesero");
            System.out.println("Mesa " + numeroMesa + " cerrada correctamente.");
        } else if (mesa != null) {
            System.out.println("La mesa " + numeroMesa + " no está ocupada (actual: " + mesa.getEstado() + ")");
        } else {
            System.out.println("Mesa no encontrada.");
        }
    }

    public Mesa obtenerMesa(int numeroMesa) {
        return buscarPorNumero(numeroMesa);
    }
}


