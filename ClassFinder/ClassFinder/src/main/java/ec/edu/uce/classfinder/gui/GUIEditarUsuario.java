package ec.edu.uce.classfinder.gui;

import ec.edu.uce.classfinder.dominio.Rol;
import ec.edu.uce.classfinder.dominio.Universidad;
import ec.edu.uce.classfinder.dominio.Usuario;
import ec.edu.uce.classfinder.util.Validadores;
import java.util.Scanner;

/**
 * Interfaz gráfica para editar un usuario en el sistema ClassFinder.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class GUIEditarUsuario {

    private Scanner entradaTeclado = new Scanner(System.in);

    /**
     * Permite editar los datos de un usuario.
     * @param universidad Instancia de Universidad para buscar usuarios
     * @return el usuario actualizado con los nuevos datos, o null si no se puede editar
     */
    public Usuario editar(Universidad universidad) {
        if (universidad == null || universidad.getNumUsuarios() == 0) {
            System.out.println("Error: No hay usuarios disponibles para editar.");
            return null;
        }

        System.out.println("\n=== EDITAR USUARIO ===");
        System.out.println("Lista de usuarios actuales:");
        System.out.println(universidad.consultarUsuarios());

        int pos;
        do {
            System.out.print("Ingrese la posición del usuario a editar ");
            if (entradaTeclado.hasNextInt()) {
                pos = entradaTeclado.nextInt();
                entradaTeclado.nextLine();
                if (pos < 0 || pos >= universidad.getNumUsuarios()) {
                    System.out.println("Error: Posición inválida.");
                } else {
                    break;
                }
            } else {
                System.out.println("Error: Debe ingresar un número válido.");
                entradaTeclado.nextLine();
            }
        } while (true);

        Usuario usuario = universidad.getUsuarios()[pos];
        System.out.println("Datos actuales: " + usuario.toString());

        // No editamos el ID
        System.out.println("ID del usuario: " + usuario.getIdUsuario() + " (no editable)");

        // Nombre
        do {
            System.out.print("Ingrese nuevo nombre del usuario (deje en blanco para no cambiar): ");
            String nombre = entradaTeclado.nextLine();
            if (!nombre.isEmpty()) {
                if (!Validadores.esTextoValido(nombre)) {
                    System.out.println("Error: El nombre debe contener solo letras y espacios, máximo 75 caracteres.");
                } else {
                    usuario.setNombre(nombre);
                    break;
                }
            } else {
                break;
            }
        } while (true);

        // Contraseña
        do {
            System.out.print("Ingrese nueva contraseña (mínimo 6 caracteres, deje en blanco para no cambiar): ");
            String contrasena = entradaTeclado.nextLine();
            if (!contrasena.isEmpty()) {
                if (!Validadores.esContrasenaValida(contrasena)) {
                    System.out.println("Error: La contraseña debe tener entre 6 y 20 caracteres alfanuméricos y algunos símbolos (.@-_!%+*).");
                } else {
                    usuario.setContrasena(contrasena);
                    break;
                }
            } else {
                break;
            }
        } while (true);

        // Cédula
        do {
            System.out.print("Ingrese nueva cédula de identidad (10 dígitos, deje en blanco para no cambiar): ");
            String cedulaIdentidad = entradaTeclado.nextLine();
            if (!cedulaIdentidad.isEmpty()) {
                if (!Validadores.esCedulaValida(cedulaIdentidad)) {
                    System.out.println("Error: La cédula debe ser un número de 10 dígitos.");
                } else {
                    usuario.setCedulaIdentidad(cedulaIdentidad);
                    break;
                }
            } else {
                break;
            }
        } while (true);

        // Correo
        do {
            System.out.print("Ingrese nuevo correo electrónico (deje en blanco para no cambiar): ");
            String correo = entradaTeclado.nextLine();
            if (!correo.isEmpty()) {
                if (!Validadores.esCorreoValido(correo)) {
                    System.out.println("Error: El correo debe ser válido (ej. usuario@gmail.com o usuario@hotmail.com).");
                } else {
                    usuario.setCorreo(correo);
                    break;
                }
            } else {
                break;
            }
        } while (true);

        // Rol (selección por número)
        int opcionRol;
        do {
            System.out.println("\nSeleccione el nuevo rol: ");
            System.out.println("1. Estudiante");
            System.out.println("2. Docente");
            System.out.println("3. Administrador");
            System.out.print("Ingrese el número de la opción: ");
            if (entradaTeclado.hasNextInt()) {
                opcionRol = entradaTeclado.nextInt();
                entradaTeclado.nextLine();
                if (opcionRol == 0) break;
                switch (opcionRol) {
                    case 1:
                        usuario.setRol(Rol.ESTUDIANTE);
                        break;
                    case 2:
                        usuario.setRol(Rol.DOCENTE);
                        break;
                    case 3:
                        usuario.setRol(Rol.ADMINISTRADOR);
                        break;
                    default:
                        System.out.println("Error: Opción inválida. Intente de nuevo.");
                        continue;
                }
                if (!Validadores.esRolValido(usuario.getRol())) {
                    System.out.println("Error: Rol inválido. Seleccione una opción válida.");
                    continue;
                }
                break;
            } else {
                System.out.println("Error: Debe ingresar un número válido.");
                entradaTeclado.nextLine();
            }
        } while (true);

        return usuario;
    }
}