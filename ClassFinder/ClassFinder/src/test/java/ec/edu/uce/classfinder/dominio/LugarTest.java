package ec.edu.uce.classfinder.dominio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba unitaria para la clase Lugar.
 * Verifica el comportamiento de los métodos relacionados con ID, nombre, descripción y gestión de espacios.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
class LugarTest {

    private Lugar lugar;

    @BeforeEach
    void setUp() {
        lugar = new Lugar();
    }

    @Test
    void getIdLugar() {
        lugar.setIdLugar("LUG-002");
        assertEquals("LUG-002", lugar.getIdLugar());
    }

    @Test
    void setIdLugar() {
        lugar.setIdLugar("LUG-002");
        assertEquals("LUG-002", lugar.getIdLugar());

    }

    @Test
    void getNombre() {
        lugar.setNombre("Biblioteca");
        assertEquals("Biblioteca", lugar.getNombre());
    }

    @Test
    void setNombre() {
        lugar.setNombre("Biblioteca");
        assertEquals("Biblioteca", lugar.getNombre());

    }

    @Test
    void getDescripcion() {
        lugar.setDescripcion("Área de estudio");
        assertEquals("Área de estudio", lugar.getDescripcion());
    }

    @Test
    void setDescripcion() {
        lugar.setDescripcion("Área de estudio");
        assertEquals("Área de estudio", lugar.getDescripcion());

    }

    @Test
    void getEspacios() {
        Espacio espacio = new Espacio();
        lugar.crearEspacio(espacio);
        assertEquals(espacio, lugar.getEspacios()[0]);
    }

    @Test
    void setEspacios() {
        Espacio[] espacios = new Espacio[3];
        espacios[0] = new Espacio("ESP-001", "Aula 101", 30, "mediano");
        espacios[1] = new Espacio("ESP-002", "Aula 102", 40, "grande");
        lugar.setEspacios(espacios);
        assertEquals(2, lugar.getNumEspacios());
        assertEquals("ESP-001", lugar.getEspacios()[0].getIdEspacio());

    }

    @Test
    void getNumEspacios() {
        Espacio espacio = new Espacio();
        lugar.crearEspacio(espacio);
        assertEquals(1, lugar.getNumEspacios());
    }

    @Test
    void setNumEspacios() {
        lugar.setNumEspacios(3);
        assertEquals(3, lugar.getNumEspacios());


    }

    @Test
    void crearEspacio() {
        Espacio espacio = new Espacio("ESP-001", "Aula 101", 30, "mediano");
        assertTrue(lugar.crearEspacio(espacio));
        assertEquals(1, lugar.getNumEspacios());
        assertEquals(espacio, lugar.getEspacios()[0]);

    }

    @Test
    void consultarEspacios() {
        Espacio espacio1 = new Espacio("ESP-001", "Aula 101", 30, "mediano");
        Espacio espacio2 = new Espacio("ESP-002", "Aula 102", 40, "grande");
        lugar.crearEspacio(espacio1);
        lugar.crearEspacio(espacio2);
        String resultado = lugar.consultarEspacios();
        assertTrue(resultado.contains("idEspacio='ESP-001'"));
        assertTrue(resultado.contains("idEspacio='ESP-002'"));

        lugar.eliminarEspacio(0);
        lugar.eliminarEspacio(0);
        assertEquals("No hay espacios registrados.", lugar.consultarEspacios());
    }

    @Test
    void editarEspacio() {
        Espacio espacio = new Espacio("ESP-001", "Aula 101", 30, "mediano");
        lugar.crearEspacio(espacio);
        Espacio espacioActualizado = new Espacio("ESP-001", "Aula 101 Nueva", 50, "grande");
        assertTrue(lugar.editarEspacio(0, espacioActualizado));
        assertEquals("Aula 101 Nueva", lugar.getEspacios()[0].getNombre());

    }

    @Test
    void eliminarEspacio() {
        Espacio espacio = new Espacio("ESP-001", "Aula 101", 30, "mediano");
        lugar.crearEspacio(espacio);
        assertTrue(lugar.eliminarEspacio(0));
        assertEquals(0, lugar.getNumEspacios());
        assertNull(lugar.getEspacios()[0]);

    }

    @Test
    void testToString() {
        lugar.setIdLugar("LUG-002");
        lugar.setNombre("Biblioteca");
        lugar.setDescripcion("Área de estudio");
        String expected = "Lugar{idLugar='LUG-002', nombre='Biblioteca', descripcion='Área de estudio'}";
        assertEquals(expected, lugar.toString());
    }
}