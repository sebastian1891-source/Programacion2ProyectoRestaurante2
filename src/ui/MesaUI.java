package ui;

import exception.MesaNoDisponibleException;
import model.Mesa;
import service.MesaService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MesaUI extends JFrame {

    private final MesaService mesaService = new MesaService();
    private final Color vinotinto = new Color(139, 0, 0);
    private final Color fondo = new Color(20, 20, 20);
    private JPanel panelMesas;

    public MesaUI() {
        setTitle("Gestión de Mesas");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout(15, 15));
        panelPrincipal.setBackground(fondo);
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Título
        JLabel titulo = new JLabel("Gestión de Mesas", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 30));
        titulo.setForeground(vinotinto);
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        // Panel para las mesas (se llena dinámicamente)
        panelMesas = new JPanel(new GridLayout(0, 6, 15, 15)); // 6 columnas
        panelMesas.setBackground(new Color(35, 35, 35));
        actualizarVistaMesas();

        JScrollPane scroll = new JScrollPane(panelMesas);
        scroll.setBorder(BorderFactory.createEmptyBorder());

        // Panel inferior de botones
        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(fondo);

        JButton btnAbrir = crearBoton("Abrir Mesa");
        JButton btnCerrar = crearBoton("Cerrar Mesa");
        JButton btnListar = crearBoton("Actualizar Vista");
        JButton btnVolver = crearBoton("Volver al Menú");

        panelBotones.add(btnAbrir);
        panelBotones.add(btnCerrar);
        panelBotones.add(btnListar);
        panelBotones.add(btnVolver);

        // Agregar componentes al panel principal
        panelPrincipal.add(titulo, BorderLayout.NORTH);
        panelPrincipal.add(scroll, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal);

        // Acciones
        btnAbrir.addActionListener(e -> abrirMesa());
        btnCerrar.addActionListener(e -> cerrarMesa());
        btnListar.addActionListener(e -> actualizarVistaMesas());
        btnVolver.addActionListener(e -> dispose());

        setVisible(true);
    }

    private JButton crearBoton(String texto) {
        JButton btn = new JButton(texto);
        btn.setBackground(vinotinto);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    // Actualiza la vista de las mesas en pantalla
    private void actualizarVistaMesas() {
        panelMesas.removeAll();
        List<Mesa> mesas = mesaService.obtenerMesas();

        for (Mesa mesa : mesas) {
            JPanel card = new JPanel(new BorderLayout());
            card.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
            card.setBackground(mesa.getEstado() == Mesa.Estado.LIBRE
                    ? new Color(0, 128, 0) // Verde si está libre
                    : new Color(178, 34, 34)); // Rojo si está ocupada

            JLabel lblNumero = new JLabel("Mesa " + mesa.getNumero(), SwingConstants.CENTER);
            lblNumero.setFont(new Font("Segoe UI", Font.BOLD, 18));
            lblNumero.setForeground(Color.WHITE);

            JLabel lblEstado = new JLabel(mesa.getEstado().toString(), SwingConstants.CENTER);
            lblEstado.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            lblEstado.setForeground(Color.LIGHT_GRAY);

            card.add(lblNumero, BorderLayout.CENTER);
            card.add(lblEstado, BorderLayout.SOUTH);
            panelMesas.add(card);
        }

        panelMesas.revalidate();
        panelMesas.repaint();
    }

    private void abrirMesa() {
        try {
            int numero = Integer.parseInt(JOptionPane.showInputDialog("Número de mesa a abrir:"));
            mesaService.abrirMesa(numero);
            JOptionPane.showMessageDialog(this, "Mesa " + numero + " abierta correctamente.");
            actualizarVistaMesas();
        } catch (MesaNoDisponibleException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Mesa no disponible", JOptionPane.WARNING_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cerrarMesa() {
        try {
            int numero = Integer.parseInt(JOptionPane.showInputDialog("Número de mesa a cerrar:"));
            mesaService.cerrarMesa(numero);
            JOptionPane.showMessageDialog(this, "Mesa " + numero + " cerrada correctamente.");
            actualizarVistaMesas();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MesaUI::new);
    }
}




