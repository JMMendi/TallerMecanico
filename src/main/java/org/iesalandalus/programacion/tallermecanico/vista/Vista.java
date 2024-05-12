package org.iesalandalus.programacion.tallermecanico.vista;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.GestorEventos;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface Vista {
    Cliente leerCliente();

    Cliente leerClienteDni();

    String leerNuevoNombre();

    String leerNuevoTelefono();

    Vehiculo leerVehiculo();

    Vehiculo leerVehiculoMatricula();

    Trabajo leerRevision();

    Trabajo leerMecanico();

    int leerHoras();

    float leerPrecioMaterial();

    LocalDate leerFechaCierre();

    Trabajo leerTrabajoVehiculo();

    LocalDate leerMes();

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

    default void mostrarTrabajosCliente(List<Trabajo> trabajosCliente) {mostrarTrabajos(trabajosCliente);}

    default void mostrarTrabajosVehiculo(List<Trabajo> trabajosVehiculo) {mostrarTrabajos(trabajosVehiculo);}

    void mostrarEstadisticasMensuales(Map<TipoTrabajo, Integer> estadistica);


}

