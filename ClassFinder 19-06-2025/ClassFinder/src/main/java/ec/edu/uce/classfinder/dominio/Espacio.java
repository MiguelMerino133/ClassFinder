
        package ec.edu.uce.classfinder.dominio;

import ec.edu.uce.classfinder.util.Validadores;

/**
 * Clase abstracta que representa un espacio genérico en el sistema ClassFinder.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public abstract class Espacio {
    private static int contador = 1;
    private static final String PREFIJO = "ESP-";
    private final String idEspacio;
    private String nombre;
    private int capacidad;
    private Tamano tamano;

    public Espacio() {
        idEspacio = PREFIJO + String.format("%03d", contador++);
        nombre = "";
        capacidad = 10;
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
        this.nombre = (nombre == null || nombre.isBlank() || Validadores.esTextoValido(nombre)) ? nombre : "";
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = (capacidad > 0 && Validadores.esCapacidadValido(String.valueOf(capacidad))) ? capacidad : 10;
    }

    public Tamano getTamano() {
        return tamano;
    }

    public void setTamano(Tamano tamano) {
        this.tamano = (tamano != null && Validadores.esTamanoValido(tamano)) ? tamano : Tamano.MEDIANO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Espacio)) return false;
        Espacio espacio = (Espacio) o;
        if (this instanceof Aula && espacio instanceof Aula) {
            return ((Aula) this).getNumeroAula().equalsIgnoreCase(((Aula) espacio).getNumeroAula());
        }
        return nombre != null && !nombre.isEmpty() && nombre.equalsIgnoreCase(espacio.getNombre());
    }

    @Override
    public int hashCode() {
        if (this instanceof Aula) {
            return ((Aula) this).getNumeroAula().toLowerCase().hashCode();
        }
        return nombre != null && !nombre.isEmpty() ? nombre.toLowerCase().hashCode() : 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Espacio:\r\nID: " + idEspacio);
        if (nombre != null && !nombre.isEmpty()) {
            sb.append("\r\nNombre: " + nombre);
        }
        sb.append("\r\nCapacidad: " + capacidad + "\r\nTamaño: " + tamano.getDescripcion());
        return sb.toString();
    }

    public abstract String describirEspacio();

    public static int getContador() {
        return contador;
    }
}