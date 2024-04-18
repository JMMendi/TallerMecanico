package org.iesalandalus.programacion.tallermecanico.modelo.negocio.ficheros;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IVehiculos;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.naming.OperationNotSupportedException;
import javax.xml.parsers.DocumentBuilder;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vehiculos implements IVehiculos {
    private static final String FICHERO_VEHICULOS = "vehiculos.xml";
    private static final String RAIZ = "Datos";
    private static final String VEHICULO = "vehiculo";
    private static final String MARCA = "marca";
    private static final String MODELO = "modelo";
    private static final String MATRICULA = "matricula";

    static Vehiculos instancia;
    private List<Vehiculo> coleccionVehiculos;

    private Vehiculos() {
        coleccionVehiculos = new ArrayList<>();
    }
    static Vehiculos getInstancia() {
        if (instancia == null) {
            instancia = new Vehiculos();
        }
        return instancia;
    }
    public void comenzar() {

    }
    private void procesarDocumentoXml(Document documentoXml) {
        Objects.requireNonNull(documentoXml, "El documento xml de vehículos no puede ser nulo.");
        UtilidadesXml.leerDocumentoXml(RAIZ + File.separator + FICHERO_VEHICULOS);
    }

    public List<Vehiculo> get() {
        return new ArrayList<>(coleccionVehiculos);
    }
    private Vehiculo getVehiculo(Element elemento) {
        Document documentoXml = UtilidadesXml.leerDocumentoXml(RAIZ + File.separator + FICHERO_VEHICULOS);
        String marca = null;
        String modelo = null;
        String matricula = null;

        if (documentoXml.getDocumentElement().equals(elemento)) {
            if (elemento.hasAttribute(MARCA)) {
                marca = elemento.getAttribute(MARCA);
            }
            if (elemento.hasAttribute(MODELO)) {
                modelo = elemento.getAttribute(MODELO);
            }
            if (elemento.hasAttribute(MATRICULA)) {
                matricula = elemento.getAttribute(MATRICULA);
            }
        }
        return new Vehiculo(marca, modelo, matricula);
    }
    public void terminar() {

    }
    private Document crearDocumentoXml() {
        DocumentBuilder constructor = UtilidadesXml.crearConstructorDocumentoXml();
        Document documentoXml = null;
        if (constructor != null) {
            documentoXml = constructor.newDocument();
        }
        return documentoXml;
    }
    private Element getElement(Document documentoXml, Vehiculo vehiculo) {
        Objects.requireNonNull(documentoXml, "El documento xml de vehículos no puede ser nulo.");
        Objects.requireNonNull(vehiculo, "El vehículo no puede ser nulo.");
        Element elementoVehiculo = documentoXml.createElement(VEHICULO);
        elementoVehiculo.setAttribute(MARCA, vehiculo.marca());
        elementoVehiculo.setAttribute(MODELO, vehiculo.modelo());
        elementoVehiculo.setAttribute(MATRICULA, vehiculo.matricula());

        return elementoVehiculo;
    }

    public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
        Objects.requireNonNull(vehiculo, "No se puede insertar un vehículo nulo.");
        if (coleccionVehiculos.contains(vehiculo)) {
            throw new OperationNotSupportedException("Ya existe un vehículo con esa matrícula.");
        }
        coleccionVehiculos.add(vehiculo);
    }
    public Vehiculo buscar(Vehiculo vehiculo) {
        Objects.requireNonNull(vehiculo, "No se puede buscar un vehículo nulo.");
        int indice = coleccionVehiculos.indexOf(vehiculo);
        return indice == -1 ? null : coleccionVehiculos.get(indice);
    }
    public void borrar(Vehiculo vehiculo) throws OperationNotSupportedException {
        Objects.requireNonNull(vehiculo, "No se puede borrar un vehículo nulo.");
        if (!coleccionVehiculos.contains(vehiculo)) {
            throw new OperationNotSupportedException("No existe ningún vehículo con esa matrícula.");
        }
        coleccionVehiculos.remove(vehiculo);
    }
}
