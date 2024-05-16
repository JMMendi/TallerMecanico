package org.iesalandalus.programacion.tallermecanico.vista.grafica.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Dialogos;

public class BorrarCliente extends Controlador {

    private boolean cancelado = true;

    private String bDni;

    @FXML
    private Button btAceptar;

    @FXML
    private Button btCancelar;


    @FXML
    private TextField tfDni;



    public Cliente getCliente() {
        bDni = tfDni.getText();

        return Cliente.get(bDni);
    }


    @FXML
    void borrarCliente() {
        getCliente();
        VistaGrafica.getInstancia().leerClienteDni();
        cancelado = false;
        getEscenario().close();
    }

    @FXML
    void cancelarBorrado() {
        getEscenario().close();
    }

    boolean isCancelado() {
        return cancelado;
    }

    void limpiar() {
        tfDni.clear();
    }

    @FXML
    void initialize() {

    }

}
