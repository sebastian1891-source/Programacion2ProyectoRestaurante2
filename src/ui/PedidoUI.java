package ui;

import exception.StockInsuficienteException;
import model.Pedido;
import model.Producto;
import service.MesaService;
import service.PedidoService;
import service.ProductoService;

import javax.swing.*;
import java.awt.*;

public class PedidoUI extends JFrame {

    private MesaService mesaService = new MesaService();
    private ProductoService productoService = new ProductoService();
    private PedidoService pedidoService = new PedidoService();

    public PedidoUI() {
        setTitle("Pedidos y Pagos");
        setSize(500, 400);
        setLocationRelativeTo(null);

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

        Pedido pedidoActual = new Pedido();

        btnAgregar.addActionListener(e -> {
            try {
                String prod = txtProducto.getText();
                int cant = Integer.parseInt(txtCantidad.getText());
                Producto p = productoService.buscarProductoPorNombre(prod);
                pedidoService.agregarProductoAlPedido(pedidoActual, p, cant);
                JOptionPane.showMessageDialog(this, "Producto agregado al pedido.");
            } catch (StockInsuficienteException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Datos inválidos.");
            }
        });

        btnCerrar.addActionListener(e -> {
            double total = pedidoService.calcularTotal(pedidoActual);
            JOptionPane.showMessageDialog(this, "Total a pagar: $" + total);
            pedidoService.cerrarPedido(pedidoActual);
        });
    }
}
