package ec.edu.uce.classfinder.modelo;

import java.util.Date;

public class ReservaTemporal extends Reserva {

    private int duracionHoras;


    public ReservaTemporal() {}

    public ReservaTemporal(String idReserva, Date fechaInicio, Date fechaFin, String estado, String idUsuario,
                           String idEspacio, int duracionHoras) {
        super(idReserva, fechaInicio, fechaFin, estado, idUsuario, idEspacio);
        this.duracionHoras = duracionHoras;
    }

    public int getDuracionHoras() { return duracionHoras; }
    public void setDuracionHoras(int duracionHoras) { this.duracionHoras = duracionHoras; }

    public void establecerDuracion() {}
    public void notificarExpiracion() {}
}
