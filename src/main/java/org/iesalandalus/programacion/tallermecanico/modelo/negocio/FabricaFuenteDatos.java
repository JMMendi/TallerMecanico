package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ficheros.FuenteDatosMemoria;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ficheros.IFuenteDatos;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.mariadb.FuenteDatosMariaDB;

public enum FabricaFuenteDatos {
    FICHEROS {
        @Override
        public IFuenteDatos crear() {
            return new FuenteDatosMemoria();
        }
    },
    MARIADB {
        @Override
        public IFuenteDatos crear() {return new FuenteDatosMariaDB();
        }
    };

    public abstract IFuenteDatos crear();
}

