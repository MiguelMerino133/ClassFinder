package ec.edu.uce.classfinder.consola;

import ec.edu.uce.classfinder.gui.GUIRegistrarUsuario;
import ec.edu.uce.classfinder.gui.GUIEditarUsuario;
import ec.edu.uce.classfinder.gui.GUIEliminarUsuario;
import ec.edu.uce.classfinder.modelo.Usuario;
import java.util.Scanner;

public class SubMenuUsuarios {

    public void mostrar(){
        Scanner entradaTeclado=new Scanner(System.in);
        int opcion;

        do {
            System.out.println("=== GESTIÓN DE USUARIOS ===");
            System.out.println("1. Registrar Usuario");
            System.out.println("2. Editar Usuario");
            System.out.println("3. Eliminar Usuario");
            System.out.println("4. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcion = entradaTeclado.nextInt();

            switch (opcion) {
                case 1:
                    GUIRegistrarUsuario guiRegistrar = new GUIRegistrarUsuario();
                    Usuario nuevoUsuario = guiRegistrar.registrar();
                    System.out.println("Usuario registrado: " + nuevoUsuario.getNombre());
                    break;
                case 2:
                    GUIEditarUsuario guiEditar = new GUIEditarUsuario();
                    Usuario usuarioEditado = guiEditar.editar();
                    System.out.println("Usuario editado: " + usuarioEditado.getNombre());
                    break;
                case 3:
                    GUIEliminarUsuario guiEliminar = new GUIEliminarUsuario();
                    String idUsuario = guiEliminar.eliminar();
                    System.out.println("Usuario con ID " + idUsuario + " eliminado.");
                    break;
                case 4:
                    System.out.println("Regresando al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }

        } while (opcion != 4);

    }

}
