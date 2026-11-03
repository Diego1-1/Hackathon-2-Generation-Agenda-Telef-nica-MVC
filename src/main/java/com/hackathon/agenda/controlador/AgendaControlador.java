package com.hackathon.agenda.controlador;



import com.hackathon.agenda.modelo.Agenda;// importamos el modeolo, osea guardar buscar y borrar
import com.hackathon.agenda.modelo.Contacto; // importamos la entidad e contacto nombres apellidos tel etc
import com.hackathon.agenda.vista.VentanaPrincipal; // importamos la vista  la ventana de los campos botones  etc
import javax.swing.table.DefaultTableModel; // con defaulmodel ees el modelo de datos como la filas y colomnas
import java.util.List;// importamos lista para usaar listas

public class AgendaControlador {
    private final Agenda agenda;//referencia final no se puede reasignar despues de crearse
    private final VentanaPrincipal vista;


    // constructor
    public AgendaControlador(Agenda agenda, VentanaPrincipal vista){// el main para el modelo y la vista ya creados por mis compñaeros
        this.agenda = agenda;// guardamos el modelo que se recibe en el atributo de la clase
        this.vista = vista ;
        conectarEventos();// se le asigana cada boton lo que debe hacer cuando lo precionen
        refrescarTabla();//se llena la tabal al incial con los contacto que ya existen

    }


    // vamos a conectar los listeners
    // se van a conectar los eventos de la lista con las acciones del controlador que estblecimos arrib

    private void conectarEventos(){
        vista.getBtnAgregar().addActionListener(e ->agregar());// cuando haga clic en agregar se ejecura el agregar e es una exprecion lambda
        vista.getBtnModificar().addActionListener(e -> modificarTelefono()); // lo mismo cuando precione modificar pues ejecuta modificar
        vista.getBtnEliminar().addActionListener(e -> eliminar()); //lo mismo con eliminar
        vista.getBtnBuscar().addActionListener(e -> buscar());// igualmente
        vista.getBtnLimpiar().addActionListener(e -> limpiarFormulario());// este vacia el formulario

        // vamos con la sincronizacion cuando el litener se activa cada vez que cambia la fila seleccionada
        vista.getTabla().getSelectionModel().addListSelectionListener(e -> {
           if(!e.getValueIsAdjusting()){//el adjusting es un true mientras la selecciion aun este cambiando
               // esperamos q eu finalice par no ejecutar el codigo de nuevo y quede repetido
               cargarFilaSeleccionada();//copianos losdarow de la fila a los campos de texto
           }
        });
    }


    // acciones

    // boton agregar
    private void agregar(){
        try{// con try intrntamos hacer la operacion, si algo falla saltamos al cathc
           //creamos un contacto con lo que el usuario escribio en llos 3 campos
           // el contructor de contacto valida nombre vacios etc y lanza la exepcion de que algo esta mal
           Contacto c = new Contacto(
                   vista.getTxtNombre().getText(),
                   vista.getTxtApellido().getText(),
                   vista.getTxtTelefono().getText());

           agenda.añadirContacto(c);// pedimos a la agenda que lo guarde y valida si hay duplicados y espacios
           refrescarTabla();//este puento es si no hubo un error y se actualiza la tabla ára mostrarnos el nuevo contacto
           limpiarFormulario();// se vacia el formulario para el siguiente registr
           vista.mostrarExito("contacto agregado espacios libres "+ agenda.espaciosLibres());






        } catch (RuntimeException ex){// atrapa cualquier eror de validacion de la agenda
            vista.mostrarError(mensaje(ex));// mensaje dojo en el panel de estado con el motivo del error

        }
    }

    // modificar
    private void modificarTelefono() {
        try {
          agenda.modificarTelefono(// la agenda busca el contacto por nombre y apellido
                vista.getTxtNombre().getText(),
                vista.getTxtApellido().getText(),
                vista.getTxtTelefono().getText()
          );
          refrescarTabla();//actualizamos la tabla para que s e vea el telefono nuevo
          vista.mostrarExito("telefono actualizado correctamente"); //mensaje verde de exito
        } catch (RuntimeException ex ){
            vista.mostrarError(mensaje(ex));//mensaje rojo si el contacto no existe o el telefono es errado
        }
    }

