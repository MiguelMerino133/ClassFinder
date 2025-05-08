package ec.edu.uce.classfinder.gui;

import java.util.Scanner;

public class GUICancelarReserva {

    private Scanner entradaTeclado = new Scanner(System.in);

    public String cancelar() {
        System.out.println("\n=== CANCELAR RESERVA ===");
        System.out.print("Ingrese ID de la reserva a cancelar: ");
        return entradaTeclado.nextLine();
    }

}
