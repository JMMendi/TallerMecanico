package org.iesalandalus.programacion.tallermecanico.modelo.negocio.ficheros;

import org.iesalandalus.programacion.tallermecanico.modelo.negocio.*;

public class FuenteDatosMemoria implements IFuenteDatos {
    @Override
    public IClientes crearClientes() {
        return Clientes.getInstancia();
    }
    @Override
    public IVehiculos crearVehiculos() {
        return Vehiculos.getInstancia();
    }
    @Override
    public ITrabajos crearTrabajos() {
        return Trabajos.getInstancia();
    }
}
