package ec.edu.uce.classfinder.consola;

import ec.edu.uce.classfinder.dominio.Lugar;
import ec.edu.uce.classfinder.dominio.Universidad;
import ec.edu.uce.classfinder.gui.GUILugar;
import ec.edu.uce.classfinder.util.Validadores;
import java.util.Scanner;

/**
 * Submenú para gestionar lugares en el sistema ClassFinder.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class SubMenuLugares {
    private Scanner entradaTeclado;
    private Universidad universidad;
    private GUILugar guiLugar;

    public SubMenuLugares(Scanner entradaTeclado, Universidad universidad) {
        this.entradaTeclado = entradaTeclado;
        this.universidad = universidad;
        this.guiLugar = new GUILugar(universidad);
    }

    public void mostrarMenuLugares() {
        int opcion;

        do {
            System.out.println("\n=== MENÚ LUGARES ===");
            System.out.println("1. Registrar lugar");
            System.out.println("2. Consultar lugar");
            System.out.println("3. Editar lugar");
            System.out.println("4. Eliminar lugar");
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
                    Lugar nuevoLugar = guiLugar.registrar();
                    if (nuevoLugar != null && universidad.validarDuplicado(nuevoLugar)) {
                        if (universidad.crearLugar(nuevoLugar)) {
                            System.out.println("Lugar registrado: " + nuevoLugar.getNombre());
                        } else {
                            System.out.println("Error al registrar lugar.");
                        }
                    } else {
                        System.out.println("Error: Lugar duplicado o datos inválidos.");
                    }
                    break;
                case 2:
                    guiLugar.consultar();
                    break;
                case 3:
                    Lugar lugarEditado = guiLugar.editar();
                    if (lugarEditado != null) {
                        System.out.println("Lugar actualizado: " + lugarEditado.getNombre());
                    }
                    break;
                case 4:
                    if (guiLugar.eliminar()) {
                        System.out.println("Lugar eliminado correctamente.");
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