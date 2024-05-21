package org.iesalandalus.programacion.tallermecanico.vista.grafica.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;

import java.util.List;


public class ListarClientes extends Controlador {

    private ObservableList<Cliente> CLIENTES;

    @FXML
    private Button btSalir;

    @FXML
    private Button btMostrar;

    @FXML
    private TableColumn<Cliente, String> tcDni;

    @FXML
    private TableColumn<Cliente, String> tcNombre;

    @FXML
    private TableColumn<Cliente, String> tcTelefono;

    @FXML
    private TableView<Cliente> tvListaClientes;

    @FXML
    private void inicializarLista(){
        VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.LISTAR_CLIENTES);
    }

    public void rellenarLista(List<Cliente> listaCliente) {
        CLIENTES = FXCollections.observableArrayList(listaCliente);
        tvListaClientes.setItems(CLIENTES);
    }

    @FXML
    void salir() {
        getEscenario().close();
    }


    @FXML
    void initialize() {
        tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
        tcTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
    }

}
