package ec.edu.uce.classfinder.dominio;

import ec.edu.uce.classfinder.util.Validadores;

/**
 * Representa una reserva a largo plazo para un semestre.
 * Extiende la clase {@link Reserva} para heredar sus atributos básicos.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class ReservaSemestre extends Reserva {

    private String codigoSemestre;

    public ReservaSemestre() {
        super();
        codigoSemestre = "2025-1";
    }

    public ReservaSemestre(String fechaInicio, String fechaFin, EstadoReserva estado, Usuario usuario, Espacio espacio, String codigoSemestre) {
        super(fechaInicio, fechaFin, estado, usuario, espacio);
        setCodigoSemestre(codigoSemestre);
    }

    public String getCodigoSemestre() {
        return codigoSemestre;
    }

    public void setCodigoSemestre(String codigoSemestre) {
        this.codigoSemestre = (codigoSemestre != null && Validadores.esCodigoSemestreValido(codigoSemestre)) ? codigoSemestre : "2025-1";
    }

    @Override
    public String toString() {
        return super.toString() + "\r\nSemestre: " + codigoSemestre;
    }
}