package org.iesalandalus.programacion.tallermecanico.vista.grafica.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controladores;

import javax.naming.OperationNotSupportedException;
import java.util.Objects;

public class VentanaPrincipal extends Controlador {

    private final InsertarCliente ventanaInsertarCliente = (InsertarCliente) Controladores.get("/vistas/InsertarCliente.fxml", "Inserción de Clientes", getEscenario());
    private final BorrarCliente ventanaBorrarCliente = (BorrarCliente) Controladores.get("/vistas/BorrarCliente.fxml", "Borrado de Clientes", getEscenario());
    private final BuscarCliente ventanaBuscarCliente = (BuscarCliente) Controladores.get("/vistas/BuscarCliente.fxml", "Búsqueda de Clientes", getEscenario());

    @FXML
    private Button btBorrarCliente;

    @FXML
    private Button btBorrarTrabajos;

    @FXML
    private Button btBorrarVehiculos;

    @FXML
    private Button btInsertarCliente;

    @FXML
    private Button btInsertarTrabajos;

    @FXML
    private Button btInsertarVehiculos;

    @FXML
    private Button btMostrarCliente;

    @FXML
    private Button btMostrarTrabajo;

    @FXML
    private Button btMostrarVehiculo;

    @FXML
    private ImageView ivBorrar;

    @FXML
    private ImageView ivInsertar;

    @FXML
    private ImageView ivMostrar;

    @FXML
    private MenuItem mListaClientes;

    @FXML
    private MenuItem mListaTrabajos;

    @FXML
    private MenuItem mListaVehiculos;

    @FXML
    private MenuBar mMenu;

    @FXML
    private Menu mMenu1;

    @FXML
    private Menu mMenu2;

    @FXML
    private MenuItem mModificarClientes;

    @FXML
    private MenuItem mModificarTrabajos;

    @FXML
    private MenuItem mModificarVehiculos;

    private Image imagenLupa = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/lupa.jpg")), 100, 100, true, true);
    private Image imagenCuaderno = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/cuaderno.png")), 100, 100, true, true);
    private Image imagenBorrador = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/borrador.png")), 100, 100, true, true);

    @FXML
    void insertarCliente() throws OperationNotSupportedException {
        ventanaInsertarCliente.limpiar();
        ventanaInsertarCliente.getEscenario().showAndWait();
        if (!ventanaInsertarCliente.isCancelado()) {
            VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.INSERTAR_CLIENTE);
        }
    }

    //setOnCloseRequest =
    //WindowEvent
    @FXML
    void borrarCliente() {
        ventanaBorrarCliente.limpiar();
        ventanaBorrarCliente.getEscenario().showAndWait();
        if (!ventanaBorrarCliente.isCancelado()) {
            VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.BORRAR_CLIENTE);
        }
    }

    @FXML
    void borrarTrabajos() {

    }

    @FXML
    void borrarVehiculos() {

    }


    @FXML
    void insertarTrabajos() {

    }

    @FXML
    void insertarVehiculos() {

    }

    @FXML
    void listaClientes() {

    }

    @FXML
    void listaTrabajos() {

    }

    @FXML
    void listaVehiculos() {

    }

    @FXML
    void modificarClientes() {

    }

    @FXML
    void modificarTrabajos() {

    }

    @FXML
    void modificarVehiculos() {

    }

    @FXML
    void mostrarCliente() {
        ventanaBuscarCliente.limpiar();
        ventanaBuscarCliente.getEscenario().showAndWait();
    }

    @FXML
    void mostrarTrabajo() {

    }

    @FXML
    void mostrarVehiculo() {

    }

    @FXML
    void initialize() {
        ivInsertar.setImage(imagenCuaderno);
        ivBorrar.setImage(imagenBorrador);
        ivMostrar.setImage(imagenLupa);
    }

}
