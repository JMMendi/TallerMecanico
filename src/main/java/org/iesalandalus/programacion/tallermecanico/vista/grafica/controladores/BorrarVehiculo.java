package org.iesalandalus.programacion.tallermecanico.vista.grafica.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;

public class BorrarVehiculo extends Controlador {

    private String bMatricula;

    private boolean cancelado = false;

    @FXML
    private Button btAceptar;

    @FXML
    private Button btCancelar;

    @FXML
    private TextField tfMatricula;

    void limpiar(){
        tfMatricula.clear();
    }

    public Vehiculo getVehiculoMatricula() {
        bMatricula = tfMatricula.getText();

        return Vehiculo.get(bMatricula);
    }

    boolean isCancelado() {
        return cancelado;
    }

    @FXML
    void borrarVehiculo() {
        getVehiculoMatricula();
        VistaGrafica.getInstancia().leerVehiculoMatricula();

        getEscenario().close();
    }

    @FXML
    void cancelarSalir() {
        cancelado = true;
        getEscenario().close();
    }


    @FXML
    void initialize() {

    }

}