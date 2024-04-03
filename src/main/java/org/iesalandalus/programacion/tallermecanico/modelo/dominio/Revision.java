package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.Objects;

public class Revision extends Trabajo {
    private static final float FACTOR_HORA = 35F;

    public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        super(cliente, vehiculo, fechaInicio);
    }
    public Revision(Revision revision){
        super(revision);
        Objects.requireNonNull(revision, "La revisión no puede ser nula.");
        cliente = new Cliente(revision.cliente);
        vehiculo = revision.vehiculo;
        fechaInicio = revision.fechaInicio;
    }

    public float getPrecioEspecifico() {
        return (estaCerrado()) ? FACTOR_HORA * getHoras() : 0;
    }

    @Override
    public String toString() {
        String cadena;
        if (!estaCerrado()) {
            cadena = String.format("Revisión -> %s - %s (%s) - %s %s - %s (%s - ): %s horas", this.cliente.getNombre(), this.cliente.getDni(), this.cliente.getTelefono(), this.vehiculo.marca(), this.vehiculo.modelo(), this.vehiculo.matricula(), this.getFechaInicio().format(FORMATO_FECHA), this.horas);
        } else {
            cadena = String.format("Revisión -> %s - %s (%s) - %s %s - %s (%s - %s): %s horas, %.2f € total", this.cliente.getNombre(), this.cliente.getDni(), this.cliente.getTelefono(), this.vehiculo.marca(), this.vehiculo.modelo(), this.vehiculo.matricula(), this.getFechaInicio().format(FORMATO_FECHA), this.getFechaFin().format(FORMATO_FECHA), this.horas, this.getPrecio());
        }
        return cadena;
    }
}
