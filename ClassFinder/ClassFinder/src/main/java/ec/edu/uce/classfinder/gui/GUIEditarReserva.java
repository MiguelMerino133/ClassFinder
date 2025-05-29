package ec.edu.uce.classfinder.gui;

import ec.edu.uce.classfinder.dominio.Espacio;
import ec.edu.uce.classfinder.dominio.Reserva;
import ec.edu.uce.classfinder.dominio.Universidad;
import ec.edu.uce.classfinder.dominio.Usuario;
import ec.edu.uce.classfinder.util.Validadores;
import java.util.Scanner;

/**
 * Interfaz gráfica para editar una reserva en el sistema ClassFinder.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class GUIEditarReserva {

    private Scanner entradaTeclado = new Scanner(System.in);

    /**
     * Permite editar los datos de una reserva.
     * @param universidad Instancia de Universidad para buscar usuarios y espacios
     * @return la reserva actualizada con los nuevos datos, o null si no se puede editar
     */
    public Reserva editar(Universidad universidad) {
        if (universidad == null || universidad.getNumReservas() == 0) {
            System.out.println("Error: No hay reservas disponibles para editar.");
            return null;
        }

        System.out.println("\n=== EDITAR RESERVA ===");
        System.out.println("Lista de reservas actuales:");
        System.out.println(universidad.consultarReservas());

        int pos;
        do {
            System.out.print("Ingrese la posición de la reserva a editar: ");
            if (entradaTeclado.hasNextInt()) {
                pos = entradaTeclado.nextInt();
                entradaTeclado.nextLine();
                if (pos < 0 || pos >= universidad.getNumReservas()) {
                    System.out.println("Error: Posición inválida.");
                } else {
                    break;
                }
            } else {
                System.out.println("Error: Debe ingresar un número válido.");
                entradaTeclado.nextLine();
            }
        } while (true);

        Reserva reserva = universidad.getReservas()[pos];
        System.out.println("Datos actuales: " + reserva.toString());

        // No editamos el ID
        System.out.println("ID de la reserva: " + reserva.getIdReserva() + " (no editable)");

        // Fecha de inicio
        do {
            System.out.print("Ingrese nueva fecha de inicio (formato YYYY/MM/DD HH:MM, deje en blanco para no cambiar): ");
            String fechaInicio = entradaTeclado.nextLine();
            if (!fechaInicio.isEmpty()) {
                if (!Validadores.esFechaConHoraValida(fechaInicio)) {
                    System.out.println("Error: La fecha debe seguir el formato YYYY/MM/DD HH:MM y ser futura.");
                } else {
                    reserva.setFechaInicio(fechaInicio);
                    break;
                }
            } else {
                break;
            }
        } while (true);

        // Fecha de fin
        do {
            System.out.print("Ingrese nueva fecha de fin (formato YYYY/MM/DD HH:MM, deje en blanco para no cambiar): ");
            String fechaFin = entradaTeclado.nextLine();
            if (!fechaFin.isEmpty()) {
                if (!Validadores.esFechaConHoraValida(fechaFin) || !Validadores.esFechaFinValida(reserva.getFechaInicio(), fechaFin)) {
                    System.out.println("Error: La fecha de fin debe ser válida y posterior a la fecha de inicio.");
                } else {
                    reserva.setFechaFin(fechaFin);
                    break;
                }
            } else {
                break;
            }
        } while (true);

        // No editamos el estado aquí
        System.out.println("Estado: " + reserva.getEstadoReserva().getDescripcion() + " (solo se puede cambiar en Aprobar/Rechazar Reserva)");

        // Editar usuario
        do {
            System.out.print("Ingrese nuevo ID del usuario (formato USR-001, deje en blanco para no cambiar): ");
            String idUsuario = entradaTeclado.nextLine();
            if (!idUsuario.isEmpty()) {
                if (!Validadores.esIdValido(idUsuario)) {
                    System.out.println("Error: El ID debe tener el formato USR-001.");
                } else {
                    Usuario usuario = universidad.buscarUsuario(idUsuario);
                    if (usuario == null) {
                        System.out.println("Error: Usuario no encontrado.");
                    } else {
                        reserva.setUsuario(usuario);
                        break;
                    }
                }
            } else {
                break;
            }
        } while (true);

        // Editar espacio
        do {
            System.out.print("Ingrese nuevo ID del espacio (formato ESP-001, deje en blanco para no cambiar): ");
            String idEspacio = entradaTeclado.nextLine();
            if (!idEspacio.isEmpty()) {
                if (!Validadores.esIdValido(idEspacio)) {
                    System.out.println("Error: El ID debe tener el formato ESP-001.");
                } else {
                    Espacio espacio = universidad.buscarEspacio(idEspacio);
                    if (espacio == null) {
                        System.out.println("Error: Espacio no encontrado.");
                    } else {
                        reserva.setEspacio(espacio);
                        break;
                    }
                }
            } else {
                break;
            }
        } while (true);

        return reserva;
    }
}