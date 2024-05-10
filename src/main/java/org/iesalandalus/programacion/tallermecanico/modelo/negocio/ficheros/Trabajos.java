package org.iesalandalus.programacion.tallermecanico.modelo.negocio.ficheros;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.ITrabajos;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.naming.OperationNotSupportedException;
import javax.xml.parsers.DocumentBuilder;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Trabajos implements ITrabajos {
    private static String FICHERO_TRABAJOS = String.format("%s%s%s", "Datos", File.separator, "trabajos.xml");
    private static DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static String RAIZ = "trabajos";
    private static String TRABAJO = "trabajo";
    private static String CLIENTE = "cliente";
    private static String VEHICULO = "vehiculo";
    private static String FECHA_INICIO = "fechaInicio";
    private static String FECHA_FIN = "fechaFin";
    private static String HORAS = "horas";
    private static String PRECIO_MATERIAL = "precio material";
    private static String TIPO = "tipo";
    private static String REVISION = "revision";
    private static String MECANICO = "mecanico";


    private static Trabajos instancia;
    private List<Trabajo> coleccionTrabajos;

    private Trabajos() {
        coleccionTrabajos = new ArrayList<>();
    }


    static Trabajos getInstancia() {
        if (instancia == null) {
            instancia = new Trabajos();
        }
        return instancia;
    }

    public void comenzar() {
        Document trabajoXml = UtilidadesXml.leerDocumentoXml(FICHERO_TRABAJOS);
        procesarDocumentoXML(trabajoXml);
        System.out.println("El fichero de trabajos se ha leído correctamente.");
    }

    @Override
    public void terminar() {
        Document trabajoXML = crearDocumentoXml();
        if (trabajoXML != null) {
            trabajoXML.appendChild(trabajoXML.createElement(RAIZ));
            for (Trabajo trabajo : coleccionTrabajos) {
                Element elementoTrabajo = getElemento(trabajoXML, trabajo);
                trabajoXML.getDocumentElement().appendChild(elementoTrabajo);
            }
        }
        UtilidadesXml.escribirDocumentoXml(trabajoXML, FICHERO_TRABAJOS);
    }

    private void procesarDocumentoXML(Document documentoXML) {
        Objects.requireNonNull(documentoXML, "El documento XML no puede ser nulo.");
        NodeList trabajos = documentoXML.getElementsByTagName(TRABAJO);
        for (int i = 0; i < trabajos.getLength(); i++) {
            Element elementoTrabajo = (Element) trabajos.item(i);
            if (elementoTrabajo.getNodeType() == Node.ELEMENT_NODE) {
                try {
                    insertar(getTrabajo(elementoTrabajo));
                } catch (OperationNotSupportedException | NullPointerException e) {
                    System.out.println("Error en el trabajo número: " + i + " : " + e.getMessage());
                }
            }
        }
    }

    private Trabajo getTrabajo(Element element) throws OperationNotSupportedException {
        Trabajo elementoTrabajo = null;
        Cliente cliente = Clientes.getInstancia().buscar(Cliente.get(element.getAttribute(CLIENTE)));
        Vehiculo vehiculo = Vehiculos.getInstancia().buscar(Vehiculo.get(element.getAttribute(VEHICULO)));
        LocalDate fechaInicio = LocalDate.parse(element.getAttribute(FECHA_INICIO), FORMATO_FECHA);
        LocalDate fechaFin;
        int horas;
        float precioMaterial;
        if (element.hasAttribute(HORAS)) {
            horas = Integer.parseInt(element.getAttribute(HORAS));
            if (element.getAttribute(TIPO).equals(REVISION)) {
                elementoTrabajo = new Revision(cliente, vehiculo, fechaInicio);
                elementoTrabajo.anadirHoras(horas);
                if (element.hasAttribute(FECHA_FIN)) {
                    fechaFin = LocalDate.parse(element.getAttribute(FECHA_FIN), FORMATO_FECHA);
                    elementoTrabajo.cerrar(fechaFin);
                }
            } else if (element.getAttribute(TIPO).equals(MECANICO)) {
                elementoTrabajo = new Mecanico(cliente, vehiculo, fechaInicio);
                elementoTrabajo.anadirHoras(horas);
                if (element.hasAttribute(PRECIO_MATERIAL)) {
                    precioMaterial = Float.parseFloat(element.getAttribute(PRECIO_MATERIAL));
                    ((Mecanico) elementoTrabajo).anadirPrecioMaterial(precioMaterial);

                }
                if (element.hasAttribute(FECHA_FIN)) {
                    fechaFin = LocalDate.parse(element.getAttribute(FECHA_FIN), FORMATO_FECHA);
                    elementoTrabajo.cerrar(fechaFin);
                }
            }
        }



        /* Document documentoXml = UtilidadesXml.leerDocumentoXml(FICHERO_TRABAJOS);
        Trabajo elementoTrabajo = null;
        Cliente cliente = null;
        Vehiculo vehiculo = null;
        LocalDate fechaInicio = null;
        LocalDate fechaFin = null;
        int horas = 0;
        float precioMaterial = 0;
        if (documentoXml.getDocumentElement().equals(element)) {
            cliente = Cliente.get(element.getAttribute(CLIENTE));
            vehiculo = Vehiculo.get(element.getAttribute(VEHICULO));
            fechaInicio = LocalDate.parse(element.getAttribute(FECHA_INICIO), FORMATO_FECHA);
            if (element.getAttribute(TIPO).equals(REVISION)) {
                elementoTrabajo = new Revision(cliente, vehiculo, fechaInicio);
            } else {
                elementoTrabajo = new Mecanico(cliente, vehiculo, fechaInicio);
            }
            if (element.hasAttribute(PRECIO_MATERIAL) && elementoTrabajo instanceof Mecanico) {
                precioMaterial = Float.parseFloat(element.getAttribute(PRECIO_MATERIAL));
                ((Mecanico) elementoTrabajo).anadirPrecioMaterial(precioMaterial);
            }
            if (element.hasAttribute(HORAS)) {
                horas = Integer.parseInt(element.getAttribute(HORAS));
                elementoTrabajo.anadirHoras(horas);
            }
            if (element.hasAttribute(FECHA_FIN)) {
                fechaFin = LocalDate.parse(element.getAttribute(FECHA_FIN), FORMATO_FECHA);
                elementoTrabajo.cerrar(fechaFin);
            }
        } */
        return elementoTrabajo;
    }

    private Document crearDocumentoXml() {
        DocumentBuilder constructor = UtilidadesXml.crearConstructorDocumentoXml();
        Document documentoXml = null;
        if (constructor != null) {
            documentoXml = constructor.newDocument();
        }
        return documentoXml;
    }

    private Element getElemento(Document documentoXml, Trabajo trabajo) {
        Objects.requireNonNull(documentoXml, "El documento xml no puede ser nulo.");
        Objects.requireNonNull(trabajo, "El trabajo no puede ser nulo.");
        Element elementoTrabajo = documentoXml.createElement("trabajo");
        elementoTrabajo.setAttribute(CLIENTE, trabajo.getCliente().getDni());
        elementoTrabajo.setAttribute(VEHICULO, trabajo.getVehiculo().matricula());
        elementoTrabajo.setAttribute(FECHA_INICIO, trabajo.getFechaInicio().format(FORMATO_FECHA));
        if (trabajo.getHoras() != 0) {
            elementoTrabajo.setAttribute(HORAS, Integer.toString(trabajo.getHoras()));
        }
        if (trabajo.estaCerrado()) {
            elementoTrabajo.setAttribute(FECHA_FIN, trabajo.getFechaFin().format(FORMATO_FECHA));
        }
        if (TipoTrabajo.get(trabajo) != null) {
            elementoTrabajo.setAttribute(TIPO, TipoTrabajo.get(trabajo).toString());
        }
        if (trabajo instanceof Mecanico mecanico) {
            if (mecanico.getPrecioMaterial() != 0) {
                elementoTrabajo.setAttribute(PRECIO_MATERIAL, String.valueOf(mecanico.getPrecioMaterial()).replace(',', '.'));
            }
        }
        return elementoTrabajo;
    }

    public Map<TipoTrabajo, Integer> getEstadisticasMensuales(LocalDate mes) {
        Objects.requireNonNull(mes, "El mes no puede ser nulo.");
        Map<TipoTrabajo, Integer> estadisticaMensual = inicializarEstadistica();
        int trabajosMecanicos = 0;
        int trabajosRevisiones = 0;
        for (Trabajo trabajo : coleccionTrabajos) {
            if (trabajo.getFechaInicio().getMonth().equals(mes.getMonth())) {
                if (trabajo instanceof Mecanico) {
                    trabajosMecanicos++;
                } else if (trabajo instanceof Revision) {
                    trabajosRevisiones++;
                }
            }
        }
        estadisticaMensual.replace(TipoTrabajo.MECANICO, trabajosMecanicos);
        estadisticaMensual.replace(TipoTrabajo.REVISION, trabajosRevisiones);
        return estadisticaMensual;
    }

    private Map<TipoTrabajo, Integer> inicializarEstadistica() {
        Map<TipoTrabajo, Integer> estadisticas = new HashMap<>();

        estadisticas.put(TipoTrabajo.MECANICO, 0);
        estadisticas.put(TipoTrabajo.REVISION, 0);
        return estadisticas;
    }

    @Override
    public List<Trabajo> get() {
        return new ArrayList<>(coleccionTrabajos);
    }

    @Override
    public List<Trabajo> get(Cliente cliente) {
        List<Trabajo> revisionesClientes = new ArrayList<>();
        for (Trabajo trabajo : coleccionTrabajos) {
            if (trabajo.getCliente().equals(cliente)) {
                revisionesClientes.add(trabajo);
            }
        }
        return revisionesClientes;
    }

    @Override
    public List<Trabajo> get(Vehiculo vehiculo) {
        List<Trabajo> vehiculos = new ArrayList<>();
        for (Trabajo trabajo : coleccionTrabajos) {
            if (trabajo.getVehiculo().equals(vehiculo)) {
                vehiculos.add(trabajo);
            }
        }
        return vehiculos;
    }

    @Override
    public void insertar(Trabajo trabajo) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "No se puede insertar un trabajo nulo.");
        comprobarTrabajo(trabajo.getCliente(), trabajo.getVehiculo(), trabajo.getFechaInicio());
        coleccionTrabajos.add(trabajo);
    }

    private void comprobarTrabajo(Cliente cliente, Vehiculo vehiculo, LocalDate fechaTrabajo) throws OperationNotSupportedException {
        for (Trabajo trabajo : coleccionTrabajos) {
            if (trabajo.getCliente().equals(cliente)) {
                if (!trabajo.estaCerrado()) {
                    throw new OperationNotSupportedException("El cliente tiene otro trabajo en curso.");
                }
                if (trabajo.estaCerrado() && (!fechaTrabajo.isAfter(trabajo.getFechaFin()))) {
                    throw new OperationNotSupportedException("El cliente tiene otro trabajo posterior.");
                }
            }
            if (trabajo.getVehiculo().equals(vehiculo)) {
                if (!trabajo.estaCerrado()) {
                    throw new OperationNotSupportedException("El veh�culo est� actualmente en el taller.");
                }
                if (trabajo.estaCerrado() && (!fechaTrabajo.isAfter(trabajo.getFechaFin()))) {
                    throw new OperationNotSupportedException("El veh�culo tiene otro trabajo posterior.");
                }
            }
        }
    }

    private Trabajo getTrabajo(Trabajo trabajo) {
        Objects.requireNonNull(trabajo, "La revisi�n no puede ser nula.");
        int indice = coleccionTrabajos.indexOf(trabajo);
        return indice == -1 ? null : coleccionTrabajos.get(indice);
    }

    private Trabajo getTrabajoAbierto(Vehiculo vehiculo) throws OperationNotSupportedException {
        Objects.requireNonNull(vehiculo, "El veh�culo no puede ser nulo.");
        Trabajo trabajoEncontrado = null;
        Iterator<Trabajo> iteradorTrabajos = coleccionTrabajos.iterator();
        while (iteradorTrabajos.hasNext() && trabajoEncontrado == null) {
            Trabajo trabajo = iteradorTrabajos.next();
            if (trabajo.getVehiculo().equals(vehiculo) && !trabajo.estaCerrado()) {
                trabajoEncontrado = trabajo;
            }
        }
        if (trabajoEncontrado == null) {
            throw new OperationNotSupportedException("No existe ning�n trabajo abierto para dicho veh�culo.");
        }
        return trabajoEncontrado;
    }

    @Override
    public void anadirHoras(Trabajo trabajo, int horas) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "No puedo a�adir horas a un trabajo nulo.");
        Trabajo trabajoEncontrado = getTrabajoAbierto(trabajo.getVehiculo());
        trabajoEncontrado.anadirHoras(horas);
    }

    @Override
    public void anadirPrecioMaterial(Trabajo trabajo, float precioMaterial) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "No puedo a�adir precio del material a un trabajo nulo.");
        if (getTrabajoAbierto(trabajo.getVehiculo()) == null) {
            throw new OperationNotSupportedException("No existe ning�n trabajo abierto para dicho veh�culo.");
        }
        if (trabajo instanceof Revision) {
            throw new OperationNotSupportedException("No se puede a�adir precio al material para este tipo de trabajos.");
        }
        ((Mecanico) trabajo).anadirPrecioMaterial(precioMaterial);
    }

    @Override
    public void cerrar(Trabajo trabajo, LocalDate fechaFin) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "No puedo cerrar un trabajo nulo.");
        if (getTrabajoAbierto(trabajo.getVehiculo()) == null) {
            throw new OperationNotSupportedException("No existe ning�n trabajo abierto para dicho veh�culo.");
        }
        trabajo.cerrar(fechaFin);
    }

    @Override
    public Trabajo buscar(Trabajo trabajo) {
        Objects.requireNonNull(trabajo, "No se puede buscar un trabajo nulo.");
        int indice = coleccionTrabajos.indexOf(trabajo);
        return (indice == -1) ? null : coleccionTrabajos.get(indice);
    }

    @Override
    public void borrar(Trabajo trabajo) throws OperationNotSupportedException {
        Objects.requireNonNull(trabajo, "No se puede borrar un trabajo nulo.");
        if (!coleccionTrabajos.contains(trabajo)) {
            throw new OperationNotSupportedException("No existe ning�n trabajo igual.");
        }
        coleccionTrabajos.remove(trabajo);
    }
}
