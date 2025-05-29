package ec.edu.uce.classfinder.consola;

import ec.edu.uce.classfinder.gui.GUIRegistrarEspacio;
import ec.edu.uce.classfinder.gui.GUIConsultarEspacio;
import ec.edu.uce.classfinder.gui.GUIEditarEspacio;
import ec.edu.uce.classfinder.gui.GUIEliminarEspacio;
import ec.edu.uce.classfinder.dominio.Espacio;
import ec.edu.uce.classfinder.dominio.Lugar;
import ec.edu.uce.classfinder.dominio.Universidad;
import ec.edu.uce.classfinder.util.Validadores;
import java.util.Scanner;

/**
 * Representa el submenú para gestionar espacios en el sistema ClassFinder.
 * Permite realizar operaciones CRUD sobre los espacios a través de {@link Universidad}.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class SubMenuEspacios {

    private Universidad universidad;

    /**
     * Constructor que recibe una instancia de {@link Universidad}.
     * @param universidad instancia de la universidad para gestionar los datos
     */
    public SubMenuEspacios(Universidad universidad) {
        this.universidad = universidad;
    }

    /**
     * Muestra el submenú de gestión de espacios y maneja las opciones del usuario.
     */
    public void mostrar() {
        Scanner entradaTeclado = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("=== GESTIÓN ESPACIOS ===");
            System.out.println("1. Registrar Espacios");
            System.out.println("2. Consultar Espacio");
            System.out.println("3. Editar Espacio");
            System.out.println("4. Eliminar Espacio");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            String entrada = entradaTeclado.nextLine();
            if (Validadores.esNumeroValido(entrada)) {
                opcion = Integer.parseInt(entrada);
            } else {
                System.out.println("Error: Debe ingresar un número válido. Intente de nuevo.");
                opcion = 0;
                continue;
            }

            switch (opcion) {
                case 1:
                    GUIRegistrarEspacio guiRegistrar = new GUIRegistrarEspacio();
                    Espacio nuevoEspacio = guiRegistrar.registrar();
                    if (nuevoEspacio != null) {
                        System.out.println("Lista de lugares actuales:");
                        System.out.println(universidad.consultarLugares());
                        int posLugar;
                        do {
                            System.out.print("Ingrese la posición del lugar donde registrar el espacio: ");
                            entrada = entradaTeclado.nextLine();
                            if (Validadores.esNumeroValido(entrada)) {
                                posLugar = Integer.parseInt(entrada);
                                if (posLugar >= 0 && posLugar < universidad.getNumLugares()) {
                                    Lugar lugar = universidad.getLugares()[posLugar];
                                    if (lugar.crearEspacio(nuevoEspacio)) {
                                        System.out.println("Espacio registrado: " + nuevoEspacio.getNombre());
                                    } else {
                                        System.out.println("Error al registrar espacio.");
                                    }
                                    break;
                                } else {
                                    System.out.println("Error: Posición inválida.");
                                }
                            } else {
                                System.out.println("Error: Debe ingresar un número válido.");
                            }
                        } while (true);
                    }
                    break;
                case 2:
                    if (universidad.getNumLugares() == 0) {
                        System.out.println("No hay lugares registrados.");
                        continue;
                    }
                    GUIConsultarEspacio guiConsultar = new GUIConsultarEspacio(universidad);
                    String idConsulta = guiConsultar.consultar();
                    guiConsultar.mostrarEspacio(idConsulta);
                    break;
                case 3:
                    if (universidad.getNumLugares() == 0) {
                        System.out.println("No hay lugares registrados.");
                        continue;
                    }
                    GUIEditarEspacio guiEditar = new GUIEditarEspacio();
                    System.out.println("Lista de lugares actuales:");
                    System.out.println(universidad.consultarLugares());
                    int posLugarEditar;
                    do {
                        System.out.print("Ingrese la posición del lugar donde editar el espacio");
                        entrada = entradaTeclado.nextLine();
                        if (Validadores.esNumeroValido(entrada)) {
                            posLugarEditar = Integer.parseInt(entrada);
                            if (posLugarEditar >= 0 && posLugarEditar < universidad.getNumLugares()) {
                                Lugar lugar = universidad.getLugares()[posLugarEditar];
                                Espacio espacioEditado = guiEditar.editar(universidad, lugar);
                                if (espacioEditado != null) {
                                    int posEspacio;
                                    do {
                                        System.out.print("Confirme la posición del espacio a editar");
                                        entrada = entradaTeclado.nextLine();
                                        if (Validadores.esNumeroValido(entrada)) {
                                            posEspacio = Integer.parseInt(entrada);
                                            if (posEspacio >= 0 && posEspacio < lugar.getNumEspacios()) {
                                                if (lugar.editarEspacio(posEspacio, espacioEditado)) {
                                                    System.out.println("Espacio editado: " + espacioEditado.getNombre());
                                                } else {
                                                    System.out.println("Error al editar espacio.");
                                                }
                                                break;
                                            } else {
                                                System.out.println("Error: Posición inválida.");
                                            }
                                        } else {
                                            System.out.println("Error: Debe ingresar un número válido.");
                                        }
                                    } while (true);
                                }
                                break;
                            } else {
                                System.out.println("Error: Posición inválida.");
                            }
                        } else {
                            System.out.println("Error: Debe ingresar un número válido.");
                        }
                    } while (true);
                    break;
                case 4:
                    if (universidad.getNumLugares() == 0) {
                        System.out.println("No hay lugares registrados.");
                        continue;
                    }
                    GUIEliminarEspacio guiEliminar = new GUIEliminarEspacio();
                    System.out.println("Lista de lugares actuales:");
                    System.out.println(universidad.consultarLugares());
                    int posLugarEliminar;
                    do {
                        System.out.print("Ingrese la posición del lugar donde eliminar el espacio");
                        entrada = entradaTeclado.nextLine();
                        if (Validadores.esNumeroValido(entrada)) {
                            posLugarEliminar = Integer.parseInt(entrada);
                            if (posLugarEliminar >= 0 && posLugarEliminar < universidad.getNumLugares()) {
                                Lugar lugar = universidad.getLugares()[posLugarEliminar];
                                Espacio resultado = guiEliminar.eliminar(universidad, lugar);
                                if (resultado == null) {
                                    int posEspacioEliminar;
                                    do {
                                        System.out.print("Confirme la posición del espacio a eliminar");
                                        entrada = entradaTeclado.nextLine();
                                        if (Validadores.esNumeroValido(entrada)) {
                                            posEspacioEliminar = Integer.parseInt(entrada);
                                            if (posEspacioEliminar >= 0 && posEspacioEliminar < lugar.getNumEspacios()) {
                                                if (lugar.eliminarEspacio(posEspacioEliminar)) {
                                                    System.out.println("Espacio eliminado en posición " + posEspacioEliminar);
                                                } else {
                                                    System.out.println("Error al eliminar espacio.");
                                                }
                                                break;
                                            } else {
                                                System.out.println("Error: Posición inválida.");
                                            }
                                        } else {
                                            System.out.println("Error: Debe ingresar un número válido.");
                                        }
                                    } while (true);
                                }
                                break;
                            } else {
                                System.out.println("Error: Posición inválida.");
                            }
                        } else {
                            System.out.println("Error: Debe ingresar un número válido.");
                        }
                    } while (true);
                    break;
                case 5:
                    System.out.println("Regresando al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }

        } while (opcion != 5);
    }
}