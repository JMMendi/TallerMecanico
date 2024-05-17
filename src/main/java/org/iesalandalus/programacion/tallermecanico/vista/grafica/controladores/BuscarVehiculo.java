package org.iesalandalus.programacion.tallermecanico.vista.grafica.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;

public class BuscarVehiculo extends Controlador {

    private String bMatricula;

    @FXML
    private Button bMostrar;

    @FXML
    private Button btAceptar;

    @FXML
    private TextField tfMarca;

    @FXML
    private TextField tfMatricula;

    @FXML
    private TextField tfModelo;

    public Vehiculo getVehiculoMatricula() {
        bMatricula = tfMatricula.getText();

        return Vehiculo.get(bMatricula);
    }

    void limpiar() {
        tfMarca.clear();
        tfModelo.clear();
        tfMatricula.clear();
    }

    @FXML
    void mostrarVehiculo() {
        VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.BUSCAR_VEHICULO);
    }

    public void actualizar(Vehiculo vehiculo) {
        tfModelo.setText(vehiculo.modelo());
        tfMarca.setText(vehiculo.marca());
    }

    @FXML
    void salir() {
        getEscenario().close();
    }

    @FXML
    void initialize() {

    }

}
