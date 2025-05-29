package ec.edu.uce.classfinder.gui;

import ec.edu.uce.classfinder.dominio.Reserva;
import ec.edu.uce.classfinder.dominio.Universidad;

import java.util.Scanner;

/**
 * Interfaz gráfica para eliminar una reserva en el sistema ClassFinder.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class GUIEliminarReserva {

    private Scanner entradaTeclado = new Scanner(System.in);

    /**
     * Permite eliminar una reserva.
     * @param universidad Instancia de Universidad para verificar reservas
     * @return null si se confirma la eliminación, una reserva vacía si se cancela
     */
    public Reserva eliminar(Universidad universidad) {
        if (universidad == null || universidad.getNumReservas() == 0) {
            System.out.println("Error: No hay reservas disponibles para eliminar.");
            return null;
        }

        System.out.println("\n=== ELIMINAR RESERVA ===");
        System.out.println("Lista de reservas actuales:");
        System.out.println(universidad.consultarReservas());

        int pos;
        do {
            System.out.print("Ingrese la posición de la reserva a eliminar ");
            if (entradaTeclado.hasNextInt()) {
                pos = entradaTeclado.nextInt();
                entradaTeclado.nextLine();
                if (pos < 0 || pos >= universidad.getNumReservas()) {
                    System.out.println("Error: Posición inválida.");
                } else {
                    break;
                }
            } else {
                System.out.println("Error: Debe ingresar un número válido.");
                entradaTeclado.nextLine();
            }
        } while (true);

        Reserva reserva = universidad.getReservas()[pos];
        System.out.print("¿Está seguro de eliminar la reserva con ID " + reserva.getIdReserva() + "? (s/n): ");
        String confirmacion = entradaTeclado.nextLine().toLowerCase();
        if (confirmacion.equals("s")) {
            System.out.println("Reserva con ID " + reserva.getIdReserva() + " marcada para eliminación.");
            return null; // Indica que se debe eliminar
        } else {
            System.out.println("Eliminación cancelada o campo erroneo.");
            return new Reserva(); // Retorna una reserva vacía si no se confirma
        }
    }
}