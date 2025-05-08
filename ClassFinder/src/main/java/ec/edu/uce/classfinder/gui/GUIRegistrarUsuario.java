package ec.edu.uce.classfinder.gui;

import ec.edu.uce.classfinder.modelo.Usuario;
import java.util.Scanner;

public class GUIRegistrarUsuario {

    private Scanner entradaTeclado = new Scanner(System.in);

    public Usuario registrar() {
        System.out.println("\n=== REGISTRAR USUARIO ===");
        Usuario usuario = new Usuario();

        System.out.print("Ingrese ID del usuario: ");
        usuario.setIdUsuario(entradaTeclado.nextLine());

        System.out.print("Ingrese nombre: ");
        usuario.setNombre(entradaTeclado.nextLine());

        System.out.println("Ingrese una contraseña");
        usuario.setContrasena(entradaTeclado.nextLine());

        System.out.print("Ingrese cédula de identidad: ");
        usuario.setCedulaIdentidad(entradaTeclado.nextLine());

        System.out.print("Ingrese correo: ");
        usuario.setCorreo(entradaTeclado.nextLine());

        System.out.print("Ingrese rol (administrador/docente/estudiante/invitado): ");
        usuario.setRol(entradaTeclado.nextLine());

        return usuario;
    }


}
