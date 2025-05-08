package ec.edu.uce.classfinder.modelo;

import java.util.Date;

public class ReservaSemestre extends Reserva {

    private String semestre;

    public ReservaSemestre() {}

    public ReservaSemestre(String idReserva, Date fechaInicio, Date fechaFin, String estado, String idUsuario,
                           String idEspacio, String semestre) {
        super(idReserva, fechaInicio, fechaFin, estado, idUsuario, idEspacio);
        this.semestre = semestre;
    }


    public String getSemestre() { return semestre; }
    public void setSemestre(String semestre) { this.semestre = semestre; }

    public void asignarSemestre() {}
}
