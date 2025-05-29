package ec.edu.uce.classfinder.gui;

import ec.edu.uce.classfinder.dominio.Lugar;
import ec.edu.uce.classfinder.dominio.Universidad;

import java.util.Scanner;

/**
 * Interfaz gráfica para eliminar un lugar en el sistema ClassFinder.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class GUIEliminarLugar {

    private Scanner entradaTeclado = new Scanner(System.in);

    /**
     * Permite eliminar un lugar.
     * @param universidad Instancia de Universidad para verificar lugares
     * @return null si se confirma la eliminación, un lugar vacío si se cancela
     */
    public Lugar eliminar(Universidad universidad) {
        if (universidad == null || universidad.getNumLugares() == 0) {
            System.out.println("Error: No hay lugares disponibles para eliminar.");
            return null;
        }

        System.out.println("\n=== ELIMINAR LUGAR ===");
        System.out.println("Lista de lugares actuales:");
        System.out.println(universidad.consultarLugares());

        int pos;
        do {
            System.out.print("Ingrese la posición del lugar a eliminar ");
            if (entradaTeclado.hasNextInt()) {
                pos = entradaTeclado.nextInt();
                entradaTeclado.nextLine();
                if (pos < 0 || pos >= universidad.getNumLugares()) {
                    System.out.println("Error: Posición inválida.");
                } else {
                    break;
                }
            } else {
                System.out.println("Error: Debe ingresar un número válido.");
                entradaTeclado.nextLine();
            }
        } while (true);

        Lugar lugar = universidad.getLugares()[pos];
        System.out.print("¿Está seguro de eliminar el lugar con ID " + lugar.getIdLugar() + "? (s/n): ");
        String confirmacion = entradaTeclado.nextLine().toLowerCase();
        if (confirmacion.equals("s")) {
            System.out.println("Lugar con ID " + lugar.getIdLugar() + " marcado para eliminación.");
            return null; // Indica que se debe eliminar
        } else {
            System.out.println("Eliminación cancelada.");
            return new Lugar(); // Retorna un lugar vacío si no se confirma
        }
    }
}