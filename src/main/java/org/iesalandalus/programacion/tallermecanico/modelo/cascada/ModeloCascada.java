package org.iesalandalus.programacion.tallermecanico.modelo.cascada;

import org.iesalandalus.programacion.tallermecanico.modelo.Modelo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.FabricaFuenteDatos;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IClientes;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ITrabajos;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IVehiculos;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.*;

public class ModeloCascada implements Modelo {
    private ITrabajos trabajos;
    private IVehiculos vehiculos;
    private IClientes clientes;

    public ModeloCascada(FabricaFuenteDatos fabricaFuenteDatos) {
        Objects.requireNonNull(fabricaFuenteDatos, "La fábrica de fuente de datos no puede ser nula.");
        clientes = fabricaFuenteDatos.crear().crearClientes();
        vehiculos = fabricaFuenteDatos.crear().crearVehiculos();
        trabajos = fabricaFuenteDatos.crear().crearTrabajos();
    }

    @Override
    public void comenzar() {
        clientes.comenzar();
        vehiculos.comenzar();
        trabajos.comenzar();
    }
    @Override
    public void terminar() {
        clientes.terminar();
        vehiculos.terminar();
        trabajos.terminar();
    }
    @Override
    public void insertar(Cliente cliente) throws OperationNotSupportedException {
        clientes.insertar(new Cliente(cliente));
    }
    @Override
    public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
        vehiculos.insertar(vehiculo);
    }
    @Override
    public void insertar(Trabajo trabajo) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "La revisión a insertar no puede ser nula.");
        Cliente cliente = clientes.buscar(trabajo.getCliente());
        Vehiculo vehiculo = vehiculos.buscar(trabajo.getVehiculo());
        if (trabajo instanceof Revision) {
            trabajo = new Revision(cliente, vehiculo, trabajo.getFechaInicio());
        } else {
            trabajo = new Mecanico(cliente, vehiculo, trabajo.getFechaInicio());
        }
        trabajos.insertar(trabajo);
    }
    @Override
    public Cliente buscar(Cliente cliente) {
        cliente = Objects.requireNonNull(clientes.buscar(cliente), "No existe un cliente igual.");
        return new Cliente(cliente);
    }
    @Override
    public Vehiculo buscar(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculos.buscar(vehiculo), "El vehículo no puede ser nulo buscar.");
        return vehiculo;
    }
    @Override
    public Trabajo buscar(Trabajo trabajo) {
        trabajo = Objects.requireNonNull(trabajos.buscar(trabajo), "La revisión no puede ser nula buscar.");
        return Trabajo.copiar(trabajo);
    }
    @Override
    public boolean modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
        return clientes.modificar(cliente, nombre, telefono);
    }

    @Override
    public void anadirHoras(Trabajo trabajo, int horas) throws OperationNotSupportedException {
        trabajos.anadirHoras(trabajo, horas);
    }
    @Override
    public void anadirPrecioMaterial(Trabajo trabajo, float precioMaterial) throws OperationNotSupportedException {
        trabajos.anadirPrecioMaterial(trabajo, precioMaterial);
    }
    @Override
    public void cerrar(Trabajo trabajo, LocalDate fechaFin) throws OperationNotSupportedException {
        trabajos.cerrar(trabajo, fechaFin);
    }
    @Override
    public void borrar(Cliente cliente) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo borrar.");
        for (Trabajo trabajo : trabajos.get(cliente)) {
            trabajos.borrar(trabajo);
        }
        clientes.borrar(cliente);
    }
    @Override
    public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo borrar.");
        for (Trabajo trabajo : trabajos.get(vehiculo)) {
            trabajos.borrar(trabajo);
        }
        vehiculos.borrar(vehiculo);
    }
    @Override
    public void borrar(Trabajo trabajo) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "La revisión no puede ser nula borrar.");
        trabajos.borrar(trabajo);
    }
    @Override
    public List<Cliente> getClientes() {
        List<Cliente> listaClientes = new ArrayList<>();
        for (Cliente cliente : clientes.get()) {
            listaClientes.add(new Cliente(cliente));
        }
        return listaClientes;
    }
    @Override
    public List<Vehiculo> getVehiculos() {
        return vehiculos.get();
    }

    @Override
    public List<Trabajo> getTrabajos() {
        List<Trabajo> listaRevision = new ArrayList<>();
        for (Trabajo trabajo : trabajos.get()) {
            if (trabajo instanceof Revision revision) {
                listaRevision.add(new Revision(revision));
            } else if (trabajo instanceof Mecanico mecanico) {
                listaRevision.add(new Mecanico(mecanico));
            }
        }
        return listaRevision;
    }
    @Override
    public List<Trabajo> getTrabajos(Cliente cliente) {
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo getRevisiones.");
        List<Trabajo> trabajoCliente = new ArrayList<>();
        for (Trabajo trabajo : trabajos.get(cliente)) {
            if (trabajo instanceof Revision revision) {
                trabajoCliente.add(new Revision(revision));
            } else if (trabajo instanceof Mecanico mecanico) {
                trabajoCliente.add(new Mecanico(mecanico));
            }
        }
        return trabajoCliente;
    }
    @Override
    public List<Trabajo> getTrabajos(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo getRevisiones.");
        List<Trabajo> trabajoVehiculo = new ArrayList<>();
        for (Trabajo trabajo : trabajos.get(vehiculo)) {
            if (trabajo instanceof Revision revision) {
                trabajoVehiculo.add(new Revision(revision));
            } else if (trabajo instanceof Mecanico mecanico) {
                trabajoVehiculo.add(new Mecanico(mecanico));
            }
        }
        return trabajoVehiculo;
    }
    public Map<TipoTrabajo, Integer> getEstadisticasMensuales (LocalDate mes) {
        return trabajos.getEstadisticasMensuales(mes);
    }
}
