package service;

import exception.MesaNoDisponibleException;
import model.Mesa;
import repository.MesaRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio que gestiona la lógica de las mesas del restaurante.
 * Se asegura de que los cambios sean persistentes y visibles desde la UI.
 */
public class MesaService implements IMesaService {

    private static final MesaRepository repo = new MesaRepository();
    private static final List<Mesa> mesas = new ArrayList<>();

    public MesaService() {
        // Solo carga las mesas una vez (manteniendo los datos en memoria compartida)
        if (mesas.isEmpty()) {
            mesas.addAll(repo.cargarMesas());
            if (mesas.isEmpty()) inicializarMesas(10); // crea 10 mesas por defecto
        }
    }

    /** Crea mesas iniciales con estado LIBRE y mesero "Sin asignar" */
    private void inicializarMesas(int cantidad) {
        mesas.clear();
        for (int i = 1; i <= cantidad; i++) {
            mesas.add(new Mesa(i, Mesa.Estado.LIBRE, "Sin asignar"));
        }
        repo.guardarMesas(mesas);
    }

    @Override
    public void abrirMesa(int numero) throws MesaNoDisponibleException {
        Mesa mesa = obtenerMesa(numero);
        if (mesa == null)
            throw new MesaNoDisponibleException("Mesa no encontrada.");

        if (mesa.getEstado() != Mesa.Estado.LIBRE)
            throw new MesaNoDisponibleException("La mesa " + numero + " no está disponible.");

        mesa.setEstado(Mesa.Estado.OCUPADA);
        repo.guardarMesas(mesas); // Persistimos el cambio
        sincronizar(); // Recargamos el estado desde el archivo
    }

    @Override
    public void cerrarMesa(int numero) {
        Mesa mesa = obtenerMesa(numero);
        if (mesa != null) {
            mesa.setEstado(Mesa.Estado.LIBRE);
            repo.guardarMesas(mesas);
            sincronizar();
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
        sincronizar(); // siempre muestra el estado actualizado
        return new ArrayList<>(mesas);
    }

    /** Recarga las mesas desde el archivo para reflejar los cambios guardados */
    private void sincronizar() {
        List<Mesa> actualizadas = repo.cargarMesas();
        mesas.clear();
        mesas.addAll(actualizadas);
    }
}


