package org.iesalandalus.programacion.tallermecanico.modelo.negocio.ficheros;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.IVehiculos;
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

public class Vehiculos implements IVehiculos {
    private static final String FICHERO_VEHICULOS = String.format("%s%s%s", "Datos", File.separator, "vehiculos.xml");
    private static final String RAIZ = "vehiculos";
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
        Document vehiculosXml = UtilidadesXml.leerDocumentoXml(FICHERO_VEHICULOS);
        System.out.println("Fichero vehículos leído correctamente.");
        procesarDocumentoXml(vehiculosXml);
    }
    private void procesarDocumentoXml(Document documentoXml) {
        Objects.requireNonNull(documentoXml, "El documento xml de vehículos no puede ser nulo.");
        NodeList vehiculos = documentoXml.getElementsByTagName(VEHICULO);
        for (int i = 0 ; i < vehiculos.getLength() ; i++) {
            Element vehiculo = (Element) vehiculos.item(i);
            if (vehiculo.getNodeType() == Node.ELEMENT_NODE) {
                try {
                    insertar(getVehiculo(vehiculo));
                } catch (OperationNotSupportedException e) {
                    System.out.println("kkk");
                }
            }
        }
    }

    public List<Vehiculo> get() {
        return new ArrayList<>(coleccionVehiculos);
    }
    private Vehiculo getVehiculo(Element elemento) {
        String marca = elemento.getAttribute(MARCA);
        String modelo = elemento.getAttribute(MODELO);
        String matricula = elemento.getAttribute(MATRICULA);
        /* Document documentoXml = UtilidadesXml.leerDocumentoXml(FICHERO_VEHICULOS);
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
        } */
        return new Vehiculo(marca, modelo, matricula);
    }
    public void terminar() {
        Document vehiculoXml = crearDocumentoXml();
        if (vehiculoXml != null) {
            vehiculoXml.appendChild(vehiculoXml.createElement(RAIZ));
            for (Vehiculo vehiculo : coleccionVehiculos) {
                Element elementoVehiculo = getElement(vehiculoXml, vehiculo);
                vehiculoXml.getDocumentElement().appendChild(elementoVehiculo);
            }
        }
        UtilidadesXml.escribirDocumentoXml(vehiculoXml, FICHERO_VEHICULOS);
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
