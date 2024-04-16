package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

public enum TipoTrabajo {
    MECANICO("Mecánico"),
    REVISION("Revisión");

    String nombre;
    TipoTrabajo(String nombre){}

    public static TipoTrabajo get(Trabajo trabajo) {
        TipoTrabajo tipo = null;
        if (trabajo instanceof Mecanico) {
            tipo = MECANICO;
        } else if (trabajo instanceof Revision) {
            tipo = REVISION;
        }
        return tipo;
    }
}
