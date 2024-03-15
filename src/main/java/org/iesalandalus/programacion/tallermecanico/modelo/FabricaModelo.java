package org.iesalandalus.programacion.tallermecanico.modelo;

import org.iesalandalus.programacion.tallermecanico.modelo.cascada.ModeloCascada;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.FabricaFuenteDatos;

public enum FabricaModelo {
    CASCADA {
        @Override
        public org.iesalandalus.programacion.tallermecanico.modelo.Modelo crear(FabricaFuenteDatos fabricaFuenteDatos) {
            return new ModeloCascada(fabricaFuenteDatos) {
            };
        }
    };
    public abstract org.iesalandalus.programacion.tallermecanico.modelo.Modelo crear(FabricaFuenteDatos fabricaFuenteDatos);
}
