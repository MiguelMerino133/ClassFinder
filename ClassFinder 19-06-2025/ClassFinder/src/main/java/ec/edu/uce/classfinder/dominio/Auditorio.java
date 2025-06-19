package ec.edu.uce.classfinder.dominio;

/**
 * Representa un auditorio en el sistema ClassFinder.
 * Extiende la clase {@link Espacio} para heredar sus atributos básicos.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class Auditorio extends Espacio {
    private boolean equipoSonido;

    public Auditorio() {
        super();
        equipoSonido = false;
    }

    public Auditorio(String nombre, int capacidad, Tamano tamano, boolean equipoSonido) {
        super(nombre, capacidad, tamano);
        setEquipoSonido(equipoSonido);
    }

    public boolean hasEquipoSonido() {
        return equipoSonido;
    }

    public void setEquipoSonido(boolean equipoSonido) {
        this.equipoSonido = equipoSonido;
    }

    @Override
    public String toString() {
        return super.toString() + "\r\nEquipo de Sonido: " + (equipoSonido ? "Sí" : "No");
    }

    @Override
    public String describirEspacio() {
        return "Auditorio: " + getNombre() + " (Equipo de sonido: " + (equipoSonido ? "Sí" : "No") + ")";
    }
}