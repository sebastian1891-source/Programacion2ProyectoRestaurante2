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
    private int contadorPedidos = 1;    // Generador local de número de pedido

    public PedidoUI() {
        setTitle("Pedidos y Pagos");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setMinimumSize(new Dimension(800, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Paleta de colores
        Color vinotinto = new Color(90, 0, 0);
        Color negro = new Color(20, 20, 20);
        Color grisClaro = new Color(230, 230, 230);
        Color grisOscuro = new Color(45, 45, 45);

        // Panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(negro);
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(30, 80, 30, 80));

        // Título superior
        JLabel titulo = new JLabel("Gestión de Pedidos y Pagos", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 34));
        titulo.setForeground(vinotinto);
        titulo.setBorder(BorderFactory.createEmptyBorder(30, 0, 50, 0));

        // Panel de contenido central
        JPanel panelCampos = new JPanel(new GridLayout(6, 2, 15, 20));
        panelCampos.setBackground(grisOscuro);
        panelCampos.setBorder(BorderFactory.createEmptyBorder(40, 100, 40, 100));

        JLabel lblMesa = crearEtiqueta("Número de Mesa:", grisClaro);
        JTextField txtMesa = crearCampoTexto();

        JLabel lblProducto = crearEtiqueta("Nombre del Producto:", grisClaro);
        JTextField txtProducto = crearCampoTexto();

        JLabel lblCantidad = crearEtiqueta("Cantidad:", grisClaro);
        JTextField txtCantidad = crearCampoTexto();

        // Botones estilizados
        JButton btnAgregar = crearBoton("Agregar al Pedido", vinotinto, grisClaro);
        JButton btnCerrar = crearBoton("Cerrar Pedido / Pagar", Color.DARK_GRAY, Color.WHITE);

        // Añadir componentes al panel de campos
        panelCampos.add(lblMesa);
        panelCampos.add(txtMesa);
        panelCampos.add(lblProducto);
        panelCampos.add(txtProducto);
        panelCampos.add(lblCantidad);
        panelCampos.add(txtCantidad);
        panelCampos.add(new JLabel()); // espacio visual
        panelCampos.add(new JLabel()); // espacio visual
        panelCampos.add(btnAgregar);
        panelCampos.add(btnCerrar);

        // Imagen decorativa
        JLabel imagenDecorativa = new JLabel();
        imagenDecorativa.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon icon = cargarImagen("src/ui/img/pedido.png", 150, 150);
        if (icon != null) imagenDecorativa.setIcon(icon);

        // Estructura general
        panelPrincipal.add(titulo, BorderLayout.NORTH);
        panelPrincipal.add(panelCampos, BorderLayout.CENTER);
        panelPrincipal.add(imagenDecorativa, BorderLayout.SOUTH);

        add(panelPrincipal);
        setVisible(true);

        // Eventos de botones (idénticos al original)
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

    // Métodos auxiliares visuales
    private JLabel crearEtiqueta(String texto, Color color) {
        JLabel lbl = new JLabel(texto);
        lbl.setForeground(color);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 18));
        return lbl;
    }

    private JTextField crearCampoTexto() {
        JTextField txt = new JTextField();
        txt.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txt.setBackground(new Color(240, 240, 240));
        txt.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 1),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        return txt;
    }

    private JButton crearBoton(String texto, Color fondo, Color textoColor) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btn.setBackground(fondo);
        btn.setForeground(textoColor);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        return btn;
    }

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

    // Generador local del número de pedido
    private int generarNumeroPedido() {
        return contadorPedidos++;
    }

    // Método main para pruebas
    public static void main(String[] args) {
        SwingUtilities.invokeLater(PedidoUI::new);
    }
}


