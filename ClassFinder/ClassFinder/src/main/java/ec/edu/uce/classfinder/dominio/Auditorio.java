package ec.edu.uce.classfinder.dominio;

/**
 * Representa un auditorio, que es un tipo de espacio con equipo de sonido.
 * Extiende la clase {@link Espacio} para heredar sus atributos básicos.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class Auditorio extends Espacio {

    private boolean equipoSonido;

    /**
     * Constructor por defecto.
     * Inicializa un auditorio con equipo de sonido habilitado por defecto.
     */
    public Auditorio() {
        super();
        equipoSonido = true;
    }

    /**
     * Constructor con parámetros.
     * @param idEspacio identificador del espacio
     * @param nombre nombre del espacio
     * @param capacidad capacidad del espacio
     * @param tamano tamaño del espacio
     * @param equipoSonido indica si tiene equipo de sonido
     */
    public Auditorio(String idEspacio, String nombre, int capacidad, String tamano, boolean equipoSonido) {
        super(idEspacio, nombre, capacidad, tamano);
        this.equipoSonido = equipoSonido;
    }

    /**
     * Obtiene el estado del equipo de sonido.
     * @return true si tiene equipo de sonido, false de lo contrario
     */
    public boolean isEquipoSonido() {
        return equipoSonido;
    }

    /**
     * Establece el estado del equipo de sonido.
     * @param equipoSonido nuevo estado del equipo de sonido
     */
    public void setEquipoSonido(boolean equipoSonido) {
        this.equipoSonido = equipoSonido;
    }


    @Override
    public String toString() {
        return super.toString() + "\r\nEquipo de sonido: "+equipoSonido;
    }
}