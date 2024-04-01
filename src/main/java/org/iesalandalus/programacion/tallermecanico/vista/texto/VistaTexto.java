package org.iesalandalus.programacion.tallermecanico.vista.texto;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.GestorEventos;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class VistaTexto implements Vista {
    private final GestorEventos gestorEventos = new GestorEventos();

    @Override
    public GestorEventos getGestorEventos() {
        return gestorEventos;
    }

    @Override
    public void comenzar() throws OperationNotSupportedException {
        Consola.mostrarMenu();
        ejecutar(Consola.elegirOpcion());

    }
    @Override
    public void terminar() {
        System.out.println("Cerrando Vista. Que tenga un buen día.");
    }

    @Override
    public Cliente leerCliente() {
        String leerClienteNombre;
        System.out.print("Introduzca el nombre del cliente aquí: ");
        leerClienteNombre = Entrada.cadena();

        String leerClienteDNI;
        System.out.print("Introduzca el dni del cliente aquí: ");
        leerClienteDNI = Entrada.cadena();

        String leerClienteTelefono;
        System.out.print("Introduzca el teléfono del cliente aquí: ");
        leerClienteTelefono = Entrada.cadena();

        return new Cliente(leerClienteNombre, leerClienteDNI, leerClienteTelefono);
    }

    @Override
    public Cliente leerClienteDni() {
        String dni;
        System.out.print("Introduce el dni del cliente aquí: ");
        dni = Entrada.cadena();

        return Cliente.get(dni);
    }

    @Override
    public String leerNuevoNombre() {
        String nuevoNombre;
        System.out.print("Introduce el nuevo nombre aquí: ");
        nuevoNombre = Entrada.cadena();

        return nuevoNombre;
    }

    @Override
    public String leerNuevoTelefono() {
        String nuevoTelefono;
        System.out.print("Introduce el nuevo teléfono aquí: ");
        nuevoTelefono = Entrada.cadena();

        return nuevoTelefono;
    }

    @Override
    public Vehiculo leerVehiculo() {
        String leerMarca;
        String leerMatricula;
        String leerModelo;

        System.out.print("Introduce la marca del vehículo: ");
        leerMarca = Entrada.cadena();

        System.out.print("Introduce la matrícula del vehículo: ");
        leerMatricula = Entrada.cadena();

        System.out.print("Introduce el modelo del vehículo: ");
        leerModelo = Entrada.cadena();

        return new Vehiculo(leerMarca, leerModelo, leerMatricula);
    }

    @Override
    public Vehiculo leerVehiculoMatricula() {
        String leerMatricula;
        System.out.print("Introduce la matrícula del vehículo aquí: ");
        leerMatricula = Entrada.cadena();

        return Vehiculo.get(leerMatricula);
    }

    @Override
    public Trabajo leerRevision() {
        String leerFecha;
        System.out.print("Introduce la fecha de inicio de la revisión (Recuerde: el formato es dd/MM/yyyy): ");
        leerFecha = Entrada.cadena();

        return new Revision(leerCliente(), leerVehiculo(), LocalDate.parse(leerFecha));
    }

    @Override
    public Trabajo leerMecanico() {
        String leerFecha;
        System.out.print("Introduce la fecha de inicio de la revisión (Recuerde: el formato es dd/MM/yyyy): ");
        leerFecha = Entrada.cadena();

        return new Mecanico(leerCliente(), leerVehiculo(), LocalDate.parse(leerFecha));
    }

    @Override
    public int leerHoras() {
        int horas;
        System.out.print("Introduce el número de horas de la revisión aquí: ");
        horas = Entrada.entero();
        return horas;
    }

    @Override
    public float leerPrecioMaterial() {
        float precioMaterial;
        System.out.print("Introduce el precio del material aquí: ");
        precioMaterial = Entrada.real();

        return precioMaterial;
    }

    @Override
    public LocalDate leerFechaCierre() {
        String fechaCierre;
        System.out.print("Introduce la fecha de cierre de la revisión aquí: ");
        fechaCierre = Entrada.cadena();

        return LocalDate.parse(fechaCierre);
    }

    private void ejecutar(Evento evento) throws OperationNotSupportedException {
        switch (evento) {
            case INSERTAR_CLIENTE -> notificarResultado(evento, "Insertando Cliente", true);
            case INSERTAR_VEHICULO -> notificarResultado(evento, "Insertando Vehículo", true);
            case INSERTAR_REVISION -> notificarResultado(evento, "Insertando Revisión", true);
            case BUSCAR_CLIENTE -> notificarResultado(evento, "Buscando Cliente", true);
            case BUSCAR_VEHICULO -> notificarResultado(evento, "Buscando Vehículo", true);
            case BUSCAR_TRABAJO -> notificarResultado(evento, "Buscando Trabajo", true);
            case MODIFICAR_CLIENTE -> notificarResultado(evento, "Modificando Cliente", true);
            case ANADIR_HORAS_TRABAJO -> notificarResultado(evento, "Añadiendo Horas al Trabajo", true);
            case ANADIR_PRECIO_MATERIAL_TRABAJO -> notificarResultado(evento, "Añadiendo Precio de Material al Trabajo", true);
            case INSERTAR_MECANICO -> notificarResultado(evento, "Insertando Trabajo Mecánico", true);
            case CERRAR_TRABAJO -> notificarResultado(evento, "Cerrando Trabajo", true);
            case BORRAR_CLIENTE -> notificarResultado(evento, "Borrando Cliente", true);
            case BORRAR_TRABAJO -> notificarResultado(evento, "Borrando Trabajo", true);
            case BORRAR_VEHICULO -> notificarResultado(evento, "Borrando Vehículo", true);
            case LISTAR_CLIENTES -> notificarResultado(evento, "Listando Clientes", true);
            case LISTAR_VEHICULOS -> notificarResultado(evento, "Listando Vehículos", true);
            case LISTAR_TRABAJOS -> notificarResultado(evento, "Listando Trabajos", true);
            case LISTAR_TRABAJOS_CLIENTE -> notificarResultado(evento, "Listando Trabajos del Cliente", true);
            case LISTAR_TRABAJOS_VEHICULO -> notificarResultado(evento, "Listando Trabajos del Vehículo", true);
            case SALIR -> notificarResultado(evento, "Saliendo", true);
            default -> System.out.print("Error, tiene que escoger de entre las opciones válidas.");
        }
    }

    @Override
    public void notificarResultado(Evento evento, String texto, boolean exito) throws OperationNotSupportedException {
        Objects.requireNonNull(evento, "El evento no puede ser nulo.");
        Objects.requireNonNull(texto, "El texto no puede ser nulo.");
        gestorEventos.notificar(evento);
        if (exito) {
            System.out.printf("%s%n", texto);
        } else {
            System.out.printf("ERROR: %s%n", texto);
        }
    }
    @Override
    public void mostrarCliente(Cliente cliente){
        System.out.printf("%s%n", cliente);
    }
    @Override
    public void mostrarVehiculo(Vehiculo vehiculo) {
        System.out.printf("%s%n", vehiculo);
    }
    @Override
    public void mostrarTrabajo(Trabajo trabajo) {
        System.out.printf("%s%n", trabajo);
    }
    @Override
    public void mostrarClientes(List<Cliente> clientes) {
        System.out.printf("%s%n", clientes);
    }
    @Override
    public void mostrarVehiculos(List<Vehiculo> vehiculos) {
        System.out.printf("%s%n", vehiculos);
    }
    @Override
    public void mostrarTrabajos(List<Trabajo> trabajos) {
        System.out.printf("%s%n", trabajos);
    }
}
