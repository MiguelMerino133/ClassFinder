package ec.edu.uce.classfinder.gui;

import ec.edu.uce.classfinder.modelo.Usuario;
import java.util.Scanner;

public class GUIEditarUsuario {

    private Scanner entradaTeclado = new Scanner(System.in);

    public Usuario editar() {
        System.out.println("\n=== EDITAR USUARIO ===");
        Usuario usuario = new Usuario();
        System.out.print("Ingrese ID del usuario a editar: ");
        usuario.setIdUsuario(entradaTeclado.nextLine());
        System.out.print("Ingrese nuevo nombre: ");
        usuario.setNombre(entradaTeclado.nextLine());
        System.out.print("Ingrese nueva cédula de identidad: ");
        usuario.setCedulaIdentidad(entradaTeclado.nextLine());
        System.out.print("Ingrese nuevo correo: ");
        usuario.setCorreo(entradaTeclado.nextLine());
        System.out.print("Ingrese nuevo rol (administrador/docente/estudiante/invitado): ");
        usuario.setRol(entradaTeclado.nextLine());
        return usuario;
    }

}
