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

    private ProductoService productoService = new ProductoService();
    private JTextArea areaProductos;

    public CartaUI() {
        setTitle("Gestión de Carta / Menú");
        setSize(500, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());
        areaProductos = new JTextArea();
        JScrollPane scroll = new JScrollPane(areaProductos);

        JButton btnAgregar = new JButton("Agregar Producto");
        JButton btnListar = new JButton("Mostrar Carta");

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAgregar);
        panelBotones.add(btnListar);

        panel.add(scroll, BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.SOUTH);
        add(panel);

        btnAgregar.addActionListener(e -> agregarProducto());
        btnListar.addActionListener(e -> mostrarProductos());

        mostrarProductos();
        setVisible(true);
    }

    private void mostrarProductos() {
        areaProductos.setText("");
        for (Producto p : productoService.listarProductos()) {
            areaProductos.append(p + "\n");
        }
    }

    private void agregarProducto() {
        String nombre = JOptionPane.showInputDialog("Nombre del producto:");
        String tipo = JOptionPane.showInputDialog("Tipo (Comida, Bebida, Postre):");
        double precio = Double.parseDouble(JOptionPane.showInputDialog("Precio:"));
        int stock = Integer.parseInt(JOptionPane.showInputDialog("Stock inicial:"));

        Producto nuevo;
        switch (tipo.toLowerCase()) {
            case "bebida" -> nuevo = new Bebida(nombre, precio, stock);
            case "postre" -> nuevo = new Postre(nombre, precio, stock);
            default -> nuevo = new Comida(nombre, precio, stock);
        }

        try {
            productoService.agregarProducto(nuevo);
            JOptionPane.showMessageDialog(this, "Producto agregado correctamente.");
            mostrarProductos();
        } catch (DatoInvalidoException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
