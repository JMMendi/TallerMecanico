package org.iesalandalus.programacion.tallermecanico.vista.grafica.controladores;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.TipoTrabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;

public class InsertarTrabajo extends Controlador {

    private String bDni;
    private String bMatricula;
    private boolean cancelado = true;
    private boolean tipoTrabajo;


    @FXML
    private Button btAceptar;

    @FXML
    private Button btCancelar;

    @FXML
    private ChoiceBox<TipoTrabajo> cbTipoTrabajo;

    @FXML
    private DatePicker dpFechaInicio;

    @FXML
    private TextField tfDni;

    @FXML
    private TextField tfMarca;

    @FXML
    private TextField tfMatricula;

    @FXML
    private TextField tfModelo;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfTelefono;

    public Cliente getCliente() {
        bDni = tfDni.getText();
        return new Cliente(Cliente.get(bDni));
    }

    @FXML
    void mostrarCliente(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER) {
            VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.BUSCAR_CLIENTE);
        }
    }

    public void actualizar(Cliente cliente) {
        tfNombre.setText(cliente.getNombre());
        tfTelefono.setText(cliente.getTelefono());
    }

    public Vehiculo getVehiculoMatricula() {
        bMatricula = tfMatricula.getText();

        return Vehiculo.get(bMatricula);
    }

    @FXML
    void mostrarVehiculo(KeyEvent e) {
        if (e.getCode() == KeyCode.A) {
            System.out.println("Se ha presionado el enter.");
            VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.BUSCAR_CLIENTE);
        }
    }

    public void actualizar(Vehiculo vehiculo) {
        tfModelo.setText(vehiculo.modelo());
        tfMarca.setText(vehiculo.marca());
    }

    void limpiar() {
        tfNombre.clear();
        tfDni.clear();
        tfTelefono.clear();

        tfMatricula.clear();
        tfMarca.clear();
        tfModelo.clear();
    }

    boolean isCancelado() {
        return cancelado;
    }

    boolean isTipoTrabajo(){
        if (cbTipoTrabajo.getSelectionModel().isSelected(0)) {
            tipoTrabajo = true;
        } else if (cbTipoTrabajo.getSelectionModel().isSelected(1)) {
            tipoTrabajo = false;
        }
        return tipoTrabajo;
    }


    @FXML
    void cancelarSalir() {
        getEscenario().close();
    }

    @FXML
    void insertarTrabajo() {
        cancelado = false;
    }

    @FXML
    void initialize() {
        cbTipoTrabajo.setItems(FXCollections.observableArrayList(TipoTrabajo.MECANICO, TipoTrabajo.REVISION));
        cbTipoTrabajo.getSelectionModel().isSelected(0);


    }

}
