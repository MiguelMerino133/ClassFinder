package ec.edu.uce.classfinder.consola;

import ec.edu.uce.classfinder.dominio.Lugar;
import ec.edu.uce.classfinder.dominio.Universidad;
import ec.edu.uce.classfinder.gui.GUILugar;

import java.util.Scanner;

/**
 * Submenú para gestionar lugares en el sistema ClassFinder.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class SubMenuLugares {
    private Scanner scanner;
    private GUILugar guiLugar;

    public SubMenuLugares() {
        scanner = new Scanner(System.in);
        guiLugar = new GUILugar();
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n=== MENÚ DE LUGARES ===");
            System.out.println("1. Registrar lugar");
            System.out.println("2. Consultar lugar");
            System.out.println("3. Editar lugar");
            System.out.println("4. Eliminar lugar");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            try {
                opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1:
                        guiLugar.registrar(); // Solo llamar a registrar
                        break;
                    case 2: guiLugar.consultar(); break;
                    case 3: {
                        Lugar lugar = guiLugar.editar();
                        if (lugar != null) {
                            System.out.println("Lugar actualizado con éxito.");
                        } else {
                            System.out.println("Error al actualizar el lugar.");
                        }
                        break;
                    }
                    case 4: {
                        if (guiLugar.eliminar()) {
                            System.out.println("Lugar eliminado con éxito.");
                        } else {
                            System.out.println("Error al eliminar el lugar.");
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