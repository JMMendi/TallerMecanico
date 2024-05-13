package org.iesalandalus.programacion.tallermecanico.vista.grafica.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controladores;

import javax.naming.OperationNotSupportedException;

public class VentanaPrincipal extends Controlador {

    private final InsertarCliente ventanaInsertar = (InsertarCliente) Controladores.get("/vistas/InsertarCliente.fxml", "Inserción de Clientes", getEscenario());

    @FXML
    private Button btInsertarCliente;

    @FXML
    void insertarCliente() throws OperationNotSupportedException {
        VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.INSERTAR_CLIENTE);
        ventanaInsertar.getEscenario().show();

    }

}
