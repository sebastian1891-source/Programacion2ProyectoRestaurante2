package ui;

import exception.DatoInvalidoException;
import model.Bebida;
import model.Comida;
import model.Postre;
import model.Producto;
import service.ProductoService;

import javax.swing.*;
import java.awt.*;

public class CartaUI extends JFrame {

    private final ProductoService productoService = new ProductoService();

    public CartaUI() {
        setTitle("Gestión de Carta / Menú");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Colores base
        Color fondo = new Color(20, 20, 20);
        Color rojoVinotinto = new Color(139, 0, 0);

        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBackground(fondo);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Gestión de Carta / Menú", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titulo.setForeground(rojoVinotinto);

        JButton btnAgregar = new JButton("Agregar Producto");
        JButton btnListar = new JButton("Mostrar Carta");
        JButton btnVolver = new JButton("Volver al Menú");

        // Personalización de botones
        for (JButton b : new JButton[]{btnAgregar, btnListar, btnVolver}) {
            b.setBackground(rojoVinotinto);
            b.setForeground(Color.WHITE);
            b.setFont(new Font("Segoe UI", Font.BOLD, 16));
            b.setFocusPainted(false);
            b.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        }

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(fondo);
        panelBotones.add(btnAgregar);
        panelBotones.add(btnListar);
        panelBotones.add(btnVolver);

        panel.add(titulo, BorderLayout.NORTH);
        panel.add(panelBotones, BorderLayout.SOUTH);

        add(panel);

        // Acciones
        btnAgregar.addActionListener(e -> agregarProducto());
        btnListar.addActionListener(e -> mostrarProductos());
        btnVolver.addActionListener(e -> {
            dispose();
            new MainMenuUI();
        });

        setVisible(true);
    }

    // Mostrar productos con imágenes
    private void mostrarProductos() {
        JPanel panelProductos = new JPanel();
        panelProductos.setBackground(new Color(35, 35, 35));
        panelProductos.setLayout(new GridLayout(0, 4, 15, 15)); // 4 columnas

        for (Producto p : productoService.listarProductos()) {
            JPanel card = new JPanel(new BorderLayout());
            card.setBackground(new Color(50, 50, 50));
            card.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

            // Imagen
            JLabel lblImagen;
            if (p.getImagenPath() != null && !p.getImagenPath().isEmpty()) {
                ImageIcon icon = new ImageIcon(p.getImagenPath());
                Image img = icon.getImage().getScaledInstance(180, 120, Image.SCALE_SMOOTH);
                lblImagen = new JLabel(new ImageIcon(img));
            } else {
                lblImagen = new JLabel("Sin imagen", SwingConstants.CENTER);
                lblImagen.setForeground(Color.LIGHT_GRAY);
            }

            // Texto
            JLabel lblNombre = new JLabel(p.getNombre(), SwingConstants.CENTER);
            lblNombre.setForeground(Color.WHITE);
            lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 14));

            JLabel lblPrecio = new JLabel(String.format("$ %.2f", p.getPrecio()), SwingConstants.CENTER);
            lblPrecio.setForeground(new Color(255, 204, 0));

            card.add(lblNombre, BorderLayout.NORTH);
            card.add(lblImagen, BorderLayout.CENTER);
            card.add(lblPrecio, BorderLayout.SOUTH);

            panelProductos.add(card);
        }

        JScrollPane scroll = new JScrollPane(panelProductos);
        scroll.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        getContentPane().removeAll();
        add(scroll, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    // Agregar producto con imagen
    private void agregarProducto() {
        try {
            String nombre = JOptionPane.showInputDialog("Nombre del producto:");
            if (nombre == null || nombre.isBlank())
                throw new DatoInvalidoException("El nombre no puede estar vacío.");

            String tipo = JOptionPane.showInputDialog("Tipo (Comida, Bebida, Postre):");
            if (tipo == null || tipo.isBlank())
                throw new DatoInvalidoException("Debe especificar un tipo de producto.");

            double precio = Double.parseDouble(JOptionPane.showInputDialog("Precio:"));
            int stock = Integer.parseInt(JOptionPane.showInputDialog("Stock inicial:"));

            // Seleccionar imagen (opcional)
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Seleccionar imagen del producto");
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Imágenes (.jpg, .png)", "jpg", "png"));

            String imagenRuta = null;
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                imagenRuta = fileChooser.getSelectedFile().getAbsolutePath();
            }

            int id = productoService.generarNuevoId();
            Producto nuevo;

            switch (tipo.toLowerCase()) {
                case "bebida" -> nuevo = new Bebida(id, nombre, precio, stock, true, imagenRuta);
                case "postre" -> nuevo = new Postre(id, nombre, precio, stock, true, imagenRuta);
                case "comida" -> nuevo = new Comida(id, nombre, precio, stock, true, imagenRuta);
                default -> throw new DatoInvalidoException("Tipo de producto no válido. Debe ser Comida, Bebida o Postre.");
            }

            productoService.agregarProducto(nuevo);

            JOptionPane.showMessageDialog(this, "Producto agregado correctamente.");
            mostrarProductos();

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Debe ingresar valores numéricos válidos para precio y stock.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (DatoInvalidoException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CartaUI::new);
    }
}




