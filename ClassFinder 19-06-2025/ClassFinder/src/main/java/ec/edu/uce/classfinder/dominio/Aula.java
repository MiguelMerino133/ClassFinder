
package ec.edu.uce.classfinder.dominio;

import ec.edu.uce.classfinder.util.Validadores;

/**
 * Representa un aula en el sistema ClassFinder.
 * Extiende la clase {@link Espacio} para heredar sus atributos básicos.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class Aula extends Espacio {
    private static int contadorAulas = 1;
    private static final String PREFIJO_AULA = "AUL-";
    private String numeroAula;

    public Aula() {
        super();
        this.numeroAula = PREFIJO_AULA + String.format("%03d", contadorAulas++);
    }

    public Aula(int capacidad, Tamano tamano, String numeroAula) {
        super("", capacidad, tamano);
        setNumeroAula(numeroAula);
    }

    public String getNumeroAula() {
        return numeroAula;
    }

    public void setNumeroAula(String numeroAula) {
        this.numeroAula = (numeroAula != null && Validadores.esNumeroAulaValido(numeroAula)) ?
                numeroAula : PREFIJO_AULA + String.format("%03d", contadorAulas++);
    }

    @Override
    public String toString() {
        return super.toString() + "\r\nNúmero de Aula: " + numeroAula;
    }

    @Override
    public String describirEspacio() {
        return "Aula: " + numeroAula;
    }

    public static int getContadorAulas() {
        return contadorAulas;
    }
}