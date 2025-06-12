package ec.edu.uce.classfinder.dominio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba unitaria para la clase ReservaSemestre.
 * Verifica el comportamiento de los métodos relacionados con el semestre y la herencia de Reserva.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
class ReservaSemestreTest {

    private ReservaSemestre reservaSemestre;

    @BeforeEach
    void setUp() {
        reservaSemestre = new ReservaSemestre();
    }

    @Test
    void getSemestre() {
        reservaSemestre.setSemestre("2026-2");
        assertEquals("2026-2", reservaSemestre.getSemestre());
    }

    @Test
    void setSemestre() {
        reservaSemestre.setSemestre("2026-2");
        assertEquals("2026-2", reservaSemestre.getSemestre());

    }

    @Test
    void testHerencia() {
        reservaSemestre.setIdReserva("RES-002");
        assertEquals("RES-002", reservaSemestre.getIdReserva());
        reservaSemestre.aprobarReserva();
        assertEquals("aprobada", reservaSemestre.getEstadoReserva());
    }

    @Test
    void testToString() {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario("USR-002");
        Espacio espacio = new Espacio();
        espacio.setIdEspacio("ESP-002");
        reservaSemestre = new ReservaSemestre("RES-002", "2025/05/23 10:00", "2025/05/23 12:00", EstadoReserva.PENDIENTE, usuario, espacio, "2026-1");
        String expected = "ReservaSemestre{idReserva='RES-002', fechaInicio='2025/05/23 10:00', fechaFin='2025/05/23 12:00', estado='Pendiente', usuario=USR-002, espacio=ESP-002, semestre='2026-1'}";

    }
}