package ec.edu.uce.classfinder.dominio;

import ec.edu.uce.classfinder.util.Validadores;

/**
 * Representa una reserva temporal por horas.
 * Extiende la clase {@link Reserva} para heredar sus atributos básicos.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class ReservaTemporal extends Reserva {

    private int duracionHoras;

    /**
     * Constructor por defecto.
     * Inicializa una reserva temporal con una duración predeterminada de 2 horas.
     */
    public ReservaTemporal() {
        super();
        duracionHoras = 2;
    }

    /**
     * Constructor con parámetros.
     * @param idReserva identificador de la reserva
     * @param fechaInicio fecha de inicio de la reserva
     * @param fechaFin fecha de fin de la reserva
     * @param estado estado de la reserva
     * @param usuario usuario que realiza la reserva
     * @param espacio espacio reservado
     * @param duracionHoras duración en horas de la reserva
     */
    public ReservaTemporal(String idReserva, String fechaInicio, String fechaFin, EstadoReserva estado, Usuario usuario, Espacio espacio, int duracionHoras) {
        super(idReserva, fechaInicio, fechaFin, estado, usuario, espacio);
        this.duracionHoras = duracionHoras;
    }

    /**
     * Obtiene la duración en horas de la reserva.
     * @return la duración en horas
     */
    public int getDuracionHoras() {
        return duracionHoras;
    }

    /**
     * Establece la duración en horas de la reserva.
     * Valida que el valor sea positivo y válido, usando un valor por defecto si falla.
     * @param duracionHoras nueva duración en horas
     */
    public void setDuracionHoras(int duracionHoras) {
        if (duracionHoras <= 0 || !Validadores.esNumeroValido(String.valueOf(duracionHoras))) {
            this.duracionHoras = 2;
        } else {
            this.duracionHoras = duracionHoras;
        }
    }


    /**
     * Notifica la expiración de la reserva.
     * Cambia el estado de la reserva a "expirada".
     */
    public void notificarExpiracion() {
        this.estado =EstadoReserva.EXPIRADA;
    }

    @Override
    public String toString() {
        return super.toString() + "\r\nDuración (horas): " + duracionHoras;
    }
}