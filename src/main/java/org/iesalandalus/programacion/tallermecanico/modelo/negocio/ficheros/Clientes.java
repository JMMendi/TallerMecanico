package org.iesalandalus.programacion.tallermecanico.modelo.negocio.ficheros;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IClientes;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.naming.OperationNotSupportedException;
import javax.xml.parsers.DocumentBuilder;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Clientes implements IClientes {
    private static final String FICHERO_CLIENTES = String.format("%s%s%s", "Datos", File.separator, "ficheroClientes.xml");
    private static final String RAIZ = "DATOS";
    private static final String CLIENTE = "cliente";
    private static final String NOMBRE = "nombre";
    private static final String DNI = "dni";
    private static final String TELEFONO = "telefono";

    private final List<Cliente> coleccionClientes;
    static Clientes instancia;

    public Clientes() {
        coleccionClientes = new ArrayList<>();
    }
    static Clientes getInstancia() {
        if (instancia == null) {
            instancia = new Clientes();
        }
        return instancia;
    }
    public void comenzar() {
    }
    private void procesarDocumentoXml(Document documentoXml){
        Objects.requireNonNull(documentoXml, "El documento xml de clientes no puede ser nulo.");
        UtilidadesXml.leerDocumentoXml(FICHERO_CLIENTES);
    }
    private Cliente getCliente(Element element) {
        Document documentoXml = UtilidadesXml.leerDocumentoXml(FICHERO_CLIENTES);
        String dni = null;
        String nombre = null;
        String telefono = null;
        if (documentoXml.getDocumentElement().equals(element)) {
            if (element.hasAttribute(DNI)) {
                dni = element.getAttribute(DNI);
            }
            if (element.hasAttribute(NOMBRE)) {
                nombre = element.getAttribute(NOMBRE);
            }
            if (element.hasAttribute(TELEFONO)) {
                telefono = element.getAttribute(TELEFONO);
            }
        }
        return new Cliente(nombre, dni, telefono);
    }
    public List<Cliente> get() {
        return coleccionClientes;
    }
    public void insertar(Cliente cliente) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "No se puede insertar un cliente nulo.");
        if (coleccionClientes.contains(cliente)) {
            throw new OperationNotSupportedException("Ya existe un cliente con ese DNI.");
        }
        coleccionClientes.add(cliente);
    }
    private Document crearDocumentoXml(){
        DocumentBuilder constructor = UtilidadesXml.crearConstructorDocumentoXml();
        Document documentoXml = null;
        if (constructor != null) {
            documentoXml = constructor.newDocument();
        }
        return documentoXml;
    }
    public void terminar() {

    }
    private Element getElemento(Document documentoXml, Cliente cliente) {
        Objects.requireNonNull(documentoXml, "El documento xml de clientes no puede ser nulo.");
        Objects.requireNonNull(cliente, "El cliente no puede ser nulo.");
        Element elementoCliente = documentoXml.createElement(CLIENTE);
        elementoCliente.setAttribute(NOMBRE, cliente.getNombre());
        elementoCliente.setAttribute(DNI, cliente.getDni());
        elementoCliente.setAttribute(TELEFONO, cliente.getTelefono());

        return elementoCliente;
    }
    public boolean modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "No se puede modificar un cliente nulo.");
        boolean modificacion = false;
        if (!coleccionClientes.contains(cliente)) {
            throw new OperationNotSupportedException("No existe ningún cliente con ese DNI.");
        }
        if (nombre != null && !nombre.isBlank()) {
            cliente.setNombre(nombre);
            modificacion = true;
        }
        if (telefono != null && !telefono.isBlank()) {
            cliente.setTelefono(telefono);
            modificacion = true;
        }

        return modificacion;
    }
    public Cliente buscar(Cliente cliente) {
        Objects.requireNonNull(cliente, "No se puede buscar un cliente nulo.");
        int indice = coleccionClientes.indexOf(cliente);
        return indice != -1 ? coleccionClientes.get(indice) : null;
    }
    public void borrar(Cliente cliente) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "No se puede borrar un cliente nulo.");
        int indice = coleccionClientes.indexOf(cliente);
        if (indice == -1) {
            throw new OperationNotSupportedException("No existe ningún cliente con ese DNI.");
        } else {
            coleccionClientes.remove(indice);
        }
    }
}
