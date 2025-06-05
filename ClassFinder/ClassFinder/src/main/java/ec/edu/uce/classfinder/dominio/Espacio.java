package ec.edu.uce.classfinder.dominio;

import ec.edu.uce.classfinder.util.Validadores;

/**
 * Representa un espacio dentro de un lugar en el sistema ClassFinder.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class Espacio {
    private static int contador = 1;
    private static final String PREFIJO = "ESP-";
    private final String idEspacio;
    private String nombre;
    private int capacidad;
    private String tamano;

    public Espacio() {
        idEspacio = PREFIJO + String.format("%03d", contador++);
        nombre = "";
        capacidad = 1;
        tamano = "desconocido";
    }

    public Espacio(String idEspacio, String nombre, int capacidad, String tamano) {
        this.idEspacio = idEspacio != null && Validadores.esIdValido(idEspacio) ? idEspacio : PREFIJO + String.format("%03d", contador++);
        setNombre(nombre);
        setCapacidad(capacidad);
        setTamano(tamano);
    }

    public String getIdEspacio() {
        return idEspacio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = (nombre != null && Validadores.esTextoValido(nombre)) ? nombre : "";
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = (capacidad > 0) ? capacidad : 1;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = (tamano != null && Validadores.esTextoValido(tamano)) ? tamano : "desconocido";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Espacio)) return false;
        Espacio espacio = (Espacio) o;
        return idEspacio.equals(espacio.idEspacio);
    }

    @Override
    public int hashCode() {
        return idEspacio.hashCode();
    }

    @Override
    public String toString() {
        return "Espacio:" + "\r\nID: " + idEspacio + "\r\nNombre: " + nombre +
                "\r\nCapacidad: " + capacidad + "\r\nTamaño: " + tamano;
    }
}