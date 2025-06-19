package ec.edu.uce.classfinder.gui;

import ec.edu.uce.classfinder.dominio.*;
import ec.edu.uce.classfinder.util.Validadores;
import java.util.Scanner;

public class GUIEspacio {
    private Scanner entradaTeclado = new Scanner(System.in);
    private Universidad universidad;

    public GUIEspacio() {
        this.universidad = Universidad.getInstance();
    }

    public String registrar() {
        System.out.println("REGISTRAR ESPACIO");
        Lugar lugar;
        while (true) {
            System.out.print("Ingrese el ID del lugar (Ej: LUG-001): ");
            String idLugar = entradaTeclado.nextLine();
            if (!Validadores.esIdValido(idLugar)) {
                System.out.println("ID inválido. Use formato (LUG-001).");
                continue;
            }
            Object lugarObj = universidad.getLugarCRUD().buscarPorId(parseId(idLugar, "LUG-"));
            if (lugarObj instanceof Lugar) {
                lugar = (Lugar) lugarObj;
                break;
            }
            System.out.println("Lugar con ID " + idLugar + " no encontrado.");
        }

        String tipoEspacio;
        while (true) {
            System.out.println("Tipo de espacio: 1. Aula  2. Auditorio");
            System.out.print("Opción (Ej: 1): ");
            tipoEspacio = entradaTeclado.nextLine();
            if (tipoEspacio.equals("1") || tipoEspacio.equals("2")) break;
            System.out.println("Opción inválida. Ingrese 1 para Aula o 2 para Auditorio.");
        }

        int capacidad;
        while (true) {
            System.out.print("Ingrese la capacidad (Ej: 30): ");
            String capacidadStr = entradaTeclado.nextLine();
            if (Validadores.esCapacidadValido(capacidadStr)) {
                capacidad = Integer.parseInt(capacidadStr);
                break;
            }
            System.out.println("Capacidad inválida. Debe ser un número entre 1 y 999.");
        }

        Tamano tamano;
        while (true) {
            System.out.println("Seleccione el tamaño:");
            for (Tamano t : Tamano.values()) {
                System.out.println(t.ordinal() + 1 + ". " + t.getDescripcion());
            }
            System.out.print("Opción (Ej: 1): ");
            String opcionTamano = entradaTeclado.nextLine();
            try {
                int opcionNum = Integer.parseInt(opcionTamano);
                if (opcionNum >= 1 && opcionNum <= Tamano.values().length) {
                    tamano = Tamano.values()[opcionNum - 1];
                    break;
                }
            } catch (NumberFormatException e) {

            }
            System.out.println("Tamaño inválido. Ingrese un número entre 1 y " + Tamano.values().length + ".");
        }

        Espacio nuevoEspacio;
        String idEspacio = "ESP-" + String.format("%03d", Espacio.getContador());
        if (tipoEspacio.equals("1")) {
            String numeroAula = "AUL-" + String.format("%03d", Aula.getContadorAulas());
            nuevoEspacio = new Aula(capacidad, tamano, numeroAula);
            System.out.println("Aula creada con número: " + numeroAula + ", ID: " + idEspacio);
        } else {
            String nombre;
            while (true) {
                System.out.print("Ingrese el nombre del auditorio (Ej: Auditorio A-1): ");
                nombre = entradaTeclado.nextLine();
                if (Validadores.esTextoValido(nombre)) break;
                System.out.println("Nombre inválido. Use letras, espacios, máx. 75 caracteres.");
            }
            boolean equipoSonido;
            while (true) {
                System.out.print("¿Tiene equipo de sonido? (s/n) (Ej: s): ");
                String equipoSonidoStr = entradaTeclado.nextLine();
                if (equipoSonidoStr.equalsIgnoreCase("s") || equipoSonidoStr.equalsIgnoreCase("n")) {
                    equipoSonido = equipoSonidoStr.equalsIgnoreCase("s");
                    break;
                }
                System.out.println("Opción inválida. Ingrese 's' para sí o 'n' para no.");
            }
            nuevoEspacio = new Auditorio(nombre, capacidad, tamano, equipoSonido);
            System.out.println("Auditorio creado con ID: " + idEspacio);
        }

        String resultado = lugar.nuevo(nuevoEspacio);
        return resultado.contains("exitosamente") ? resultado : "Error al crear el espacio: " + resultado;
    }

