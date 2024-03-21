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
    }
    @Override
    public void comenzar() throws OperationNotSupportedException {
        modelo.comenzar();
        vista.comenzar();
        vista.getGestorEventos().subscribir(this, Evento.values());
    }
    @Override
    public void actualizar(Evento evento) {
        Objects.requireNonNull(evento, "El evento no puede ser nulo.");

    }
    @Override
    public void terminar() {
        modelo.terminar();
        vista.terminar();
    }
}
