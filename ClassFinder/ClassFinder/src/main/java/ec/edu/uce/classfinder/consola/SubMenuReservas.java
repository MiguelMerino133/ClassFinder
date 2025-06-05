package ec.edu.uce.classfinder.consola;

import ec.edu.uce.classfinder.dominio.Reserva;
import ec.edu.uce.classfinder.dominio.Universidad;
import ec.edu.uce.classfinder.gui.GUIReserva;
import ec.edu.uce.classfinder.util.Validadores;

import java.util.Scanner;

/**
 * Submenú para gestionar reservas en el sistema ClassFinder.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class SubMenuReservas {
    private final Scanner entradaTeclado;
    private final Universidad universidad;
    private final GUIReserva guiReserva;

    public SubMenuReservas(Scanner entradaTeclado, Universidad universidad) {
        this.entradaTeclado = entradaTeclado;
        this.universidad = universidad;
        this.guiReserva = new GUIReserva(universidad);
    }

    public void mostrarMenuReservas() {
        int opcion;
        do {
            System.out.println("\n=== MENÚ RESERVAS ===");
            System.out.println("1. Registrar reserva");
            System.out.println("2. Consultar reserva");
            System.out.println("3. Editar reserva");
            System.out.println("4. Eliminar reserva");
            System.out.println("5. Aprobar/Rechazar reserva");
            System.out.println("6. Regresar al menú principal");
            System.out.print("Seleccione una opción: ");

            String entrada = entradaTeclado.nextLine().trim();
            if (!Validadores.esNumeroValido(entrada)) {
                System.out.println("Error: Debe ingresar un número válido.");
                opcion = 0;
                continue;
            }

            opcion = Integer.parseInt(entrada);
            switch (opcion) {
                case 1:
                    Reserva nuevaReserva = guiReserva.registrar();
                    if (nuevaReserva != null && universidad.validarDuplicado(nuevaReserva)) {
                        if (universidad.crearReserva(nuevaReserva)) {
                            System.out.println("Reserva registrada: " + nuevaReserva.getIdReserva());
                        } else {
                            System.out.println("Error al registrar reserva.");
                        }
                    } else {
                        System.out.println("Error: ID de reserva ya registrado.");
                    }
                    break;
                case 2:
                    guiReserva.consultar();
                    break;
                case 3:
                    Reserva reservaEditada = guiReserva.editar();
                    if (reservaEditada != null) {
                        System.out.println("Reserva actualizada: " + reservaEditada.getIdReserva());
                    } else {
                        System.out.println("Error al editar reserva.");
                    }
                    break;
                case 4:
                    if (guiReserva.eliminar()) {
                        System.out.println("Reserva eliminada correctamente.");
                    } else {
                        System.out.println("Error al eliminar reserva.");
                    }
                    break;
                case 5:
                    if (guiReserva.aprobarRechazar()) {
                        System.out.println("Estado de reserva actualizado correctamente.");
                    } else {
                        System.out.println("Error al actualizar estado de reserva.");
                    }
                    break;
                case 6:
                    System.out.println("Regresando al menú principal...");
                    break;
                default:
                    System.out.println("Error: Opción inválida.");
            }
        } while (opcion != 6);
    }
}