package ec.edu.uce.classfinder.gui;

import ec.edu.uce.classfinder.dominio.Edificio;
import ec.edu.uce.classfinder.dominio.Lugar;
import ec.edu.uce.classfinder.dominio.Universidad;
import ec.edu.uce.classfinder.util.Validadores;
import java.util.Scanner;

/**
 * Interfaz gráfica consolidada para gestionar lugares en el sistema ClassFinder.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class GUILugar {
    private Scanner entradaTeclado = new Scanner(System.in);
    private Universidad universidad;

    public GUILugar(Universidad universidad) {
        this.universidad = universidad;
    }

    public Lugar registrar() {
        System.out.println("\n=== REGISTRAR LUGAR (EDIFICIO) ===");
        String idLugar, nombre, descripcion, ubicacion;
        int numeroPisos;

        do {
            System.out.print("Ingrese ID del lugar (formato LUG-001): ");
            idLugar = entradaTeclado.nextLine().trim();
            if (!Validadores.esIdValido(idLugar)) {
                System.out.println("Error: El ID debe tener el formato LUG-001.");
            }
        } while (!Validadores.esIdValido(idLugar));

        do {
            System.out.print("Ingrese nombre del lugar: ");
            nombre = entradaTeclado.nextLine().trim();
            if (!Validadores.esTextoValido(nombre)) {
                System.out.println("Error: El nombre debe contener solo letras y espacios, máximo 75 caracteres.");
            }
        } while (!Validadores.esTextoValido(nombre));

        do {
            System.out.print("Ingrese descripción del lugar: ");
            descripcion = entradaTeclado.nextLine().trim();
            if (!Validadores.esTextoValido(descripcion)) {
                System.out.println("Error: La descripción debe contener solo letras y espacios, máximo 75 caracteres.");
            }
        } while (!Validadores.esTextoValido(descripcion));

        do {
            System.out.print("Ingrese número de pisos (mínimo 1): ");
            try {
                numeroPisos = Integer.parseInt(entradaTeclado.nextLine().trim());
                if (numeroPisos <= 0) {
                    System.out.println("Error: El número de pisos debe ser mayor que 0.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido.");
            }
        } while (true);

        do {
            System.out.print("Ingrese ubicación: ");
            ubicacion = entradaTeclado.nextLine().trim();
            if (!Validadores.esTextoValido(ubicacion)) {
                System.out.println("Error: La ubicación debe contener solo letras y espacios, máximo 75 caracteres.");
            }
        } while (!Validadores.esTextoValido(ubicacion));

        return new Edificio(idLugar, nombre, descripcion, numeroPisos, ubicacion);
    }

    public void consultar() {
        System.out.println("\n=== CONSULTAR LUGAR ===");
        String idLugar;
        do {
            System.out.print("Ingrese ID del lugar (formato LUG-001): ");
            idLugar = entradaTeclado.nextLine().trim();
            if (!Validadores.esIdValido(idLugar)) {
                System.out.println("Error: El ID debe tener el formato LUG-001.");
            }
        } while (!Validadores.esIdValido(idLugar));

        boolean encontrado = false;
        for (Lugar lugar : universidad.getLugares()) {
            if (lugar != null && lugar.getIdLugar().equals(idLugar)) {
                System.out.println("Consulta de Lugar - ID: " + idLugar);
                System.out.println("Nombre: " + lugar.getNombre());
                System.out.println("Descripción: " + lugar.getDescripcion());
                if (lugar instanceof Edificio edificio) {
                    System.out.println("Número de pisos: " + edificio.getNumeroPisos());
                    System.out.println("Ubicación: " + edificio.getUbicacion());
                }
                System.out.println("Espacios: ");
                System.out.println(lugar.consultarEspacios());
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Lugar con ID " + idLugar + " no encontrado.");
        }
    }

    public Lugar editar() {
        if (universidad.getNumLugares() == 0) {
            System.out.println("Error: No hay lugares disponibles para editar.");
            return null;
        }

        System.out.println("\n=== EDITAR LUGAR ===");
        System.out.println("Lista de lugares actuales:");
        System.out.println(universidad.consultarLugares());

        int pos;
        do {
            System.out.print("Ingrese la posición del lugar a editar: ");
            try {
                pos = Integer.parseInt(entradaTeclado.nextLine().trim()) - 1;
                if (pos < 0 || pos >= universidad.getNumLugares()) {
                    System.out.println("Error: Posición inválida.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido.");
            }
        } while (true);

        Lugar lugar = universidad.getLugares()[pos];
        System.out.println("Datos actuales: " + lugar.toString());

        System.out.println("ID del lugar: " + lugar.getIdLugar() + " (no editable)");

        do {
            System.out.print("Ingrese nuevo nombre (deje en blanco para no cambiar): ");
            String nombre = entradaTeclado.nextLine().trim();
            if (!nombre.isEmpty()) {
                if (!Validadores.esTextoValido(nombre)) {
                    System.out.println("Error: El nombre debe contener solo letras y espacios, máximo 75 caracteres.");
                } else {
                    lugar.setNombre(nombre);
                    break;
                }
            } else {
                break;
            }
        } while (true);

        do {
            System.out.print("Ingrese nueva descripción (deje en blanco para no cambiar): ");
            String descripcion = entradaTeclado.nextLine().trim();
            if (!descripcion.isEmpty()) {
                if (!Validadores.esTextoValido(descripcion)) {
                    System.out.println("Error: La descripción debe contener solo letras y espacios, máximo 75 caracteres.");
                } else {
                    lugar.setDescripcion(descripcion);
                    break;
                }
            } else {
                break;
            }
        } while (true);

        if (lugar instanceof Edificio edificio) {
            do {
                System.out.print("Ingrese nuevo número de pisos (0 para no cambiar): ");
                try {
                    int numeroPisos = Integer.parseInt(entradaTeclado.nextLine().trim());
                    if (numeroPisos == 0) break;
                    if (numeroPisos <= 0) {
                        System.out.println("Error: El número de pisos debe ser mayor que 0.");
                    } else {
                        edificio.setNumeroPisos(numeroPisos);
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Debe ingresar un número válido.");
                }
            } while (true);

            do {
                System.out.print("Ingrese nueva ubicación (deje en blanco para no cambiar): ");
                String ubicacion = entradaTeclado.nextLine().trim();
                if (!ubicacion.isEmpty()) {
                    if (!Validadores.esTextoValido(ubicacion)) {
                        System.out.println("Error: La ubicación debe contener solo letras y espacios, máximo 75 caracteres.");
                    } else {
                        edificio.setUbicacion(ubicacion);
                        break;
                    }
                } else {
                    break;
                }
            } while (true);
        }

        return lugar;
    }

    public boolean eliminar() {
        if (universidad.getNumLugares() == 0) {
            System.out.println("Error: No hay lugares disponibles para eliminar.");
            return false;
        }

        System.out.println("\n=== ELIMINAR LUGAR ===");
        System.out.println("Lista de lugares actuales:");
        System.out.println(universidad.consultarLugares());

        int pos;
        do {
            System.out.print("Ingrese la posición del lugar a eliminar: ");
            try {
                pos = Integer.parseInt(entradaTeclado.nextLine().trim()) - 1;
                if (pos < 0 || pos >= universidad.getNumLugares()) {
                    System.out.println("Error: Posición inválida.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido.");
            }
        } while (true);

        Lugar lugar = universidad.getLugares()[pos];
        System.out.print("¿Está seguro de eliminar el lugar con ID " + lugar.getIdLugar() + "? (s/n): ");
        String confirmacion = entradaTeclado.nextLine().trim().toLowerCase();
        if (confirmacion.equals("s")) {
            return universidad.eliminarLugar(pos);
        }
        System.out.println("Eliminación cancelada.");
        return false;
    }
}