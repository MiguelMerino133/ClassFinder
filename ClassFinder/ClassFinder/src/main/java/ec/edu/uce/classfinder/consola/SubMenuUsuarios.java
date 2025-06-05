package ec.edu.uce.classfinder.consola;

import ec.edu.uce.classfinder.dominio.Universidad;
import ec.edu.uce.classfinder.dominio.Usuario;
import ec.edu.uce.classfinder.gui.GUIUsuario;
import ec.edu.uce.classfinder.util.Validadores;
import java.util.Scanner;

/**
 * Submenú para gestionar usuarios en el sistema ClassFinder.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class SubMenuUsuarios {
    private Scanner entradaTeclado;
    private Universidad universidad;
    private GUIUsuario guiUsuario;

    public SubMenuUsuarios(Scanner entradaTeclado, Universidad universidad) {
        this.entradaTeclado = entradaTeclado;
        this.universidad = universidad;
        this.guiUsuario = new GUIUsuario(universidad);
    }

    public void mostrarMenuUsuarios() {
        int opcion;

        do {
            System.out.println("\n=== MENÚ USUARIOS ===");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Consultar usuario");
            System.out.println("3. Editar usuario");
            System.out.println("4. Eliminar usuario");
            System.out.println("5. Regresar al menú principal");
            System.out.print("Seleccione una opción: ");

            String entrada = entradaTeclado.nextLine();
            if (!Validadores.esNumeroValido(entrada)) {
                System.out.println("Error: Debe ingresar un número válido.");
                opcion = 0;
                continue;
            }

            opcion = Integer.parseInt(entrada);
            switch (opcion) {
                case 1:
                    Usuario nuevoUsuario = guiUsuario.registrar();
                    if (nuevoUsuario != null && universidad.validarDuplicado(nuevoUsuario)) {
                        if (universidad.crearUsuario(nuevoUsuario)) {
                            System.out.println("Usuario registrado: " + nuevoUsuario.getNombre());
                        } else {
                            System.out.println("Error al registrar usuario.");
                        }
                    } else {
                        System.out.println("Error: Usuario duplicado o datos inválidos.");
                    }
                    break;
                case 2:
                    guiUsuario.consultar();
                    break;
                case 3:
                    Usuario usuarioEditado = guiUsuario.editar();
                    if (usuarioEditado != null) {
                        System.out.println("Usuario actualizado: " + usuarioEditado.getNombre());
                    }
                    break;
                case 4:
                    if (guiUsuario.eliminar()) {
                        System.out.println("Usuario eliminado correctamente.");
                    }
                    break;
                case 5:
                    System.out.println("Regresando al menú principal...");
                    break;
                default:
                    System.out.println("Error: Opción inválida.");
            }
        } while (opcion != 5);
    }
}