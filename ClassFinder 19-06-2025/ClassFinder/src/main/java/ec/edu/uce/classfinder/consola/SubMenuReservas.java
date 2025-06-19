package ec.edu.uce.classfinder.consola;

import ec.edu.uce.classfinder.dominio.Universidad;
import ec.edu.uce.classfinder.gui.GUIReserva;

import java.util.Scanner;

/**
 * Submenú para gestionar reservas en el sistema ClassFinder.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class SubMenuReservas {
    private Scanner scanner;
    private GUIReserva guiReserva;

    public SubMenuReservas() {
        scanner = new Scanner(System.in);
        guiReserva = new GUIReserva();
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n=== MENÚ DE RESERVAS ===");
            System.out.println("1. Registrar reserva");
            System.out.println("2. Consultar reserva");
            System.out.println("3. Editar reserva");
            System.out.println("4. Eliminar reserva");
            System.out.println("5. Aprobar/Rechazar reserva");
            System.out.println("6. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            try {
                opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1:
                        System.out.println(guiReserva.registrar());
                        break;
                    case 2:
                        System.out.println(guiReserva.consultar());
                        break;
                    case 3:
                        System.out.println(guiReserva.editar());
                        break;
                    case 4:
                        System.out.println(guiReserva.eliminar());
                        break;
                    case 5:
                        System.out.println(guiReserva.aprobarRechazar());
                        break;
                    case 6:
                        System.out.println("Volviendo al menú principal...");
                        break;
                    default:
                        System.out.println("Opción inválida. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido.");
                opcion = -1;
            }
        } while (opcion != 6);
    }
}