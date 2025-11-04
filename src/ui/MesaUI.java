package ui;

import exception.MesaNoDisponibleException;
import model.Mesa;
import service.MesaService;

import javax.swing.*;
import java.awt.*;

public class MesaUI extends JFrame {

    private MesaService mesaService = new MesaService();
    private JTextArea areaMesas;

    public MesaUI() {
        setTitle("Gestión de Mesas");
        setSize(400, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        areaMesas = new JTextArea();
        JScrollPane scroll = new JScrollPane(areaMesas);
        JButton btnAbrir = new JButton("Abrir Mesa");
        JButton btnCerrar = new JButton("Cerrar Mesa");
        JButton btnRefrescar = new JButton("Actualizar");

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAbrir);
        panelBotones.add(btnCerrar);
        panelBotones.add(btnRefrescar);

        panel.add(scroll, BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);
        add(panel);

        // Acciones
        btnAbrir.addActionListener(e -> abrirMesa());
        btnCerrar.addActionListener(e -> cerrarMesa());
        btnRefrescar.addActionListener(e -> mostrarMesas());

        mostrarMesas();
        setVisible(true);
    }

    private void mostrarMesas() {
        areaMesas.setText("");
        for (Mesa m : mesaService.listarMesas()) {
            areaMesas.append("Mesa " + m.getNumero() + " - Estado: " + m.getEstado() + "\n");
        }
    }

    private void abrirMesa() {
        try {
            int numero = Integer.parseInt(JOptionPane.showInputDialog("Número de mesa a abrir:"));
            mesaService.abrirMesa(numero);
            JOptionPane.showMessageDialog(this, "Mesa abierta correctamente.");
            mostrarMesas();
        } catch (MesaNoDisponibleException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Entrada inválida.");
        }
    }

    private void cerrarMesa() {
        try {
            int numero = Integer.parseInt(JOptionPane.showInputDialog("Número de mesa a cerrar:"));
            mesaService.cerrarMesa(numero);
            JOptionPane.showMessageDialog(this, "Mesa cerrada correctamente.");
            mostrarMesas();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Entrada inválida.");
        }
    }
}
