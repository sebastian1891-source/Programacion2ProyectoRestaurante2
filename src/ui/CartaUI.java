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

        Color fondo = new Color(20, 20, 20);
        Color rojoVinotinto = new Color(139, 0, 0);

        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBackground(fondo);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Gestión de Carta / Menú", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titulo.setForeground(rojoVinotinto);

        // Botones
        JButton btnAgregar = new JButton("Agregar Producto");
        JButton btnEditar = new JButton("Editar Producto");    // NUEVO
        JButton btnEliminar = new JButton("Eliminar Producto"); // NUEVO
        JButton btnListar = new JButton("Mostrar Carta");
        JButton btnVolver = new JButton("Volver al Menú");

        // Personalización
        for (JButton b : new JButton[]{btnAgregar, btnEditar, btnEliminar, btnListar, btnVolver}) {
            b.setBackground(rojoVinotinto);
            b.setForeground(Color.WHITE);
            b.setFont(new Font("Segoe UI", Font.BOLD, 16));
            b.setFocusPainted(false);
            b.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        }

        JPanel panelBotones = new JPanel();
        panelBotones.setBackground(fondo);
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);   // agregado
        panelBotones.add(btnEliminar); // agregado
        panelBotones.add(btnListar);
        panelBotones.add(btnVolver);

        panel.add(titulo, BorderLayout.NORTH);
        panel.add(panelBotones, BorderLayout.SOUTH);

        add(panel);

        // Acciones
        btnAgregar.addActionListener(e -> agregarProducto());
        btnEditar.addActionListener(e -> editarProducto());     // agregado
        btnEliminar.addActionListener(e -> eliminarProducto()); // agregado
        btnListar.addActionListener(e -> mostrarProductos());
        btnVolver.addActionListener(e -> {
            dispose();
            new MainMenuUI(); // vuelve al menú principal
        });

        setVisible(true);
    }

    private void mostrarProductos() {
        JPanel panelProductos = new JPanel();
        panelProductos.setBackground(new Color(35, 35, 35));
        panelProductos.setLayout(new GridLayout(0, 4, 15, 15));

        for (Producto p : productoService.listarProductos()) {
            JPanel card = new JPanel(new BorderLayout());
            card.setBackground(new Color(50, 50, 50));
            card.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

            JLabel lblImagen;
            if (p.getImagenPath() != null && !p.getImagenPath().isEmpty()) {
                ImageIcon icon = new ImageIcon(p.getImagenPath());
                Image img = icon.getImage().getScaledInstance(180, 120, Image.SCALE_SMOOTH);
                lblImagen = new JLabel(new ImageIcon(img));
            } else {
                lblImagen = new JLabel("Sin imagen", SwingConstants.CENTER);
                lblImagen.setForeground(Color.LIGHT_GRAY);
            }

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

    // Agregar producto
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
                default -> throw new DatoInvalidoException("Tipo no válido. Debe ser Comida, Bebida o Postre.");
            }

            productoService.agregarProducto(nuevo);
            JOptionPane.showMessageDialog(this, "Producto agregado correctamente.");
            mostrarProductos();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    // Editar producto
    private void editarProducto() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del producto a editar:");
        if (nombre == null || nombre.isBlank()) return;

        Producto producto = productoService.buscarPorNombre(nombre);
        if (producto == null) {
            JOptionPane.showMessageDialog(this, "Producto no encontrado.");
            return;
        }

        try {
            String nuevoPrecioStr = JOptionPane.showInputDialog("Nuevo precio (actual: " + producto.getPrecio() + "):");
            if (nuevoPrecioStr != null && !nuevoPrecioStr.isBlank()) {
                producto.setPrecio(Double.parseDouble(nuevoPrecioStr));
            }

            String nuevoStockStr = JOptionPane.showInputDialog("Nuevo stock (actual: " + producto.getStock() + "):");
            if (nuevoStockStr != null && !nuevoStockStr.isBlank()) {
                producto.setStock(Integer.parseInt(nuevoStockStr));
            }

            productoService.agregarProducto(producto); // guarda actualizado
            JOptionPane.showMessageDialog(this, "Producto actualizado correctamente.");
            mostrarProductos();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al editar: " + e.getMessage());
        }
    }

    // Eliminar producto
    private void eliminarProducto() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del producto a eliminar:");
        if (nombre == null || nombre.isBlank()) return;

        boolean eliminado = productoService.eliminarPorNombre(nombre);
        if (eliminado) {
            JOptionPane.showMessageDialog(this, "Producto eliminado correctamente.");
            mostrarProductos();
        } else {
            JOptionPane.showMessageDialog(this, "Producto no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CartaUI::new);
    }
}