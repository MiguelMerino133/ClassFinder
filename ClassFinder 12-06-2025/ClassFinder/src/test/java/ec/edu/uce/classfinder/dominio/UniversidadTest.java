package ec.edu.uce.classfinder.dominio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba unitaria para la clase Universidad.
 * Verifica el comportamiento de los métodos relacionados con ID, nombre, rector, teléfono y las operaciones CRUD de lugares, usuarios y reservas.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
class UniversidadTest {

    private Universidad universidad;

    @BeforeEach
    void setUp() {
        universidad = new Universidad();
    }

    @Test
    void getIdUniversidad() {
        universidad.setIdUniversidad("UNI-002");
        assertEquals("UNI-002", universidad.getIdUniversidad());
    }

    @Test
    void setIdUniversidad() {
        universidad.setIdUniversidad("UNI-002");
        assertEquals("UNI-002", universidad.getIdUniversidad());

        universidad.setIdUniversidad(null); // Prueba con valor inválido
        assertEquals("UNI-001", universidad.getIdUniversidad());
    }

    @Test
    void getNombreUniversidad() {
        universidad.setNombreUniversidad("UCE");
        assertEquals("UCE", universidad.getNombreUniversidad());
    }

    @Test
    void setNombreUniversidad() {
        universidad.setNombreUniversidad("UCE");
        assertEquals("UCE", universidad.getNombreUniversidad());

        universidad.setNombreUniversidad(null); // Prueba con valor inválido
        assertEquals("Universidad Central", universidad.getNombreUniversidad());
    }

    @Test
    void getNombreRector() {
        universidad.setNombreRector("Dra Maria Lopez");
        assertEquals("Dra Maria Lopez", universidad.getNombreRector());
    }

    @Test
    void setNombreRector() {
        universidad.setNombreRector("Dra Maria Lopez");
        assertEquals("Dra Maria Lopez", universidad.getNombreRector());

    }

    @Test
    void getTelefono() {
        universidad.setTelefono("0987654321");
        assertEquals("0987654321", universidad.getTelefono());
    }

    @Test
    void setTelefono() {
        universidad.setTelefono("0987654321");
        assertEquals("0987654321", universidad.getTelefono());

        universidad.setTelefono("123"); // Prueba con valor inválido (menos de 10 dígitos)
        assertEquals("0991234567", universidad.getTelefono());
    }

    @Test
    void getNumLugares() {
        assertEquals(0, universidad.getNumLugares());
        universidad.crearLugar(new Lugar());
        assertEquals(1, universidad.getNumLugares());
    }

    @Test
    void getLugares() {
        Lugar lugar = new Lugar();
        universidad.crearLugar(lugar);
        assertEquals(lugar, universidad.getLugares()[0]);
    }

    @Test
    void getNumReservas() {
        assertEquals(0, universidad.getNumReservas());
        universidad.crearReserva(new Reserva());
        assertEquals(1, universidad.getNumReservas());
    }

    @Test
    void getReservas() {
        Reserva reserva = new Reserva();
        universidad.crearReserva(reserva);
        assertEquals(reserva, universidad.getReservas()[0]);
    }

    @Test
    void getNumUsuarios() {
        assertEquals(0, universidad.getNumUsuarios());
        universidad.crearUsuario(new Usuario());
        assertEquals(1, universidad.getNumUsuarios());
    }

    @Test
    void getUsuarios() {
        Usuario usuario = new Usuario();
        universidad.crearUsuario(usuario);
        assertEquals(usuario, universidad.getUsuarios()[0]);
    }

    @Test
    void crearLugar() {
        Lugar lugar = new Lugar("LUG-001", "Facultad de Ingenieria", "Edificio diagonal a la entrada de la gasca");
        assertTrue(universidad.crearLugar(lugar));
        assertEquals(1, universidad.getNumLugares());
        assertEquals(lugar, universidad.getLugares()[0]);

        assertFalse(universidad.crearLugar(null)); // Prueba con valor nulo
    }

    @Test
    void consultarLugares() {
        Lugar lugar1 = new Lugar("LUG-001", "Biblioteca", "Biblioteca ubicada dentro de Edificio Civil");
        Lugar lugar2 = new Lugar("LUG-002", "Auditorio", "Ubicado dentro de Edificio Civil ");
        universidad.crearLugar(lugar1);
        universidad.crearLugar(lugar2);
        String resultado = universidad.consultarLugares();
        assertTrue(resultado.contains("0: Lugar{idLugar='LUG-001'"));
        assertTrue(resultado.contains("1: Lugar{idLugar='LUG-002'"));

    }

    @Test
    void editarLugar() {
        Lugar lugar1 = new Lugar("LUG-001", "Biblioteca", "Área de estudio");
        universidad.crearLugar(lugar1);
        Lugar lugarActualizado = new Lugar("LUG-001", "Biblioteca Nueva", "Área renovada");
        assertTrue(universidad.editarLugar(0, lugarActualizado));
        assertEquals("Biblioteca Nueva", universidad.getLugares()[0].getNombre());

    }

