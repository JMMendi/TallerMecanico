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
    public void actualizar(Evento evento) {
        Objects.requireNonNull(evento, "El evento no puede ser nulo.");
        String resultado = "";
        boolean exito = true;
        try {
            switch (evento) {
                case INSERTAR_CLIENTE -> {
                    modelo.insertar(vista.leerCliente());
                    resultado = "Cliente insertado correctamente.";
                }
                case INSERTAR_VEHICULO -> {
                    modelo.insertar(vista.leerVehiculo());
                    resultado = "Vehículo insertado correctamente.";
                }
                case INSERTAR_REVISION -> {
                    modelo.insertar(vista.leerRevision());
                    resultado = "Revisión insertada correctamente.";
                }
                case BUSCAR_CLIENTE -> modelo.buscar(vista.leerCliente());
                case BUSCAR_VEHICULO -> modelo.buscar(vista.leerVehiculo());
                case BUSCAR_TRABAJO -> modelo.buscar(vista.leerMecanico());
                case MODIFICAR_CLIENTE -> {
                    modelo.modificar(vista.leerCliente(), vista.leerNuevoNombre(), vista.leerNuevoTelefono());
                    resultado = "Cliente modificado correctamente.";
                }
                case ANADIR_HORAS_TRABAJO -> {
                    modelo.anadirHoras(vista.leerRevision(), vista.leerHoras());
                    resultado = "Añadidas las horas correctamente.";
                }
                case ANADIR_PRECIO_MATERIAL_TRABAJO -> {
                    modelo.anadirPrecioMaterial(vista.leerMecanico(), vista.leerPrecioMaterial());
                    resultado = "Añadido el precio del material correctamente.";
                }
                case INSERTAR_MECANICO -> {
                    modelo.insertar(vista.leerMecanico());
                    resultado = "Trabajo mecánico insertado correctamente.";
                }
                case CERRAR_TRABAJO -> {
                    modelo.cerrar(vista.leerMecanico(), vista.leerFechaCierre());
                    resultado = "Trabajo cerrado correctamente.";
                }
                case BORRAR_CLIENTE -> {
                    modelo.borrar(vista.leerCliente());
                    resultado = "Cliente borrado correctamente.";
                }
                case BORRAR_TRABAJO -> {
                    modelo.borrar(vista.leerMecanico());
                    resultado = "Trabajo borrado correctamente.";
                }
                case BORRAR_VEHICULO -> {
                    modelo.borrar(vista.leerVehiculo());
                    resultado = "Vehículo borrado correctamente.";
                }
                case LISTAR_CLIENTES -> modelo.getClientes();
                case LISTAR_VEHICULOS -> modelo.getVehiculos();
                case LISTAR_TRABAJOS -> modelo.getTrabajos();
                case LISTAR_TRABAJOS_CLIENTE -> modelo.getTrabajos(vista.leerCliente());
                case LISTAR_TRABAJOS_VEHICULO -> modelo.getTrabajos(vista.leerVehiculo());
                case SALIR -> modelo.terminar();
                default -> System.out.print("Error, tiene que escoger de entre las opciones válidas.");
            }
            if (!resultado.isBlank()) {
                vista.notificarResultado(evento, resultado, exito);
            }
        } catch (OperationNotSupportedException e) {
            exito = false;
            System.out.printf("ERROR: %s", e.getMessage());
        }
    }

    @Override
    public void terminar() {
        modelo.terminar();
        vista.terminar();
    }
}
