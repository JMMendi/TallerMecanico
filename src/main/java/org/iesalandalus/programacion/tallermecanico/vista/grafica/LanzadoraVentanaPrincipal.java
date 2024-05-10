package org.iesalandalus.programacion.tallermecanico.vista.grafica;

import javafx.application.Application;
import javafx.stage.Stage;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controladores;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;


public class LanzadoraVentanaPrincipal extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Controlador ventanaPrincipal = Controladores.get("/vistas/ventanaPrincipal.fxml", "Taller Mecánico", null);
        VistaGrafica.getInstancia().setVentanaPrincipal(ventanaPrincipal);

        ventanaPrincipal.getEscenario().show();

    }

    public static void comenzar() {launch(LanzadoraVentanaPrincipal.class);}
}
