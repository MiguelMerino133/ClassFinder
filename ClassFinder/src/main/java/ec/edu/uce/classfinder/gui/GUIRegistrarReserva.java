package ec.edu.uce.classfinder.gui;

import ec.edu.uce.classfinder.modelo.Reserva;
import java.util.Date;
import java.util.Scanner;


public class GUIRegistrarReserva {

    private Scanner entradaTeclado = new Scanner(System.in);

    public Reserva registrar() {
        System.out.println("\n=== REGISTRAR RESERVA ===");
        Reserva reserva = new Reserva();
        System.out.print("Ingrese ID de la reserva: ");
        reserva.setIdReserva(entradaTeclado.nextLine());
        System.out.print("Ingrese ID del usuario: ");
        reserva.setIdUsuario(entradaTeclado.nextLine());
        System.out.print("Ingrese ID del espacio: ");
        reserva.setIdEspacio(entradaTeclado.nextLine());
        System.out.print("Ingrese fecha de inicio: ");
        reserva.setFechaInicio(new Date()); // Placeholder
        System.out.print("Ingrese fecha de fin: ");
        reserva.setFechaFin(new Date()); // Placeholder

        return reserva;
    }

}
