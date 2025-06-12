package ec.edu.uce.classfinder.dominio;

import ec.edu.uce.classfinder.util.Validadores;

/**
 * Representa un edificio en el sistema ClassFinder.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class Edificio extends Lugar {
    private int numeroPisos;
    private String ubicacion;

    public Edificio() {
        super();
        numeroPisos = 1;
        ubicacion = "";
    }

    public Edificio(String nombre, String descripcion, int numeroPisos, String ubicacion) {
        super(nombre, descripcion);
        setNumeroPisos(numeroPisos);
        setUbicacion(ubicacion);
    }

    public int getNumeroPisos() {
        return numeroPisos;
    }

    public void setNumeroPisos(int numeroPisos) {
        this.numeroPisos = numeroPisos > 0 ? numeroPisos : 1;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = (ubicacion != null && Validadores.esTextoValido(ubicacion)) ? ubicacion : "";
    }

    @Override
    public String toString() {
        return super.toString() + "\r\nNúmero de pisos: " + numeroPisos + "\r\nUbicación: " + ubicacion;
    }
}