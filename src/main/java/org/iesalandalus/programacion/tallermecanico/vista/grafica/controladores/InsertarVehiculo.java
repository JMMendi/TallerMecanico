package org.iesalandalus.programacion.tallermecanico.vista.grafica.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;

public class InsertarVehiculo extends Controlador {

    private String bMarca;
    private String bModelo;
    private String bMatricula;

    private boolean cancelado = true;

    @FXML
    private Button btAceptar;

    @FXML
    private Button btCancelar;

    @FXML
    private TextField tfMarca;

    @FXML
    private TextField tfMatricula;

    @FXML
    private TextField tfModelo;

    boolean isCancelado() {
        return cancelado;
    }

    public Vehiculo getVehiculo() {
        bMarca = tfMarca.getText();
        bModelo = tfModelo.getText();
        bMatricula = tfMatricula.getText();

        return new Vehiculo(bMarca, bModelo, bMatricula);
    }

    void limpiar() {
        tfMarca.clear();
        tfModelo.clear();
        tfMatricula.clear();
    }

    @FXML
    void cancelarSalir() {
        isCancelado();
        getEscenario().close();
    }

    @FXML
    void insertarVehiculo() {
        getVehiculo();
        cancelado = false;
        getEscenario().close();
    }

    @FXML
    void initialize() {

    }

}
