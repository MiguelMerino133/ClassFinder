package ec.edu.uce.classfinder.gui;

import ec.edu.uce.classfinder.dominio.Edificio;
import ec.edu.uce.classfinder.dominio.Lugar;
import ec.edu.uce.classfinder.dominio.Universidad;
import ec.edu.uce.classfinder.util.Validadores;
import java.util.Scanner;

public class GUILugar {
    private Scanner entradaTeclado = new Scanner(System.in);
    private Universidad universidad;

    public GUILugar() {
        this.universidad = Universidad.getInstance();
    }

    public String registrar() {
        System.out.println("REGISTRAR LUGAR");
        String tipoLugar;
        while (true) {
            System.out.println("Tipo de lugar: 1. Lugar genérico  2. Edificio");
            System.out.print("Opción (Ej: 1): ");
            tipoLugar = entradaTeclado.nextLine();
            if (tipoLugar.equals("1") || tipoLugar.equals("2")) break;
            System.out.println("Opción inválida. Ingrese 1 para Lugar genérico o 2 para Edificio.");
        }

        String nombre;
        while (true) {
            System.out.print("Ingrese el nombre del lugar (Ej: Biblioteca Central): ");
            nombre = entradaTeclado.nextLine();
            if (Validadores.esTextoValido(nombre)) break;
            System.out.println("Nombre inválido. Use solo letras, espacios, máx. 75 caracteres.");
        }

        String descripcion;
        while (true) {
            System.out.print("Ingrese la descripción (opcional, Ej: Lugar principal): ");
            descripcion = entradaTeclado.nextLine();
            if (descripcion.isBlank() || Validadores.esTextoValido(descripcion)) break;
            System.out.println("Descripción inválida. Use solo letras, espacios, máx. 75 caracteres.");
        }

        Lugar nuevoLugar;
        String idLugar = "LUG-" + String.format("%03d", Lugar.getContador());
        if (tipoLugar.equals("2")) {
            int pisos;
            while (true) {
                System.out.print("Ingrese el número de pisos (Ej: 4): ");
                String pisosStr = entradaTeclado.nextLine();
                if (Validadores.esPisosValido(pisosStr)) {
                    pisos = Integer.parseInt(pisosStr);
                    break;
                }
                System.out.println("Pisos inválidos. Debe ser un número entre 1 y 100.");
            }

            String direccion;
            while (true) {
                System.out.print("Ingrese la dirección del edificio (Ej: Av. América): ");
                direccion = entradaTeclado.nextLine();
                if (Validadores.esTextoValido(direccion)) break;
                System.out.println("Dirección inválida. Use solo letras, espacios, máx. 75 caracteres.");
            }

            nuevoLugar = new Edificio(nombre, descripcion, pisos, direccion);
        } else {
            nuevoLugar = new Lugar(nombre, descripcion);
        }

        String resultado = universidad.getLugarCRUD().nuevo(nuevoLugar);
        return resultado.contains("exitosamente") ? "Lugar creado exitosamente con ID: " + idLugar : "Error al crear el lugar: " + resultado;
    }

    public String consultar() {
        System.out.println("CONSULTAR LUGAR");
        while (true) {
            System.out.print("Ingrese el ID del lugar (Ej: LUG-001): ");
            String idLugar = entradaTeclado.nextLine();
            if (!Validadores.esIdValido(idLugar)) {
                System.out.println("ID inválido. Use formato (LUG-001).");
                continue;
            }
            Object lugar = universidad.getLugarCRUD().buscarPorId(parseId(idLugar));
            return lugar != null ? lugar.toString() : "Lugar con ID " + idLugar + " no encontrado.";
        }
    }

    public String consultarTodos() {
        System.out.println("LISTA DE LUGARES");
        return universidad.getLugarCRUD().listar();
    }

    public String editar() {
        System.out.println("EDITAR LUGAR");
        while (true) {
            System.out.print("Ingrese el ID del lugar a editar (Ej: LUG-001): ");
            String idLugar = entradaTeclado.nextLine();
            if (!Validadores.esIdValido(idLugar)) {
                System.out.println("ID inválido. Use formato (LUG-001).");
                continue;
            }
            Object lugarObj = universidad.getLugarCRUD().buscarPorId(parseId(idLugar));
            if (!(lugarObj instanceof Lugar)) {
                return "Lugar con ID " + idLugar + " no encontrado.";
            }

            Lugar lugar = (Lugar) lugarObj;
            System.out.println("Lugar encontrado: " + lugar.toString());
            System.out.println("Deje en blanco si no desea modificar un campo.");

            System.out.print("Nuevo nombre [" + lugar.getNombre() + "] (Ej: Biblioteca Central): ");
            String nombre = entradaTeclado.nextLine();
            if (!nombre.isBlank() && Validadores.esTextoValido(nombre)) {
                lugar.setNombre(nombre);
            } else if (!nombre.isBlank()) {
                System.out.println("Nombre inválido, se mantiene el actual.");
            }

            System.out.print("Nueva descripción [" + lugar.getDescripcion() + "] (Ej: Lugar principal): ");
            String descripcion = entradaTeclado.nextLine();
            if (!descripcion.isBlank() && Validadores.esTextoValido(descripcion)) {
                lugar.setDescripcion(descripcion);
            } else if (!descripcion.isBlank()) {
                System.out.println("Descripción inválida, se mantiene la actual.");
            }

            if (lugar instanceof Edificio) {
                Edificio edificio = (Edificio) lugar;
                System.out.print("Nuevo número de pisos [" + edificio.getPisos() + "] (Ej: 4): ");
                String pisosStr = entradaTeclado.nextLine();
                if (!pisosStr.isBlank() && Validadores.esPisosValido(pisosStr)) {
                    edificio.setPisos(Integer.parseInt(pisosStr));
                } else if (!pisosStr.isBlank()) {
                    System.out.println("Pisos inválidos, se mantiene el actual.");
                }

                System.out.print("Nueva dirección [" + edificio.getDireccion() + "] (Ej: Av. América): ");
                String direccion = entradaTeclado.nextLine();
                if (!direccion.isBlank() && Validadores.esTextoValido(direccion)) {
                    edificio.setDireccion(direccion);
                } else if (!direccion.isBlank()) {
                    System.out.println("Dirección inválida, se mantiene la actual.");
                }
            }

            String resultado = universidad.getLugarCRUD().editar(lugar);
            return resultado.contains("exitosamente") ? resultado : "Error al editar el lugar: " + resultado;
        }
    }

    public String eliminar() {
        System.out.println("ELIMINAR LUGAR");
        while (true) {
            System.out.print("Ingrese el ID del lugar a eliminar (Ej: LUG-001): ");
            String idLugar = entradaTeclado.nextLine();
            if (!Validadores.esIdValido(idLugar)) {
                System.out.println("ID inválido. Use formato (Ej: LUG-001).");
                continue;
            }
            Object lugarObj = universidad.getLugarCRUD().buscarPorId(parseId(idLugar));
            if (!(lugarObj instanceof Lugar)) {
                return "Lugar con ID " + idLugar + " no encontrado.";
            }
            String resultado = universidad.getLugarCRUD().borrar(lugarObj);
            return resultado.contains("exitosamente") ? resultado : "Error al eliminar el lugar: " + resultado;
        }
    }

    private Integer parseId(String id) {
        try {
            return Integer.parseInt(id.replace("LUG-", ""));
        } catch (NumberFormatException e) {
            return null;
        }
    }
}