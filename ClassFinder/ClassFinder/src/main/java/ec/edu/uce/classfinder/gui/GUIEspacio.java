package ec.edu.uce.classfinder.gui;

import ec.edu.uce.classfinder.dominio.Aula;
import ec.edu.uce.classfinder.dominio.Auditorio;
import ec.edu.uce.classfinder.dominio.Espacio;
import ec.edu.uce.classfinder.dominio.Lugar;
import ec.edu.uce.classfinder.dominio.Universidad;
import ec.edu.uce.classfinder.util.Validadores;
import java.util.Scanner;

/**
 * Interfaz gráfica consolidada para gestionar espacios en el sistema ClassFinder.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class GUIEspacio {
    private Scanner entradaTeclado = new Scanner(System.in);
    private Universidad universidad;

    public GUIEspacio(Universidad universidad) {
        this.universidad = universidad;
    }

    public Espacio registrar(Lugar lugar) {
        if (lugar == null) {
            System.out.println("Error: No se ha seleccionado un lugar válido.");
            return null;
        }

        System.out.println("\n=== REGISTRAR ESPACIO ===");
        String idEspacio, nombre, tamano, numeroAula;
        int capacidad;
        boolean equipoSonido;

        do {
            System.out.print("Ingrese ID del espacio (formato ESP-001): ");
            idEspacio = entradaTeclado.nextLine();
            if (!Validadores.esIdValido(idEspacio)) {
                System.out.println("Error: El ID debe tener el formato ESP-001.");
            }
        } while (!Validadores.esIdValido(idEspacio));

        do {
            System.out.print("Ingrese nombre del espacio: ");
            nombre = entradaTeclado.nextLine();
            if (!Validadores.esTextoValido(nombre)) {
                System.out.println("Error: El nombre debe contener solo letras y espacios, máximo 75 caracteres.");
            }
        } while (!Validadores.esTextoValido(nombre));

        do {
            System.out.print("Ingrese capacidad (mínimo 1): ");
            try {
                capacidad = Integer.parseInt(entradaTeclado.nextLine());
                if (!Validadores.esCapacidadValido(String.valueOf(capacidad))) {
                    System.out.println("Error: La capacidad debe ser un número entre 1 y 999.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido.");
            }
        } while (true);

        do {
            System.out.print("Ingrese tamaño (pequeño/mediano/grande): ");
            tamano = entradaTeclado.nextLine().toLowerCase();
            if (!Validadores.esTamanoValido(tamano)) {
                System.out.println("Error: El tamaño debe ser 'pequeño', 'mediano' o 'grande'.");
            }
        } while (!Validadores.esTamanoValido(tamano));

        System.out.println("Seleccione el tipo de espacio:");
        System.out.println("1. Aula");
        System.out.println("2. Auditorio");
        int opcion;
        do {
            System.out.print("Ingrese el número de la opción: ");
            try {
                opcion = Integer.parseInt(entradaTeclado.nextLine());
                if (opcion < 1 || opcion > 2) {
                    System.out.println("Error: Opción inválida.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido.");
            }
        } while (true);

        switch (opcion) {
            case 1:
                do {
                    System.out.print("Ingrese número de aula (formato AUL-001): ");
                    numeroAula = entradaTeclado.nextLine();
                    if (!Validadores.esNumeroAulaValido(numeroAula)) {
                        System.out.println("Error: El número de aula debe tener el formato AUL-001.");
                    }
                } while (!Validadores.esNumeroAulaValido(numeroAula));
                return new Aula(idEspacio, nombre, capacidad, tamano, numeroAula);
            case 2:
                do {
                    System.out.print("¿Tiene equipo de sonido? (s/n): ");
                    String respuesta = entradaTeclado.nextLine().toLowerCase();
                    if (respuesta.equals("s")) {
                        equipoSonido = true;
                        break;
                    } else if (respuesta.equals("n")) {
                        equipoSonido = false;
                        break;
                    } else {
                        System.out.println("Error: Responda con 's' o 'n'.");
                    }
                } while (true);
                return new Auditorio(idEspacio, nombre, capacidad, tamano, equipoSonido);
            default:
                return null;
        }
    }

    public void consultar() {
        System.out.println("\n=== CONSULTAR ESPACIO ===");
        String idEspacio;
        do {
            System.out.print("Ingrese ID del espacio (formato ESP-001): ");
            idEspacio = entradaTeclado.nextLine();
            if (!Validadores.esIdValido(idEspacio)) {
                System.out.println("Error: El ID debe tener el formato ESP-001.");
            }
        } while (!Validadores.esIdValido(idEspacio));

        Espacio espacio = universidad.buscarEspacio(idEspacio);
        if (espacio != null) {
            System.out.println("Consulta de Espacio - ID: " + idEspacio);
            System.out.println("Nombre: " + espacio.getNombre());
            System.out.println("Capacidad: " + espacio.getCapacidad());
            System.out.println("Tamaño: " + espacio.getTamano());
            if (espacio instanceof Aula) {
                System.out.println("Número de aula: " + ((Aula) espacio).getNumeroAula());
            } else if (espacio instanceof Auditorio) {
                System.out.println("Equipo de sonido: " + (((Auditorio) espacio).isEquipoSonido() ? "Sí" : "No"));
            }
        } else {
            System.out.println("Espacio con ID " + idEspacio + " no encontrado.");
        }
    }

    public Espacio editar(Lugar lugar) {
        if (lugar == null || lugar.getNumEspacios() == 0) {
            System.out.println("Error: No hay espacios disponibles para editar en este lugar.");
            return null;
        }

        System.out.println("\n=== EDITAR ESPACIO ===");
        System.out.println("Espacios en el lugar:");
        System.out.println(lugar.consultarEspacios());

        int pos;
        do {
            System.out.print("Ingrese la posición del espacio a editar: ");
            try {
                pos = Integer.parseInt(entradaTeclado.nextLine()) - 1;
                if (pos < 0 || pos >= lugar.getNumEspacios()) {
                    System.out.println("Error: Posición inválida.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido.");
            }
        } while (true);

        Espacio espacio = lugar.getEspacios()[pos];
        System.out.println("Datos actuales: " + espacio.toString());

        System.out.println("ID del espacio: " + espacio.getIdEspacio() + " (no editable)");

        do {
            System.out.print("Ingrese nuevo nombre (deje en blanco para no cambiar): ");
            String nombre = entradaTeclado.nextLine().trim();
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

        do {
            System.out.print("Ingrese nueva capacidad (0 para no cambiar): ");
            try {
                int capacidad = Integer.parseInt(entradaTeclado.nextLine().trim());
                if (capacidad == 0) break;
                if (!Validadores.esCapacidadValido(String.valueOf(capacidad))) {
                    System.out.println("Error: La capacidad debe ser un número entre 1 y 999.");
                } else {
                    espacio.setCapacidad(capacidad);
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido.");
            }
        } while (true);

        do {
            System.out.print("Ingrese nuevo tamaño (pequeño/mediano/grande, deje en blanco para no cambiar): ");
            String tamano = entradaTeclado.nextLine().trim().toLowerCase();
            if (!tamano.isEmpty()) {
                if (!Validadores.esTamanoValido(tamano)) {
                    System.out.println("Error: El tamaño debe ser 'pequeño', 'mediano' o 'grande'.");
                } else {
                    espacio.setTamano(tamano);
                    break;
                }
            } else {
                break;
            }
        } while (true);

        if (espacio instanceof Aula aula) {
            do {
                System.out.print("Ingrese nuevo número de aula (formato AUL-001, deje en blanco para no cambiar): ");
                String numeroAula = entradaTeclado.nextLine().trim();
                if (!numeroAula.isEmpty()) {
                    if (!Validadores.esNumeroAulaValido(numeroAula)) {
                        System.out.println("Error: El número de aula debe tener el formato AUL-001.");
                    } else {
                        aula.setNumeroAula(numeroAula);
                        break;
                    }
                } else {
                    break;
                }
            } while (true);
        } else if (espacio instanceof Auditorio auditorio) {
            do {
                System.out.print("¿Tiene equipo de sonido? (s/n, deje en blanco para no cambiar): ");
                String respuesta = entradaTeclado.nextLine().trim().toLowerCase();
                if (!respuesta.isEmpty()) {
                    if (respuesta.equals("s")) {
                        auditorio.setEquipoSonido(true);
                        break;
                    } else if (respuesta.equals("n")) {
                        auditorio.setEquipoSonido(false);
                        break;
                    } else {
                        System.out.println("Error: Responda con 's' o 'n'.");
                    }
                } else {
                    break;
                }
            } while (true);
        }

        return espacio;
    }

    public boolean eliminar(Lugar lugar) {
        if (lugar == null || lugar.getNumEspacios() == 0) {
            System.out.println("Error: No hay espacios disponibles para eliminar en este lugar.");
            return false;
        }

        System.out.println("\n=== ELIMINAR ESPACIO ===");
        System.out.println("Espacios en el lugar:");
        System.out.println(lugar.consultarEspacios());

        int pos;
        do {
            System.out.print("Ingrese la posición del espacio a eliminar: ");
            try {
                pos = Integer.parseInt(entradaTeclado.nextLine()) - 1;
                if (pos < 0 || pos >= lugar.getNumEspacios()) {
                    System.out.println("Error: Posición inválida.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un número válido.");
            }
        } while (true);

        Espacio espacio = lugar.getEspacios()[pos];
        System.out.print("¿Está seguro de eliminar el espacio con ID " + espacio.getIdEspacio() + "? (s/n): ");
        String confirmacion = entradaTeclado.nextLine().trim().toLowerCase();
        if (confirmacion.equals("s")) {
            return lugar.eliminarEspacio(pos);
        }
        System.out.println("Eliminación cancelada.");
        return false;
    }
}