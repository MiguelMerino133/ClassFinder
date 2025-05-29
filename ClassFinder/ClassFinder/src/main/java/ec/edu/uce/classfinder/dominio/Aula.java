package ec.edu.uce.classfinder.dominio;

import ec.edu.uce.classfinder.util.Validadores;

/**
 * Representa un aula, que es un tipo de espacio con un número de aula.
 * Extiende la clase {@link Espacio} para heredar sus atributos básicos.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class Aula extends Espacio {

    private String numeroAula;
    private static int contadorAulas = 101; // Contador para generar números secuenciales

    /**
     * Constructor por defecto.
     * Inicializa un aula con un número predeterminado "A-101".
     */
    public Aula() {
        super();
        numeroAula = "A-101";
    }

    /**
     * Constructor con parámetros.
     * @param idEspacio identificador del espacio
     * @param nombre nombre del espacio
     * @param capacidad capacidad del espacio
     * @param tamano tamaño del espacio
     * @param numeroAula número del aula
     */
    public Aula(String idEspacio, String nombre, int capacidad, String tamano, String numeroAula) {
        super(idEspacio, nombre, capacidad, tamano);
        this.numeroAula = numeroAula;
    }

    /**
     * Obtiene el número del aula.
     * @return el número del aula
     */
    public String getNumeroAula() {
        return numeroAula;
    }

    /**
     * Establece el número del aula.
     * Valida que el valor no sea nulo ni inválido, usando un valor por defecto si falla.
     * @param numeroAula nuevo número del aula
     */
    public void setNumeroAula(String numeroAula) {
        if (numeroAula == null || !Validadores.esTextoValido(numeroAula)) {
            this.numeroAula = "A-101";
        } else {
            this.numeroAula = numeroAula;
        }
    }


    @Override
    public String toString() {
        return super.toString() + "\r\nNúmero de aula: " + numeroAula;
    }
}