    @Test
    void eliminarLugar() {
        Lugar lugar = new Lugar();
        universidad.crearLugar(lugar);
        assertTrue(universidad.eliminarLugar(0));
        assertEquals(0, universidad.getNumLugares());
        assertNull(universidad.getLugares()[0]);

    }

    @Test
    void agregarEdificio() {
        Edificio edificio = new Edificio("EDI-001", "Edificio A", "Edificio principal", 5, "Campus Central");
        assertTrue(universidad.agregarEdificio(edificio));
        assertEquals(1, universidad.getNumLugares());
        assertEquals(edificio, universidad.getLugares()[0]);
    }

    @Test
    void crearUsuario() {
        Usuario usuario = new Usuario("USR-001", "Juan", "pass123", "1728803247", "juan@gmail.com", Rol.ESTUDIANTE);
        assertTrue(universidad.crearUsuario(usuario));
        assertEquals(1, universidad.getNumUsuarios());
        assertEquals(usuario, universidad.getUsuarios()[0]);

    }

    @Test
    void consultarUsuarios() {
        Usuario usuario1 = new Usuario("USR-001", "Juan", "pass123", "1728803247", "juan@gmail.com", Rol.ESTUDIANTE);
        Usuario usuario2 = new Usuario("USR-002", "María", "pass456", "1728803248", "maria@gmail.com", Rol.DOCENTE);
        universidad.crearUsuario(usuario1);
        universidad.crearUsuario(usuario2);
        String resultado = universidad.consultarUsuarios();
        assertTrue(resultado.contains("0: Usuario{idUsuario='USR-001'"));
        assertTrue(resultado.contains("1: Usuario{idUsuario='USR-002'"));

    }

    @Test
    void editarUsuario() {
        Usuario usuario1 = new Usuario("USR-001", "Juan", "pass123", "1728803247", "juan@gmail.com", Rol.ESTUDIANTE);
        universidad.crearUsuario(usuario1);
        Usuario usuarioActualizado = new Usuario("USR-001", "Juan Actualizado", "newpass", "1728803247", "juan@gmail.com", Rol.DOCENTE);
        assertTrue(universidad.editarUsuario(0, usuarioActualizado));
        assertEquals("Juan Actualizado", universidad.getUsuarios()[0].getNombre());

    }

    @Test
    void eliminarUsuario() {
        Usuario usuario = new Usuario("USR-001", "Juan", "pass123", "1728803247", "juan@gmail.com", Rol.ESTUDIANTE);
        universidad.crearUsuario(usuario);
        assertTrue(universidad.eliminarUsuario(0));
        assertEquals(0, universidad.getNumUsuarios());
        assertNull(universidad.getUsuarios()[0]);

    }

    @Test
    void crearReserva() {
        Reserva reserva = new Reserva();
        reserva.setIdReserva("RES-001");
        assertTrue(universidad.crearReserva(reserva));
        assertEquals(1, universidad.getNumReservas());
        assertEquals(reserva, universidad.getReservas()[0]);

    }

    @Test
    void consultarReservas() {
        Reserva reserva1 = new Reserva();
        reserva1.setIdReserva("RES-001");
        Reserva reserva2 = new Reserva();
        reserva2.setIdReserva("RES-002");
        universidad.crearReserva(reserva1);
        universidad.crearReserva(reserva2);
        String resultado = universidad.consultarReservas();
        assertTrue(resultado.contains("0: Reserva{idReserva='RES-001'"));
        assertTrue(resultado.contains("1: Reserva{idReserva='RES-002'"));

    }

    @Test
    void editarReserva() {
        Reserva reserva1 = new Reserva();
        reserva1.setIdReserva("RES-001");
        universidad.crearReserva(reserva1);
        Reserva reservaActualizada = new Reserva();
        reservaActualizada.setIdReserva("RES-001");
        assertTrue(universidad.editarReserva(0, reservaActualizada));
        assertEquals("aprobada", universidad.getReservas()[0].getEstadoReserva());
    }

    @Test
    void eliminarReserva() {
        Reserva reserva = new Reserva();
        reserva.setIdReserva("RES-001");
        universidad.crearReserva(reserva);
        assertTrue(universidad.eliminarReserva(0));
        assertEquals(0, universidad.getNumReservas());
        assertNull(universidad.getReservas()[0]);
    }

    @Test
    void testToString() {
        universidad.setIdUniversidad("UNI-002");
        universidad.setNombreUniversidad("UCE");
        universidad.setNombreRector("Dra Maria Lopez");
        universidad.setTelefono("0987654321");
        String expected = "Universidad{idUniversidad='UNI-002', nombreUniversidad='UCE', nombreRector='Dra Maria Lopez', telefono='0987654321'}";
        assertEquals(expected, universidad.toString());
    }
}