    public String consultar() {
        System.out.println("CONSULTAR ESPACIO");
        Lugar lugar;
        while (true) {
            System.out.print("Ingrese el ID del lugar (Ej: LUG-001): ");
            String idLugar = entradaTeclado.nextLine();
            if (!Validadores.esIdValido(idLugar)) {
                System.out.println("ID inválido. Use formato (LUG-001).");
                continue;
            }
            Object lugarObj = universidad.getLugarCRUD().buscarPorId(parseId(idLugar, "LUG-"));
            if (lugarObj instanceof Lugar) {
                lugar = (Lugar) lugarObj;
                break;
            }
            System.out.println("Lugar con ID " + idLugar + " no encontrado.");
        }

        while (true) {
            System.out.print("Ingrese el ID del espacio (Ej: ESP-001): ");
            String idEspacio = entradaTeclado.nextLine();
            if (!Validadores.esIdValido(idEspacio)) {
                System.out.println("ID inválido. Use formato (ESP-001).");
                continue;
            }
            Object espacio = lugar.buscarPorId(parseId(idEspacio, "ESP-"));
            return espacio != null ? espacio.toString() : "Espacio con ID " + idEspacio + " no encontrado.";
        }
    }

    public String consultarTodos() {
        System.out.println("LISTA DE ESPACIOS");
        Lugar lugar;
        while (true) {
            System.out.print("Ingrese el ID del lugar (Ej: LUG-001): ");
            String idLugar = entradaTeclado.nextLine();
            if (!Validadores.esIdValido(idLugar)) {
                System.out.println("ID inválido. Use formato (LUG-001).");
                continue;
            }
            Object lugarObj = universidad.getLugarCRUD().buscarPorId(parseId(idLugar, "LUG-"));
            if (lugarObj instanceof Lugar) {
                lugar = (Lugar) lugarObj;
                break;
            }
            System.out.println("Lugar con ID " + idLugar + " no encontrado.");
        }
        return lugar.listar();
    }

