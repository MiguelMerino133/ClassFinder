package ec.edu.uce.classfinder.gui;

import ec.edu.uce.classfinder.dominio.Espacio;
import ec.edu.uce.classfinder.dominio.EstadoReserva;
import ec.edu.uce.classfinder.dominio.Reserva;
import ec.edu.uce.classfinder.dominio.Usuario;
import ec.edu.uce.classfinder.util.Validadores;
import java.util.Scanner;

/**
 * Interfaz gráfica para registrar una reserva en el sistema ClassFinder.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class GUIRegistrarReserva {

    private Scanner entradaTeclado = new Scanner(System.in);

    /**
     * Permite registrar una nueva reserva.
     * @return la reserva creada con los datos ingresados
     */
    public Reserva registrar() {
        System.out.println("\n=== REGISTRAR RESERVA ===");
        Reserva reserva = new Reserva();
        String idReserva, fechaInicio, fechaFin, estado;
        Usuario usuario = new Usuario();
        Espacio espacio = new Espacio();

        do {
            System.out.print("Ingrese ID de la reserva (formato RES-001): ");
            idReserva = entradaTeclado.nextLine();
            if (!Validadores.esIdValido(idReserva)) {
                System.out.println("Error: El ID debe tener el formato RES-001.");
            }
        } while (!Validadores.esIdValido(idReserva));
        reserva.setIdReserva(idReserva);

        do {
            System.out.print("Ingrese fecha de inicio (formato YYYY/MM/DD HH:MM): ");
            fechaInicio = entradaTeclado.nextLine();
            if (!Validadores.esFechaConHoraValida(fechaInicio)) {
                System.out.println("Error: La fecha debe seguir el formato YYYY/MM/DD HH:MM.");
            }
        } while (!Validadores.esFechaConHoraValida(fechaInicio));
        reserva.setFechaInicio(fechaInicio);

        do {
            System.out.print("Ingrese fecha de fin (formato YYYY/MM/DD HH:MM): ");
            fechaFin = entradaTeclado.nextLine();
            if (!Validadores.esFechaConHoraValida(fechaFin) || !Validadores.esFechaFinValida(fechaInicio, fechaFin)) {
                System.out.println("Error: La fecha de fin debe ser válida y posterior a la fecha de inicio.");
            }
        } while (!Validadores.esFechaConHoraValida(fechaFin) || !Validadores.esFechaFinValida(fechaInicio, fechaFin));
        reserva.setFechaFin(fechaFin);

        reserva.setEstadoReserva(EstadoReserva.PENDIENTE);


        do {
            System.out.print("Ingrese ID del usuario (formato USR-001): ");
            String idUsuario = entradaTeclado.nextLine();
            if (Validadores.esIdValido(idUsuario)) {
                usuario.setIdUsuario(idUsuario);
                break;
            } else {
                System.out.println("Error: El ID debe tener el formato USR-001.");
            }
        } while (true);
        reserva.setUsuario(usuario);

        do {
            System.out.print("Ingrese ID del espacio (formato ESP-001): ");
            String idEspacio = entradaTeclado.nextLine();
            if (Validadores.esIdValido(idEspacio)) {
                espacio.setIdEspacio(idEspacio);
                break;
            } else {
                System.out.println("Error: El ID debe tener el formato ESP-001.");
            }
        } while (true);
        reserva.setEspacio(espacio);

        return reserva;
    }
}
