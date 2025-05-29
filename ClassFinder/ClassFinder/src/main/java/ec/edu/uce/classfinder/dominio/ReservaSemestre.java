package ec.edu.uce.classfinder.dominio;

/**
 * Representa una reserva a largo plazo para un semestre.
 * Extiende la clase {@link Reserva} para heredar sus atributos básicos.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class ReservaSemestre extends Reserva {

    private String semestre;

    /**
     * Constructor por defecto.
     * Inicializa una reserva de semestre con un valor predeterminado "2025-1".
     */
    public ReservaSemestre() {
        super();
        semestre = "2025-1";
    }

    /**
     * Constructor con parámetros.
     * @param idReserva identificador de la reserva
     * @param fechaInicio fecha de inicio de la reserva
     * @param fechaFin fecha de fin de la reserva
     * @param estado estado de la reserva
     * @param usuario usuario que realiza la reserva
     * @param espacio espacio reservado
     * @param semestre semestre de la reserva
     */
    public ReservaSemestre(String idReserva, String fechaInicio, String fechaFin, EstadoReserva estado, Usuario usuario, Espacio espacio, String semestre) {
        super(idReserva, fechaInicio, fechaFin, estado, usuario, espacio);
        this.semestre = semestre;
    }

    /**
     * Obtiene el semestre de la reserva.
     * @return el semestre
     */
    public String getSemestre() {
        return semestre;
    }

    /**
     * Establece el semestre de la reserva.
     * Valida que el valor siga el formato "YYYY-N" (ej. "2025-1"), usando un valor por defecto si falla.
     * @param semestre nuevo semestre
     */
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