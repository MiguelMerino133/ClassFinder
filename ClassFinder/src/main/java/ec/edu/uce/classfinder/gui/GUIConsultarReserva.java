package ec.edu.uce.classfinder.gui;

import java.util.Scanner;

public class GUIConsultarReserva {

    private Scanner entradaTeclado = new Scanner(System.in);

    public String consultar() {
        System.out.println("\n=== CONSULTAR RESERVA ===");
        System.out.print("Ingrese ID de la reserva a consultar: ");
        return entradaTeclado.nextLine();
    }

    public void mostrarReserva(String idReserva) {
        System.out.println("Consulta de Reserva - ID: " + idReserva);
        System.out.println("Detalles simulados: Usuario: usuario123, Espacio: aula101, Estado: aprobada");
    }

}
