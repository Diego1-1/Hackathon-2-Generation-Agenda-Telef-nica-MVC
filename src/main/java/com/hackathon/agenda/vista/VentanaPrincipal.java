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
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

public class VentanaPrincipal extends JFrame {
    public VentanaPrincipal() {
        super("Agenda telefónica — Vacía");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(760, 560));
        setSize(950, 660);
        setLocationRelativeTo(null);

        JPanel contenido = new JPanel(new BorderLayout(0, 14));
        contenido.setBorder(BorderFactory.createEmptyBorder(18, 20, 18, 20));
        contenido.setBackground(new java.awt.Color(238, 238, 238));
        setContentPane(contenido);

        JPanel encabezado = new JPanel(new BorderLayout(0, 14));
        encabezado.setOpaque(false);

        JLabel titulo = new JLabel("Agenda telefónica");
        titulo.setFont(titulo.getFont().deriveFont(java.awt.Font.BOLD, 18f));
        encabezado.add(titulo, BorderLayout.NORTH);

        JPanel formulario = new JPanel(new GridLayout(1, 3, 14, 0));
        formulario.setOpaque(false);
        formulario.add(crearCampo("Nombre"));
        formulario.add(crearCampo("Apellido"));
        formulario.add(crearCampo("Teléfono"));

        JPanel botones = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        botones.setOpaque(false);
        botones.add(new JButton("Añadir contacto"));
        botones.add(new JButton("Buscar"));
        botones.add(new JButton("Eliminar"));
        botones.add(new JButton("Modificar teléfono"));

        JPanel zonaSuperior = new JPanel(new BorderLayout(0, 14));
        zonaSuperior.setOpaque(false);
        zonaSuperior.add(formulario, BorderLayout.NORTH);
        zonaSuperior.add(botones, BorderLayout.SOUTH);
        encabezado.add(zonaSuperior, BorderLayout.CENTER);
        contenido.add(encabezado, BorderLayout.NORTH);

        DefaultTableModel modeloTabla = new DefaultTableModel(
                new Object[]{"Nombre", "Apellido", "Teléfono"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable tablaContactos = new JTable(modeloTabla);
        tablaContactos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaContactos.setRowHeight(26);
        tablaContactos.getTableHeader().setReorderingAllowed(false);
        tablaContactos.setFillsViewportHeight(true);

        JScrollPane desplazamiento = new JScrollPane(tablaContactos);
        desplazamiento.setBorder(BorderFactory.createLineBorder(new java.awt.Color(160, 160, 160)));
        contenido.add(desplazamiento, BorderLayout.CENTER);

        JPanel pie = new JPanel(new BorderLayout(0, 14));
        pie.setOpaque(false);

        JPanel capacidad = new JPanel(new BorderLayout());
        capacidad.setOpaque(false);
        capacidad.add(new JLabel("Capacidad máxima: 10"), BorderLayout.WEST);
        JLabel espaciosLibres = new JLabel("Espacios libres: 10", SwingConstants.RIGHT);
        capacidad.add(espaciosLibres, BorderLayout.EAST);
        pie.add(capacidad, BorderLayout.NORTH);

        JPanel estado = new JPanel(new BorderLayout(0, 6));
        estado.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new java.awt.Color(160, 160, 160)),
                "Estado"
        ));
        estado.setBackground(contenido.getBackground());
        estado.add(new JLabel("Listo."), BorderLayout.NORTH);
        pie.add(estado, BorderLayout.CENTER);
        contenido.add(pie, BorderLayout.SOUTH);
    }

    private JPanel crearCampo(String etiqueta) {
        JPanel campo = new JPanel(new BorderLayout(0, 6));
        campo.setOpaque(false);
        campo.add(new JLabel(etiqueta), BorderLayout.NORTH);
        campo.add(new JTextField(), BorderLayout.CENTER);
        return campo;
    }
}
