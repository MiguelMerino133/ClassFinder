package ec.edu.uce.classfinder.dominio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba unitaria para la clase Aula.
 * Verifica el comportamiento de los métodos relacionados con el número de aula y su asignación.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
class AulaTest {

    private Aula aula;

    @BeforeEach
    void setUp() {
        aula = new Aula();
    }

    @Test
    void getNumeroAula() {
        aula.setNumeroAula("A-101");
        assertEquals("A-101", aula.getNumeroAula());
    }

    @Test
    void setNumeroAula() {
        aula.setNumeroAula("A-102");
        assertEquals("A-102", aula.getNumeroAula());

    }


    @Test
    void testToString() {
        aula.setNumeroAula("A-101");
        String expected = "Aula{numeroAula=A-101}";
        assertTrue(aula.toString().contains(expected));
    }
}