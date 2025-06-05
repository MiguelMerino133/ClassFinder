package ec.edu.uce.classfinder.dominio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba unitaria para la clase Usuario.
 * Verifica el comportamiento de los métodos relacionados con ID, nombre, contraseña, cédula, correo y rol.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
class UsuarioTest {

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
    }

    @Test
    void getIdUsuario() {
        usuario.setIdUsuario("USR-002");
        assertEquals("USR-002", usuario.getIdUsuario());
    }

    @Test
    void setIdUsuario() {
        usuario.setIdUsuario("USR-002");
        assertEquals("USR-002", usuario.getIdUsuario());

    }

    @Test
    void getNombre() {
        usuario.setNombre("Ana");
        assertEquals("Ana", usuario.getNombre());
    }

    @Test
    void setNombre() {
        usuario.setNombre("Ana");
        assertEquals("Ana", usuario.getNombre());

    }

    @Test
    void getContrasena() {
        usuario.setContrasena("abc123");
        assertEquals("abc123", usuario.getContrasena());
    }

    @Test
    void setContrasena() {
        usuario.setContrasena("abc123");
        assertEquals("abc123", usuario.getContrasena());
    }

    @Test
    void getCedulaIdentidad() {
        usuario.setCedulaIdentidad("1728803247");
        assertEquals("1728803247", usuario.getCedulaIdentidad());
    }

    @Test
    void setCedulaIdentidad() {
        usuario.setCedulaIdentidad("1728803247");
        assertEquals("1728803247", usuario.getCedulaIdentidad());

    }

    @Test
    void getCorreo() {
        usuario.setCorreo("ana@gmail.com");
        assertEquals("ana@gmail.com", usuario.getCorreo());
    }

    @Test
    void setCorreo() {
        usuario.setCorreo("ana@gmail.com");
        assertEquals("ana@gmail.com", usuario.getCorreo());

    }

    @Test
    void getRol() {
        usuario.setRol(Rol.ESTUDIANTE);
        assertEquals(Rol.ESTUDIANTE, usuario.getRol());
    }

    @Test
    void setRol() {
        usuario.setRol(Rol.ESTUDIANTE);
        assertEquals(Rol.ESTUDIANTE, usuario.getRol());

    }

    @Test
    void testToString() {
        usuario.setIdUsuario("USR-002");
        usuario.setNombre("Ana");
        usuario.setCedulaIdentidad("1728803247");
        usuario.setCorreo("ana@gmail.com");
        usuario.setRol(Rol.ESTUDIANTE);
        String expected = "Usuario{idUsuario='USR-002', nombre='Ana', cedulaIdentidad='1728803247', correo='ana@gmail.com', rol='Docente'}";
        assertEquals(expected, usuario.toString());
    }
}