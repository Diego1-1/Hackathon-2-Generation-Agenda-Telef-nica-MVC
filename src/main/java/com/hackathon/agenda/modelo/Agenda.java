package com.hackathon.agenda.modelo;

import java.util.*;

public class Agenda {

    private List<Contacto> contactos;
    private int capacidadMaxima;

    public Agenda() {
        this.capacidadMaxima = 10;
        this.contactos = new Arraylist<>();
    }

    public Agenda(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
        this.contactos = new ArrayList<>();
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public List<Contacto> getContactos() {
        return contactos;
    }

    public boolean AgendaLlena() {
        if (contactos.size() >= capacidadMaxima) {
            return true;
        } else {
            return false;
        }
    }

    public espaciosLibres() {
        this.capacidadMaxima - return this.contactos.size();
    }

    public boolean existeContacto(Contacto c) {
        return this.contactos.contains(c);
    }

    public void añadirContacto(Contacto c) {
        if (c == null || AgendaLlena() || existeContacto(c)) {
            System.out.println("No es posible añadir, contacto nulo, Agenda llena o contacto ya existe");
        } else {
            this.contactos.add(c);
            System.out.println("Contacto Añadido");
        }
    }

    public List<Contacto> listarContactos() {

        List<Contacto> listaOrdenada = new ArrayList<>(this.contactos);

        listaOrdenada.sort(Comparator.comparing(Contacto::getNombre, String.CASE_INSENSITIVE_ORDER));

        return listaOrdenada;
    }

    public String buscarContacto(String nombre, String apellido) {
        for (Contacto c : this.contactos) {
            if (c.getNombre().equalsIgnoreCase(nombre) && c.getApellido().equalsIgnoreCase(apellido)) {
                return c.getTelefono();
            }
        }
        return null;
    }

    public void eliminarContacto(Contacto c) {
        if (existeContacto(c)) {
            this.contactos.remove(c);
            System.out.println("Contacto Eliminado.");
        } else {
            System.out.println("Contacto no existe.");
        }
    }

    public String modificarTelefono(String nombre, String apellido, String nuevoTelefono) {
        String regex = "^\\+?[0-9\\s]+$";

        if (nuevoTelefono != null && nuevoTelefono.matches(regex)) {
            for (Contacto c : this.contactos) {
                if (c.getNombre().equalsIgnoreCase(nombre) && c.getApellido().equalsIgnoreCase(apellido)) {
                    c.setTelefono(nuevoTelefono); // Actualizamos el teléfono
                    return "Teléfono actualizado con éxito.";
                }
            }
            return "Contacto no encontrado.";
        } else {
            return "Formato de teléfono inválido.";
        }
    }
}