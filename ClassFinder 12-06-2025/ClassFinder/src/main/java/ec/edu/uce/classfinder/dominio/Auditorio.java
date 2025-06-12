package ec.edu.uce.classfinder.dominio;

/**
 * Representa un auditorio en el sistema ClassFinder.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class Auditorio extends Espacio {
    private boolean equipoSonido;

    public Auditorio() {
        super();
        this.equipoSonido = false;
    }

    public Auditorio(String nombre, int capacidad, Tamano tamano, boolean equipoSonido) {
        super(nombre, capacidad, tamano);
        this.equipoSonido = equipoSonido;
    }

    public boolean isEquipoSonido() {
        return equipoSonido;
    }

    public void setEquipoSonido(boolean equipoSonido) {
        this.equipoSonido = equipoSonido;
    }

    @Override
    public String toString() {
        return super.toString() + "\r\nEquipo de Sonido: " + (equipoSonido ? "Sí" : "No");
    }
}