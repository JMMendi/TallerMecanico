package org.iesalandalus.programacion.tallermecanico.vista.eventos;

import java.util.HashMap;
import java.util.Map;

public enum Evento {
    INSERTAR_CLIENTE(1,"Insertar Cliente"),
    BUSCAR_CLIENTE(2,"Buscar Cliente"),
    BORRAR_CLIENTE(3,"Borrar Cliente"),
    LISTAR_CLIENTES(4,"Listar Clientes"),
    MODIFICAR_CLIENTE(5,"Modificar Cliente"),
    INSERTAR_VEHICULO(6, "Insertar Vehículo"),
    BUSCAR_VEHICULO(7,"Buscar Vehículo"),
    BORRAR_VEHICULO(8,"Borrar Vehículo"),
    LISTAR_VEHICULOS(9,"Listar Vehículos"),
    INSERTAR_REVISION(10,"Insertar Revisión"),
    INSERTAR_MECANICO(11, "Insertar Trabajo Mecánico"),
    BUSCAR_TRABAJO(12,"Buscar Trabajo"),
    BORRAR_TRABAJO(13,"Borrar Trabajo"),
    LISTAR_TRABAJOS(14,"Listar Trabajos"),
    LISTAR_TRABAJOS_CLIENTE(15,"Listar Trabajos del Cliente"),
    LISTAR_TRABAJOS_VEHICULO(16,"Listar Trabajos del Vehículo"),
    ANADIR_HORAS_TRABAJO(17,"Añadir Horas al Trabajo"),
    ANADIR_PRECIO_MATERIAL_TRABAJO(18,"Añadir Precio de Material al Trabajo"),
    CERRAR_TRABAJO(19,"Cerrar Trabajo"),
    SALIR(20,"Salir");
    private String texto;
    private int codigo;
    private static Map<Integer, Evento> opciones = new HashMap<>();
    private Evento(int codigo, String texto) {
        this.texto = texto;
        this.codigo = codigo;
    }
    public static boolean esValida(int codigo) {
        return codigo >= 1 && codigo <= 19;
    }
    public static Evento get(int codigo) {
        if (!esValida(codigo)) {
            throw new IllegalArgumentException("No es un número de opción válido. Escoja un valor del 1-19.");
        }
        return opciones.get(codigo);
    }

    @Override
    public String toString() {
        return String.format("%s - %s%n", this.codigo, this.texto);
    }
}
