package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record Vehiculo(String marca, String modelo, String matricula) {
    private static final String ER_MARCA = "[A-Z][a-z]+(?:[- ]?[A-Z][a-z]+)?|[A-Z]+";
    private static final String ER_MATRICULA = "\\d{4}[^\\W_AEIOUa-z]{3}";

    public Vehiculo {
        validarMarca(marca);
        validarModelo(modelo);
        validarMatricula(matricula);
    }

    private void validarMatricula(String matricula) {
        Objects.requireNonNull(matricula, "La matr�cula no puede ser nula.");
        Pattern patron = Pattern.compile(ER_MATRICULA);
        Matcher comparador = patron.matcher(matricula);
        if (!comparador.matches()) {
            throw new IllegalArgumentException("La matr�cula no tiene un formato v�lido.");
        }
    }

    private void validarModelo(String modelo) {
        Objects.requireNonNull(modelo, "El modelo no puede ser nulo.");
        if (modelo.isBlank()) {
            throw new IllegalArgumentException("El modelo no puede estar en blanco.");
        }
    }

    private void validarMarca(String marca) {
        Objects.requireNonNull(marca, "La marca no puede ser nula.");
        Pattern patron = Pattern.compile(ER_MARCA);
        Matcher comparador = patron.matcher(marca);
        if (!comparador.matches()){
            throw new IllegalArgumentException("La marca no tiene un formato v�lido.");
        }
    }

    public static Vehiculo get(String matricula) {
        Objects.requireNonNull(matricula, "La matr�cula no puede ser nula.");
        return new Vehiculo("Seat", "Alpha", matricula);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehiculo vehiculo = (Vehiculo) o;
        return Objects.equals(matricula, vehiculo.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }

    @Override
    public String toString() {
        return String.format("%s %s - %s", this.marca, this.modelo, this.matricula);
    }
}
