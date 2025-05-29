package ec.edu.uce.classfinder.consola;

import ec.edu.uce.classfinder.gui.GUIRegistrarLugar;
import ec.edu.uce.classfinder.gui.GUIEditarLugar;
import ec.edu.uce.classfinder.gui.GUIEliminarLugar;
import ec.edu.uce.classfinder.gui.GUIConsultarLugar;
import ec.edu.uce.classfinder.dominio.Lugar;
import ec.edu.uce.classfinder.dominio.Universidad;
import ec.edu.uce.classfinder.util.Validadores;
import java.util.Scanner;

/**
 * Representa el submenú para gestionar lugares en el sistema ClassFinder.
 * Permite realizar operaciones CRUD sobre los lugares a través de {@link Universidad}.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class SubMenuLugares {

    private Universidad universidad;

    /**
     * Constructor que recibe una instancia de {@link Universidad}.
     * @param universidad instancia de la universidad para gestionar los datos
     */
    public SubMenuLugares(Universidad universidad) {
        this.universidad = universidad;
    }

    /**
     * Muestra el submenú de gestión de lugares y maneja las opciones del usuario.
     */
    public void mostrar() {
        Scanner entradaTeclado = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("=== GESTIÓN DE LUGARES ===");
            System.out.println("1. Registrar Lugar");
            System.out.println("2. Consultar Lugar");
            System.out.println("3. Editar Lugar");
            System.out.println("4. Eliminar Lugar");
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
                    GUIRegistrarLugar guiRegistrar = new GUIRegistrarLugar();
                    Lugar nuevoLugar = guiRegistrar.registrar();
                    if (nuevoLugar != null && universidad.crearLugar(nuevoLugar)) {
                        System.out.println("Lugar registrado: " + nuevoLugar.getNombre());
                    } else {
                        System.out.println("Error al registrar lugar.");
                    }
                    break;
                case 2:
                    if (universidad.getNumLugares() == 0) {
                        System.out.println("No hay lugares registrados.");
                        continue;
                    }
                    GUIConsultarLugar guiConsultar = new GUIConsultarLugar(universidad);
                    String idConsulta = guiConsultar.consultar();
                    guiConsultar.mostrarLugar(idConsulta);
                    break;
                case 3:
                    if (universidad.getNumLugares() == 0) {
                        System.out.println("No hay lugares registrados.");
                        continue;
                    }
                    GUIEditarLugar guiEditar = new GUIEditarLugar();
                    Lugar lugarEditado = guiEditar.editar(universidad);
                    if (lugarEditado != null) {
                        int posEditar;
                        do {
                            System.out.print("Confirme la posición del lugar a editar");
                            entrada = entradaTeclado.nextLine();
                            if (Validadores.esNumeroValido(entrada)) {
                                posEditar = Integer.parseInt(entrada);
                                if (posEditar >= 0 && posEditar < universidad.getNumLugares()) {
                                    if (universidad.editarLugar(posEditar, lugarEditado)) {
                                        System.out.println("Lugar editado: " + lugarEditado.getNombre());
                                    } else {
                                        System.out.println("Error al editar lugar.");
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
                case 4:
                    if (universidad.getNumLugares() == 0) {
                        System.out.println("No hay lugares registrados.");
                        continue;
                    }
                    GUIEliminarLugar guiEliminar = new GUIEliminarLugar();
                    Lugar resultado = guiEliminar.eliminar(universidad);
                    if (resultado == null) {
                        int posEliminar;
                        do {
                            System.out.print("Confirme la posición del lugar a eliminar: ");
                            entrada = entradaTeclado.nextLine();
                            if (Validadores.esNumeroValido(entrada)) {
                                posEliminar = Integer.parseInt(entrada);
                                if (posEliminar >= 0 && posEliminar < universidad.getNumLugares()) {
                                    if (universidad.eliminarLugar(posEliminar)) {
                                        System.out.println("Lugar eliminado en posición " + posEliminar);
                                    } else {
                                        System.out.println("Error al eliminar lugar.");
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
                case 5:
                    System.out.println("Regresando al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }

        } while (opcion != 5);
    }
}