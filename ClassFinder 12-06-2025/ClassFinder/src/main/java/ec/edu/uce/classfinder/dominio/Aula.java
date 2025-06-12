package ec.edu.uce.classfinder.dominio;

import ec.edu.uce.classfinder.util.Validadores;

/**
 * Representa un aula en el sistema ClassFinder.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class Aula extends Espacio {
    private String numeroAula;
    private static int contadorAulas = 1;
    private static final String PREFIJO_AULA = "AUL-";

    public Aula() {
        super();
        this.numeroAula = PREFIJO_AULA + String.format("%03d", contadorAulas++);
    }

    public Aula(String nombre, int capacidad, Tamano tamano, String numeroAula) {
        super(nombre, capacidad, tamano);
        setNumeroAula(numeroAula);
    }

    public String getNumeroAula() {
        return numeroAula;
    }

    public void setNumeroAula(String numeroAula) {
        this.numeroAula = (numeroAula != null && Validadores.esNumeroAulaValido(numeroAula)) ? numeroAula : PREFIJO_AULA + String.format("%03d", contadorAulas++);
    }

    @Override
    public String toString() {
        return super.toString() + "\r\nNúmero de Aula: " + numeroAula;
    }
}