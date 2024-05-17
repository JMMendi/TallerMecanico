package org.iesalandalus.programacion.tallermecanico.vista.grafica.controladores;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Dialogos;

public class BuscarCliente extends Controlador {

    private String bDni;
    private boolean busqueda = false;

    @FXML
    private Button btAceptar;

    @FXML
    private Button btCancelar;

    @FXML
    private Button btMostrar;

    @FXML
    private TextField tfDni;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfTelefono;

    public Cliente getCliente() {
        bDni = tfDni.getText();
        return new Cliente(Cliente.get(bDni));
    }

    boolean isBusqueda() {
        return busqueda;
    }

    @FXML
    void comprobar() {
        if (!tfDni.getText().isBlank()) {
            getCliente();
            VistaGrafica.getInstancia().leerClienteDni();
            VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.BUSCAR_CLIENTE);
            rellenar();
        } else {
            Dialogos.mostrarDialogoError("Dni Vacío", "Error, el campo de DNI está vacío", getEscenario());
        }
    }

    void rellenar() {

    }

    void limpiar() {
        tfNombre.clear();
        tfDni.clear();
        tfTelefono.clear();
    }

    @FXML
    void salir() {
        limpiar();
        getEscenario().close();
    }

    @FXML
    void initialize() {

    }

}
