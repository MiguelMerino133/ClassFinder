package ec.edu.uce.classfinder.gui;

import ec.edu.uce.classfinder.dominio.Espacio;
import ec.edu.uce.classfinder.dominio.Lugar;
import ec.edu.uce.classfinder.dominio.Tamano;
import ec.edu.uce.classfinder.dominio.Universidad;
import ec.edu.uce.classfinder.util.Validadores;

import java.util.Scanner;

/**
 * Interfaz gráfica para gestionar espacios en el sistema ClassFinder.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class GUIEspacio {
    private final Scanner entradaTeclado = new Scanner(System.in);
    private final Universidad universidad;

    public GUIEspacio() {
        this.universidad = Universidad.getInstance();
    }

    public Espacio registrar(Lugar lugar) {
        System.out.println("\n=== CREAR ESPACIO ===");
        String nombre;
        int capacidad;
        Tamano tamano = null;

        do {
            System.out.print("Ingrese nombre del espacio: ");
            nombre = entradaTeclado.nextLine().trim();
            if (!Validadores.esTextoValido(nombre)) {
                System.out.println("Error: El nombre debe contener solo letras y espacios, máximo 75 caracteres.");
            }
        } while (!Validadores.esTextoValido(nombre));

        do {
            System.out.print("Ingrese capacidad (1-999): ");
            String capacidadStr = entradaTeclado.nextLine().trim();
            if (!Validadores.esCapacidadValido(capacidadStr)) {
                System.out.println("Error: La capacidad debe ser un número entre 1 y 999.");
            } else {
                capacidad = Integer.parseInt(capacidadStr);
                break;
            }
        } while (true);

        do {
            System.out.println("Seleccione el tamaño:");
            System.out.println("1. Pequeño");
            System.out.println("2. Mediano");
            System.out.println("3. Grande");
            System.out.print("Ingrese el número de la opción: ");
            if (entradaTeclado.hasNextInt()) {
                int opcionTamano = entradaTeclado.nextInt();
                entradaTeclado.nextLine();
                switch (opcionTamano) {
                    case 1: tamano = Tamano.PEQUENO; break;
                    case 2: tamano = Tamano.MEDIANO; break;
                    case 3: tamano = Tamano.GRANDE; break;
                    default: System.out.println("Error: Opción inválida.");
                }
            } else {
                System.out.println("Error: Debe ingresar un número válido.");
                entradaTeclado.nextLine();
            }
        } while (tamano == null);

        Espacio nuevoEspacio = new Espacio(nombre, capacidad, tamano);
        if (lugar.crearEspacio(nuevoEspacio)) {
            System.out.println("Espacio creado exitosamente con ID: " + nuevoEspacio.getIdEspacio());
            return nuevoEspacio;
        } else {
            System.out.println("Error: No se pudo crear el espacio, posible duplicado.");
            return null;
        }
    }

    public void consultar() {
        System.out.println("\n=== CONSULTAR ESPACIO ===");
        String idEspacio;
        do {
            System.out.print("Ingrese ID del espacio (formato ESP-XXX): ");
            idEspacio = entradaTeclado.nextLine().trim();
            if (!Validadores.esIdValido(idEspacio)) {
                System.out.println("Error: El ID debe seguir el formato ESP-XXX.");
            }
        } while (!Validadores.esIdValido(idEspacio));

        Espacio espacio = universidad.buscarEspacio(idEspacio);
        if (espacio != null) {
            System.out.println("Consulta de Espacio - ID: " + idEspacio);
            System.out.println("Nombre: " + espacio.getNombre());
            System.out.println("Capacidad: " + espacio.getCapacidad());
            System.out.println("Tamaño: " + espacio.getTamano().getDescripcion());
        } else {
            System.out.println("Error: Espacio con ID " + idEspacio + " no encontrado.");
        }
    }

    public Espacio editar(Lugar lugar) {
        if (lugar.getNumEspacios() == 0) {
            System.out.println("Error: No hay espacios disponibles para editar.");
            return null;
        }

        System.out.println("\n=== EDITAR ESPACIO ===");
        System.out.println("Lista de espacios actuales:");
        System.out.println(lugar.consultarEspacios());

        int pos;
        do {
            System.out.print("Ingrese la posición del espacio a editar: ");
            if (entradaTeclado.hasNextInt()) {
                pos = entradaTeclado.nextInt() - 1;
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

        System.out.println("ID del espacio: " + espacio.getIdEspacio() + " (no editable)");

        String nombre;
        do {
            System.out.print("Ingrese nuevo nombre (deje en blanco para no cambiar): ");
            nombre = entradaTeclado.nextLine().trim();
            if (!nombre.isEmpty()) {
                if (!Validadores.esTextoValido(nombre)) {
                    System.out.println("Error: El nombre debe contener solo letras y espacios, máximo 75 caracteres.");
                } else {
                    break;
                }
            } else {
                nombre = espacio.getNombre();
                break;
            }
        } while (true);

        int capacidad;
        do {
            System.out.print("Ingrese nueva capacidad (0 para no cambiar): ");
            String capacidadStr = entradaTeclado.nextLine().trim();
            if (capacidadStr.equals("0")) {
                capacidad = espacio.getCapacidad();
                break;
            }
            if (!Validadores.esCapacidadValido(capacidadStr)) {
                System.out.println("Error: La capacidad debe ser un número entre 1 y 999.");
            } else {
                capacidad = Integer.parseInt(capacidadStr);
                break;
            }
        } while (true);

        Tamano tamano = null;
        do {
            System.out.print("¿Desea cambiar el tamaño? (s/n): ");
            String cambiarTamano = entradaTeclado.nextLine().trim().toLowerCase();
            if (cambiarTamano.equals("s")) {
                System.out.println("Seleccione el nuevo tamaño:");
                System.out.println("1. Pequeño");
                System.out.println("2. Mediano");
                System.out.println("3. Grande");
                System.out.print("Ingrese el número de la opción: ");
                if (entradaTeclado.hasNextInt()) {
                    int opcionTamano = entradaTeclado.nextInt();
                    entradaTeclado.nextLine();
                    switch (opcionTamano) {
                        case 1: tamano = Tamano.PEQUENO; break;
                        case 2: tamano = Tamano.MEDIANO; break;
                        case 3: tamano = Tamano.GRANDE; break;
                        default: System.out.println("Error: Opción inválida.");
                    }
                } else {
                    System.out.println("Error: Debe ingresar un número válido.");
                    entradaTeclado.nextLine();
                }
            } else {
                tamano = espacio.getTamano();
                break;
            }
        } while (tamano == null);

        Espacio espacioEditado = new Espacio(nombre, capacidad, tamano);
        if (lugar.editarEspacio(pos, espacioEditado)){
            System.out.println("Espacio editado exitosamente.");
            return espacioEditado;
        } else {
            System.out.println("Error: No se pudo editar el espacio, posible duplicado.");
            return null;
        }
    }

    public boolean eliminarEspacio(Lugar lugar) {
        if (lugar.getNumEspacios() == 0) {
            System.out.println("Error: No hay espacios disponibles para eliminar.");
            return false;
        }

        System.out.println("\n=== ELIMINAR ESPACIO ===");
        System.out.println("Lista de espacios actuales:");
        System.out.println(lugar.consultarEspacios());

        int pos;
        do {
            System.out.print("Ingrese la posición del espacio a eliminar: ");
            if (entradaTeclado.hasNextInt()) {
                pos = entradaTeclado.nextInt() - 1;
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
        System.out.print("¿Está seguro de eliminar el espacio con ID " + espacio.getIdEspacio() + "? (s/n): ");
        String confirmacion = entradaTeclado.nextLine().trim().toLowerCase();
        if (confirmacion.equals("s")) {
            if (lugar.eliminarEspacio(pos)) {
                System.out.println("Espacio eliminado exitosamente.");
                return true;
            } else {
                System.out.println("Error: No se pudo eliminar el espacio.");
                return false;
            }
        }
        System.out.println("Eliminación cancelada.");
        return false;
    }
}