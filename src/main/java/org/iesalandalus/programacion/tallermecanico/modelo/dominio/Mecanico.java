package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.Objects;

public class Mecanico extends Trabajo{
    private static final float FACTOR_HORA = 30F;
    private static final float FACTOR_PRECIO_MATERIAL = 1.5F;
    protected float precioMaterial;

    public Mecanico(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        super(cliente, vehiculo, fechaInicio);
        precioMaterial = 0;
    }
    public Mecanico(Mecanico mecanico) {
        super(mecanico);
        Objects.requireNonNull(mecanico, "El mecánico no puede ser nulo.");
        this.precioMaterial = mecanico.precioMaterial;
        this.cliente = new Cliente(mecanico.cliente);
        this.vehiculo = mecanico.vehiculo;
        this.fechaInicio = mecanico.fechaInicio;

    }
    public float getPrecioMaterial() {
        return precioMaterial;
    }

    public void anadirPrecioMaterial (float precioMaterial) throws OperationNotSupportedException {
        if (estaCerrado()) {
            throw new OperationNotSupportedException("No se puede añadir precio del material, ya que el trabajo mecánico está cerrado.");
        }
        if (precioMaterial <= 0) {
            throw new IllegalArgumentException("El precio del material a añadir debe ser mayor que cero.");
        }
        this.precioMaterial += precioMaterial;
    }

    public float getPrecioEspecifico() {
        return (super.getHoras() * FACTOR_HORA) + (getPrecioMaterial() * FACTOR_PRECIO_MATERIAL);
    }

    @Override
    public String toString() {
        String cadena;
        if (!estaCerrado()) {
            cadena = String.format("Mecánico -> %s - %s (%s) - %s %s - %s (%s - ): %s horas, %.2f € en material", this.cliente.getNombre(), this.cliente.getDni(), this.cliente.getTelefono(), this.vehiculo.marca(), this.vehiculo.modelo(), this.vehiculo.matricula(), this.fechaInicio.format(FORMATO_FECHA), this.horas, this.getPrecioMaterial());
        } else {
            cadena = String.format("Mecánico -> %s - %s (%s) - %s %s - %s (%s - %s): %s horas, %.2f € en material, %.2f € total", this.cliente.getNombre(), this.cliente.getDni(), this.cliente.getTelefono(), this.vehiculo.marca(), this.vehiculo.modelo(), this.vehiculo.matricula(), this.fechaInicio.format(FORMATO_FECHA), this.fechaFin.format(FORMATO_FECHA), this.horas, this.getPrecioMaterial(), this.getPrecio());
        }
        return cadena;
    }
}
