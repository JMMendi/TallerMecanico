package org.iesalandalus.programacion.tallermecanico.vista.eventos;

import javax.naming.OperationNotSupportedException;
import java.util.*;

public class GestorEventos {
    private final Map<Evento, List<ReceptorEventos>> receptores;

    public GestorEventos(Evento... eventos) {
        receptores = new EnumMap<>(Evento.class);
        for (Evento evento : eventos) {
            receptores.put(evento, new ArrayList<>());
        }
    }

    public void subscribir(ReceptorEventos receptor, Evento... evento) {
        Objects.requireNonNull(receptor, "El receptor de eventos no puede ser nulo.");
        Objects.requireNonNull(evento, "El evento no puede ser nulo.");
        for (Evento eventoNuevo : evento) {
            if (receptores.containsKey(eventoNuevo)) {
                List<ReceptorEventos> subscriptores = new ArrayList<>();
                subscriptores.add(receptor);
                receptores.replace(eventoNuevo, subscriptores);
            }
        }
        // for (Evento evento : eventos) List<ReceptoEventos> usuarios = receptores.get(evento
        // usuarios.add(receptor) <--- Esto usarlo en el desubscribir
    }

    public void desubscribir(ReceptorEventos receptor, Evento... evento) {
        Objects.requireNonNull(receptor, "El receptor de eventos no puede ser nulo.");
        Objects.requireNonNull(evento, "El evento no puede ser nulo.");
        for (Evento eventoNuevo : evento) {
            if (receptores.containsKey(eventoNuevo)) {
                receptores.get(eventoNuevo).remove(receptor);
            }
        }
    }

    public void notificar(Evento evento) throws OperationNotSupportedException {
        Objects.requireNonNull(evento, "El evento no puede ser nulo.");
        for (ReceptorEventos receptor : receptores.get(evento)) {
            receptor.actualizar(evento);
        }
    }
}