package ec.edu.uce.classfinder.test;

import ec.edu.uce.classfinder.modelo.Usuario;

public class UsuarioTest {

    public static void main(String[] args) {

        Usuario usuario = new Usuario("U001", "Miguel Merino", "1728803246", "mdmerino@uce.edu.ec", "Estudiante");


        usuario.setIdUsuario("U001");
        usuario.setNombre("Miguel Merino");
        usuario.setCedulaIdentidad("1728803246");
        usuario.setCorreo("mdmerino@uce.edu.ec");
        usuario.setRol("Estudiante");
        usuario.setContrasena("pass123");


        System.out.println("ID del usuario: " + usuario.getIdUsuario());
        System.out.println("Nombre: " + usuario.getNombre());
        System.out.println("Cédula: " + usuario.getCedulaIdentidad());
        System.out.println("Correo: " + usuario.getCorreo());
        System.out.println("Rol: " + usuario.getRol());
        System.out.println("Contraseña: " + usuario.getContrasena());


        usuario.registrarUsuario();
        usuario.registrarReserva();
        usuario.consultarEspacio();
        usuario.consultarLugar();
        usuario.cancelarReserva();
    }

}
