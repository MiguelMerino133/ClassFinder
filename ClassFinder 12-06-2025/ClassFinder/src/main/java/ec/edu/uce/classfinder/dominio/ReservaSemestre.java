package ec.edu.uce.classfinder.dominio;

/**
 * Representa una reserva a largo plazo para un semestre.
 * Extiende la clase {@link Reserva} para heredar sus atributos básicos.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class ReservaSemestre extends Reserva {

    private String semestre;

    public ReservaSemestre() {
        super();
        semestre = "2025-1";
    }

    public ReservaSemestre(String fechaInicio, String fechaFin, EstadoReserva estado, Usuario usuario, Espacio espacio, String semestre) {
        super(fechaInicio, fechaFin, estado, usuario, espacio);
        setSemestre(semestre);
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        if (semestre == null || !semestre.matches("\\d{4}-[1-2]")) {
            this.semestre = "2025-1";
        } else {
            this.semestre = semestre;
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\r\nSemestre: " + semestre;
    }
}