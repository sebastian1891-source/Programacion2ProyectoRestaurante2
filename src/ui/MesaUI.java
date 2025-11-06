package ui;

import exception.MesaNoDisponibleException;
import model.Mesa;
import service.MesaService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MesaUI extends JFrame {

    private final MesaService mesaService = new MesaService();
    private final JPanel panelMesas;

    public MesaUI() {
        setTitle("Gestión de Mesas");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Colores base del sistema
        Color fondo = new Color(20, 20, 20);
        Color rojoVinotinto = new Color(139, 0, 0);
        Color texto = Color.WHITE;

        // Panel principal
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBackground(fondo);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Título
        JLabel titulo = new JLabel("Gestión de Mesas", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 32));
        titulo.setForeground(rojoVinotinto);
        panel.add(titulo, BorderLayout.NORTH);

        // Panel dinámico de mesas
        panelMesas = new JPanel();
        panelMesas.setLayout(new GridLayout(0, 5, 15, 15)); // auto-ajustable
        panelMesas.setBackground(fondo);
        JScrollPane scroll = new JScrollPane(panelMesas);
        scroll.setBorder(BorderFactory.createLineBorder(rojoVinotinto, 2));
        panel.add(scroll, BorderLayout.CENTER);

        // Botones inferiores
        JButton btnAbrir = new JButton("Abrir Mesa");
        JButton btnCerrar = new JButton("Cerrar Mesa");
        JButton btnRefrescar = new JButton("Actualizar");
        JButton btnVolver = new JButton("Volver al Menú");

        for (JButton b : new JButton[]{btnAbrir, btnCerrar, btnRefrescar, btnVolver}) {
            b.setBackground(rojoVinotinto);
            b.setForeground(Color.WHITE);
            b.setFont(new Font("Segoe UI", Font.BOLD, 16));
            b.setFocusPainted(false);
            b.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        }

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(fondo);
        panelBotones.add(btnAbrir);
        panelBotones.add(btnCerrar);
        panelBotones.add(btnRefrescar);
        panelBotones.add(btnVolver);
        panel.add(panelBotones, BorderLayout.SOUTH);

        // Acciones
        btnAbrir.addActionListener(e -> {
            abrirMesa();
            mostrarMesas();
        });

        btnCerrar.addActionListener(e -> {
            cerrarMesa();
            mostrarMesas();
        });

        btnRefrescar.addActionListener(e -> mostrarMesas());

        btnVolver.addActionListener(e -> {
            dispose();
            new MainMenuUI();
        });

        // Actualización automática cada 5 segundos
        Timer refresco = new Timer(5000, e -> mostrarMesas());
        refresco.start();

        // Mostrar mesas al iniciar
        mostrarMesas();

        add(panel);
        setVisible(true);
    }

    /** Muestra las mesas con colores visuales según su estado */
    private void mostrarMesas() {
        panelMesas.removeAll();
        List<Mesa> lista = mesaService.listarMesas();

        for (Mesa m : lista) {
            JPanel tarjeta = new JPanel(new BorderLayout());
            tarjeta.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            tarjeta.setPreferredSize(new Dimension(150, 100));

            // Color según el estado
            switch (m.getEstado()) {
                case LIBRE -> tarjeta.setBackground(new Color(0, 153, 0)); // verde
                case OCUPADA -> tarjeta.setBackground(new Color(204, 0, 0)); // rojo
                case RESERVADA -> tarjeta.setBackground(new Color(255, 204, 0)); // amarillo
            }

            JLabel lblNumero = new JLabel("Mesa " + m.getNumero(), SwingConstants.CENTER);
            lblNumero.setFont(new Font("Segoe UI", Font.BOLD, 18));
            lblNumero.setForeground(Color.WHITE);

            JLabel lblEstado = new JLabel(m.getEstado().toString(), SwingConstants.CENTER);
            lblEstado.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            lblEstado.setForeground(Color.WHITE);

            JLabel lblMesero = new JLabel("Mesero: " + m.getMesero(), SwingConstants.CENTER);
            lblMesero.setFont(new Font("Segoe UI", Font.ITALIC, 13));
            lblMesero.setForeground(Color.WHITE);

            tarjeta.add(lblNumero, BorderLayout.NORTH);
            tarjeta.add(lblEstado, BorderLayout.CENTER);
            tarjeta.add(lblMesero, BorderLayout.SOUTH);

            panelMesas.add(tarjeta);
        }

        panelMesas.revalidate();
        panelMesas.repaint();
    }

    /** Abre una mesa */
    private void abrirMesa() {
        try {
            int numero = Integer.parseInt(JOptionPane.showInputDialog("Número de mesa a abrir:"));
            mesaService.abrirMesa(numero);
            JOptionPane.showMessageDialog(this, "Mesa abierta correctamente.");
        } catch (MesaNoDisponibleException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Entrada inválida.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /** Cierra una mesa */
    private void cerrarMesa() {
        try {
            int numero = Integer.parseInt(JOptionPane.showInputDialog("Número de mesa a cerrar:"));
            mesaService.cerrarMesa(numero);
            JOptionPane.showMessageDialog(this, "Mesa cerrada correctamente.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Entrada inválida.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MesaUI::new);
    }
}




