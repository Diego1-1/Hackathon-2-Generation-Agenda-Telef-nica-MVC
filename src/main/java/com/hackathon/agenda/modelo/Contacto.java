package com.hackathon.agenda.modelo;
import java.util.Objects;

public class Contacto {

    private final String nombre;
    private final String apellido;
    private String telefono;

    private static final String REGEX_TELEFONO = "^(\\+\\d{1,3}\\s?\\d{9,10}|\\d{9,10})$";

    public Contacto(String nombre, String apellido, String telefono) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException( "El nombre no puede estar vacío.");

        }
        if (apellido == null || apellido.isBlank()){
            throw new IllegalArgumentException("El apellido no puede estar vacío");

        }
        validarTelefono(telefono);
        this.nombre = nombre.trim();
        this.apellido = apellido.trim();
        this.telefono = telefono.trim();
    }
    private static void validarTelefono(String telefono){
        if(telefono == null || !telefono.trim().matches(REGEX_TELEFONO)){
            throw new IllegalArgumentException("El formato del teléfono no es válido.");
        }
    }

    public String getNombre(){
        return nombre;
    }

    public String getApellido(){
        return apellido;
    }

    public String getTelefono(){
        return telefono;
    }

    public void setTelefono(String telefono){
        validarTelefono(telefono);
        this.telefono = telefono.trim();
    }

    @Override
    public boolean equals(Object objeto) {
        if(this == objeto){
            return true;
        }

        if(!(objeto instanceof Contacto otro)) {
            return false;
        }

        return nombre.equalsIgnoreCase(otro.nombre) && apellido.equalsIgnoreCase(otro.apellido);

    }

    @Override
    public int hashCode(){
        return Objects.hash(
                nombre.toLowerCase(java.util.Locale.ROOT), apellido.toLowerCase(java.util.Locale.ROOT));

    }
    @Override
    public String toString(){
        return nombre + " " + apellido + " - " + telefono;
    }

}
