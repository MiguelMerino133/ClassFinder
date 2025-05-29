package ec.edu.uce.classfinder.consola;

import ec.edu.uce.classfinder.gui.*;
import ec.edu.uce.classfinder.dominio.Reserva;
import ec.edu.uce.classfinder.dominio.Universidad;
import ec.edu.uce.classfinder.util.Validadores;
import java.text.ParseException;
import java.util.Scanner;

/**
 * Representa el submenú para gestionar reservas en el sistema ClassFinder.
 * Permite realizar operaciones CRUD sobre las reservas a través de {@link Universidad}.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class SubMenuReservas {

    private Universidad universidad;

    public SubMenuReservas(Universidad universidad) {
        this.universidad = universidad;
    }

    public void mostrar() throws ParseException {
        Scanner entradaTeclado = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("=== GESTIÓN DE RESERVAS ===");
            System.out.println("1. Registrar Reserva");
            System.out.println("2. Consultar Reserva");
            System.out.println("3. Editar Reserva");
            System.out.println("4. Eliminar Reserva");
            System.out.println("5. Aprobar/Rechazar Reserva");
            System.out.println("6. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            String entrada = entradaTeclado.nextLine();
            if (Validadores.esNumeroValido(entrada)) {
                opcion = Integer.parseInt(entrada);
            } else {
                System.out.println("Error: Debe ingresar un número válido. Intente de nuevo.");
                opcion = 0;
                continue;
            }

            switch (opcion) {
                case 1:
                    GUIRegistrarReserva guiRegistrar = new GUIRegistrarReserva();
                    Reserva nuevaReserva = guiRegistrar.registrar();
                    if (nuevaReserva != null && universidad.crearReserva(nuevaReserva)) {
                        System.out.println("Reserva registrada: " + nuevaReserva.toString());
                    } else {
                        System.out.println("Error al registrar reserva.");
                    }
                    break;
                case 2:
                    if (universidad.getNumReservas() == 0) {
                        System.out.println("No hay reservas registradas.");
                        continue;
                    }
                    GUIConsultarReserva guiConsultar = new GUIConsultarReserva(universidad);
                    String idReserva = guiConsultar.consultar();
                    guiConsultar.mostrarReserva(idReserva);
                    break;
                case 3:
                    if (universidad.getNumReservas() == 0) {
                        System.out.println("No hay reservas registradas.");
                        continue;
                    }
                    GUIEditarReserva guiEditar = new GUIEditarReserva();
                    Reserva reservaEditada = guiEditar.editar(universidad);
                    if (reservaEditada != null) {
                        int posEditar;
                        do {
                            System.out.print("Confirme la posición de la reserva a editar: ");
                            entrada = entradaTeclado.nextLine();
                            if (Validadores.esNumeroValido(entrada)) {
                                posEditar = Integer.parseInt(entrada);
                                if (posEditar >= 0 && posEditar < universidad.getNumReservas()) {
                                    if (universidad.editarReserva(posEditar, reservaEditada)) {
                                        System.out.println("Reserva editada: " + reservaEditada.toString());
                                    } else {
                                        System.out.println("Error al editar reserva.");
                                    }
                                    break;
                                } else {
                                    System.out.println("Error: Posición inválida.");
                                }
                            } else {
                                System.out.println("Error: Debe ingresar un número válido.");
                            }
                        } while (true);
                    }
                    break;
                case 4:
                    if (universidad.getNumReservas() == 0) {
                        System.out.println("No hay reservas registradas.");
                        continue;
                    }
                    GUIEliminarReserva guiEliminar = new GUIEliminarReserva();
                    Reserva resultado = guiEliminar.eliminar(universidad);
                    if (resultado == null) {
                        int posEliminar;
                        do {
                            System.out.print("Confirme la posición de la reserva a elminar: ");
                            entrada = entradaTeclado.nextLine();
                            if (Validadores.esNumeroValido(entrada)) {
                                posEliminar = Integer.parseInt(entrada);
                                if (posEliminar >= 0 && posEliminar < universidad.getNumReservas()) {
                                    if (universidad.eliminarReserva(posEliminar)) {
                                        System.out.println("Reserva cancelada en posición " + posEliminar);
                                    } else {
                                        System.out.println("Error al cancelar reserva.");
                                    }
                                    break;
                                } else {
                                    System.out.println("Error: Posición inválida.");
                                }
                            } else {
                                System.out.println("Error: Debe ingresar un número válido.");
                            }
                        } while (true);
                    }
                    break;
                case 5:
                    if (universidad.getNumReservas() == 0) {
                        System.out.println("No hay reservas registradas.");
                        continue;
                    }
                    GUIAprobarRechazarReserva guiAprobarRechazar = new GUIAprobarRechazarReserva(universidad);
                    guiAprobarRechazar.aprobarRechazar();
                    break;
                case 6:
                    System.out.println("Regresando al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 6);
    }
}