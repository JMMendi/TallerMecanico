package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Trabajos {
    private List<Trabajo> coleccionTrabajos;

    public Trabajos() {
        coleccionTrabajos = new ArrayList<>();
    }
    public List<Trabajo> get() {
        return new ArrayList<>(coleccionTrabajos);
    }
    public List<Trabajo> get(Cliente cliente) {
        List<Trabajo> revisionesClientes = new ArrayList<>();
        for (Trabajo trabajo : coleccionTrabajos) {
            if (trabajo.getCliente().equals(cliente)) {
                revisionesClientes.add(trabajo);
            }
        }
        return revisionesClientes;
    }
    public List<Trabajo> get(Vehiculo vehiculo) {
        List<Trabajo> vehiculos = new ArrayList<>();
        for (Trabajo trabajo : coleccionTrabajos) {
            if (trabajo.getVehiculo().equals(vehiculo)) {
                vehiculos.add(trabajo);
            }
        }
        return vehiculos;
    }
    public void insertar(Trabajo trabajo) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "No se puede insertar una revisión nula.");
        comprobarTrabajo(trabajo.getCliente(), trabajo.getVehiculo(), trabajo.getFechaInicio());
        coleccionTrabajos.add(trabajo);
    }
    private void comprobarTrabajo(Cliente cliente, Vehiculo vehiculo, LocalDate fechaTrabajo) throws OperationNotSupportedException {
        for (Trabajo trabajo : coleccionTrabajos) {
            if (trabajo.getCliente().equals(cliente)) {
                if (!trabajo.estaCerrado()) {
                    throw new OperationNotSupportedException("El cliente tiene otra revisión en curso.");
                }
                if (trabajo.estaCerrado() && (!fechaTrabajo.isAfter(trabajo.getFechaFin()))) {
                    throw new OperationNotSupportedException("El cliente tiene una revisión posterior.");
                }
            }
            if (trabajo.getVehiculo().equals(vehiculo)) {
                if (!trabajo.estaCerrado()) {
                    throw new OperationNotSupportedException("El vehículo está actualmente en revisión.");
                }
                if (trabajo.estaCerrado() && (!fechaTrabajo.isAfter(trabajo.getFechaFin()))) {
                    throw new OperationNotSupportedException("El vehículo tiene una revisión posterior.");
                }
            }
        }
    }
    private Trabajo getTrabajo(Trabajo trabajo) {
        Objects.requireNonNull(trabajo, "La revisión no puede ser nula.");
        int indice = coleccionTrabajos.indexOf(trabajo);
        return indice == -1 ? null : coleccionTrabajos.get(indice);
    }

    public void anadirHoras(int horas, Trabajo trabajo) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "No puedo operar sobre una revisión nula.");
        if (getTrabajo(trabajo) == null) {
            throw new OperationNotSupportedException("No existe ninguna revisión igual.");
        }
        getTrabajo(trabajo);
        trabajo.anadirHoras(horas);
    }
    public void anadirPrecioMaterial(Mecanico mecanico, float precioMaterial) throws OperationNotSupportedException {
        Objects.requireNonNull(mecanico, "No puedo operar sobre una revisión nula.");
        if (getTrabajo(mecanico) == null) {
            throw new OperationNotSupportedException("No existe ninguna revisión igual.");
        }
        mecanico.anadirPrecioMaterial(precioMaterial);
    }
    public void cerrar(Trabajo trabajo, LocalDate fechaFin) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "No puedo operar sobre una revisión nula.");
        if (getTrabajo(trabajo) == null) {
            throw new OperationNotSupportedException("No existe ninguna revisión igual.");
        }
        trabajo.cerrar(fechaFin);
    }
    public Trabajo buscar(Trabajo trabajo) {
        Objects.requireNonNull(trabajo, "No se puede buscar una revisión nula.");
        return coleccionTrabajos.contains(trabajo) ? getTrabajo(trabajo) : null;
    }
    public void borrar(Trabajo trabajo) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "No se puede borrar una revisión nula.");
        if (!coleccionTrabajos.contains(trabajo)) {
            throw new OperationNotSupportedException("No existe ninguna revisión igual.");
        }
        coleccionTrabajos.remove(trabajo);
    }
}
