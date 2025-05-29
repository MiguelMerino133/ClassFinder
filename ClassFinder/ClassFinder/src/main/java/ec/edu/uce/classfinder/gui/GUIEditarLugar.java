package ec.edu.uce.classfinder.gui;

import ec.edu.uce.classfinder.dominio.Lugar;
import ec.edu.uce.classfinder.dominio.Universidad;
import ec.edu.uce.classfinder.util.Validadores;
import java.util.Scanner;

/**
 * Interfaz gráfica para editar un lugar en el sistema ClassFinder.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class GUIEditarLugar {

    private Scanner entradaTeclado = new Scanner(System.in);

    /**
     * Permite editar los datos de un lugar.
     * @param universidad Instancia de Universidad para verificar lugares
     * @return el lugar actualizado con los nuevos datos, o null si no se puede editar
     */
    public Lugar editar(Universidad universidad) {
        if (universidad == null || universidad.getNumLugares() == 0) {
            System.out.println("Error: No hay lugares disponibles para editar.");
            return null;
        }

        System.out.println("\n=== EDITAR LUGAR ===");
        System.out.println("Lista de lugares actuales:");
        System.out.println(universidad.consultarLugares());

        int pos;
        do {
            System.out.print("Ingrese la posición del lugar a editar ");
            if (entradaTeclado.hasNextInt()) {
                pos = entradaTeclado.nextInt();
                entradaTeclado.nextLine();
                if (pos < 0 || pos >= universidad.getNumLugares()) {
                    System.out.println("Error: Posición inválida.");
                } else {
                    break;
                }
            } else {
                System.out.println("Error: Debe ingresar un número válido.");
                entradaTeclado.nextLine();
            }
        } while (true);

        Lugar lugar = universidad.getLugares()[pos];
        System.out.println("Datos actuales: " + lugar.toString());

        // No editamos el ID
        System.out.println("ID del lugar: " + lugar.getIdLugar() + " (no editable)");

        // Nombre
        do {
            System.out.print("Ingrese nuevo nombre del lugar (deje en blanco para no cambiar): ");
            String nombre = entradaTeclado.nextLine();
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

        // Descripción
        do {
            System.out.print("Ingrese nueva descripción del lugar (deje en blanco para no cambiar): ");
            String descripcion = entradaTeclado.nextLine();
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

        return lugar;
    }
}