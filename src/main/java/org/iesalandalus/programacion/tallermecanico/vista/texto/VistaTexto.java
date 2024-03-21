package org.iesalandalus.programacion.tallermecanico.vista.texto;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.GestorEventos;

import javax.naming.OperationNotSupportedException;
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

    private void ejecutar(Evento evento) throws OperationNotSupportedException {
        switch (evento) {
            case INSERTAR_CLIENTE -> gestorEventos.notificar(Evento.INSERTAR_CLIENTE);
            case INSERTAR_VEHICULO -> gestorEventos.notificar(Evento.INSERTAR_VEHICULO);
            case INSERTAR_REVISION -> gestorEventos.notificar(Evento.INSERTAR_REVISION);
            case BUSCAR_CLIENTE -> gestorEventos.notificar(Evento.BUSCAR_CLIENTE);
            case BUSCAR_VEHICULO -> gestorEventos.notificar(Evento.BUSCAR_VEHICULO);
            case BUSCAR_TRABAJO -> gestorEventos.notificar(Evento.BUSCAR_TRABAJO);
            case MODIFICAR_CLIENTE -> gestorEventos.notificar(Evento.MODIFICAR_CLIENTE);
            case ANADIR_HORAS_TRABAJO -> gestorEventos.notificar(Evento.ANADIR_HORAS_TRABAJO);
            case ANADIR_PRECIO_MATERIAL_TRABAJO -> gestorEventos.notificar(Evento.ANADIR_PRECIO_MATERIAL_TRABAJO);
            case INSERTAR_MECANICO -> gestorEventos.notificar(Evento.INSERTAR_MECANICO);
            case CERRAR_TRABAJO -> gestorEventos.notificar(Evento.CERRAR_TRABAJO);
            case BORRAR_CLIENTE -> gestorEventos.notificar(Evento.BORRAR_CLIENTE);
            case BORRAR_TRABAJO -> gestorEventos.notificar(Evento.BORRAR_TRABAJO);
            case BORRAR_VEHICULO -> gestorEventos.notificar(Evento.BORRAR_VEHICULO);
            case LISTAR_CLIENTES -> gestorEventos.notificar(Evento.LISTAR_CLIENTES);
            case LISTAR_VEHICULOS -> gestorEventos.notificar(Evento.LISTAR_VEHICULOS);
            case LISTAR_TRABAJOS -> gestorEventos.notificar(Evento.LISTAR_TRABAJOS);
            case LISTAR_TRABAJOS_CLIENTE -> gestorEventos.notificar(Evento.LISTAR_TRABAJOS_CLIENTE);
            case LISTAR_TRABAJOS_VEHICULO -> gestorEventos.notificar(Evento.LISTAR_TRABAJOS_VEHICULO);
            case SALIR -> gestorEventos.notificar(Evento.SALIR);
            default -> System.out.print("Error, tiene que escoger de entre las opciones válidas.");
        }
    }

    @Override
    public void notificarResultado(Evento evento, String texto, boolean exito) throws OperationNotSupportedException {
        Objects.requireNonNull(evento, "El evento no puede ser nulo.");
        Objects.requireNonNull(texto, "El texto no puede ser nulo.");

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
