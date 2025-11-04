package service;

import exception.MesaNoDisponibleException;
import model.Mesa;

import java.util.List;

// Interface para la gestión de mesas
interface IMesaService {

    void abrirMesa(int numero) throws MesaNoDisponibleException;

    void cerrarMesa(int numero);

    Mesa obtenerMesa(int numero);

    List<Mesa> listarMesas();
}
