package org.iesalandalus.programacion.tallermecanico.vista.grafica.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;

public class InsertarCliente extends Controlador {
    private String nombre;
    private String dni;
    private String telefono;

    private boolean cancelado = false;

    @FXML
    private Button btAceptar;

    @FXML
    private Button btCancelar;

    @FXML
    private TextField tfDni;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfTelefono;

    boolean isCancelado() {
        return cancelado;
    }

    public Cliente getCliente() {
        nombre = tfNombre.getText();
        dni = tfDni.getText();
        telefono = tfTelefono.getText();

        return new Cliente(nombre, dni, telefono);
    }

    @FXML
    void aceptarInsertar() {
        getCliente();
        VistaGrafica.getInstancia().leerCliente();
        getEscenario().close();
    }

    void limpiar() {
        tfNombre.clear();
        tfDni.clear();
        tfTelefono.clear();
    }

    @FXML
    void cancelarInsertar() {
        cancelado = true;
        isCancelado();
        getEscenario().close();
    }

    @FXML
    void initialize() {
//        tfNombre.textProperty().addListener((observable, oldValue, newValue) -> aceptarInsertar());
//        tfDni.textProperty().addListener((observable, oldValue, newValue) -> aceptarInsertar());
//        tfTelefono.textProperty().addListener((observable, oldValue, newValue) -> aceptarInsertar());

    }

}
