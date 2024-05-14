package org.iesalandalus.programacion.tallermecanico.vista.grafica;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controladores;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Dialogos;


public class LanzadoraVentanaPrincipal extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Controlador ventanaPrincipal = Controladores.get("/vistas/ventanaPrincipal.fxml", "Taller Mecánico", null);
        VistaGrafica.getInstancia().setVentanaPrincipal(ventanaPrincipal);
        ventanaPrincipal.getEscenario().setOnCloseRequest(e -> salir(e, ventanaPrincipal.getEscenario()));
        ventanaPrincipal.getEscenario().show();

    }

    public static void comenzar() {launch(LanzadoraVentanaPrincipal.class);}

    void salir(WindowEvent e, Stage escenario) {
        if (Dialogos.mostrarDialogoConfirmacion("Salir", "¿Estás seguro de querer salir de la aplicación?", escenario)){
            escenario.close();
            VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.SALIR);
        } else {
            e.consume();
        }
    }
}
