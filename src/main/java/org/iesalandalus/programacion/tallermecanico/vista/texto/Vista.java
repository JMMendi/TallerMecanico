package org.iesalandalus.programacion.tallermecanico.vista.texto;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.GestorEventos;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.List;

public interface Vista {
    public Cliente leerCliente();

    public Cliente leerClienteDni();

    public String leerNuevoNombre();

    public String leerNuevoTelefono();

    public Vehiculo leerVehiculo();

    public Vehiculo leerVehiculoMatricula();

    public Trabajo leerRevision();

    public Trabajo leerMecanico();

    public int leerHoras();

    public float leerPrecioMaterial();

    public LocalDate leerFechaCierre();

    GestorEventos getGestorEventos();

    void comenzar() throws OperationNotSupportedException;

    void terminar();

    void notificarResultado(Evento evento, String texto, boolean exito) throws OperationNotSupportedException;

    void mostrarCliente(Cliente cliente);

    void mostrarVehiculo(Vehiculo vehiculo);

    void mostrarTrabajo(Trabajo trabajo);

    void mostrarClientes(List<Cliente> clientes);

    void mostrarVehiculos(List<Vehiculo> vehiculos);

    void mostrarTrabajos(List<Trabajo> trabajos);
}
