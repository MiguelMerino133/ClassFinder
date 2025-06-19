package ec.edu.uce.classfinder.gui;

import ec.edu.uce.classfinder.dominio.*;
import ec.edu.uce.classfinder.util.Validadores;
import java.util.Scanner;

public class GUIReserva {
    private Scanner entradaTeclado = new Scanner(System.in);
    private Universidad universidad;

    public GUIReserva() {
        this.universidad = Universidad.getInstance();
    }

    public String registrar() {
        System.out.println("REGISTRAR RESERVA");
        String tipoReserva;
        while (true) {
            System.out.println("Tipo de reserva: 1. Temporal  2. Semestre");
            System.out.print("Opción (Ej: 1): ");
            tipoReserva = entradaTeclado.nextLine();
            if (tipoReserva.equals("1") || tipoReserva.equals("2")) break;
            System.out.println("Opción inválida. Ingrese 1 para Temporal o 2 para Semestre.");
        }

        String idUsuario;
        Usuario usuario;
        while (true) {
            System.out.print("Ingrese el ID del usuario (Ej: USR-001): ");
            idUsuario = entradaTeclado.nextLine();
            if (!Validadores.esIdValido(idUsuario)) {
                System.out.println("ID inválido. Use formato (USR-001).");
                continue;
            }
            usuario = universidad.buscarUsuario(idUsuario);
            if (usuario != null) break;
            System.out.println("Usuario con ID " + idUsuario + " no encontrado.");
        }

        String idEspacio;
        Espacio espacio;
        while (true) {
            System.out.print("Ingrese el ID del espacio (Ej: ESP-001): ");
            idEspacio = entradaTeclado.nextLine();
            if (!Validadores.esIdValido(idEspacio)) {
                System.out.println("ID inválido. Use formato (ESP-001).");
                continue;
            }
            espacio = universidad.buscarEspacio(idEspacio);
            if (espacio != null) break;
            System.out.println("Espacio con ID " + idEspacio + " no encontrado.");
        }

        String fechaInicio;
        while (true) {
            System.out.print("Ingrese la fecha y hora de inicio (Ej: 2025/07/01 08:00): ");
            fechaInicio = entradaTeclado.nextLine();
            if (Validadores.esFechaConHoraValida(fechaInicio)) break;
            System.out.println("Fecha inválida. Use formato yyyy/MM/dd HH:mm y fecha futura.");
        }

        String fechaFin;
        while (true) {
            System.out.print("Ingrese la fecha y hora de fin (Ej: 2025/07/01 10:00): ");
            fechaFin = entradaTeclado.nextLine();
            if (Validadores.esFechaConHoraValida(fechaFin) && Validadores.esFechaFinValida(fechaInicio, fechaFin)) break;
            System.out.println("Fecha inválida. Debe ser posterior a la fecha de inicio.");
        }

        Reserva nuevaReserva;
        String idReserva = "RES-" + String.format("%03d", Reserva.getContador());
        if (tipoReserva.equals("1")) {
            int duracion;
            while (true) {
                System.out.print("Ingrese la duración en horas (Ej: 2): ");
                String duracionStr = entradaTeclado.nextLine();
                if (Validadores.esNumeroValido(duracionStr)) {
                    duracion = Integer.parseInt(duracionStr);
                    if (duracion > 0) break;
                }
                System.out.println("Duración inválida. Debe ser un número positivo.");
            }
            nuevaReserva = new ReservaTemporal(fechaInicio, fechaFin, EstadoReserva.PENDIENTE, usuario, espacio, duracion);
        } else {
            String codigoSemestre;
            while (true) {
                System.out.print("Ingrese el código del semestre (Ej: 2025-1): ");
                codigoSemestre = entradaTeclado.nextLine();
                if (Validadores.esCodigoSemestreValido(codigoSemestre)) break;
                System.out.println("Código inválido. Use formato YYYY-N (Ej: 2025-1).");
            }
            nuevaReserva = new ReservaSemestre(fechaInicio, fechaFin, EstadoReserva.PENDIENTE, usuario, espacio, codigoSemestre);
        }

        String resultado = universidad.getReservaCRUD().nuevo(nuevaReserva);
        return resultado.contains("exitosamente") ? "Reserva creada exitosamente con ID: " + idReserva : "Error al crear la reserva: " + resultado;
    }

    public String consultar() {
        System.out.println("CONSULTAR RESERVA");
        while (true) {
            System.out.print("Ingrese el ID de la reserva (Ej: RES-001): ");
            String idReserva = entradaTeclado.nextLine();
            if (!Validadores.esIdValido(idReserva)) {
                System.out.println("ID inválido. Use formato (RES-001).");
                continue;
            }
            Object reserva = universidad.getReservaCRUD().buscarPorId(parseId(idReserva));
            return reserva != null ? reserva.toString() : "Reserva con ID " + idReserva + " no encontrada.";
        }
    }

    public String consultarTodos() {
        System.out.println("LISTA DE RESERVAS");
        return universidad.getReservaCRUD().listar();
    }

