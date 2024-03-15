package org.iesalandalus.programacion.tallermecanico.modelo.negocio.memoria;

import org.iesalandalus.programacion.tallermecanico.modelo.negocio.*;

public class FuenteDatosMemoria implements IFuenteDatos {
    @Override
    public IClientes crearClientes() {
        return new Clientes();
    }
    @Override
    public IVehiculos crearVehiculos() {
        return new Vehiculos();
    }
    @Override
    public ITrabajos crearTrabajos() {
        return new Trabajos();
    }
}
