package ec.edu.uce.classfinder.dominio;

import ec.edu.uce.classfinder.util.Validadores;

/**
 * Representa una reserva de un espacio por parte de un usuario.
 * Es una clase base para tipos específicos como {@link ReservaSemestre} y {@link ReservaTemporal}.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class Reserva {

    private String idReserva;
    private String fechaInicio;
    private String fechaFin;
    private Usuario usuario;
    private Espacio espacio;
    protected EstadoReserva estado;

    /**
     * Constructor por defecto.
     * Inicializa una reserva con valores predeterminados.
     */
    public Reserva() {
        idReserva = "RES-001";
        fechaInicio = "2025/05/22 10:00";
        fechaFin = "2025/05/22 12:00";
        estado = EstadoReserva.PENDIENTE;
        usuario = new Usuario();
        espacio = new Espacio();
    }

    /**
     * Constructor con parámetros.
     * @param idReserva identificador de la reserva
     * @param fechaInicio fecha de inicio de la reserva
     * @param fechaFin fecha de fin de la reserva
     * @param estado estado de la reserva
     * @param usuario usuario que realiza la reserva
     * @param espacio espacio reservado
     */
    public Reserva(String idReserva, String fechaInicio, String fechaFin, EstadoReserva estado, Usuario usuario, Espacio espacio) {
        setIdReserva(idReserva);
        setFechaInicio(fechaInicio);
        setFechaFin(fechaFin);
        setEstadoReserva(estado);
        setUsuario(usuario);
        setEspacio(espacio);
    }

    /**
     * Obtiene el identificador de la reserva.
     * @return el identificador de la reserva
     */
    public String getIdReserva() {
        return idReserva;
    }

    /**
     * Establece el identificador de la reserva.
     * Valida que el valor no sea nulo ni inválido, usando un valor por defecto si falla.
     * @param idReserva nuevo identificador
     */
    public void setIdReserva(String idReserva) {
        if (idReserva == null || !Validadores.esIdValido(idReserva)) {
            this.idReserva = "RES-001";
        } else {
            this.idReserva = idReserva;
        }
    }

    /**
     * Obtiene la fecha de inicio de la reserva.
     * @return la fecha de inicio
     */
    public String getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Establece la fecha de inicio de la reserva.
     * Valida que el valor no sea nulo ni inválido, usando un valor por defecto si falla.
     * @param fechaInicio nueva fecha de inicio
     */
    public void setFechaInicio(String fechaInicio) {
        if (fechaInicio == null || !Validadores.esFechaConHoraValida(fechaInicio)) {
            this.fechaInicio = "2025/05/22 10:00";
        } else {
            this.fechaInicio = fechaInicio;
        }
    }

    /**
     * Obtiene la fecha de fin de la reserva.
     * @return la fecha de fin
     */
    public String getFechaFin() {
        return fechaFin;
    }

    /**
     * Establece la fecha de fin de la reserva.
     * Valida que el valor no sea nulo ni inválido y que sea posterior a la fecha de inicio, usando un valor por defecto si falla.
     * @param fechaFin nueva fecha de fin
     */
    public void setFechaFin(String fechaFin) {
        if (fechaFin == null || !Validadores.esFechaConHoraValida(fechaFin) || !Validadores.esFechaFinValida(this.fechaInicio, fechaFin)) {
            this.fechaFin = "2025/05/22 12:00";
        } else {
            this.fechaFin = fechaFin;
        }
    }

    /**
     * Obtiene el estado de la reserva.
     * @return el estado de la reserva
     */
    public EstadoReserva getEstadoReserva() {
        return estado;
    }

    /**
     * Establece el estado de la reserva.
     * Valida que el valor no sea nulo ni inválido, usando un valor por defecto si falla.
     * @param estado nuevo estado
     */
    public void setEstadoReserva(EstadoReserva estado) {
        if (estado == null || !Validadores.esEstadoValido(estado)) {
            this.estado = EstadoReserva.PENDIENTE;
        } else {
            this.estado = estado;
        }
    }

    /**
     * Obtiene el usuario asociado a la reserva.
     * @return el usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario asociado a la reserva.
     * Valida que el valor no sea nulo, usando un valor por defecto si falla.
     * @param usuario nuevo usuario
     */
    public void setUsuario(Usuario usuario) {
        if (usuario == null) {
            this.usuario = new Usuario();
        } else {
            this.usuario = usuario;
        }
    }

    /**
     * Obtiene el espacio asociado a la reserva.
     * @return el espacio
     */
    public Espacio getEspacio() {
        return espacio;
    }

    /**
     * Establece el espacio asociado a la reserva.
     * Valida que el valor no sea nulo, usando un valor por defecto si falla.
     * @param espacio nuevo espacio
     */
    public void setEspacio(Espacio espacio) {
        if (espacio == null) {
            this.espacio = new Espacio();
        } else {
            this.espacio = espacio;
        }
    }

    /**
     * Aproba la reserva.
     * Actualiza el estado a "aprobada" y muestra un mensaje.
     */
    public void aprobarReserva() {
        this.estado = EstadoReserva.APROBADO;
        System.out.println("Reserva aprobada: " + idReserva);
    }

    /**
     * Rechaza la reserva.
     * Actualiza el estado a "rechazada" y muestra un mensaje.
     */
    public void rechazarReserva() {
        this.estado = EstadoReserva.RECHAZADO;
        System.out.println("Reserva rechazada: " + idReserva);
    }


    @Override
    public String toString() {
        return "Reserva" +
                "\r\nID: " + idReserva + "\r\nFecha de inicio: " + fechaInicio + "\r\nFecha de fin: " + fechaFin + "\r\nEstado: " + estado.getDescripcion() +
                "\r\nUsuario: " + usuario.getIdUsuario() + "\r\nEspacio: " + espacio.getIdEspacio();
    }
}