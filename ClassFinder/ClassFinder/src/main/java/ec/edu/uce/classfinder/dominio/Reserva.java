package ec.edu.uce.classfinder.dominio;

import ec.edu.uce.classfinder.util.Validadores;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Representa una reserva en el sistema ClassFinder.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class Reserva {
    private static int contador = 1;
    private static final String PREFIJO = "RES-";
    private final String idReserva;
    private Date fechaInicio;
    private Date fechaFin;
    protected EstadoReserva estado;
    private Usuario usuario;
    private Espacio espacio;

    public Reserva() {
        idReserva = PREFIJO + String.format("%03d", contador++);
        fechaInicio = new Date();
        fechaFin = new Date();
        estado = EstadoReserva.PENDIENTE;
        usuario = null;
        espacio = null;
    }

    public Reserva(String idReserva, String fechaInicio, String fechaFin, EstadoReserva estado, Usuario usuario, Espacio espacio) {
        this.idReserva = idReserva != null && Validadores.esIdValido(idReserva) ? idReserva : PREFIJO + String.format("%03d", contador++);
        setFechaInicio(fechaInicio);
        setFechaFin(fechaFin);
        setEstado(estado);
        setUsuario(usuario);
        setEspacio(espacio);
    }

    public String getIdReserva() {
        return idReserva;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        try {
            this.fechaInicio = (fechaInicio != null) ? new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(fechaInicio) : new Date();
        } catch (ParseException e) {
            this.fechaInicio = new Date();
        }
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        try {
            this.fechaFin = (fechaFin != null) ? new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(fechaFin) : new Date();
        } catch (ParseException e) {
            this.fechaFin = new Date();
        }
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public void setEstado(EstadoReserva estado) {
        this.estado = (estado != null) ? estado : EstadoReserva.PENDIENTE;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Espacio getEspacio() {
        return espacio;
    }

    public void setEspacio(Espacio espacio) {
        this.espacio = espacio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reserva)) return false;
        Reserva reserva = (Reserva) o;
        return idReserva.equals(reserva.idReserva);
    }

    @Override
    public int hashCode() {
        return idReserva.hashCode();
    }

    @Override
    public String toString() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        return "Reserva:" + "\r\nID: " + idReserva +
                "\r\nFecha Inicio: " + formato.format(fechaInicio) +
                "\r\nFecha Fin: " + formato.format(fechaFin) +
                "\r\nEstado: " + estado +
                "\r\nUsuario: " + (usuario != null ? usuario.getNombre() : "N/A") +
                "\r\nEspacio: " + (espacio != null ? espacio.getNombre() : "N/A");
    }
}