    public String editar() {
        System.out.println("EDITAR ESPACIO");
        Lugar lugar;
        while (true) {
            System.out.print("Ingrese el ID del lugar (Ej: LUG-001): ");
            String idLugar = entradaTeclado.nextLine();
            if (!Validadores.esIdValido(idLugar)) {
                System.out.println("ID inválido. Use formato (LUG-001).");
                continue;
            }
            Object lugarObj = universidad.getLugarCRUD().buscarPorId(parseId(idLugar, "LUG-"));
            if (lugarObj instanceof Lugar) {
                lugar = (Lugar) lugarObj;
                break;
            }
            System.out.println("Lugar con ID " + idLugar + " no encontrado.");
        }

        Espacio espacio;
        while (true) {
            System.out.print("Ingrese el ID del espacio a editar (Ej: ESP-001): ");
            String idEspacio = entradaTeclado.nextLine();
            if (!Validadores.esIdValido(idEspacio)) {
                System.out.println("ID inválido. Use formato (ESP-001).");
                continue;
            }
            Object espacioObj = lugar.buscarPorId(parseId(idEspacio, "ESP-"));
            if (espacioObj instanceof Espacio) {
                espacio = (Espacio) espacioObj;
                break;
            }
            System.out.println("Espacio con ID " + idEspacio + " no encontrado.");
        }

        System.out.println("Espacio encontrado: " + espacio.toString());
        System.out.println("Deje en blanco si no desea modificar un campo.");

        if (espacio instanceof Auditorio) {
            System.out.print("Nuevo nombre [" + espacio.getNombre() + "] (Ej: Auditorio A-1): ");
            String nombre = entradaTeclado.nextLine();
            if (!nombre.isBlank() && Validadores.esTextoValido(nombre)) {
                espacio.setNombre(nombre);
            } else if (!nombre.isBlank()) {
                System.out.println("Nombre inválido, se mantiene el actual.");
            }
        }

        System.out.print("Nueva capacidad [" + espacio.getCapacidad() + "] (Ej: 30): ");
        String capacidadStr = entradaTeclado.nextLine();
        if (!capacidadStr.isBlank() && Validadores.esCapacidadValido(capacidadStr)) {
            espacio.setCapacidad(Integer.parseInt(capacidadStr));
        } else if (!capacidadStr.isBlank()) {
            System.out.println("Capacidad inválida, se mantiene la actual.");
        }

        System.out.println("Seleccione el nuevo tamaño (o presione Enter para mantener [" + espacio.getTamano().getDescripcion() + "]):");
        for (Tamano t : Tamano.values()) {
            System.out.println(t.ordinal() + 1 + ". " + t.getDescripcion());
        }
        System.out.print("Opción (Ej: 1): ");
        String opcionTamano = entradaTeclado.nextLine();
        if (!opcionTamano.isBlank()) {
            try {
                int opcionNum = Integer.parseInt(opcionTamano);
                if (opcionNum >= 1 && opcionNum <= Tamano.values().length) {
                    espacio.setTamano(Tamano.values()[opcionNum - 1]);
                } else {
                    System.out.println("Tamaño inválido, se mantiene el actual.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Tamaño inválido, se mantiene el actual.");
            }
        }

        if (espacio instanceof Auditorio) {
            Auditorio auditorio = (Auditorio) espacio;
            System.out.print("¿Tiene equipo de sonido? (s/n) [" + (auditorio.hasEquipoSonido() ? "s" : "n") + "] (Ej: s): ");
            String equipoSonidoStr = entradaTeclado.nextLine();
            if (!equipoSonidoStr.isBlank() && (equipoSonidoStr.equalsIgnoreCase("s") || equipoSonidoStr.equalsIgnoreCase("n"))) {
                auditorio.setEquipoSonido(equipoSonidoStr.equalsIgnoreCase("s"));
            }
        }

        String resultado = lugar.editar(espacio);
        return resultado.contains("exitosamente") ? resultado : "Error al editar el espacio: " + resultado;
    }

    public String eliminar() {
        System.out.println("ELIMINAR ESPACIO");
        Lugar lugar;
        while (true) {
            System.out.print("Ingrese el ID del lugar (Ej: LUG-001): ");
            String idLugar = entradaTeclado.nextLine();
            if (!Validadores.esIdValido(idLugar)) {
                System.out.println("ID inválido. Use formato (LUG-001).");
                continue;
            }
            Object lugarObj = universidad.getLugarCRUD().buscarPorId(parseId(idLugar, "LUG-"));
            if (lugarObj instanceof Lugar) {
                lugar = (Lugar) lugarObj;
                break;
            }
            System.out.println("Lugar con ID " + idLugar + " no encontrado.");
        }

        while (true) {
            System.out.print("Ingrese el ID del espacio a eliminar (Ej: ESP-001): ");
            String idEspacio = entradaTeclado.nextLine();
            if (!Validadores.esIdValido(idEspacio)) {
                System.out.println("ID inválido. Use formato (ESP-001).");
                continue;
            }
            Object espacioObj = lugar.buscarPorId(parseId(idEspacio, "ESP-"));
            if (!(espacioObj instanceof Espacio)) {
                return "Espacio con ID " + idEspacio + " no encontrado.";
            }
            String resultado = lugar.borrar(espacioObj);
            return resultado.contains("exitosamente") ? resultado : "Error al eliminar el espacio: " + resultado;
        }
    }

    private Integer parseId(String id, String prefix) {
        try {
            return Integer.parseInt(id.replace(prefix, ""));
        } catch (NumberFormatException e) {
            return null;
        }
    }
}