    public String editar() {
        System.out.println("EDITAR RESERVA");
        while (true) {
            System.out.print("Ingrese el ID de la reserva a editar (Ej: RES-001): ");
            String idReserva = entradaTeclado.nextLine();
            if (!Validadores.esIdValido(idReserva)) {
                System.out.println("ID inválido. Use formato (RES-001).");
                continue;
            }
            Object reservaObj = universidad.getReservaCRUD().buscarPorId(parseId(idReserva));
            if (!(reservaObj instanceof Reserva)) {
                return "Reserva con ID " + idReserva + " no encontrada.";
            }
            Reserva reserva = (Reserva) reservaObj;
            System.out.println("Reserva encontrada: " + reserva.toString());
            System.out.println("Deje en blanco si no desea modificar un campo.");

            System.out.print("Nueva fecha y hora de inicio [" + reserva.getFechaInicioStr() + "] (Ej: 2025/07/01 08:00): ");
            String fechaInicio = entradaTeclado.nextLine();
            String nuevaFechaInicio = fechaInicio.isBlank() ? reserva.getFechaInicioStr() : fechaInicio;
            if (!fechaInicio.isBlank() && !Validadores.esFechaConHoraValida(fechaInicio)) {
                System.out.println("Fecha inválida, se mantiene la actual.");
                nuevaFechaInicio = reserva.getFechaInicioStr();
            }

            System.out.print("Nueva fecha y hora de fin [" + reserva.getFechaFinStr() + "] (Ej: 2025/07/01 10:00): ");
            String fechaFin = entradaTeclado.nextLine();
            String nuevaFechaFin = fechaFin.isBlank() ? reserva.getFechaFinStr() : fechaFin;
            if (!fechaFin.isBlank() && (!Validadores.esFechaConHoraValida(fechaFin) || !Validadores.esFechaFinValida(nuevaFechaInicio, fechaFin))) {
                System.out.println("Fecha inválida o no posterior a inicio, se mantiene la actual.");
                nuevaFechaFin = reserva.getFechaFinStr();
            }

            reserva.setFechaInicio(nuevaFechaInicio);
            reserva.setFechaFin(nuevaFechaFin);

            if (reserva instanceof ReservaTemporal) {
                ReservaTemporal reservaTemporal = (ReservaTemporal) reserva;
                System.out.print("Nueva duración en horas [" + reservaTemporal.getDuracionHoras() + "] (Ej: 2): ");
                String duracionStr = entradaTeclado.nextLine();
                if (!duracionStr.isBlank() && Validadores.esNumeroValido(duracionStr)) {
                    int duracion = Integer.parseInt(duracionStr);
                    if (duracion > 0) {
                        reservaTemporal.setDuracionHoras(duracion);
                    } else {
                        System.out.println("Duración inválida, se mantiene la actual.");
                    }
                }
            } else if (reserva instanceof ReservaSemestre) {
                ReservaSemestre reservaSemestre = (ReservaSemestre) reserva;
                System.out.print("Nuevo código de semestre [" + reservaSemestre.getCodigoSemestre() + "] (Ej: 2025-1): ");
                String codigoSemestre = entradaTeclado.nextLine();
                if (!codigoSemestre.isBlank() && Validadores.esCodigoSemestreValido(codigoSemestre)) {
                    reservaSemestre.setCodigoSemestre(codigoSemestre);
                } else if (!codigoSemestre.isBlank()) {
                    System.out.println("Código inválido, se mantiene el actual.");
                }
            }

            String resultado = universidad.getReservaCRUD().editar(reserva);
            return resultado.contains("exitosamente") ? resultado : "Error al editar la reserva: " + resultado;
        }
    }

    public String eliminar() {
        System.out.println("ELIMINAR RESERVA");
        while (true) {
            System.out.print("Ingrese el ID de la reserva a eliminar (Ej: RES-001): ");
            String idReserva = entradaTeclado.nextLine();
            if (!Validadores.esIdValido(idReserva)) {
                System.out.println("ID inválido. Use formato (RES-001).");
                continue;
            }
            Object reservaObj = universidad.getReservaCRUD().buscarPorId(parseId(idReserva));
            if (!(reservaObj instanceof Reserva)) {
                return "Reserva con ID " + idReserva + " no encontrada.";
            }
            String resultado = universidad.getReservaCRUD().borrar(reservaObj);
            return resultado.contains("exitosamente") ? resultado : "Error al eliminar la reserva: " + resultado;
        }
    }

    public String aprobarRechazar() {
        System.out.println("APROBAR/RECHAZAR RESERVA");
        while (true) {
            System.out.print("Ingrese el ID de la reserva (Ej: RES-001): ");
            String idReserva = entradaTeclado.nextLine();
            if (!Validadores.esIdValido(idReserva)) {
                System.out.println("ID inválido. Use formato (RES-001).");
                continue;
            }
            Object reservaObj = universidad.getReservaCRUD().buscarPorId(parseId(idReserva));
            if (!(reservaObj instanceof Reserva)) {
                return "Reserva con ID " + idReserva + " no encontrada.";
            }
            Reserva reserva = (Reserva) reservaObj;
            if (reserva.getEstado() != EstadoReserva.PENDIENTE) {
                return "La reserva no está en estado PENDIENTE.";
            }
            System.out.println("Reserva encontrada: " + reserva.toString());
            while (true) {
                System.out.println("1. Aprobar reserva");
                System.out.println("2. Rechazar reserva");
                System.out.print("Opción (Ej: 1): ");
                String opcion = entradaTeclado.nextLine();
                if (opcion.equals("1") || opcion.equals("2")) {
                    reserva.setEstado(opcion.equals("1") ? EstadoReserva.APROBADO : EstadoReserva.RECHAZADO);
                    String resultado = universidad.getReservaCRUD().editar(reserva);
                    return resultado.contains("exitosamente") ?
                            (opcion.equals("1") ? "Reserva aprobada exitosamente." : "Reserva rechazada exitosamente.") :
                            "Error al procesar la reserva: " + resultado;
                }
                System.out.println("Opción inválida. Ingrese 1 para Aprobar o 2 para Rechazar.");
            }
        }
    }

    private Integer parseId(String id) {
        try {
            return Integer.parseInt(id.replace("RES-", ""));
        } catch (NumberFormatException e) {
            return null;
        }
    }
}