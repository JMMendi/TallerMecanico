package org.iesalandalus.programacion.tallermecanico;



import org.iesalandalus.programacion.tallermecanico.controlador.Controlador;
import org.iesalandalus.programacion.tallermecanico.modelo.FabricaModelo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.FabricaFuenteDatos;
import org.iesalandalus.programacion.tallermecanico.vista.FabricaVista;


import javax.naming.OperationNotSupportedException;

public class Main {
    public static void main(String[] args) throws OperationNotSupportedException {
        Controlador controlador = new Controlador(FabricaModelo.CASCADA, FabricaFuenteDatos.FICHEROS, FabricaVista.TEXTO);
        controlador.comenzar();
    }
}
