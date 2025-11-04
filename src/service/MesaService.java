package service;

import exception.MesaNoDisponibleException;
import model.Mesa;
import repository.MesaRepository;

import java.util.ArrayList;
import java.util.List;

// Implementa la lógica de las mesas
public class MesaService implements IMesaService {

    private final MesaRepository repo = new MesaRepository();
    private final List<Mesa> mesas;

    public MesaService() {
        this.mesas = repo.cargarMesas();
        if (mesas.isEmpty()) inicializarMesas(10); // crea 10 mesas por defecto
    }

    // 🔹 Crea mesas por defecto con estado LIBRE y mesero "Sin asignar"
    private void inicializarMesas(int cantidad) {
        for (int i = 1; i <= cantidad; i++) {
            mesas.add(new Mesa(i, Mesa.Estado.LIBRE, "Sin asignar"));
        }
        repo.guardarMesas(mesas);
    }

    @Override
    public void abrirMesa(int numero) throws MesaNoDisponibleException {
        Mesa mesa = obtenerMesa(numero);
        if (mesa == null) throw new MesaNoDisponibleException("Mesa no encontrada.");

        // Verificamos el estado usando el enum directamente
        if (mesa.getEstado() != Mesa.Estado.LIBRE) {
            throw new MesaNoDisponibleException("La mesa " + numero + " no está disponible.");
        }

        mesa.setEstado(Mesa.Estado.OCUPADA);
        repo.guardarMesas(mesas);
    }

    @Override
    public void cerrarMesa(int numero) {
        Mesa mesa = obtenerMesa(numero);
        if (mesa != null) {
            mesa.setEstado(Mesa.Estado.LIBRE);
            repo.guardarMesas(mesas);
        }
    }

    @Override
    public Mesa obtenerMesa(int numero) {
        return mesas.stream()
                .filter(m -> m.getNumero() == numero)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Mesa> listarMesas() {
        return new ArrayList<>(mesas);
    }
}


