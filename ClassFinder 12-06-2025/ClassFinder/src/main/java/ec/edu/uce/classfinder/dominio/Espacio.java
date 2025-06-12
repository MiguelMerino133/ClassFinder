package ec.edu.uce.classfinder.dominio;

import ec.edu.uce.classfinder.util.Validadores;

/**
 * Representa un espacio en el sistema ClassFinder.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class Espacio {
    private static int contador = 1;
    private static final String PREFIJO = "ESP-";
    private final String idEspacio;
    private String nombre;
    private int capacidad;
    private Tamano tamano;

    public Espacio() {
        idEspacio = PREFIJO + String.format("%03d", contador++);
        nombre = "";
        capacidad = 1;
        tamano = Tamano.MEDIANO;
    }

    public Espacio(String nombre, int capacidad, Tamano tamano) {
        this.idEspacio = PREFIJO + String.format("%03d", contador++);
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
        this.capacidad = (capacidad > 0 && Validadores.esCapacidadValido(String.valueOf(capacidad))) ? capacidad : 1;
    }

    public Tamano getTamano() {
        return tamano;
    }

    public void setTamano(Tamano tamano) {
        this.tamano = (tamano != null) ? tamano : Tamano.MEDIANO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Espacio)) return false;
        Espacio espacio = (Espacio) o;
        return nombre.equalsIgnoreCase(espacio.nombre);
    }

    @Override
    public int hashCode() {
        return nombre.toLowerCase().hashCode();
    }


    @Override
    public String toString() {
        return "Espacio:" + "\r\nID: " + idEspacio + "\r\nNombre: " + nombre +
                "\r\nCapacidad: " + capacidad + "\r\nTamaño: " + tamano.getDescripcion();
    }
}