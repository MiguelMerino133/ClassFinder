package ec.edu.uce.classfinder.consola;

import ec.edu.uce.classfinder.gui.GUIValidarUsuario;
import ec.edu.uce.classfinder.gui.GUIIngresarSistema;
import java.util.Scanner;

import java.util.Scanner;

public class SubMenuIngresarSistema {
    Scanner entradaTeclado = new Scanner(System.in);
    public void mostrar() {

        int opcion;

        do {
            System.out.println("=== INGRESAR AL SISTEMA ===");
            System.out.println("1. Validar Usuario");
            System.out.println("2. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcion = entradaTeclado.nextInt();
            entradaTeclado.nextLine();

            switch (opcion) {
                case 1:
                    GUIValidarUsuario guiValidar = new GUIValidarUsuario();
                    String[] credenciales = guiValidar.validar();
                    String idUsuario = credenciales[0];
                    // Simulación de validación exitosa (sin lógica real)
                    GUIIngresarSistema guiAcceder = new GUIIngresarSistema();
                    guiAcceder.ingresar(idUsuario);
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
