package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cliente {
    private static final String ER_NOMBRE = "(?:[A-Z][a-záéíóúñ]+ ?)+";
    private static final String ER_DNI = "(\\d{8})([A-Z])";
    private static final String ER_TELEFONO = "\\d{9}";
    private String nombre;
    private String dni;
    private String telefono;

    public Cliente(String nombre, String dni, String telefono) {
        setNombre(nombre);
        setDni(dni);
        setTelefono(telefono);
    }

    public Cliente(Cliente cliente) {
        Objects.requireNonNull(cliente, "No es posible copiar un cliente nulo.");
        this.nombre = cliente.nombre;
        this.dni = cliente.dni;
        this.telefono = cliente.telefono;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        Objects.requireNonNull(nombre, "El nombre no puede ser nulo.");
        Pattern patron = Pattern.compile(ER_NOMBRE);
        Matcher comparador = patron.matcher(nombre);
        if (!comparador.matches()) {
            throw new IllegalArgumentException("El nombre no tiene un formato válido.");
        }
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    private void setDni(String dni) {
        Objects.requireNonNull(dni, "El DNI no puede ser nulo.");
        Pattern patron = Pattern.compile(ER_DNI);
        Matcher comparador = patron.matcher(dni);
        if (!comparador.matches()) {
            throw new IllegalArgumentException("El DNI no tiene un formato válido.");
        }
        comprobarLetraDNI(dni);
        this.dni = dni;
    }
    private boolean comprobarLetraDNI(String dni) {
        Objects.requireNonNull(dni, "El dni no puede ser nulo");
        boolean letraCorrecta = false;
        char letra = dni.charAt(dni.length()-1);
        int dniCopia = Integer.parseInt(dni.substring(0,dni.length()-2));

        char[] letraDNI = {'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E'};
        if (letra == 'I' || letra == 'O' || letra == 'U' || letra == 'V' || letra == 'L') {
            throw new IllegalArgumentException("La letra del DNI no es correcta.");
        }

        if (letra == letraDNI[dniCopia % 23]) {
            letraCorrecta = true;
        }
        return letraCorrecta;
    }
    public String getTelefono(){
        return telefono;
    }
    public void setTelefono(String telefono) {
        Objects.requireNonNull(telefono, "El teléfono no puede ser nulo.");
        Pattern patron = Pattern.compile(ER_TELEFONO);
        Matcher comparador = patron.matcher(telefono);
        if (!comparador.matches()) {
            throw new IllegalArgumentException("El teléfono no tiene un formato válido.");
        }
        this.telefono = telefono;
    }
    public static Cliente get(String dni) {
        Cliente cliente = new Cliente("Bob Esponja", dni, "950123456");
        return cliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(dni, cliente.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni);
    }

    @Override
    public String toString() {
        return String.format("%s - %s (%s)", this.nombre, this.dni, this.telefono);
    }
}
