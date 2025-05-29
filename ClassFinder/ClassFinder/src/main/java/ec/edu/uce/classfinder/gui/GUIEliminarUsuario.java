package ec.edu.uce.classfinder.gui;

import ec.edu.uce.classfinder.dominio.Universidad;
import ec.edu.uce.classfinder.dominio.Usuario;

import java.util.Scanner;

/**
 * Interfaz gráfica para eliminar un usuario en el sistema ClassFinder.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class GUIEliminarUsuario {

    private Scanner entradaTeclado = new Scanner(System.in);

    /**
     * Permite eliminar un usuario.
     * @param universidad Instancia de Universidad para verificar usuarios
     * @return null si se confirma la eliminación, un usuario vacío si se cancela
     */
    public Usuario eliminar(Universidad universidad) {
        if (universidad == null || universidad.getNumUsuarios() == 0) {
            System.out.println("Error: No hay usuarios disponibles para eliminar.");
            return null;
        }

        System.out.println("\n=== ELIMINAR USUARIO ===");
        System.out.println("Lista de usuarios actuales:");
        System.out.println(universidad.consultarUsuarios());

        int pos;
        do {
            System.out.print("Ingrese la posición del usuario a eliminar ");
            if (entradaTeclado.hasNextInt()) {
                pos = entradaTeclado.nextInt();
                entradaTeclado.nextLine();
                if (pos < 0 || pos >= universidad.getNumUsuarios()) {
                    System.out.println("Error: Posición inválida.");
                } else {
                    break;
                }
            } else {
                System.out.println("Error: Debe ingresar un número válido.");
                entradaTeclado.nextLine();
            }
        } while (true);

        Usuario usuario = universidad.getUsuarios()[pos];
        System.out.print("¿Está seguro de eliminar el usuario con ID " + usuario.getIdUsuario() + "? (s/n): ");
        String confirmacion = entradaTeclado.nextLine().toLowerCase();
        if (confirmacion.equals("s")) {
            System.out.println("Usuario con ID " + usuario.getIdUsuario() + " marcado para eliminación.");
            return null; // Indica que se debe eliminar
        } else {
            System.out.println("Eliminación cancelada.");
            return new Usuario(); // Retorna un usuario vacío si no se confirma
        }
    }
}