package ui;

import javax.swing.*;
import java.awt.*;

// Ventana principal del sistema
public class MainMenuUI extends JFrame {

    public MainMenuUI() {
        setTitle("Sistema de Gestión de Restaurante");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));

        JLabel titulo = new JLabel("Menú Principal", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));

        JButton btnMesas = new JButton("Gestión de Mesas");
        JButton btnCarta = new JButton("Gestión de Carta");
        JButton btnPedidos = new JButton("Pedidos y Pagos");
        JButton btnSalir = new JButton("Salir");

        // Acciones
        btnMesas.addActionListener(e -> new MesaUI());
        btnCarta.addActionListener(e -> new CartaUI());
        btnPedidos.addActionListener(e -> new PedidoUI());
        btnSalir.addActionListener(e -> System.exit(0));

        panel.add(titulo);
        panel.add(btnMesas);
        panel.add(btnCarta);
        panel.add(btnPedidos);
        panel.add(btnSalir);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainMenuUI::new);
    }
}
