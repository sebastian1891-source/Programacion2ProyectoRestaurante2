package ui;

import javax.swing.*;
import java.awt.*;

// Ventana principal del sistema
public class MainMenuUI extends JFrame {

    public MainMenuUI() {
        setTitle("Sistema de Gestión de Restaurante");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Permitir maximizar y ocupar toda la pantalla
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(800, 600)); // tamaño mínimo razonable
        setLocationRelativeTo(null);

        // Colores principales
        Color vinotinto = new Color(90, 0, 0);
        Color negro = new Color(20, 20, 20);
        Color grisClaro = new Color(230, 230, 230);

        // Panel principal
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(negro);

        // Título superior
        JLabel titulo = new JLabel("Menú Principal", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titulo.setForeground(vinotinto);
        titulo.setBorder(BorderFactory.createEmptyBorder(40, 0, 30, 0));

        // Panel central con botones
        JPanel botonesPanel = new JPanel(new GridLayout(4, 1, 25, 25));
        botonesPanel.setBackground(negro);
        botonesPanel.setBorder(BorderFactory.createEmptyBorder(40, 200, 100, 200));

        // Crear botones estilizados
        JButton btnMesas = crearBoton("Gestión de Mesas", "src/ui/img/mesa.png", vinotinto, grisClaro);
        JButton btnCarta = crearBoton("Gestión de Carta", "src/ui/img/carta.png", vinotinto, grisClaro);
        JButton btnPedidos = crearBoton("Pedidos y Pagos", "src/ui/img/pedido.png", vinotinto, grisClaro);
        JButton btnSalir = crearBoton("Salir", "src/ui/img/salir.png", Color.DARK_GRAY, Color.WHITE);

        // Acciones de botones
        btnMesas.addActionListener(e -> new MesaUI());
        btnCarta.addActionListener(e -> new CartaUI());
        btnPedidos.addActionListener(e -> new PedidoUI());
        btnSalir.addActionListener(e -> System.exit(0));

        // Agregar botones al panel
        botonesPanel.add(btnMesas);
        botonesPanel.add(btnCarta);
        botonesPanel.add(btnPedidos);
        botonesPanel.add(btnSalir);

        // Imagen central o logo del restaurante (opcional)
        JLabel imagenCentral = new JLabel();
        imagenCentral.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon icon = cargarImagen("src/ui/img/restaurant_logo.png", 200, 200);
        if (icon != null) imagenCentral.setIcon(icon);

        // Ensamblar la interfaz
        panel.add(titulo, BorderLayout.NORTH);
        panel.add(imagenCentral, BorderLayout.CENTER);
        panel.add(botonesPanel, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }

    // Método auxiliar para crear botones estilizados con íconos
    private JButton crearBoton(String texto, String rutaIcono, Color colorFondo, Color colorTexto) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 22));
        boton.setBackground(colorFondo);
        boton.setForeground(colorTexto);
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        ImageIcon icon = cargarImagen(rutaIcono, 40, 40);
        if (icon != null) boton.setIcon(icon);

        boton.setHorizontalAlignment(SwingConstants.LEFT);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return boton;
    }

    // Método para cargar y escalar imágenes
    private ImageIcon cargarImagen(String ruta, int ancho, int alto) {
        try {
            ImageIcon icon = new ImageIcon(ruta);
            Image img = icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            return new ImageIcon(img);
        } catch (Exception e) {
            System.err.println("No se pudo cargar la imagen: " + ruta);
            return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainMenuUI::new);
    }
}

