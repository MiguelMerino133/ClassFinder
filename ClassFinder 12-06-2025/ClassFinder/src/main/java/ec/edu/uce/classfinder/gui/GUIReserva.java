package ec.edu.uce.classfinder.gui;

import ec.edu.uce.classfinder.dominio.Espacio;
import ec.edu.uce.classfinder.dominio.EstadoReserva;
import ec.edu.uce.classfinder.dominio.Reserva;
import ec.edu.uce.classfinder.dominio.ReservaTemporal;
import ec.edu.uce.classfinder.dominio.ReservaSemestre;
import ec.edu.uce.classfinder.dominio.Universidad;
import ec.edu.uce.classfinder.dominio.Usuario;
import ec.edu.uce.classfinder.util.Validadores;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Interfaz gráfica para gestionar reservas en el sistema ClassFinder.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class GUIReserva {
    private final Scanner entradaTeclado;
    private final Universidad universidad;

    public GUIReserva() {
        this.entradaTeclado = new Scanner(System.in);
        this.universidad = Universidad.getInstance();
    }

    public Reserva registrar() {
        System.out.println("\n=== REGISTRAR RESERVA ===");
        String fechaInicio, fechaFin, idUsuario, idEspacio;
        int duracionHoras;
        String semestre;

        System.out.println("Seleccione el tipo de reserva:");
        System.out.println("1. Reserva Temporal");
        System.out.println("2. Reserva de Semestre");
        int opcion;
        do {
            System.out.print("Ingrese el número de la opción: ");
            if (entradaTeclado.hasNextInt()) {
                opcion = entradaTeclado.nextInt();
                entradaTeclado.nextLine();
                if (opcion < 1 || opcion > 2) {
                    System.out.println("Error: Opción inválida.");
                } else {
                    break;
                }
            } else {
                System.out.println("Error: Debe ingresar un número válido.");
                entradaTeclado.nextLine();
            }
        } while (true);

        do {
            System.out.print("Ingrese fecha de inicio (formato YYYY/MM/DD HH:MM): ");
            fechaInicio = entradaTeclado.nextLine().trim();
            if (!Validadores.esFechaConHoraValida(fechaInicio)) {
                System.out.println("Error: La fecha debe seguir el formato YYYY/MM/DD HH:MM y ser futura.");
            }
        } while (!Validadores.esFechaConHoraValida(fechaInicio));

        do {
            System.out.print("Ingrese fecha de fin (formato YYYY/MM/DD HH:MM): ");
            fechaFin = entradaTeclado.nextLine().trim();
            if (!Validadores.esFechaConHoraValida(fechaFin) || !Validadores.esFechaFinValida(fechaInicio, fechaFin)) {
                System.out.println("Error: La fecha de fin debe ser válida y posterior a la fecha de inicio.");
            }
        } while (!Validadores.esFechaConHoraValida(fechaFin) || !Validadores.esFechaFinValida(fechaInicio, fechaFin));

        do {
            System.out.print("Ingrese ID del usuario (formato USR-XXX): ");
            idUsuario = entradaTeclado.nextLine().trim();
            if (!Validadores.esIdValido(idUsuario)) {
                System.out.println("Error: El ID debe seguir el formato USR-XXX.");
                continue;
            }
            Usuario usuario = universidad.buscarUsuario(idUsuario);
            if (usuario == null) {
                System.out.println("Error: Usuario no registrado.");
                idUsuario = "";
            }
        } while (idUsuario.isEmpty());

        do {
            System.out.print("Ingrese ID del espacio (formato ESP-XXX): ");
            idEspacio = entradaTeclado.nextLine().trim();
            if (!Validadores.esIdValido(idEspacio)) {
                System.out.println("Error: El ID debe seguir el formato ESP-XXX.");
                continue;
            }
            Espacio espacio = universidad.buscarEspacio(idEspacio);
            if (espacio == null) {
                System.out.println("Error: Espacio no encontrado.");
                idEspacio = "";
            }
        } while (idEspacio.isEmpty());

        // Validar solapamiento
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            Date nuevaInicio = sdf.parse(fechaInicio);
            Date nuevaFin = sdf.parse(fechaFin);

            Reserva[] reservas = universidad.getReservas();
            for (Reserva r : reservas) {
                if (r != null && r.getEspacio().getIdEspacio().equals(idEspacio)) {
                    Date existenteInicio = r.getFechaInicio();
                    Date existenteFin = r.getFechaFin();
                    if (!(nuevaFin.before(existenteInicio) || nuevaInicio.after(existenteFin))) {
                        System.out.println("Error: Ya existe una reserva para el espacio " + idEspacio + " en el horario solicitado.");
                        return null;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error al validar el horario de la reserva.");
            return null;
        }

        Usuario usuario = universidad.buscarUsuario(idUsuario);
        Espacio espacio = universidad.buscarEspacio(idEspacio);

        Reserva reserva = null;
        switch (opcion) {
            case 1:
                do {
                    System.out.print("Ingrese duración en horas (mínimo 1): ");
                    if (entradaTeclado.hasNextInt()) {
                        duracionHoras = entradaTeclado.nextInt();
                        entradaTeclado.nextLine();
                        if (duracionHoras <= 0) {
                            System.out.println("Error: La duración debe ser mayor que 0.");
                        } else {
                            reserva = new ReservaTemporal(fechaInicio, fechaFin, EstadoReserva.PENDIENTE, usuario, espacio, duracionHoras);
                            break;
                        }
                    } else {
                        System.out.println("Error: Debe ingresar un número válido.");
                        entradaTeclado.nextLine();
                    }
                } while (true);
                break;
            case 2:
                do {
                    System.out.print("Ingrese semestre (formato YYYY-N, donde N es 1 o 2): ");
                    semestre = entradaTeclado.nextLine().trim();
                    if (!semestre.matches("\\d{4}-[1-2]")) {
                        System.out.println("Error: El semestre debe ser del formato YYYY-N (ej. 2025-1).");
                    } else {
                        reserva = new ReservaSemestre(fechaInicio, fechaFin, EstadoReserva.PENDIENTE, usuario, espacio, semestre);
                        break;
                    }
                } while (true);
                break;
            default:
                return null;
        }

        if (universidad.crearReserva(reserva)) {
            System.out.println("Reserva creada exitosamente con ID: " + reserva.getIdReserva());
            return reserva;
        } else {
            System.out.println("Error: No se pudo crear la reserva, posible conflicto de horario.");
            return null;
        }
    }

    public void consultar() {
        System.out.println("\n=== CONSULTAR RESERVA ===");
        String idReserva;
        do {
            System.out.print("Ingrese ID de la reserva (formato RES-XXX): ");
            idReserva = entradaTeclado.nextLine().trim();
            if (!Validadores.esIdValido(idReserva)) {
                System.out.println("Error: El ID debe seguir el formato RES-XXX.");
            }
        } while (!Validadores.esIdValido(idReserva));

        Reserva reserva = universidad.buscarReservaId(idReserva);
        if (reserva != null) {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            System.out.println("Consulta de Reserva - ID: " + idReserva);
            System.out.println("Tipo: " + (reserva instanceof ReservaTemporal ? "Reserva Temporal" : "Reserva de Semestre"));
            System.out.println("Usuario: " + reserva.getUsuario().getIdUsuario() + ", Nombre: " + reserva.getUsuario().getNombre());
            System.out.println("Espacio: " + reserva.getEspacio().getIdEspacio() + ", Nombre: " + reserva.getEspacio().getNombre());
            System.out.println("Fecha Inicio: " + formato.format(reserva.getFechaInicio()));
            System.out.println("Fecha Fin: " + formato.format(reserva.getFechaFin()));
            System.out.println("Estado: " + reserva.getEstado().getDescripcion());
            if (reserva instanceof ReservaTemporal) {
                System.out.println("Duración: " + ((ReservaTemporal) reserva).getDuracionHoras() + " horas");
            } else if (reserva instanceof ReservaSemestre) {
                System.out.println("Semestre: " + ((ReservaSemestre) reserva).getSemestre());
            }
        } else {
            System.out.println("Error: Reserva con ID " + idReserva + " no encontrada.");
        }
    }

    public Reserva editar() {
        if (universidad.getNumReservas() == 0) {
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
                pos = entradaTeclado.nextInt() - 1;
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

        Reserva reservaOriginal = universidad.getReservas()[pos];
        System.out.println("Datos actuales: " + reservaOriginal.toString());

        System.out.println("ID de la reserva: " + reservaOriginal.getIdReserva() + " (no editable)");

        String fechaInicio;
        do {
            System.out.print("Ingrese nueva fecha de inicio (formato YYYY/MM/DD HH:MM, deje en blanco para no cambiar): ");
            fechaInicio = entradaTeclado.nextLine().trim();
            if (!fechaInicio.isEmpty() && !Validadores.esFechaConHoraValida(fechaInicio)) {
                System.out.println("Error: La fecha debe seguir el formato YYYY/MM/DD HH:MM y ser futura.");
            } else {
                break;
            }
        } while (true);

        String fechaFin;
        do {
            System.out.print("Ingrese nueva fecha de fin (formato YYYY/MM/DD HH:MM, deje en blanco para no cambiar): ");
            fechaFin = entradaTeclado.nextLine().trim();
            if (!fechaFin.isEmpty()) {
                String fechaInicioRef = fechaInicio.isEmpty() ? new SimpleDateFormat("yyyy/MM/dd HH:mm").format(reservaOriginal.getFechaInicio()) : fechaInicio;
                if (!Validadores.esFechaConHoraValida(fechaFin) || !Validadores.esFechaFinValida(fechaInicioRef, fechaFin)) {
                    System.out.println("Error: La fecha de fin debe ser válida y posterior a la fecha de inicio.");
                } else {
                    break;
                }
            } else {
                break;
            }
        } while (true);

        String idUsuario;
        Usuario usuario = reservaOriginal.getUsuario();
        do {
            System.out.print("Ingrese nuevo ID del usuario (formato USR-XXX, deje en blanco para no cambiar): ");
            idUsuario = entradaTeclado.nextLine().trim();
            if (!idUsuario.isEmpty()) {
                if (!Validadores.esIdValido(idUsuario)) {
                    System.out.println("Error: El ID debe seguir el formato USR-XXX.");
                } else {
                    usuario = universidad.buscarUsuario(idUsuario);
                    if (usuario == null) {
                        System.out.println("Error: Usuario no encontrado.");
                    } else {
                        break;
                    }
                }
            } else {
                break;
            }
        } while (true);

        String idEspacio;
        Espacio espacio = reservaOriginal.getEspacio();
        do {
            System.out.print("Ingrese nuevo ID del espacio (formato ESP-XXX, deje en blanco para no cambiar): ");
            idEspacio = entradaTeclado.nextLine().trim();
            if (!idEspacio.isEmpty()) {
                if (!Validadores.esIdValido(idEspacio)) {
                    System.out.println("Error: El ID debe seguir el formato ESP-XXX.");
                } else {
                    espacio = universidad.buscarEspacio(idEspacio);
                    if (espacio == null) {
                        System.out.println("Error: Espacio no encontrado.");
                    } else {
                        break;
                    }
                }
            } else {
                break;
            }
        } while (true);

        // Validar solapamiento
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            Date nuevaInicio = sdf.parse(fechaInicio.isEmpty() ? sdf.format(reservaOriginal.getFechaInicio()) : fechaInicio);
            Date nuevaFin = sdf.parse(fechaFin.isEmpty() ? sdf.format(reservaOriginal.getFechaFin()) : fechaFin);
            String idEspacioFinal = idEspacio.isEmpty() ? reservaOriginal.getEspacio().getIdEspacio() : idEspacio;

            Reserva[] reservas = universidad.getReservas();
            for (int i = 0; i < reservas.length; i++) {
                if (i != pos && reservas[i] != null && reservas[i].getEspacio().getIdEspacio().equals(idEspacioFinal)) {
                    Date existenteInicio = reservas[i].getFechaInicio();
                    Date existenteFin = reservas[i].getFechaFin();
                    if (!(nuevaFin.before(existenteInicio) || nuevaInicio.after(existenteFin))) {
                        System.out.println("Error: Ya existe una reserva para el espacio " + idEspacioFinal + " en el horario solicitado.");
                        return null;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error al validar el horario de la reserva.");
            return null;
        }

        Reserva reservaEditada = null;
        if (reservaOriginal instanceof ReservaTemporal) {
            int duracionHoras;
            do {
                System.out.print("Ingrese nueva duración en horas (0 para no cambiar): ");
                if (entradaTeclado.hasNextInt()) {
                    duracionHoras = entradaTeclado.nextInt();
                    entradaTeclado.nextLine();
                    if (duracionHoras == 0) {
                        duracionHoras = ((ReservaTemporal) reservaOriginal).getDuracionHoras();
                        break;
                    }
                    if (duracionHoras <= 0) {
                        System.out.println("Error: La duración debe ser mayor que 0.");
                    } else {
                        break;
                    }
                } else {
                    System.out.println("Error: Debe ingresar un número válido.");
                    entradaTeclado.nextLine();
                }
            } while (true);

            reservaEditada = new ReservaTemporal(
                    fechaInicio.isEmpty() ? new SimpleDateFormat("yyyy/MM/dd HH:mm").format(reservaOriginal.getFechaInicio()) : fechaInicio,
                    fechaFin.isEmpty() ? new SimpleDateFormat("yyyy/MM/dd HH:mm").format(reservaOriginal.getFechaFin()) : fechaFin,
                    reservaOriginal.getEstado(),
                    usuario,
                    espacio,
                    duracionHoras
            );
        } else if (reservaOriginal instanceof ReservaSemestre) {
            String semestre;
            do {
                System.out.print("Ingrese nuevo semestre (formato YYYY-N, deje en blanco para no cambiar): ");
                semestre = entradaTeclado.nextLine().trim();
                if (!semestre.isEmpty()) {
                    if (!semestre.matches("\\d{4}-[1-2]")) {
                        System.out.println("Error: El semestre debe ser del formato YYYY-N (ej. 2025-1).");
                    } else {
                        break;
                    }
                } else {
                    semestre = ((ReservaSemestre) reservaOriginal).getSemestre();
                    break;
                }
            } while (true);

            reservaEditada = new ReservaSemestre(
                    fechaInicio.isEmpty() ? new SimpleDateFormat("yyyy/MM/dd HH:mm").format(reservaOriginal.getFechaInicio()) : fechaInicio,
                    fechaFin.isEmpty() ? new SimpleDateFormat("yyyy/MM/dd HH:mm").format(reservaOriginal.getFechaFin()) : fechaFin,
                    reservaOriginal.getEstado(),
                    usuario,
                    espacio,
                    semestre
            );
        }

        if (reservaEditada != null && universidad.editarReserva(pos, reservaEditada)) {
            System.out.println("Reserva editada exitosamente.");
            return reservaEditada;
        } else {
            System.out.println("Error: No se pudo editar la reserva, posible conflicto.");
            return null;
        }
    }

    public boolean eliminar() {
        if (universidad.getNumReservas() == 0) {
            System.out.println("Error: No hay reservas disponibles para eliminar.");
            return false;
        }

        System.out.println("\n=== ELIMINAR RESERVA ===");
        System.out.println("Lista de reservas actuales:");
        System.out.println(universidad.consultarReservas());

        int pos;
        do {
            System.out.print("Ingrese la posición de la reserva a eliminar: ");
            if (entradaTeclado.hasNextInt()) {
                pos = entradaTeclado.nextInt() - 1;
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
        System.out.print("¿Está seguro de eliminar la reserva con ID " + reserva.getIdReserva() + "? (s/n): ");
        String confirmacion = entradaTeclado.nextLine().trim().toLowerCase();
        if (confirmacion.equals("s")) {
            if (universidad.eliminarReserva(pos)) {
                System.out.println("Reserva eliminada exitosamente.");
                return true;
            } else {
                System.out.println("Error: No se pudo eliminar la reserva.");
                return false;
            }
        }
        System.out.println("Eliminación cancelada.");
        return false;
    }

    public boolean aprobarRechazar() {
        if (universidad.getNumReservas() == 0) {
            System.out.println("Error: No hay reservas disponibles para aprobar/rechazar.");
            return false;
        }

        System.out.println("\n=== APROBAR/RECHAZAR RESERVA ===");
        System.out.println("Lista de reservas actuales:");
        System.out.println(universidad.consultarReservas());

        int pos;
        do {
            System.out.print("Ingrese la posición de la reserva: ");
            if (entradaTeclado.hasNextInt()) {
                pos = entradaTeclado.nextInt() - 1;
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
        EstadoReserva estado = null;
        do {
            System.out.println("Seleccione el estado de la reserva:");
            System.out.println("1. Pendiente");
            System.out.println("2. Aprobada");
            System.out.println("3. Rechazada");
            System.out.print("Ingrese el número de la opción: ");
            if (entradaTeclado.hasNextInt()) {
                int opcionEstado = entradaTeclado.nextInt();
                entradaTeclado.nextLine();
                switch (opcionEstado) {
                    case 1: estado = EstadoReserva.PENDIENTE; break;
                    case 2: estado = EstadoReserva.APROBADO; break;
                    case 3: estado = EstadoReserva.RECHAZADO; break;
                    default: System.out.println("Error: Opción inválida.");
                }
            } else {
                System.out.println("Error: Debe ingresar un número válido.");
                entradaTeclado.nextLine();
            }
        } while (estado == null);

        reserva.setEstado(estado);
        System.out.println("Estado de la reserva actualizado: " + estado.getDescripcion());
        return true;
    }
}