package org.iesalandalus.programacion.tallermecanico.vista.texto;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.GestorEventos;
import org.iesalandalus.programacion.utilidades.Entrada;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.List;

public interface Vista {
    static Cliente leerCliente() {
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

    static Cliente leerClienteDni() {
        String dni;
        System.out.print("Introduce el dni del cliente aquí: ");
        dni = Entrada.cadena();

        return Cliente.get(dni);
    }

    static String leerNuevoNombre() {
        String nuevoNombre;
        System.out.print("Introduce el nuevo nombre aquí: ");
        nuevoNombre = Entrada.cadena();

        return nuevoNombre;
    }

    static String leerNuevoTelefono() {
        String nuevoTelefono;
        System.out.print("Introduce el nuevo teléfono aquí: ");
        nuevoTelefono = Entrada.cadena();

        return nuevoTelefono;
    }

    static Vehiculo leerVehiculo() {
        String leerMarca;
        String leerMatricula;
        String leerModelo;

        System.out.print("Introduce la marca del vehículo: ");
        leerMarca = Entrada.cadena();

        System.out.print("Introduce la matrícula del vehículo: ");
        leerMatricula = Entrada.cadena();

        System.out.print("Introduce el modelo del vehículo: ");
        leerModelo = Entrada.cadena();

        return new Vehiculo(leerMarca, leerModelo, leerMatricula);
    }

    static Vehiculo leerVehiculoMatricula() {
        String leerMatricula;
        System.out.print("Introduce la matrícula del vehículo aquí: ");
        leerMatricula = Entrada.cadena();

        return Vehiculo.get(leerMatricula);
    }

    static Trabajo leerRevision() {
        String leerFecha;
        System.out.print("Introduce la fecha de inicio de la revisión (Recuerde: el formato es dd/MM/yyyy): ");
        leerFecha = Entrada.cadena();

        return new Revision(leerCliente(), leerVehiculo(), LocalDate.parse(leerFecha));
    }

    static Trabajo leerMecanico() {
        String leerFecha;
        System.out.print("Introduce la fecha de inicio de la revisión (Recuerde: el formato es dd/MM/yyyy): ");
        leerFecha = Entrada.cadena();

        return new Mecanico(leerCliente(), leerVehiculo(), LocalDate.parse(leerFecha));
    }

    static int leerHoras() {
        int horas;
        System.out.print("Introduce el número de horas de la revisión aquí: ");
        horas = Entrada.entero();
        return horas;
    }

    static float leerPrecioMaterial() {
        float precioMaterial;
        System.out.print("Introduce el precio del material aquí: ");
        precioMaterial = Entrada.real();

        return precioMaterial;
    }

    static LocalDate leerFechaCierre() {
        String fechaCierre;
        System.out.print("Introduce la fecha de cierre de la revisión aquí: ");
        fechaCierre = Entrada.cadena();

        return LocalDate.parse(fechaCierre);
    }

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
