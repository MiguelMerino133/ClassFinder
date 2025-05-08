package ec.edu.uce.classfinder.gui;

import ec.edu.uce.classfinder.modelo.Reserva;
import java.util.Date;
import java.util.Scanner;

public class GUIEditarReserva {

    private Scanner entradaTeclado = new Scanner(System.in);

    public Reserva editar() {
        System.out.println("\n=== EDITAR RESERVA ===");
        Reserva reserva = new Reserva();
        System.out.print("Ingrese ID de la reserva a editar: ");
        reserva.setIdReserva(entradaTeclado.nextLine());
        System.out.print("Ingrese nuevo ID del usuario: ");
        reserva.setIdUsuario(entradaTeclado.nextLine());
        System.out.print("Ingrese nuevo ID del espacio: ");
        reserva.setIdEspacio(entradaTeclado.nextLine());
        System.out.print("Ingrese nueva fecha de inicio): ");
        reserva.setFechaInicio(new Date()); // PlaceHolder
        System.out.print("Ingrese nueva fecha de fin): ");
        reserva.setFechaFin(new Date()); // PlaceHolder
        System.out.print("Ingrese nuevo estado): ");
        reserva.setEstado(entradaTeclado.nextLine());
        return reserva;
    }
}
