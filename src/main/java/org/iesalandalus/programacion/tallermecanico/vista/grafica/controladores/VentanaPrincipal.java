package org.iesalandalus.programacion.tallermecanico.vista.grafica.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;

import javax.naming.OperationNotSupportedException;

public class VentanaPrincipal extends Controlador {

    @FXML
    private Button btInsertarCliente;

    @FXML
    void insertarCliente() throws OperationNotSupportedException {
        VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.INSERTAR_CLIENTE);

    }

}
