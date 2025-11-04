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
    private final JTextArea areaProductos;

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
        try {
            String nombre = JOptionPane.showInputDialog("Nombre del producto:");
            if (nombre == null || nombre.isBlank()) throw new DatoInvalidoException("El nombre no puede estar vacío.");

            String tipo = JOptionPane.showInputDialog("Tipo (Comida, Bebida, Postre):");
            if (tipo == null || tipo.isBlank()) throw new DatoInvalidoException("Debe especificar un tipo de producto.");

            double precio = Double.parseDouble(JOptionPane.showInputDialog("Precio:"));
            int stock = Integer.parseInt(JOptionPane.showInputDialog("Stock inicial:"));

            int id = productoService.generarNuevoId(); // suponiendo que tu servicio tiene este método
            Producto nuevo;

            switch (tipo.toLowerCase()) {
                case "bebida" -> {
                    int resp = JOptionPane.showConfirmDialog(this, "¿La bebida contiene alcohol?", "Tipo de Bebida", JOptionPane.YES_NO_OPTION);
                    boolean alcoholica = (resp == JOptionPane.YES_OPTION);
                    nuevo = new Bebida(id, nombre, precio, stock, alcoholica);
                }
                case "postre" -> {
                    int resp = JOptionPane.showConfirmDialog(this, "¿El postre es apto para celíacos?", "Tipo de Postre", JOptionPane.YES_NO_OPTION);
                    boolean paraCeliacos = (resp == JOptionPane.YES_OPTION);
                    nuevo = new Postre(id, nombre, precio, stock, paraCeliacos);
                }
                case "comida" -> {
                    int resp = JOptionPane.showConfirmDialog(this, "¿La comida es vegana?", "Tipo de Comida", JOptionPane.YES_NO_OPTION);
                    boolean vegana = (resp == JOptionPane.YES_OPTION);
                    nuevo = new Comida(id, nombre, precio, stock, vegana);
                }
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
}

