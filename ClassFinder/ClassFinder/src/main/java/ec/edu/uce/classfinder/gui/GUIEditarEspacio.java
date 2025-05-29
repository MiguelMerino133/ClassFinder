package ec.edu.uce.classfinder.gui;

import ec.edu.uce.classfinder.dominio.Espacio;
import ec.edu.uce.classfinder.dominio.Lugar;
import ec.edu.uce.classfinder.dominio.Universidad;
import ec.edu.uce.classfinder.util.Validadores;
import java.util.Scanner;

/**
 * Interfaz gráfica para editar un espacio en el sistema ClassFinder.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class GUIEditarEspacio {

    private Scanner entradaTeclado = new Scanner(System.in);

    /**
     * Permite editar los datos de un espacio.
     * @param universidad Instancia de Universidad para verificar lugares
     * @param lugar Lugar donde se encuentra el espacio
     * @return el espacio actualizado con los nuevos datos, o null si no se puede editar
     */
    public Espacio editar(Universidad universidad, Lugar lugar) {
        if (lugar == null || lugar.getNumEspacios() == 0) {
            System.out.println("Error: No hay espacios disponibles para editar en este lugar.");
            return null;
        }

        System.out.println("\n=== EDITAR ESPACIO ===");
        System.out.println("Espacios en el lugar:");
        System.out.println(lugar.consultarEspacios());

        int pos;
        do {
            System.out.print("Ingrese la posición del espacio a editar ");
            if (entradaTeclado.hasNextInt()) {
                pos = entradaTeclado.nextInt();
                entradaTeclado.nextLine();
                if (pos < 0 || pos >= lugar.getNumEspacios()) {
                    System.out.println("Error: Posición inválida.");
                } else {
                    break;
                }
            } else {
                System.out.println("Error: Debe ingresar un número válido.");
                entradaTeclado.nextLine();
            }
        } while (true);

        Espacio espacio = lugar.getEspacios()[pos];
        System.out.println("Datos actuales: " + espacio.toString());

        // No editamos el ID
        System.out.println("ID del espacio: " + espacio.getIdEspacio() + " (no editable)");

        // Nombre
        do {
            System.out.print("Ingrese nuevo nombre del espacio (deje en blanco para no cambiar): ");
            String nombre = entradaTeclado.nextLine();
            if (!nombre.isEmpty()) {
                if (!Validadores.esTextoValido(nombre)) {
                    System.out.println("Error: El nombre debe contener solo letras y espacios, máximo 75 caracteres.");
                } else {
                    espacio.setNombre(nombre);
                    break;
                }
            } else {
                break;
            }
        } while (true);

        // Capacidad
        do {
            System.out.print("Ingrese nueva capacidad (mínimo 1, deje 0 para no cambiar): ");
            if (entradaTeclado.hasNextInt()) {
                int capacidad = entradaTeclado.nextInt();
                entradaTeclado.nextLine();
                if (capacidad == 0) break;
                if (!Validadores.esCapacidadValido(String.valueOf(capacidad))) {
                    System.out.println("Error: La capacidad debe ser un número positivo entre 1 y 999.");
                } else {
                    espacio.setCapacidad(capacidad);
                    break;
                }
            } else {
                System.out.println("Error: Debe ingresar un número válido.");
                entradaTeclado.nextLine();
            }
        } while (true);

        // Tamaño
        do {
            System.out.println("Seleccione el nuevo tamaño (deje 0 para no cambiar):");
            System.out.println("1. Pequeño");
            System.out.println("2. Mediano");
            System.out.println("3. Grande");
            System.out.print("Ingrese el número de la opción: ");
            if (entradaTeclado.hasNextInt()) {
                int opcionTamano = entradaTeclado.nextInt();
                entradaTeclado.nextLine();
                if (opcionTamano == 0) break;
                String tamano;
                switch (opcionTamano) {
                    case 1:
                        tamano = "pequeño";
                        break;
                    case 2:
                        tamano = "mediano";
                        break;
                    case 3:
                        tamano = "grande";
                        break;
                    default:
                        System.out.println("Error: Opción inválida. Intente de nuevo.");
                        continue;
                }
                if (!Validadores.esTamanoValido(tamano)) {
                    System.out.println("Error: El tamaño debe ser 'pequeño', 'mediano' o 'grande'.");
                    continue;
                }
                espacio.setTamano(tamano);
                break;
            } else {
                System.out.println("Error: Debe ingresar un número válido.");
                entradaTeclado.nextLine();
            }
        } while (true);

        return espacio;
    }
}