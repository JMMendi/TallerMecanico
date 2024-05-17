package org.iesalandalus.programacion.tallermecanico.vista.grafica;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.TipoTrabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Trabajo;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.GestorEventos;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.controladores.BorrarCliente;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.controladores.BuscarCliente;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.controladores.InsertarCliente;
import org.iesalandalus.programacion.tallermecanico.vista.grafica.controladores.InsertarVehiculo;
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
        InsertarCliente ventanaInsertar = (InsertarCliente) Controladores.get("/vistas/InsertarCliente.fxml", "Inserción de Clientes", ventanaPrincipal.getEscenario());
        return ventanaInsertar.getCliente();
    }

    @Override
    public Cliente leerClienteDni() {
        BorrarCliente ventanaBorrar = (BorrarCliente) Controladores.get("/vistas/BorrarCliente.fxml", "Borrado de Clientes", ventanaPrincipal.getEscenario());
        BuscarCliente ventanaBuscarCliente = (BuscarCliente) Controladores.get("/vistas/BuscarCliente.fxml", "Búsqueda de Clientes", ventanaPrincipal.getEscenario());
        Cliente cliente;

        if (ventanaBorrar.getCliente().getDni().isBlank()) {
            cliente = ventanaBuscarCliente.getCliente();
        } else {
            cliente = ventanaBorrar.getCliente();
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
        InsertarVehiculo ventanaInsertarVehiculo = (InsertarVehiculo) Controladores.get("/vistas/InsertarVehiculo.fxml", "Inserción de Vehículos", ventanaPrincipal.getEscenario());
        return ventanaInsertarVehiculo.getVehiculo();
    }

    @Override
    public Vehiculo leerVehiculoMatricula() {
        return null;
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

    }

    @Override
    public void mostrarVehiculo(Vehiculo vehiculo) {

    }

    @Override
    public void mostrarTrabajo(Trabajo trabajo) {

    }

    @Override
    public void mostrarClientes(List<Cliente> clientes) {

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
