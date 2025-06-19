package ec.edu.uce.classfinder.dominio;

import ec.edu.uce.classfinder.util.Validadores;

/**
 * Representa una reserva temporal por horas.
 * Extiende la clase {@link Reserva} para heredar sus atributos básicos.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class ReservaTemporal extends Reserva {

    private int duracionHoras;

    public ReservaTemporal() {
        super();
        duracionHoras = 2;
    }

    public ReservaTemporal(String fechaInicio, String fechaFin, EstadoReserva estado, Usuario usuario, Espacio espacio, int duracionHoras) {
        super(fechaInicio, fechaFin, estado, usuario, espacio);
        setDuracionHoras(duracionHoras);
    }

    public int getDuracionHoras() {
        return duracionHoras;
    }

    public void setDuracionHoras(int duracionHoras) {
        if (duracionHoras > 0 && Validadores.esNumeroValido(String.valueOf(duracionHoras))) {
            this.duracionHoras = duracionHoras;
        } else {
            this.duracionHoras = 2;
        }
    }

    public void notificarExpiracion() {
        this.estado = EstadoReserva.EXPIRADA;
    }

    @Override
    public String toString() {
        return super.toString() + "\r\nDuración (horas): " + duracionHoras;
    }
}