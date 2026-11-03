package com.hackathon.agenda.vista;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

public class VentanaPrincipal extends JFrame {
    private final JPanel contenido = new JPanel(new BorderLayout(0, 14));
    private final JPanel encabezado = new JPanel(new BorderLayout(0, 14));
    private final JPanel formulario = new JPanel(new GridLayout(1, 3, 14, 0));
    private final JPanel campoNombre = new JPanel(new BorderLayout(0, 6));
    private final JPanel campoApellido = new JPanel(new BorderLayout(0, 6));
    private final JPanel campoTelefono = new JPanel(new BorderLayout(0, 6));
    private final JPanel botones = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
    private final JPanel zonaSuperior = new JPanel(new BorderLayout(0, 14));
    private final JPanel pie = new JPanel(new BorderLayout(0, 14));
    private final JPanel capacidad = new JPanel(new BorderLayout());
    private final JPanel estado = new JPanel(new BorderLayout(0, 6));

    private final JLabel titulo = new JLabel("Agenda telefónica");
    private final JLabel etiquetaNombre = new JLabel("Nombre");
    private final JLabel etiquetaApellido = new JLabel("Apellido");
    private final JLabel etiquetaTelefono = new JLabel("Teléfono");
    private final JLabel capacidadMaxima = new JLabel("Capacidad máxima: 10");
    private final JLabel espaciosLibres = new JLabel("Espacios libres: 10", SwingConstants.RIGHT);
    private final JLabel mensajeEstado = new JLabel("Listo.");

    private final JTextField txtNombre = new JTextField();
    private final JTextField txtApellido = new JTextField();
    private final JTextField txtTelefono = new JTextField();

    private final JButton btnAgregar = new JButton("Añadir contacto");
    private final JButton btnBuscar = new JButton("Buscar");
    private final JButton btnEliminar = new JButton("Eliminar");
    private final JButton btnModificar = new JButton("Modificar teléfono");
    private final JButton btnLimpiar = new JButton("Limpiar");

    private final DefaultTableModel modeloTabla = new DefaultTableModel(
            new Object[]{"Nombre", "Apellido", "Teléfono"}, 0
    ) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private final JTable tabla = new JTable(modeloTabla);
    private final JScrollPane desplazamiento = new JScrollPane(tabla);

    public VentanaPrincipal() {
        super("Agenda telefónica — Vacía");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(760, 560));
        setSize(950, 660);
        setLocationRelativeTo(null);

        contenido.setBorder(BorderFactory.createEmptyBorder(18, 20, 18, 20));
        contenido.setBackground(new Color(238, 238, 238));
        setContentPane(contenido);

        encabezado.setOpaque(false);
        titulo.setFont(titulo.getFont().deriveFont(java.awt.Font.BOLD, 18f));
        encabezado.add(titulo, BorderLayout.NORTH);

        formulario.setOpaque(false);
        campoNombre.setOpaque(false);
        campoNombre.add(etiquetaNombre, BorderLayout.NORTH);
        campoNombre.add(txtNombre, BorderLayout.CENTER);
        campoApellido.setOpaque(false);
        campoApellido.add(etiquetaApellido, BorderLayout.NORTH);
        campoApellido.add(txtApellido, BorderLayout.CENTER);
        campoTelefono.setOpaque(false);
        campoTelefono.add(etiquetaTelefono, BorderLayout.NORTH);
        campoTelefono.add(txtTelefono, BorderLayout.CENTER);
        formulario.add(campoNombre);
        formulario.add(campoApellido);
        formulario.add(campoTelefono);

        botones.setOpaque(false);
        botones.add(btnAgregar);
        botones.add(btnBuscar);
        botones.add(btnEliminar);
        botones.add(btnModificar);
        botones.add(btnLimpiar);

        zonaSuperior.setOpaque(false);
        zonaSuperior.add(formulario, BorderLayout.NORTH);
        zonaSuperior.add(botones, BorderLayout.SOUTH);
        encabezado.add(zonaSuperior, BorderLayout.CENTER);
        contenido.add(encabezado, BorderLayout.NORTH);

        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.setRowHeight(26);
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.setFillsViewportHeight(true);
        desplazamiento.setBorder(BorderFactory.createLineBorder(new Color(160, 160, 160)));
        contenido.add(desplazamiento, BorderLayout.CENTER);

        pie.setOpaque(false);
        capacidad.setOpaque(false);
        capacidad.add(capacidadMaxima, BorderLayout.WEST);
        capacidad.add(espaciosLibres, BorderLayout.EAST);
        pie.add(capacidad, BorderLayout.NORTH);

        estado.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(160, 160, 160)),
                "Estado"
        ));
        estado.setBackground(contenido.getBackground());
        estado.add(mensajeEstado, BorderLayout.NORTH);
        pie.add(estado, BorderLayout.CENTER);
        contenido.add(pie, BorderLayout.SOUTH);
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtApellido() {
        return txtApellido;
    }

    public JTextField getTxtTelefono() {
        return txtTelefono;
    }

    public JButton getBtnAgregar() {
        return btnAgregar;
    }

    public JButton getBtnModificar() {
        return btnModificar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public JTable getTabla() {
        return tabla;
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public void mostrarExito(String mensaje) {
        mensajeEstado.setText(mensaje);
        mensajeEstado.setForeground(new Color(0, 128, 0));
    }

    public void mostrarError(String mensaje) {
        mensajeEstado.setText(mensaje);
        mensajeEstado.setForeground(Color.RED);
    }
}
