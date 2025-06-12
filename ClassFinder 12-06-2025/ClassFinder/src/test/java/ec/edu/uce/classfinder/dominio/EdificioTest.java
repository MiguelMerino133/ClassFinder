package ec.edu.uce.classfinder.dominio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba unitaria para la clase Edificio.
 * Verifica el comportamiento de los métodos relacionados con el número de pisos y la ubicación.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
class EdificioTest {

    private Edificio edificio;

    @BeforeEach
    void setUp() {
        edificio = new Edificio();
    }

    @Test
    void getNumeroPisos() {
        edificio.setNumeroPisos(5);
        assertEquals(5, edificio.getNumeroPisos());
    }

    @Test
    void setNumeroPisos() {
        edificio.setNumeroPisos(5);
        assertEquals(5, edificio.getNumeroPisos());

    }

    @Test
    void getUbicacion() {
        edificio.setUbicacion("Campus Norte");
        assertEquals("Campus Norte", edificio.getUbicacion());
    }

    @Test
    void setUbicacion() {
        edificio.setUbicacion("Campus Sur");
        assertEquals("Campus Sur", edificio.getUbicacion());


    }
    @Test
    void testToString() {
        edificio.setNumeroPisos(5);
        edificio.setUbicacion("Campus Norte");
        String expected = "Edificio{numeroPisos=5, ubicacion=Campus Norte}";
        assertTrue(edificio.toString().contains(expected));
    }
}