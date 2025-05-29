package ec.edu.uce.classfinder.consola;

import ec.edu.uce.classfinder.gui.GUIValidarUsuario;
import ec.edu.uce.classfinder.gui.GUIIngresarSistema;
import ec.edu.uce.classfinder.dominio.Universidad;
import ec.edu.uce.classfinder.util.Validadores;
import java.util.Scanner;

/**
 * Representa el submenú para ingresar al sistema ClassFinder.
 * Permite validar usuarios y acceder al sistema.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class SubMenuIngresarSistema {

    private Scanner entradaTeclado = new Scanner(System.in);
    private Universidad universidad;

    /**
     * Constructor que recibe una instancia de {@link Universidad}.
     * @param universidad instancia de la universidad para gestionar los datos
     */
    public SubMenuIngresarSistema(Universidad universidad) {
        this.universidad = universidad;
    }

    /**
     * Muestra el submenú de ingreso al sistema y maneja las opciones del usuario.
     */
    public void mostrar() {
        int opcion;

        do {
            System.out.println("=== INGRESAR AL SISTEMA ===");
            System.out.println("1. Validar Usuario");
            System.out.println("2. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            String entrada = entradaTeclado.nextLine();
            if (Validadores.esNumeroValido(entrada)) {
                opcion = Integer.parseInt(entrada);
            } else {
                System.out.println("Error: Debe ingresar un número válido. Intente de nuevo.");
                opcion = 0;
                continue;
            }

            switch (opcion) {
                case 1:
                    GUIValidarUsuario guiValidar = new GUIValidarUsuario(universidad);
                    String[] credenciales = guiValidar.validar();
                    if (credenciales != null) {
                        String idUsuario = credenciales[0];
                        GUIIngresarSistema guiAcceder = new GUIIngresarSistema();
                        guiAcceder.ingresar(idUsuario);
                    }
                    break;
                case 2:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }

        } while (opcion != 2);
    }
}