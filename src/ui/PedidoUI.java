package ui;

import exception.StockInsuficienteException;
import model.Mesa;
import model.Pedido;
import model.Producto;
import service.MesaService;
import service.PedidoService;
import service.ProductoService;

import javax.swing.*;
import java.awt.*;

public class PedidoUI extends JFrame {

    private final MesaService mesaService = new MesaService();
    private final ProductoService productoService = new ProductoService();
    private final PedidoService pedidoService = new PedidoService();

    private Pedido pedidoActual = null; // Se crea cuando se indica la mesa
    private int contadorPedidos = 1;    // 🔹 Generador local de número de pedido

    public PedidoUI() {
        setTitle("Pedidos y Pagos");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 🔹 Diseño del panel principal
        JPanel panel = new JPanel(new GridLayout(6, 1, 10, 10));

        JLabel lblMesa = new JLabel("Número de Mesa:");
        JTextField txtMesa = new JTextField();

        JLabel lblProducto = new JLabel("Nombre del Producto:");
        JTextField txtProducto = new JTextField();

        JLabel lblCantidad = new JLabel("Cantidad:");
        JTextField txtCantidad = new JTextField();

        JButton btnAgregar = new JButton("Agregar al Pedido");
        JButton btnCerrar = new JButton("Cerrar Pedido / Pagar");

        panel.add(lblMesa);
        panel.add(txtMesa);
        panel.add(lblProducto);
        panel.add(txtProducto);
        panel.add(lblCantidad);
        panel.add(txtCantidad);
        panel.add(btnAgregar);
        panel.add(btnCerrar);

        add(panel);
        setVisible(true);

        // 🔹 Evento botón "Agregar al Pedido"
        btnAgregar.addActionListener(e -> {
            try {
                // Si aún no existe el pedido, lo creamos con la mesa ingresada
                if (pedidoActual == null) {
                    int numeroMesa = Integer.parseInt(txtMesa.getText());
                    Mesa mesa = mesaService.obtenerMesa(numeroMesa);

                    if (mesa == null) {
                        JOptionPane.showMessageDialog(this, "La mesa no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // 🔹 Generamos número de pedido localmente
                    int numeroPedido = generarNumeroPedido();
                    pedidoActual = new Pedido(numeroPedido, mesa);

                    JOptionPane.showMessageDialog(this,
                            "Pedido #" + numeroPedido + " creado para la mesa " + numeroMesa + ".");
                }

                // Agregar producto al pedido
                String nombreProd = txtProducto.getText();
                int cantidad = Integer.parseInt(txtCantidad.getText());
                Producto producto = productoService.buscarProductoPorNombre(nombreProd);

                if (producto == null) {
                    JOptionPane.showMessageDialog(this, "Producto no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                pedidoService.agregarProductoAlPedido(pedidoActual, producto, cantidad);
                JOptionPane.showMessageDialog(this, "Producto agregado al pedido.");

            } catch (StockInsuficienteException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error de Stock", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ingrese valores numéricos válidos.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Ocurrió un error al agregar el producto: " + ex.getMessage());
            }
        });

        // 🔹 Evento botón "Cerrar Pedido / Pagar"
        btnCerrar.addActionListener(e -> {
            if (pedidoActual == null) {
                JOptionPane.showMessageDialog(this, "No hay un pedido activo.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double total = pedidoService.calcularTotal(pedidoActual);
            JOptionPane.showMessageDialog(this, "Total a pagar: $" + total);

            pedidoService.cerrarPedido(pedidoActual);
            pedidoActual = null; // Reiniciamos el pedido
        });
    }

    // 🔹 Generador local del número de pedido
    private int generarNumeroPedido() {
        return contadorPedidos++;
    }

    // Método main para probar directamente esta ventana
    public static void main(String[] args) {
        SwingUtilities.invokeLater(PedidoUI::new);
    }
}

