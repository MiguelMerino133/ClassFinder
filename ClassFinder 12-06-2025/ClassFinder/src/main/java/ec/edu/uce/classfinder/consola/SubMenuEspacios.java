package ec.edu.uce.classfinder.consola;

import ec.edu.uce.classfinder.dominio.Espacio;
import ec.edu.uce.classfinder.dominio.Lugar;
import ec.edu.uce.classfinder.dominio.Universidad;
import ec.edu.uce.classfinder.gui.GUIEspacio;

import java.util.Scanner;

/**
 * Submenú para gestionar espacios en el sistema ClassFinder.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class SubMenuEspacios {
    private Scanner scanner;
    private GUIEspacio guiEspacio;

    public SubMenuEspacios() {
        scanner = new Scanner(System.in);
        guiEspacio = new GUIEspacio();
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n=== MENÚ DE ESPACIOS ===");
            System.out.println("1. Registrar espacio");
            System.out.println("2. Consultar espacio");
            System.out.println("3. Editar espacio");
            System.out.println("4. Eliminar espacio");
            System.out.println("5. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            try {
                opcion = Integer.parseInt(scanner.nextLine());
                switch (opcion) {
                    case 1: {
                        System.out.println("\nLugares disponibles:");
                        System.out.println(Universidad.getInstance().consultarLugares());
                        System.out.print("Ingrese el número del lugar: ");
                        int posLugar = Integer.parseInt(scanner.nextLine()) - 1;
                        if (posLugar >= 0 && posLugar < Universidad.getInstance().getNumLugares()) {
                            Lugar lugar = Universidad.getInstance().getLugares()[posLugar];
                            guiEspacio.registrar(lugar); // Solo llamar a registrar
                        } else {
                            System.out.println("Lugar inválido.");
                        }
                        break;
                    }
                    case 2: guiEspacio.consultar(); break;
                    case 3: {
                        System.out.println("\nLugares disponibles:");
                        System.out.println(Universidad.getInstance().consultarLugares());
                        System.out.print("Ingrese el número del lugar: ");
                        int posLugar = Integer.parseInt(scanner.nextLine()) - 1;
                        if (posLugar >= 0 && posLugar < Universidad.getInstance().getNumLugares()) {
                            Lugar lugar = Universidad.getInstance().getLugares()[posLugar];
                            Espacio espacio = guiEspacio.editar(lugar);
                            if (espacio != null) {
                                System.out.println("Espacio actualizado con éxito.");
                            } else {
                                System.out.println("Error al actualizar el espacio.");
                            }
                        } else {
                            System.out.println("Lugar inválido.");
                        }
                        break;
                    }
                    case 4: {
                        System.out.println("\nLugares disponibles:");
                        System.out.println(Universidad.getInstance().consultarLugares());
                        System.out.print("Ingrese el número del lugar: ");
                        int posLugar = Integer.parseInt(scanner.nextLine()) - 1;
                        if (posLugar >= 0 && posLugar < Universidad.getInstance().getNumLugares()) {
                            Lugar lugar = Universidad.getInstance().getLugares()[posLugar];
                            if (guiEspacio.eliminarEspacio(lugar)) {
                                System.out.println("Espacio eliminado con éxito.");
                            } else {
                                System.out.println("Error al eliminar el espacio.");
                            }
                        } else {
                            System.out.println("Lugar inválido.");
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