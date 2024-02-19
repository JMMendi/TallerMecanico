package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;

import javax.naming.OperationNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Clientes {
    private final List<Cliente> clientes;

    public Clientes() {
        clientes = new ArrayList<>();
    }
    public List<Cliente> get() {
        return clientes;
    }
    public void insertar(Cliente cliente) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "No se puede insertar un cliente nulo.");
        if (clientes.contains(cliente)) {
            throw new OperationNotSupportedException("Ya existe un cliente con ese DNI.");
        }
        clientes.add(cliente);
    }
    public boolean modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "No se puede modificar un cliente nulo.");
        boolean modificacion = false;
        if (!clientes.contains(cliente)) {
            throw new OperationNotSupportedException("No existe ningún cliente con ese DNI.");
        }
        try {
            if (!nombre.isBlank()) {
                cliente.setNombre(nombre);
                modificacion = true;
            }
        } catch (NullPointerException e) {
            System.out.print(e.getMessage());
        }
        try {
            if (!telefono.isBlank()) {
                cliente.setTelefono(telefono);
                modificacion = true;
            }
        } catch (NullPointerException e) {
            System.out.print(e.getMessage());
        }

        return modificacion;
    }
    public Cliente buscar(Cliente cliente) {
        Objects.requireNonNull(cliente, "No se puede buscar un cliente nulo.");
        int indice = clientes.indexOf(cliente);
        return indice != -1 ? clientes.get(indice) : null;
    }
    public void borrar(Cliente cliente) throws OperationNotSupportedException {
        Objects.requireNonNull(cliente, "No se puede borrar un cliente nulo.");
        int indice = clientes.indexOf(cliente);
        if (indice == -1) {
            throw new OperationNotSupportedException("No existe ningún cliente con ese DNI.");
        } else {
            clientes.remove(indice);
        }
    }
}