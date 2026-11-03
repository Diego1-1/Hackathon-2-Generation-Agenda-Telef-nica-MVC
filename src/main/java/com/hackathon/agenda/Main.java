package com.hackathon.agenda;

import com.hackathon.agenda.controlador.AgendaControlador;
import com.hackathon.agenda.modelo.Agenda;
import com.hackathon.agenda.vista.VentanaPrincipal;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Agenda modelo = new Agenda();                 // tamaño por defecto: 10 contactos
            VentanaPrincipal vista = new VentanaPrincipal();
            new AgendaControlador(modelo, vista);         // asigna los ActionListeners

            vista.setVisible(true);
        });
    }
}