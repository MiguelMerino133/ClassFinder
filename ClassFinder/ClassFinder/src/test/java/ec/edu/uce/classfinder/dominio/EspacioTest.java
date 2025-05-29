package ec.edu.uce.classfinder.dominio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba unitaria para la clase Espacio.
 * Verifica el comportamiento de los métodos relacionados con ID, nombre, capacidad y tamaño.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
class EspacioTest {

    private Espacio espacio;

    @BeforeEach
    void setUp() {
        espacio = new Espacio();
    }

    @Test
    void getIdEspacio() {
        espacio.setIdEspacio("ESP-002");
        assertEquals("ESP-002", espacio.getIdEspacio());
    }

    @Test
    void setIdEspacio() {
        espacio.setIdEspacio("ESP-002");
        assertEquals("ESP-002", espacio.getIdEspacio());
    }

    @Test
    void getNombre() {
        espacio.setNombre("Aula 101");
        assertEquals("Aula 101", espacio.getNombre());
    }

    @Test
    void setNombre() {
        espacio.setNombre("Aula 101");
        assertEquals("Aula 101", espacio.getNombre());

    }

    @Test
    void getCapacidad() {
        espacio.setCapacidad(50);
        assertEquals(50, espacio.getCapacidad());
    }

    @Test
    void setCapacidad() {
        espacio.setCapacidad(50);
        assertEquals(50, espacio.getCapacidad());
    }

    @Test
    void getTamano() {
        espacio.setTamano("grande");
        assertEquals("grande", espacio.getTamano());
    }

    @Test
    void setTamano() {
        espacio.setTamano("grande");
        assertEquals("grande", espacio.getTamano());
    }

    @Test
    void testToString() {
        espacio.setIdEspacio("ESP-002");
        espacio.setNombre("Aula 101");
        espacio.setCapacidad(50);
        espacio.setTamano("grande");
        String expected = "Espacio{idEspacio='ESP-002', nombre='Aula 101', capacidad=50, tamano='grande'}";
        assertEquals(expected, espacio.toString());
    }
}