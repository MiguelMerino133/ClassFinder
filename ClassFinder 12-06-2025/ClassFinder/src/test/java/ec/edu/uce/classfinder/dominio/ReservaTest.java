package ec.edu.uce.classfinder.dominio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba unitaria para la clase Reserva.
 * Verifica el comportamiento de los métodos relacionados con ID, fechas, estado, usuario, espacio y aprobación/rechazo.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
class ReservaTest {

    private Reserva reserva;

    @BeforeEach
    void setUp() {
        reserva = new Reserva();
    }

    @Test
    void getIdReserva() {
        reserva.setIdReserva("RES-002");
        assertEquals("RES-002", reserva.getIdReserva());
    }

    @Test
    void setIdReserva() {
        reserva.setIdReserva("RES-002");
        assertEquals("RES-002", reserva.getIdReserva());

    }

    @Test
    void getFechaInicio() {
        reserva.setFechaInicio("2025/05/29 10:00");
        assertEquals("2025/05/29 10:00", reserva.getFechaInicio());
    }

    @Test
    void setFechaInicio() {
        reserva.setFechaInicio("2025/05/29 10:00");
        assertEquals("2025/05/29 10:00", reserva.getFechaInicio());

    }

    @Test
    void getFechaFin() {
        reserva.setFechaFin("2025/05/30 12:00");
        assertEquals("2025/05/30 12:00", reserva.getFechaFin());
    }

    @Test
    void setFechaFin() {
        reserva.setFechaInicio("2025/05/29 10:00");
        reserva.setFechaFin("2025/05/29 12:00");
        assertEquals("2025/05/29 12:00", reserva.getFechaFin());

    }

    @Test
    void getEstadoReserva() {
        reserva.setEstadoReserva(EstadoReserva.APROBADO);
        assertEquals("Aprobada", reserva.getEstadoReserva());
    }

    @Test
    void setEstadoReserva() {
        reserva.setEstadoReserva(EstadoReserva.APROBADO);
        assertEquals("Aprobada", reserva.getEstadoReserva());

    }

    @Test
    void getUsuario() {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario("USR-002");
        reserva.setUsuario(usuario);
        assertEquals("USR-002", reserva.getUsuario().getIdUsuario());
    }

    @Test
    void setUsuario() {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario("USR-002");
        reserva.setUsuario(usuario);
        assertEquals("USR-002", reserva.getUsuario().getIdUsuario());

    }

    @Test
    void getEspacio() {
        Espacio espacio = new Espacio();
        espacio.setIdEspacio("ESP-002");
        reserva.setEspacio(espacio);
        assertEquals("ESP-002", reserva.getEspacio().getIdEspacio());
    }

    @Test
    void setEspacio() {
        Espacio espacio = new Espacio();
        espacio.setIdEspacio("ESP-002");
        reserva.setEspacio(espacio);
        assertEquals("ESP-002", reserva.getEspacio().getIdEspacio());

    }

    @Test
    void aprobarReserva() {
        reserva.setEstadoReserva(EstadoReserva.PENDIENTE);
        reserva.aprobarReserva();
        assertEquals("aprobada", reserva.getEstadoReserva());
    }

    @Test
    void rechazarReserva() {
        reserva.setEstadoReserva(EstadoReserva.PENDIENTE);
        reserva.rechazarReserva();
        assertEquals("rechazada", reserva.getEstadoReserva());
    }

    @Test
    void testToString() {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario("USR-002");
        Espacio espacio = new Espacio();
        espacio.setIdEspacio("ESP-002");
        reserva = new Reserva("RES-002", "2025/05/29 10:00", "2025/05/29 12:00", EstadoReserva.PENDIENTE, usuario, espacio);
        String expected = "Reserva{idReserva='RES-002', fechaInicio='2025/05/29 10:00', fechaFin='2025/05/29 12:00', estado='pendiente', usuario=USR-002, espacio=ESP-002}";
        assertEquals(expected, reserva.toString());
    }
}