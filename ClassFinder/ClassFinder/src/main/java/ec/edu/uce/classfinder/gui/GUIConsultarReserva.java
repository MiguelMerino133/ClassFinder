package ec.edu.uce.classfinder.gui;

import ec.edu.uce.classfinder.dominio.Reserva;
import ec.edu.uce.classfinder.dominio.Universidad;
import ec.edu.uce.classfinder.util.Validadores;
import java.util.Scanner;

/**
 * Interfaz gráfica para consultar el estado de una reserva en el sistema ClassFinder.
 * Utiliza {@link Universidad} para buscar las reservas.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class GUIConsultarReserva {

    private Scanner entradaTeclado = new Scanner(System.in);
    private Universidad universidad;

    /**
     * Constructor que recibe una instancia de {@link Universidad}.
     * @param universidad instancia de la universidad para gestionar los datos
     */
    public GUIConsultarReserva(Universidad universidad) {
        this.universidad = universidad;
    }

    /**
     * Solicita el ID de la reserva a consultar.
     * @return el ID de la reserva ingresado
     */
    public String consultar() {
        System.out.println("\n=== CONSULTAR RESERVA ===");
        String idReserva;

        do {
            System.out.print("Ingrese ID de la reserva a consultar (formato RES-001): ");
            idReserva = entradaTeclado.nextLine();
            if (!Validadores.esIdValido(idReserva)) {
                System.out.println("Error: El ID debe tener el formato RES-001.");
            }
        } while (!Validadores.esIdValido(idReserva));

        return idReserva;
    }

    /**
     * Muestra los detalles de una reserva basada en su ID.
     * @param idReserva ID de la reserva a consultar
     */
    public void mostrarReserva(String idReserva) {
        boolean encontrado = false;
        for (int i = 0; i < universidad.getNumReservas(); i++) {
            Reserva reserva = universidad.getReservas()[i];
            if (reserva.getIdReserva().equals(idReserva)) {
                System.out.println("Mostrando detalles de la reserva con ID: " + idReserva);
                System.out.println("Reserva ID: " + reserva.getIdReserva());
                System.out.println("Usuario: " + reserva.getUsuario().getIdUsuario() + ", Nombre: " + reserva.getUsuario().getNombre() + ", Rol: " + reserva.getUsuario().getRol());
                System.out.println("Espacio: " + reserva.getEspacio().getIdEspacio() + ", Nombre: " + reserva.getEspacio().getNombre() + ", Capacidad: " + reserva.getEspacio().getCapacidad());
                System.out.println("Fecha Inicio: " + reserva.getFechaInicio());
                System.out.println("Fecha Fin: " + reserva.getFechaFin());
                System.out.println("Estado: " + reserva.getEstadoReserva());
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Reserva con ID " + idReserva + " no encontrada.");
        }
    }
}