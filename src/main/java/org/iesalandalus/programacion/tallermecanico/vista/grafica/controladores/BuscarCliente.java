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

    @FXML
    void mostrar() {
        VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.BUSCAR_CLIENTE);
    }

    public void actualizar(Cliente cliente) {
        tfNombre.setText(cliente.getNombre());
        tfTelefono.setText(cliente.getTelefono());
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