    //eliminar
    private void eliminar(){
        int fila = vista.getTabla().getSelectedRow();//el getselectedrow devuelve el indice de la fila seleccionada
        if (fila <0){// condicional si no hay fila seleccionada
            vista.mostrarError("seleccione un contacto de la tabla para eliminarlo");//most5ramos el error
            return;//salimos del metodo

        }

        try {
            DefaultTableModel m = vista.getModeloTabla();// definimos el modelo de la tabla para leer los vallores de la fila
            // se arma el contadod desde la fila para no depender de que el telefono escrito en el campo sea valido
            // se llena segun las pociciones 012
           Contacto c =new Contacto( m.getValueAt(fila, 0).toString(),
                    m.getValueAt(fila, 1).toString(),
                    m.getValueAt(fila, 2).toString());

            agenda.eliminarContacto(c);// la agenda lo elimina comparando por nombres y apellidos
            refrescarTabla();//actualizamos la tabla y el nombre no deberia aparecer
            limpiarFormulario();//vaciamos el formulario
            vista.mostrarExito("contacto eliminado");




        }catch (RuntimeException ex){
            vista.mostrarError(mensaje(ex));
        }
    }

    //buscar
    private void buscar() {
        try {
            String telefono = agenda.buscarContacto(
                    vista.getTxtnombre().getText(),
                    vista.getTxtApellido().getText());

            if(telefono == null) {//condicion si devuielve null
                vista.mostrarError("no se encontro ese contacto");// mensaje rooj
                return;// salimos
            }
            vista.getTxtTelefono().setText(telefono);//si existe mostramos su numero
            vista.mostrarExito("Contacto encontrado. Teléfono: " + telefono);//


        }catch (RuntimeException ex) {
            vista.mostrarError(mensaje(ex));// mensaje rojo
        }

    }

    // utilidades
    // se vuelve a hacer la tabla con los contactos actuales de la agenda
    private void refrescarTabla() {
        DefaultTableModel m = vista.getModeloTabla();// obtenemos el modelo de los datos
        m.setRowCount(0);//borramos la filas actuales y no dejamos filas
        List<Contacto> lista = agenda.listarContactos();// le pedimos a la agenda la lista de contactos
        for (Contacto c : lista) {// recorremos cada contacto de la lista
            m.addRow(new Object[]{c.getNombre(), c.getApellido(), c.getTelefono()});// agregamos a una fila nueva los datos

        }
    }

    // copa de los datows de la fila que elegimos
    private void cargarFilaSeleccionada() {
        int fila = vista.getTabla().getSelectedRow();//
        if (fila < 0) {// si no hay seleccion no hace nada
            return;
        }
        DefaultTableModel m = vista.getModeloTabla();// estos son el modelo de datos
        vista.getTxtNombre().setText(m.getValueAt(fila, 0).toString());// de la columna 0 campo nombre
        vista.getTxtApellido().setText(m.getValueAt(fila, 1).toString());// columna 1 apellido
        vista.getTxtTelefono().setText(m.getValueAt(fila, 2).toString());// 2 pues el tlefono

    }

    private void limpiarFormulario(){// vaciar los 3 campos de l formulario
        vista.getTxtNombre().setText("");// borramos el campo del nomrbe
        vista.getTxtApellido().setText("");// del apellido
        vista.getTxtTelefono().setText("");// telefono
        vista.getTabla().clearSelection();// quitamos el resaltado de la fila

    }

    private String mensaje(RuntimeException ex){
        String msg = ex.getMessage();// texto del error que escribio quien lazo la exepcion
        return (msg == null || msg.isBlank()) ? "ocurrio un error inesperado": msg; // si no hay texto usamos el mensage genrico su hay pue susamos el original
    }




}





