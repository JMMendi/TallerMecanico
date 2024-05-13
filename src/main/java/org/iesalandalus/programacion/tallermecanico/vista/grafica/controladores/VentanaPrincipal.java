package org.iesalandalus.programacion.tallermecanico.vista.grafica.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controladores;

import javax.naming.OperationNotSupportedException;
import java.util.Objects;

public class VentanaPrincipal extends Controlador {

    private final InsertarCliente ventanaInsertar = (InsertarCliente) Controladores.get("/vistas/InsertarCliente.fxml", "Inserción de Clientes", getEscenario());

    @FXML
    private Button btBorrarCliente;

    @FXML
    private Button btInsertarCliente;

    @FXML
    private Button btMostrarCliente;

    @FXML
    private ImageView ivBorrar;

    @FXML
    private ImageView ivInsertar;

    @FXML
    private ImageView ivMostrar;

    private Image imagenCuaderno = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/cuaderno.png")), 100, 100, true, true);
    private Image imagenBorrador = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/borrador.png")), 100, 100, true, true);

    @FXML
    void insertarCliente() throws OperationNotSupportedException {
        ventanaInsertar.limpiar();
        ventanaInsertar.getEscenario().showAndWait();
        if (!ventanaInsertar.isCancelado()) {
            VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.INSERTAR_CLIENTE);
        }
    }

    @FXML
    void borrarCliente() {

    }

    @FXML
    void mostrarCliente() {

    }

    @FXML
    void initialize() {
        ivInsertar.setImage(imagenCuaderno);
        ivBorrar.setImage(imagenBorrador);
    }

}
