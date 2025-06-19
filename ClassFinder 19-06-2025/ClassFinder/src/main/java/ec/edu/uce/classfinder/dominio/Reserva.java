package ec.edu.uce.classfinder.dominio;

import ec.edu.uce.classfinder.util.Validadores;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Representa una reserva en el sistema ClassFinder.
 * Clase abstracta que define el comportamiento común para reservas temporales y de semestre.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public abstract class Reserva {
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

    public Reserva(String fechaInicio, String fechaFin, EstadoReserva estado, Usuario usuario, Espacio espacio) {
        this.idReserva = PREFIJO + String.format("%03d", contador++);
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
        if (fechaInicio != null && Validadores.esFechaConHoraValida(fechaInicio)) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                sdf.setLenient(false);
                this.fechaInicio = sdf.parse(fechaInicio);
            } catch (ParseException e) {
                this.fechaInicio = new Date();
            }
        } else {
            this.fechaInicio = new Date();
        }
    }

    public String getFechaInicioStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        return sdf.format(fechaInicio);
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        if (fechaFin != null && Validadores.esFechaConHoraValida(fechaFin) && Validadores.esFechaFinValida(getFechaInicioStr(), fechaFin)) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                sdf.setLenient(false);
                this.fechaFin = sdf.parse(fechaFin);
            } catch (ParseException e) {
                this.fechaFin = new Date();
            }
        } else {
            this.fechaFin = new Date();
        }
    }

    public String getFechaFinStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        return sdf.format(fechaFin);
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public void setEstado(EstadoReserva estado) {
        this.estado = (estado != null && Validadores.esEstadoValido(estado)) ? estado : EstadoReserva.PENDIENTE;
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
        if (!espacio.equals(reserva.espacio)) return false;

        if (this.estado == EstadoReserva.RECHAZADO || this.estado == EstadoReserva.EXPIRADA ||
                reserva.estado == EstadoReserva.RECHAZADO || reserva.estado == EstadoReserva.EXPIRADA) {
            return false;
        }

        return !(fechaFin.before(reserva.fechaInicio) || fechaInicio.after(reserva.fechaFin));
    }

    @Override
    public int hashCode() {
        int result = espacio.hashCode();
        result = 31 * result + fechaInicio.hashCode();
        result = 31 * result + fechaFin.hashCode();
        return result;
    }

    @Override
    public String toString() {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        return "Reserva:" + "\r\nID: " + idReserva +
                "\r\nFecha Inicio: " + formato.format(fechaInicio) +
                "\r\nFecha Fin: " + formato.format(fechaFin) +
                "\r\nEstado: " + estado.getDescripcion() +
                "\r\nUsuario: " + (usuario != null ? usuario.getNombre() : "N/A") +
                "\r\nEspacio: " + (espacio != null ? (espacio instanceof Aula ? ((Aula) espacio).getNumeroAula() : espacio.getNombre()) : "N/A");
    }

    public static int getContador() {
        return contador;
    }
}