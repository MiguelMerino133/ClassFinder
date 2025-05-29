package ec.edu.uce.classfinder.gui;

import ec.edu.uce.classfinder.dominio.EstadoReserva;
import ec.edu.uce.classfinder.dominio.Reserva;
import ec.edu.uce.classfinder.dominio.Universidad;
import ec.edu.uce.classfinder.util.Validadores;
import java.util.Scanner;

/**
 * Interfaz gráfica para aprobar o rechazar una reserva en el sistema ClassFinder.
 * Utiliza {@link Universidad} para gestionar las reservas.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class GUIAprobarRechazarReserva {

    private Scanner entradaTeclado = new Scanner(System.in);
    private Universidad universidad;
    private Reserva reserva;

    /**
     * Constructor que recibe una instancia de {@link Universidad}.
     * @param universidad instancia de la universidad para gestionar los datos
     */
    public GUIAprobarRechazarReserva(Universidad universidad) {
        this.universidad = universidad;
    }

    /**
     * Permite aprobar o rechazar una reserva existente.
     */
    public void aprobarRechazar() {
        System.out.println("\n=== APROBAR/RECHAZAR RESERVA ===");
        String idReserva;

        do {
            System.out.print("Ingrese ID de la reserva a aprobar/rechazar (formato RES-001): ");
            idReserva = entradaTeclado.nextLine();
            if (!Validadores.esIdValido(idReserva)) {
                System.out.println("Error: El ID debe tener el formato RES-001.");
            }
        } while (!Validadores.esIdValido(idReserva));

        reserva = universidad.buscarReservaId(idReserva);
        if (reserva == null) {
            System.out.println("Error: No se encontró una reserva con ese ID.");
            return;  // Termina el método si no existe la reserva
        }

        int opcionEstado;
        do {
            System.out.println("\nSeleccione el estado de la reserva:");
            System.out.println("1. Pendiente");
            System.out.println("2. Aprobada");
            System.out.println("3. Rechazada");
            if (entradaTeclado.hasNextInt()) {
                opcionEstado = entradaTeclado.nextInt();
                entradaTeclado.nextLine();
                switch (opcionEstado) {
                    case 1:
                        reserva.setEstadoReserva(EstadoReserva.PENDIENTE);
                        break;
                    case 2:
                        reserva.setEstadoReserva(EstadoReserva.APROBADO);
                        break;
                    case 3:
                        reserva.setEstadoReserva(EstadoReserva.RECHAZADO);
                        break;
                    default:
                        System.out.println("Error: Opción inválida. Intente de nuevo.");
                        continue;
                }
                break;
            } else {
                System.out.println("Error: Debe ingresar un número válido.");
                entradaTeclado.nextLine();
            }
        } while (true);

    }
}