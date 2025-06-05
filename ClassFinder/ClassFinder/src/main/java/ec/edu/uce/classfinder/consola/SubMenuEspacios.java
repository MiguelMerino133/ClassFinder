package ec.edu.uce.classfinder.consola;

import ec.edu.uce.classfinder.dominio.Espacio;
import ec.edu.uce.classfinder.dominio.Lugar;
import ec.edu.uce.classfinder.dominio.Universidad;
import ec.edu.uce.classfinder.gui.GUIEspacio;
import ec.edu.uce.classfinder.util.Validadores;
import java.util.Scanner;

/**
 * Submenú para gestionar espacios en el sistema ClassFinder.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class SubMenuEspacios {
    private Scanner entradaTeclado;
    private Universidad universidad;
    private GUIEspacio guiEspacio;

    public SubMenuEspacios(Scanner entradaTeclado, Universidad universidad) {
        this.entradaTeclado = entradaTeclado;
        this.universidad = universidad;
        this.guiEspacio = new GUIEspacio(universidad);
    }

    public void mostrarMenuEspacios() {
        int opcion;

        do {
            System.out.println("\n=== MENÚ ESPACIOS ===");
            System.out.println("1. Registrar espacio");
            System.out.println("2. Consultar espacio");
            System.out.println("3. Editar espacio");
            System.out.println("4. Eliminar espacio");
            System.out.println("5. Regresar al menú principal");
            System.out.print("Seleccione una opción: ");

            String entrada = entradaTeclado.nextLine();
            if (!Validadores.esNumeroValido(entrada)) {
                System.out.println("Error: Debe ingresar un número válido.");
                opcion = 0;
                continue;
            }

            opcion = Integer.parseInt(entrada);
            Lugar lugar = null;
            if (opcion != 2 && opcion != 5) {
                if (universidad.getNumLugares() == 0) {
                    System.out.println("Error: No hay lugares registrados.");
                    opcion = 0;
                    continue;
                }
                System.out.println("Lugares disponibles:");
                System.out.println(universidad.consultarLugares());
                int posLugar;
                do {
                    System.out.print("Ingrese la posición del lugar: ");
                    entrada = entradaTeclado.nextLine();
                    if (!Validadores.esNumeroValido(entrada)) {
                        System.out.println("Error: Debe ingresar un número válido.");
                        posLugar = -1;
                        continue;
                    }
                    posLugar = Integer.parseInt(entrada) - 1;
                    if (posLugar < 0 || posLugar >= universidad.getNumLugares()) {
                        System.out.println("Error: Posición inválida.");
                    } else {
                        lugar = universidad.getLugares()[posLugar];
                        break;
                    }
                } while (true);
            }

            switch (opcion) {
                case 1:
                    Espacio nuevoEspacio = guiEspacio.registrar(lugar);
                    if (nuevoEspacio != null && lugar.validarDuplicado(nuevoEspacio)) {
                        if (lugar.crearEspacio(nuevoEspacio)) {
                            System.out.println("Espacio registrado: " + nuevoEspacio.getNombre());
                        } else {
                            System.out.println("Error al registrar espacio.");
                        }
                    } else {
                        System.out.println("Error: Espacio duplicado o datos inválidos.");
                    }
                    break;
                case 2:
                    guiEspacio.consultar();
                    break;
                case 3:
                    Espacio espacioEditado = guiEspacio.editar(lugar);
                    if (espacioEditado != null) {
                        System.out.println("Espacio actualizado: " + espacioEditado.getNombre());
                    }
                    break;
                case 4:
                    if (guiEspacio.eliminar(lugar)) {
                        System.out.println("Espacio eliminado correctamente.");
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