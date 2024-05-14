package org.iesalandalus.programacion.tallermecanico.vista.grafica.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Dialogos;

public class BorrarCliente extends Controlador {

    private boolean cancelado = true;
    private boolean vacio = true;

    private String bNombre;
    private String bDni;
    private String bTelefono;

    @FXML
    private Button btBuscar;

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

    private Cliente getCliente(String dni) {
        Cliente.get(dni);
        bDni = dni;
        return Cliente.get(dni);
    }

    private void ponerNombreYTelefono() {
        Cliente cliente = getCliente(bDni);
        tfNombre.setText(cliente.getNombre());
        tfTelefono.setText(cliente.getTelefono());
    }

    @FXML
    void buscarCliente() {
        ponerNombreYTelefono();
        vacio = false;
    }

    @FXML
    void borrarCliente() {
        if (!vacio) {
            cancelado = false;
            getEscenario().close();
        } else {
            Dialogos.mostrarDialogoError("No hay Cliente", "Error, no ha introducido ningún cliente a borrar.", getEscenario());
        }
    }

    @FXML
    void cancelarBorrado() {
        getEscenario().close();
    }

    boolean isCancelado() {
        return cancelado;
    }

    void limpiar() {
        tfNombre.clear();
        tfTelefono.clear();
        tfDni.clear();
    }

    @FXML
    void initialize() {

    }

}
