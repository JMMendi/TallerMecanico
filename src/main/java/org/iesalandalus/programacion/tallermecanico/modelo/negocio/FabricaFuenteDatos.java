package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.FuenteDatosMemoria;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria.IFuenteDatos;

public enum FabricaFuenteDatos {
    MEMORIA {
        @Override
        public IFuenteDatos crear() {
            return new FuenteDatosMemoria();
        }
    };

    public abstract IFuenteDatos crear();
}

