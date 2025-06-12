package ec.edu.uce.classfinder.consola;

import ec.edu.uce.classfinder.dominio.Universidad;
import ec.edu.uce.classfinder.dominio.Usuario;
import ec.edu.uce.classfinder.gui.GUIUsuario;

import java.util.Scanner;

/**
 * Submenú para gestionar usuarios en el sistema ClassFinder.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class SubMenuUsuarios {
    private Scanner scanner;
    private GUIUsuario guiUsuario;

    public SubMenuUsuarios() {
        scanner = new Scanner(System.in);
        guiUsuario = new GUIUsuario();
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n=== MENÚ DE USUARIOS ===");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Consultar usuario");
            System.out.println("3. Editar usuario");
            System.out.println("4. Eliminar usuario");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            try {
                opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1:
                        guiUsuario.registrar(); // Solo llamar a registrar, GUI maneja mensajes
                        break;
                    case 2: guiUsuario.consultar(); break;
                    case 3: {
                        Usuario usuario = guiUsuario.editar();
                        if (usuario != null) {
                            System.out.println("Usuario actualizado con éxito.");
                        } else {
                            System.out.println("Error al actualizar el usuario.");
                        }
                        break;
                    }
                    case 4: {
                        if (guiUsuario.eliminar()) {
                            System.out.println("Usuario eliminado con éxito.");
                        } else {
                            System.out.println("Error al eliminar el usuario.");
                        }
                        break;
                    }
                    case 5: System.out.println("Volviendo al menú principal..."); break;
                    default: System.out.println("Opción inválida. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido.");
                opcion = -1;
            }
        } while (opcion != 5);
    }
}