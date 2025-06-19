package ec.edu.uce.classfinder.dominio;

import ec.edu.uce.classfinder.util.Validadores;

/**
 * Clase que representa un edificio en el sistema ClassFinder.
 * Extiende la clase {@link Lugar} para heredar sus atributos básicos.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class Edificio extends Lugar {
    private int pisos;
    private String direccion;

    public Edificio() {
        super();
        pisos = 1;
        direccion = "";
    }

    public Edificio(String nombre, String descripcion, int pisos, String direccion) {
        super(nombre, descripcion);
        setPisos(pisos);
        setDireccion(direccion);
    }

    public int getPisos() {
        return pisos;
    }

    public void setPisos(int pisos) {
        this.pisos = (pisos > 0 && Validadores.esPisosValido(String.valueOf(pisos))) ? pisos : 1;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = (direccion != null && Validadores.esTextoValido(direccion)) ? direccion : "";
    }

    @Override
    public String toString() {
        return super.toString() + "\r\nPisos: " + pisos + "\r\nDirección: " + direccion;
    }
}