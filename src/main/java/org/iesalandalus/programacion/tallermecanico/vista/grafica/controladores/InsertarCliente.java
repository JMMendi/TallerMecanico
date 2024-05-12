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

    @FXML
    void aceptarInsertar() {
        nombre = tfNombre.getText();
        dni = tfDni.getText();
        telefono = tfTelefono.getText();

        Cliente cliente = new Cliente(nombre, dni, telefono);
        VistaGrafica.getInstancia().leerCliente();
        getEscenario().close();
    }

    @FXML
    void cancelarInsertar() {
        tfNombre.clear();
        tfDni.clear();
        tfTelefono.clear();
        getEscenario().close();

    }

    @FXML
    void initialize() {
//        tfNombre.textProperty().addListener((observable, oldValue, newValue) -> aceptarInsertar());
//        tfDni.textProperty().addListener((observable, oldValue, newValue) -> aceptarInsertar());
//        tfTelefono.textProperty().addListener((observable, oldValue, newValue) -> aceptarInsertar());

    }

}
