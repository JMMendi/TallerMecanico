package org.iesalandalus.programacion.tallermecanico.vista.texto;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.GestorEventos;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class VistaTexto implements Vista {
    private final GestorEventos gestorEventos = new GestorEventos(Evento.values());

    @Override
    public GestorEventos getGestorEventos() {
        return gestorEventos;
    }

    @Override
    public void comenzar() throws OperationNotSupportedException {
        Evento opcion;
        do {
            Consola.mostrarMenu();
            opcion = Consola.elegirOpcion();
            ejecutar(opcion);
        } while (opcion != Evento.SALIR);
    }
    @Override
    public void terminar() {
        System.out.println("Cerrando Vista. Que tenga un buen día.");
    }

    @Override
    public Cliente leerCliente() {
        String leerClienteNombre = Consola.leerCadena("Introduce el nombre aquí: ");
        String leerClienteDNI = Consola.leerCadena("Introduce el DNI aquí: ");
        String leerClienteTelefono = Consola.leerCadena("Introduce el teléfono aquí: ");

        return new Cliente(leerClienteNombre, leerClienteDNI, leerClienteTelefono);
    }

    @Override
    public Cliente leerClienteDni() {
        String dni = Consola.leerCadena("Introduzca el DNI aquí: ");

        return Cliente.get(dni);
    }

    @Override
    public String leerNuevoNombre() {
        return Consola.leerCadena("Introduzca el nuevo nombre aquí: ");
    }

    @Override
    public String leerNuevoTelefono() {
        return Consola.leerCadena("Introduce el nuevo teléfono aquí: ");
    }

    @Override
    public Vehiculo leerVehiculo() {
        String marca = Consola.leerCadena("Introduce la marca: ");
        String modelo = Consola.leerCadena("Introduce el modelo: ");
        String matricula = Consola.leerCadena("Introduce la matrícula: ");
        return new Vehiculo(marca, modelo, matricula);
    }

    @Override
    public Vehiculo leerVehiculoMatricula() {
        return Vehiculo.get(Consola.leerCadena("Introduce la matrícula: "));
    }

    @Override
    public Trabajo leerTrabajoVehiculo() {
        return Trabajo.get(leerVehiculoMatricula());
    }

    @Override
    public LocalDate leerMes() {
        return leerFechaCierre();
    }

    @Override
    public Trabajo leerRevision() {
        Cliente cliente = leerClienteDni();
        Vehiculo vehiculo = leerVehiculoMatricula();
        LocalDate leerFecha = Consola.leerFecha("Introduce la fecha de inicio aquí: ");

        return new Revision(cliente, vehiculo, leerFecha);
    }

    @Override
    public Trabajo leerMecanico() {
        Cliente cliente = leerClienteDni();
        Vehiculo vehiculo = leerVehiculoMatricula();
        LocalDate leerFecha = Consola.leerFecha("Introduce la fecha de inicio aquí: ");

        return new Mecanico(cliente, vehiculo, leerFecha);
    }

    @Override
    public int leerHoras() {
        return Consola.leerEntero("Introduzca el número de horas aquí: ");
    }

    @Override
    public float leerPrecioMaterial() {
        return Consola.leerReal("Introduzca el precio del materíal aquí: ");
    }

    @Override
    public LocalDate leerFechaCierre() {
        return Consola.leerFecha("Introduzca la fecha de fin aquí: ");
    }

    private void ejecutar(Evento evento) throws OperationNotSupportedException {
        Consola.mostrarCabecera(evento.toString());
        gestorEventos.notificar(evento);
    }

    @Override
    public void notificarResultado(Evento evento, String texto, boolean exito){
        if (exito) {
            System.out.println(texto);
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
    public void mostrarClientes(List<Cliente> clientes) { //Mirar si están vacios o no. Si no lo estan mostrar, sino dar error
        if (!clientes.isEmpty()) {
            clientes.sort(Comparator.comparing(Cliente::getNombre).thenComparing(Cliente::getDni));
            System.out.printf("%s%n", clientes);
        } else {
            throw new IllegalArgumentException("La lista de Clientes está vacía.");
        }
    }
    @Override
    public void mostrarVehiculos(List<Vehiculo> vehiculos) {
        if (!vehiculos.isEmpty()) {
            vehiculos.sort(Comparator.comparing(Vehiculo::marca).thenComparing(Vehiculo::modelo).thenComparing(Vehiculo::matricula));
            System.out.printf("%s%n", vehiculos);
        } else {
            throw new IllegalArgumentException("La lista de Vehículos está vacía.");
        }
    }
    @Override
    public void mostrarTrabajos(List<Trabajo> trabajos) {
        if (!trabajos.isEmpty()) {
            trabajos.sort(Comparator.comparing(Trabajo::getFechaInicio)); // si se intenta Trabajo::GetCliente no le gusta.
            System.out.printf("%s%n", trabajos);
        } else {
            throw new IllegalArgumentException("La lista de Trabajos está vacía.");
        }
    }

    @Override
    public void mostrarEstadisticasMensuales(Map<TipoTrabajo, Integer> estadistica) {
        System.out.println(estadistica);
    }

    @Override
    public void mostrarTrabajosCliente(List<Trabajo> trabajosCliente) {
        if (!trabajosCliente.isEmpty()) {
            for (Trabajo trabajo : trabajosCliente) {
                System.out.println(trabajo);
            }
        } else {
            System.out.println("No hay trabajos que mostrar para dicho cliente.");
        }
    }

    @Override
    public void mostrarTrabajosVehiculo(List<Trabajo> trabajosVehiculo) {
        if (!trabajosVehiculo.isEmpty()) {
            for (Trabajo trabajo : trabajosVehiculo) {
                System.out.println(trabajo);
            }
        } else {
            System.out.println("No hay trabajos que mostrar para dicho vehículo.");
        }
    }
}