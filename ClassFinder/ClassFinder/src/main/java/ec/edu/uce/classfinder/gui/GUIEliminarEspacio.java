package ec.edu.uce.classfinder.gui;

import ec.edu.uce.classfinder.dominio.Espacio;
import ec.edu.uce.classfinder.dominio.Lugar;
import ec.edu.uce.classfinder.dominio.Universidad;

import java.util.Scanner;

/**
 * Interfaz gráfica para eliminar un espacio en el sistema ClassFinder.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class GUIEliminarEspacio {

    private Scanner entradaTeclado = new Scanner(System.in);

    /**
     * Permite eliminar un espacio.
     * @param universidad Instancia de Universidad para verificar lugares
     * @param lugar Lugar donde se encuentra el espacio
     * @return null si se confirma la eliminación, un espacio vacío si se cancela
     */
    public Espacio eliminar(Universidad universidad, Lugar lugar) {
        if (lugar == null || lugar.getNumEspacios() == 0) {
            System.out.println("Error: No hay espacios disponibles para eliminar en este lugar.");
            return null;
        }

        System.out.println("\n=== ELIMINAR ESPACIO ===");
        System.out.println("Espacios en el lugar:");
        System.out.println(lugar.consultarEspacios());

        int pos;
        do {
            System.out.print("Ingrese la posición del espacio a eliminar: ");
            if (entradaTeclado.hasNextInt()) {
                pos = entradaTeclado.nextInt();
                entradaTeclado.nextLine();
                if (pos < 0 || pos >= lugar.getNumEspacios()) {
                    System.out.println("Error: Posición inválida.");
                } else {
                    break;
                }
            } else {
                System.out.println("Error: Debe ingresar un número válido.");
                entradaTeclado.nextLine();
            }
        } while (true);

        Espacio espacio = lugar.getEspacios()[pos];
        System.out.print("¿Está seguro de eliminar el espacio con ID " + espacio.getIdEspacio() + "? (s/n): ");
        String confirmacion = entradaTeclado.nextLine().toLowerCase();
        if (confirmacion.equals("s")) {
            System.out.println("Espacio con ID " + espacio.getIdEspacio() + " marcado para eliminación.");
            return null; // Indica que se debe eliminar
        } else {
            System.out.println("Eliminación cancelada.");
            return new Espacio(); // Retorna un espacio vacío si no se confirma
        }
    }
}