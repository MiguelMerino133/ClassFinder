package ec.edu.uce.classfinder.consola;

import ec.edu.uce.classfinder.gui.GUIRegistrarUsuario;
import ec.edu.uce.classfinder.gui.GUIEditarUsuario;
import ec.edu.uce.classfinder.gui.GUIEliminarUsuario;
import ec.edu.uce.classfinder.gui.GUIConsultarUsuario;
import ec.edu.uce.classfinder.dominio.Universidad;
import ec.edu.uce.classfinder.dominio.Usuario;
import ec.edu.uce.classfinder.util.Validadores;
import java.util.Scanner;

/**
 * Representa el submenú para gestionar usuarios en el sistema ClassFinder.
 * Permite realizar operaciones CRUD sobre los usuarios a través de {@link Universidad}.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class SubMenuUsuarios {

    private Universidad universidad;

    public SubMenuUsuarios(Universidad universidad) {
        this.universidad = universidad;
    }

    public void mostrar() {
        Scanner entradaTeclado = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("=== GESTIÓN DE USUARIOS ===");
            System.out.println("1. Registrar Usuario");
            System.out.println("2. Consultar Usuario");
            System.out.println("3. Editar Usuario");
            System.out.println("4. Eliminar Usuario");
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
                    GUIRegistrarUsuario guiRegistrar = new GUIRegistrarUsuario();
                    Usuario nuevoUsuario = guiRegistrar.registrar();
                    if (nuevoUsuario != null && universidad.crearUsuario(nuevoUsuario)) {
                        System.out.println("Usuario registrado: " + nuevoUsuario.getNombre());
                    } else {
                        System.out.println("Error al registrar usuario.");
                    }
                    break;
                case 2:
                    if (universidad.getNumUsuarios() == 0) {
                        System.out.println("No hay usuarios registrados.");
                        continue;
                    }
                    GUIConsultarUsuario guiConsultar = new GUIConsultarUsuario(universidad);
                    String idConsulta = guiConsultar.consultar();
                    guiConsultar.mostrarUsuario(idConsulta);
                    break;
                case 3:
                    if (universidad.getNumUsuarios() == 0) {
                        System.out.println("No hay usuarios registrados.");
                        continue;
                    }
                    GUIEditarUsuario guiEditar = new GUIEditarUsuario();
                    Usuario usuarioEditado = guiEditar.editar(universidad);
                    if (usuarioEditado != null) {
                        int posEditar;
                        do {
                            System.out.print("Confirme la posición del usuario a editar ): ");
                            entrada = entradaTeclado.nextLine();
                            if (Validadores.esNumeroValido(entrada)) {
                                posEditar = Integer.parseInt(entrada);
                                if (posEditar >= 0 && posEditar < universidad.getNumUsuarios()) {
                                    if (universidad.editarUsuario(posEditar, usuarioEditado)) {
                                        System.out.println("Usuario editado: " + usuarioEditado.getNombre());
                                    } else {
                                        System.out.println("Error al editar usuario.");
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
                    if (universidad.getNumUsuarios() == 0) {
                        System.out.println("No hay usuarios registrados.");
                        continue;
                    }
                    GUIEliminarUsuario guiEliminar = new GUIEliminarUsuario();
                    Usuario resultado = guiEliminar.eliminar(universidad);
                    if (resultado == null) {
                        int posEliminar;
                        do {
                            System.out.print("Confirme la posición del usuario a eliminar");
                            entrada = entradaTeclado.nextLine();
                            if (Validadores.esNumeroValido(entrada)) {
                                posEliminar = Integer.parseInt(entrada);
                                if (posEliminar >= 0 && posEliminar < universidad.getNumUsuarios()) {
                                    if (universidad.eliminarUsuario(posEliminar)) {
                                        System.out.println("Usuario eliminado en posición " + posEliminar);
                                    } else {
                                        System.out.println("Error al eliminar usuario.");
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