package org.iesalandalus.programacion.tallermecanico.controlador;

import org.iesalandalus.programacion.tallermecanico.modelo.Modelo;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.texto.Vista;


import javax.naming.OperationNotSupportedException;
import java.util.Objects;

public class Controlador implements IControlador {
    private final Vista vista;
    private final Modelo modelo;

    public Controlador(Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }
    @Override
    public void comenzar() throws OperationNotSupportedException {
        modelo.comenzar();
        vista.comenzar();
        vista.getGestorEventos().subscribir(this, Evento.values());
    }
    @Override
    public void actualizar(Evento evento) throws OperationNotSupportedException {
        Objects.requireNonNull(evento, "El evento no puede ser nulo.");
        vista.getGestorEventos().notificar(evento);
        switch (evento) {
            case INSERTAR_CLIENTE -> modelo.insertar(vista.leerCliente());
            case INSERTAR_VEHICULO -> modelo.insertar(vista.leerVehiculo());
            case INSERTAR_REVISION -> modelo.insertar(vista.leerRevision());
            case BUSCAR_CLIENTE -> modelo.buscar(vista.leerCliente());
            case BUSCAR_VEHICULO -> modelo.buscar(vista.leerVehiculo());
            case BUSCAR_TRABAJO -> modelo.buscar(vista.leerMecanico());
            case MODIFICAR_CLIENTE -> modelo.modificar(vista.leerCliente(), vista.leerNuevoNombre(), vista.leerNuevoTelefono());
            case ANADIR_HORAS_TRABAJO -> modelo.anadirHoras(vista.leerRevision(), vista.leerHoras());
            case ANADIR_PRECIO_MATERIAL_TRABAJO -> modelo.anadirPrecioMaterial(vista.leerMecanico(), vista.leerPrecioMaterial());
            case INSERTAR_MECANICO -> modelo.insertar(vista.leerMecanico());
            case CERRAR_TRABAJO -> modelo.cerrar(vista.leerMecanico(), vista.leerFechaCierre());
            case BORRAR_CLIENTE -> modelo.borrar(vista.leerCliente());
            case BORRAR_TRABAJO -> modelo.borrar(vista.leerMecanico());
            case BORRAR_VEHICULO -> modelo.borrar(vista.leerVehiculo());
            case LISTAR_CLIENTES -> modelo.getClientes();
            case LISTAR_VEHICULOS -> modelo.getVehiculos();
            case LISTAR_TRABAJOS -> modelo.getTrabajos();
            case LISTAR_TRABAJOS_CLIENTE -> modelo.getTrabajos(vista.leerCliente());
            case LISTAR_TRABAJOS_VEHICULO -> modelo.getTrabajos(vista.leerVehiculo());
            case SALIR -> modelo.terminar();
            default -> System.out.print("Error, tiene que escoger de entre las opciones válidas.");
        }
    }
    @Override
    public void terminar() {
        modelo.terminar();
        vista.terminar();
    }
}
