package org.iesalandalus.programacion.tallermecanico.vista.eventos;

import java.util.*;

public class GestorEventos {
    private Map<Evento, List<ReceptorEventos>> receptores;

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

    public void notificar(Evento evento) {
        Objects.requireNonNull(evento, "El evento no puede ser nulo.");
        System.out.printf("Se está lanzando el comando %s%n", evento);
    }
}