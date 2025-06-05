package ec.edu.uce.classfinder.dominio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba unitaria para la clase ReservaTemporal.
 * Verifica el comportamiento de los métodos relacionados con la duración y la herencia de Reserva.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
class ReservaTemporalTest {

    private ReservaTemporal reservaTemporal;

    @BeforeEach
    void setUp() {
        reservaTemporal = new ReservaTemporal();
    }

    @Test
    void getDuracionHoras() {
        reservaTemporal.setDuracionHoras(3);
        assertEquals(3, reservaTemporal.getDuracionHoras());
    }

    @Test
    void setDuracionHoras() {
        reservaTemporal.setDuracionHoras(3);
        assertEquals(3, reservaTemporal.getDuracionHoras());

    }


    @Test
    void notificarExpiracion() {
        reservaTemporal.setEstadoReserva(EstadoReserva.PENDIENTE);
        reservaTemporal.notificarExpiracion();
        assertEquals("expirada", reservaTemporal.getEstadoReserva());
    }

    @Test
    void testHerencia() {
        reservaTemporal.setIdReserva("RES-002");
        assertEquals("RES-002", reservaTemporal.getIdReserva());
        reservaTemporal.rechazarReserva();
        assertEquals("rechazada", reservaTemporal.getEstadoReserva());
    }

    @Test
    void testToString() {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario("USR-002");
        Espacio espacio = new Espacio();
        espacio.setIdEspacio("ESP-002");
        reservaTemporal = new ReservaTemporal("RES-002", "2025/05/23 10:00", "2025/05/23 12:00", EstadoReserva.PENDIENTE, usuario, espacio, 3);
        String expected = "ReservaTemporal{idReserva='RES-002', fechaInicio='2025/05/23 10:00', fechaFin='2025/05/23 12:00', estado='Estado.PENDIENTE', usuario=USR-002, espacio=ESP-002, duracionHoras=3}";

    }
}