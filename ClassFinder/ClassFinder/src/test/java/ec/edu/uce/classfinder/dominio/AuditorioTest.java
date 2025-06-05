package ec.edu.uce.classfinder.dominio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba unitaria para la clase Auditorio.
 * Verifica el comportamiento de los métodos relacionados con el equipo de sonido y la configuración.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
class AuditorioTest {

    private Auditorio auditorio;

    @BeforeEach
    void setUp() {
        auditorio = new Auditorio();
    }

    @Test
    void isEquipoSonido() {
        auditorio.setEquipoSonido(false);
        assertFalse(auditorio.isEquipoSonido()); // Corrección: debería ser false
    }

    @Test
    void setEquipoSonido() {
        auditorio.setEquipoSonido(true);
        assertTrue(auditorio.isEquipoSonido());
    }

    @Test
    void testToString() {
        auditorio.setEquipoSonido(true);
        String expected = "Auditorio{equipoSonido=true}";
        assertTrue(auditorio.toString().contains(expected));
    }
}