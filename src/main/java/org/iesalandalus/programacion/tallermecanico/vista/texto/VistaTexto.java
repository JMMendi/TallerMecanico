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
        leerMarca = Consola.leerCadena(Entrada.cadena());

        System.out.print("Introduce la matrícula del vehículo: ");
        leerMatricula = Consola.leerCadena(Entrada.cadena());

        System.out.print("Introduce el modelo del vehículo: ");
        leerModelo = Consola.leerCadena(Entrada.cadena());

        return new Vehiculo(leerMarca, leerModelo, leerMatricula);
    }

    @Override
    public Vehiculo leerVehiculoMatricula() {
        String leerMatricula;
        System.out.print("Introduce la matrícula del vehículo aquí: ");
        leerMatricula = Consola.leerCadena(Entrada.cadena());

        return Vehiculo.get(leerMatricula);
    }

    @Override
    public Trabajo leerTrabajoVehiculo() {
        return Trabajo.get(leerVehiculoMatricula());
    }

    @Override
    public Trabajo leerRevision() {
        Cliente cliente = leerClienteDni();
        Vehiculo vehiculo = leerVehiculoMatricula();
        LocalDate leerFecha = Consola.leerFecha(Entrada.cadena());

        return new Revision(cliente, vehiculo, leerFecha);
    }

    @Override
    public Trabajo leerMecanico() {
        Cliente cliente = leerClienteDni();
        Vehiculo vehiculo = leerVehiculoMatricula();
        LocalDate leerFecha = Consola.leerFecha(Entrada.cadena());

        return new Mecanico(cliente, vehiculo, leerFecha);
    }

    @Override
    public int leerHoras() {
        int horas;
        System.out.print("Introduce el número de horas de la revisión aquí: ");
        horas = Consola.leerEntero(Entrada.cadena());
        return horas;
    }

    @Override
    public float leerPrecioMaterial() {
        float precioMaterial;
        System.out.print("Introduce el precio del material aquí: ");
        precioMaterial = Consola.leerReal(Entrada.cadena());

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
        Consola.mostrarCabecera(evento.toString());
        gestorEventos.notificar(evento);
    }

    @Override
    public void notificarResultado(Evento evento, String texto, boolean exito){
        Objects.requireNonNull(evento, "El evento no puede ser nulo.");
        Objects.requireNonNull(texto, "El texto no puede ser nulo.");
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
            System.out.printf("%s%n", clientes);
        } else {
            throw new IllegalArgumentException("La lista de Clientes está vacía.");
        }
    }
    @Override
    public void mostrarVehiculos(List<Vehiculo> vehiculos) {
        if (!vehiculos.isEmpty()) {
            System.out.printf("%s%n", vehiculos);
        } else {
            throw new IllegalArgumentException("La lista de Vehículos está vacía.");
        }
    }
    @Override
    public void mostrarTrabajos(List<Trabajo> trabajos) {
        if (!trabajos.isEmpty()) {
            System.out.printf("%s%n", trabajos);
        } else {
            throw new IllegalArgumentException("La lista de Trabajos está vacía.");
        }
    }
}
