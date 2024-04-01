package org.iesalandalus.programacion.tallermecanico.vista.eventos;

import javax.naming.OperationNotSupportedException;

public interface ReceptorEventos {
    public void actualizar(Evento evento) throws OperationNotSupportedException;
}
