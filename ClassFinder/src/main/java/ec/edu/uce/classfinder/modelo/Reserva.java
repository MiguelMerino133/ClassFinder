package ec.edu.uce.classfinder.modelo;

import java.util.Date;

public class Reserva {

    private String idReserva;
    private Date fechaInicio;
    private Date fechaFin;
    private String estado;
    private String idUsuario;
    private String idEspacio;


    public Reserva() {}

    public Reserva(String idReserva, Date fechaInicio, Date fechaFin, String estado, String idUsuario, String idEspacio) {
        this.idReserva = idReserva;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estado = estado;
        this.idUsuario = idUsuario;
        this.idEspacio = idEspacio;
    }

    public String getIdReserva() { return idReserva; }
    public void setIdReserva(String idReserva) { this.idReserva = idReserva; }

    public Date getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(Date fechaInicio) { this.fechaInicio = fechaInicio; }

    public Date getFechaFin() { return fechaFin; }
    public void setFechaFin(Date fechaFin) { this.fechaFin = fechaFin; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getIdUsuario() { return idUsuario; }
    public void setIdUsuario(String idUsuario) { this.idUsuario = idUsuario; }

    public String getIdEspacio() { return idEspacio; }
    public void setIdEspacio(String idEspacio) { this.idEspacio = idEspacio; }

    public void registrarReserva() {}
    public void cancelarReserva() {}
    public void aprobarReserva(){}
    public void rechazarReserva(){}


}
