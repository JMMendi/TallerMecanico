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
        vista.getGestorEventos().subscribir(this, Evento.values());
    }

    @Override
    public void comenzar() throws OperationNotSupportedException {
        modelo.comenzar();
        vista.comenzar();

    }

    @Override
    public void actualizar(Evento evento) throws OperationNotSupportedException {
        Objects.requireNonNull(evento, "El evento no puede ser nulo.");
        String resultado = "";
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
                case BUSCAR_CLIENTE -> vista.mostrarCliente(modelo.buscar(vista.leerClienteDni()));
                case BUSCAR_VEHICULO -> vista.mostrarVehiculo(modelo.buscar(vista.leerVehiculoMatricula()));
                case BUSCAR_TRABAJO -> vista.mostrarTrabajo(modelo.buscar(vista.leerMecanico()));
                case MODIFICAR_CLIENTE -> resultado = (modelo.modificar(vista.leerCliente(), vista.leerNuevoNombre(), vista.leerNuevoTelefono())) ? "Cliente modificado correctamente." : "El cliente no se ha modificado.";
                case ANADIR_HORAS_TRABAJO -> {
                    modelo.anadirHoras(vista.leerTrabajoVehiculo(), vista.leerHoras());
                    resultado = "Añadidas las horas correctamente.";
                }
                case ANADIR_PRECIO_MATERIAL_TRABAJO -> {
                    modelo.anadirPrecioMaterial(vista.leerTrabajoVehiculo(), vista.leerPrecioMaterial());
                    resultado = "Añadido el precio del material correctamente.";
                }
                case INSERTAR_MECANICO -> {
                    modelo.insertar(vista.leerMecanico());
                    resultado = "Trabajo mecánico insertado correctamente.";
                }
                case CERRAR_TRABAJO -> {
                    modelo.cerrar(vista.leerTrabajoVehiculo(), vista.leerFechaCierre());
                    resultado = "Trabajo cerrado correctamente.";
                }
                case BORRAR_CLIENTE -> {
                    modelo.borrar(vista.leerClienteDni());
                    resultado = "Cliente borrado correctamente.";
                }
                case BORRAR_TRABAJO -> {
                    modelo.borrar(vista.leerMecanico());
                    resultado = "Trabajo borrado correctamente.";
                }
                case BORRAR_VEHICULO -> {
                    modelo.borrar(vista.leerVehiculoMatricula());
                    resultado = "Vehículo borrado correctamente.";
                }
                case LISTAR_CLIENTES -> vista.mostrarClientes(modelo.getClientes());
                case LISTAR_VEHICULOS -> vista.mostrarVehiculos(modelo.getVehiculos());
                case LISTAR_TRABAJOS -> vista.mostrarTrabajos(modelo.getTrabajos());
                case LISTAR_TRABAJOS_CLIENTE ->
                        vista.mostrarTrabajos(modelo.getTrabajos(vista.leerClienteDni())); // vista.mostrarTrabajos
                case LISTAR_TRABAJOS_VEHICULO -> vista.mostrarTrabajos(modelo.getTrabajos(vista.leerVehiculo()));
                case SALIR -> modelo.terminar();
                default -> System.out.print("Error, tiene que escoger de entre las opciones válidas.");
            }
            if (!resultado.isBlank()) {
                vista.notificarResultado(evento, resultado, true);
            }
        } catch (Exception e) {
            vista.notificarResultado(evento, e.getMessage(), false);
        }
    }

    @Override
    public void terminar() {
        modelo.terminar();
        vista.terminar();
    }
}
