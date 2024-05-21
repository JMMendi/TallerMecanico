package org.iesalandalus.programacion.tallermecanico.vista.grafica;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.TipoTrabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.GestorEventos;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.controladores.*;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Controladores;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.utilidades.Dialogos;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class VistaGrafica implements Vista {


    private final GestorEventos gestorEventos = new GestorEventos(Evento.values());

    private Controlador ventanaPrincipal;

    private static VistaGrafica instancia;

    private VistaGrafica() {}

    public static VistaGrafica getInstancia() {
        if (instancia == null) {
            instancia = new VistaGrafica();
        }
        return instancia;
    }

    void setVentanaPrincipal(Controlador ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
    }


    @Override
    public Cliente leerCliente() {
        InsertarCliente ventanaInsertar = (InsertarCliente) Controladores.get("/vistas/InsertarCliente.fxml", "Inserci�n de Clientes", ventanaPrincipal.getEscenario());
        return ventanaInsertar.getCliente();
    }

    @Override
    public Cliente leerClienteDni() {
        BorrarCliente ventanaBorrar = (BorrarCliente) Controladores.get("/vistas/BorrarCliente.fxml", "Borrado de Clientes", ventanaPrincipal.getEscenario());
        BuscarCliente ventanaBuscarCliente = (BuscarCliente) Controladores.get("/vistas/BuscarCliente.fxml", "B�squeda de Clientes", ventanaPrincipal.getEscenario());
        InsertarTrabajo ventanaInsertarTrabajo = (InsertarTrabajo) Controladores.get("/vistas/InsertarTrabajo.fxml", "Inserci�n de Trabajos", ventanaPrincipal.getEscenario());
        Cliente cliente;

        if (ventanaBuscarCliente.getEscenario().isShowing()) {
            cliente = ventanaBuscarCliente.getCliente();
        } else if (ventanaBorrar.getEscenario().isShowing()){
            cliente = ventanaBorrar.getCliente();
        } else {
            cliente = ventanaInsertarTrabajo.getCliente();
        }

        return cliente;
    }

    @Override
    public String leerNuevoNombre() {
        return "";
    }

    @Override
    public String leerNuevoTelefono() {
        return "";
    }

    @Override
    public Vehiculo leerVehiculo() {
        InsertarVehiculo ventanaInsertarVehiculo = (InsertarVehiculo) Controladores.get("/vistas/InsertarVehiculo.fxml", "Inserci�n de Veh�culos", ventanaPrincipal.getEscenario());
        return ventanaInsertarVehiculo.getVehiculo();
    }

    @Override
    public Vehiculo leerVehiculoMatricula() {
        BorrarVehiculo ventanaBorrarVehiculo = (BorrarVehiculo) Controladores.get("/vistas/BorrarVehiculo.fxml", "Borrado de Veh�culos", ventanaPrincipal.getEscenario());
        BuscarVehiculo ventanaBuscarVehiculo = (BuscarVehiculo) Controladores.get("/vistas/BuscarVehiculo.fxml", "B�squeda de Veh�culos", ventanaPrincipal.getEscenario());
        InsertarTrabajo ventanaInsertarTrabajo = (InsertarTrabajo) Controladores.get("/vistas/InsertarTrabajo.fxml", "Inserci�n de Trabajos", ventanaPrincipal.getEscenario());

        Vehiculo vehiculo = null;
        if (ventanaBorrarVehiculo.getEscenario().isShowing()) {
            vehiculo = ventanaBorrarVehiculo.getVehiculoMatricula();
        } else if (ventanaBuscarVehiculo.getEscenario().isShowing()){
            vehiculo = ventanaBuscarVehiculo.getVehiculoMatricula();
        } else if (ventanaInsertarTrabajo.getEscenario().isShowing()) {
            vehiculo = ventanaInsertarTrabajo.getVehiculoMatricula();
        }

        return vehiculo;
    }

    @Override
    public Trabajo leerRevision() {
        return null;
    }

    @Override
    public Trabajo leerMecanico() {
        return null;
    }

    @Override
    public int leerHoras() {
        return 0;
    }

    @Override
    public float leerPrecioMaterial() {
        return 0;
    }

    @Override
    public LocalDate leerFechaCierre() {
        return null;
    }

    @Override
    public Trabajo leerTrabajoVehiculo() {
        return null;
    }

    @Override
    public LocalDate leerMes() {
        return null;
    }

    @Override
    public GestorEventos getGestorEventos() {
        return gestorEventos;
    }

    @Override
    public void comenzar() throws OperationNotSupportedException {
        LanzadoraVentanaPrincipal.comenzar();
    }

    @Override
    public void terminar() {
        if (Dialogos.mostrarDialogoConfirmacion("Salir", "?Est?s seguro de querer salir de la aplicaci?n?", ventanaPrincipal.getEscenario())){
            ventanaPrincipal.getEscenario().close();
            VistaGrafica.getInstancia().getGestorEventos().notificar(Evento.SALIR);
        }
    }

    @Override
    public void notificarResultado(Evento evento, String texto, boolean exito)  {
        if (exito) {
            Dialogos.mostrarDialogoInformacion(evento.toString(), texto, ventanaPrincipal.getEscenario());
        } else {
            Dialogos.mostrarDialogoError(evento.toString(), texto, ventanaPrincipal.getEscenario());
        }
    }

    @Override
    public void mostrarCliente(Cliente cliente) {
        BuscarCliente ventanaBuscarCliente = (BuscarCliente) Controladores.get("/vistas/BuscarCliente.fxml", "B�squeda de Clientes", ventanaPrincipal.getEscenario());
        InsertarTrabajo ventanaInsertarTrabajo = (InsertarTrabajo) Controladores.get("/vistas/InsertarTrabajo.fxml", "Inserci�n de Trabajos", ventanaPrincipal.getEscenario());

        if (ventanaBuscarCliente.getEscenario().isShowing()) {
            ventanaBuscarCliente.actualizar(cliente);
        } else if (ventanaInsertarTrabajo.getEscenario().isShowing()) {
            ventanaInsertarTrabajo.actualizar(cliente);
        }

    }

    @Override
    public void mostrarVehiculo(Vehiculo vehiculo) {
        BuscarVehiculo ventanaBuscarVehiculo = (BuscarVehiculo) Controladores.get("/vistas/BuscarVehiculo.fxml", "B�squeda de Veh�culos", ventanaPrincipal.getEscenario());
        InsertarTrabajo ventanaInsertarTrabajo = (InsertarTrabajo) Controladores.get("/vistas/InsertarTrabajo.fxml", "Inserci�n de Trabajos", ventanaPrincipal.getEscenario());

        if (ventanaBuscarVehiculo.getEscenario().isShowing()) {
            ventanaBuscarVehiculo.actualizar(vehiculo);
        } else if (ventanaInsertarTrabajo.getEscenario().isShowing()) {
            ventanaInsertarTrabajo.actualizar(vehiculo);
        }
    }

    @Override
    public void mostrarTrabajo(Trabajo trabajo) {

    }

    @Override
    public void mostrarClientes(List<Cliente> clientes) {
        ListarClientes ventanaListarClientes = (ListarClientes) Controladores.get("/vistas/ListarClientes.fxml", "Listado de Clientes", ventanaPrincipal.getEscenario());
        ventanaListarClientes.rellenarLista(clientes);
    }

    @Override
    public void mostrarVehiculos(List<Vehiculo> vehiculos) {

    }

    @Override
    public void mostrarTrabajos(List<Trabajo> trabajos) {

    }

    @Override
    public void mostrarTrabajosCliente(List<Trabajo> trabajosCliente) {

    }

    @Override
    public void mostrarTrabajosVehiculo(List<Trabajo> trabajosVehiculo) {

    }

    @Override
    public void mostrarEstadisticasMensuales(Map<TipoTrabajo, Integer> estadistica) {

    }
}
