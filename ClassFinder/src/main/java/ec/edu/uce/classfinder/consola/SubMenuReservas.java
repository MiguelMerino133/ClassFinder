package ec.edu.uce.classfinder.consola;

import ec.edu.uce.classfinder.gui.GUIConsultarReserva;
import ec.edu.uce.classfinder.gui.GUIRegistrarReserva;
import ec.edu.uce.classfinder.gui.GUIEditarReserva;
import ec.edu.uce.classfinder.gui.GUICancelarReserva;
import ec.edu.uce.classfinder.modelo.Reserva;
import java.util.Scanner;

public class SubMenuReservas {
    public void mostrar() {
        Scanner entradaTeclado = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("=== GESTIÓN DE RESERVAS ===");
            System.out.println("1. Registrar Reserva");
            System.out.println("2. Consultar Estado Reserva");
            System.out.println("3. Editar Reserva");
            System.out.println("4. Cancelar Reserva");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcion = entradaTeclado.nextInt();
            entradaTeclado.nextLine();

            switch (opcion) {
                case 1:
                    GUIRegistrarReserva guiRegistrar = new GUIRegistrarReserva();
                    Reserva nuevaReserva = guiRegistrar.registrar();
                    System.out.println("Reserva registrada: " + nuevaReserva.getIdReserva());
                    break;
                case 2:
                    GUIConsultarReserva guiConsultar = new GUIConsultarReserva();
                    String idReserva = guiConsultar.consultar();
                    guiConsultar.mostrarReserva(idReserva);
                    break;
                case 3:
                    GUIEditarReserva guiEditar = new GUIEditarReserva();
                    Reserva reservaEditada = guiEditar.editar();
                    System.out.println("Reserva editada: " + reservaEditada.getIdReserva());

                    break;
                case 4:
                    GUICancelarReserva guiCancelar = new GUICancelarReserva();
                    idReserva = guiCancelar.cancelar();
                    System.out.println("Reserva con ID " + idReserva + " cancelada.");
                    break;
                case 5:
                    System.out.println("Regresando al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }

        } while (opcion != 5);

    }


}
