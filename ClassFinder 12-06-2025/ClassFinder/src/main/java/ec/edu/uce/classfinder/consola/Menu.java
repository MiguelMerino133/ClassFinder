package ec.edu.uce.classfinder.consola;

import ec.edu.uce.classfinder.dominio.Universidad;
import ec.edu.uce.classfinder.gui.*;

import java.util.Scanner;

/**
 * Menú principal del sistema ClassFinder.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class Menu {
    private Universidad universidad;
    private SubMenuIngresarSistema subMenuIngresarSistema;
    private SubMenuUsuarios subMenuUsuarios;
    private SubMenuLugares subMenuLugares;
    private SubMenuEspacios subMenuEspacios;
    private SubMenuReservas subMenuReservas;
    private Scanner scanner;

    public Menu() {
        universidad = Universidad.getInstance();
        subMenuIngresarSistema = new SubMenuIngresarSistema(universidad);
        subMenuUsuarios = new SubMenuUsuarios();
        subMenuLugares = new SubMenuLugares();
        subMenuEspacios = new SubMenuEspacios();
        subMenuReservas = new SubMenuReservas();
        scanner = new Scanner(System.in);
    }

    public void mostrarMenuPrincipal() {
        int opcion;
        do {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Ingresar al Sistema");
            System.out.println("2. Gestionar Usuarios");
            System.out.println("3. Gestionar Lugares");
            System.out.println("4. Gestionar Espacios");
            System.out.println("5. Gestionar Reservas");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            try {
                opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1:
                        subMenuIngresarSistema.mostrar();
                        break;
                    case 2:
                        subMenuUsuarios.mostrarMenu();
                        break;
                    case 3:
                        subMenuLugares.mostrarMenu();
                        break;
                    case 4:
                        subMenuEspacios.mostrarMenu();
                        break;
                    case 5:
                        subMenuReservas.mostrarMenu();
                        break;
                    case 6:
                        System.out.println("Saliendo del sistema...");
                        break;
                    default:
                        System.out.println("Opción inválida. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido.");
                opcion = -1;
            }
        } while (opcion!=6);
    }
}