package ec.edu.uce.classfinder.gui;

import ec.edu.uce.classfinder.dominio.Rol;
import ec.edu.uce.classfinder.dominio.Universidad;
import ec.edu.uce.classfinder.dominio.Usuario;
import ec.edu.uce.classfinder.util.Validadores;
import java.util.Scanner;

/**
 * Interfaz gráfica consolidada para gestionar usuarios en el sistema ClassFinder.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class GUIUsuario {
    private Scanner entradaTeclado = new Scanner(System.in);
    private Universidad universidad;

    public GUIUsuario(Universidad universidad) {
        this.universidad = universidad;
    }

    public Usuario registrar() {
        System.out.println("\n=== REGISTRAR USUARIO ===");
        String idUsuario, nombre, contrasena, cedulaIdentidad, correo;
        Rol rol = null;

        do {
            System.out.print("Ingrese ID del usuario (formato USR-001): ");
            idUsuario = entradaTeclado.nextLine().trim();
            if (!Validadores.esIdValido(idUsuario)) {
                System.out.println("Error: El ID debe tener el formato USR-001.");
                continue;
            }
            if (universidad.buscarUsuario(idUsuario) != null) {
                System.out.println("Error: El ID " + idUsuario + " ya está registrado.");
                idUsuario = null;
            }
        } while (idUsuario == null || !Validadores.esIdValido(idUsuario));

        do {
            System.out.print("Ingrese nombre del usuario: ");
            nombre = entradaTeclado.nextLine().trim();
            if (!Validadores.esTextoValido(nombre)) {
                System.out.println("Error: El nombre debe contener solo letras y espacios, máximo 75 caracteres.");
            }
        } while (!Validadores.esTextoValido(nombre));

        do {
            System.out.print("Ingrese contraseña (mínimo 6 caracteres): ");
            contrasena = entradaTeclado.nextLine().trim();
            if (!Validadores.esContrasenaValida(contrasena)) {
                System.out.println("Error: La contraseña debe tener entre 6 y 20 caracteres alfanuméricos.");
            }
        } while (!Validadores.esContrasenaValida(contrasena));

        do {
            System.out.print("Ingrese cédula de identidad (10 dígitos): ");
            cedulaIdentidad = entradaTeclado.nextLine().trim();
            if (!Validadores.esCedulaValida(cedulaIdentidad)) {
                System.out.println("Error: La cédula debe ser un número de 10 dígitos.");
                continue;
            }
            if (universidad.buscarUsuarioPorCedula(cedulaIdentidad) != null) {
                System.out.println("Error: La cédula " + cedulaIdentidad + " ya está registrada.");
                cedulaIdentidad = null;
            }
        } while (cedulaIdentidad == null || !Validadores.esCedulaValida(cedulaIdentidad));

        do {
            System.out.print("Ingrese correo electrónico: ");
            correo = entradaTeclado.nextLine().trim();
            if (!Validadores.esCorreoValido(correo)) {
                System.out.println("Error: El correo debe ser válido (ej. usuario@gmail.com).");
            }
        } while (!Validadores.esCorreoValido(correo));

        do {
            System.out.println("Seleccione el rol:");
            System.out.println("1. Estudiante");
            System.out.println("2. Docente");
            System.out.println("3. Invitado");
            System.out.print("Ingrese el número de la opción: ");
            if (entradaTeclado.hasNextInt()) {
                int opcionRol = entradaTeclado.nextInt();
                entradaTeclado.nextLine();
                switch (opcionRol) {
                    case 1: rol = Rol.ESTUDIANTE; break;
                    case 2: rol = Rol.DOCENTE; break;
                    case 3: rol = Rol.INVITADO; break;
                    default: System.out.println("Error: Opción inválida.");
                }
            } else {
                System.out.println("Error: Debe ingresar un número válido.");
                entradaTeclado.nextLine();
            }
        } while (rol == null);

        return new Usuario(idUsuario, nombre, contrasena, cedulaIdentidad, correo, rol);
    }

    public void consultar() {
        System.out.println("\n=== CONSULTAR USUARIO ===");
        String idUsuario;
        do {
            System.out.print("Ingrese ID del usuario (formato USR-001): ");
            idUsuario = entradaTeclado.nextLine();
            if (!Validadores.esIdValido(idUsuario)) {
                System.out.println("Error: The ID must follow the format USR-001.");
            }
        } while (!Validadores.esIdValido(idUsuario));

        Usuario usuario = universidad.buscarUsuario(idUsuario);
        if (usuario != null) {
            System.out.println("Consulta de Usuario - ID: " + idUsuario);
            System.out.println("Nombre: " + usuario.getNombre());
            System.out.println("Cédula: " + usuario.getCedulaIdentidad());
            System.out.println("Correo: " + usuario.getCorreo());
            System.out.println("Rol: " + usuario.getRol().getDescripcion());
        } else {
            System.out.println("Usuario con ID " + idUsuario + " no encontrado.");
        }
    }

    public Usuario editar() {
        if (universidad.getNumUsuarios() == 0) {
            System.out.println("Error: No hay usuarios disponibles para editar.");
            return null;
        }

        System.out.println("\n=== EDITAR USUARIO ===");
        System.out.println("Lista de usuarios actuales:");
        System.out.println(universidad.consultarUsuarios());
        System.out.println("\r\n");
        int pos;
        do {
            System.out.print("Ingrese la posición del usuario a editar: ");
            if (entradaTeclado.hasNextInt()) {
                pos = entradaTeclado.nextInt() - 1;
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

        System.out.println("ID del usuario: " + usuario.getIdUsuario() + " (no editable)");
        System.out.println("Cédula: " + usuario.getCedulaIdentidad() + " (no editable)");

        do {
            System.out.print("Ingrese nuevo nombre (deje en blanco para no cambiar): ");
            String nombre = entradaTeclado.nextLine().trim();
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

        do {
            System.out.print("Ingrese nueva contraseña (deje en blanco para no cambiar): ");
            String contrasena = entradaTeclado.nextLine().trim();
            if (!contrasena.isEmpty()) {
                if (!Validadores.esContrasenaValida(contrasena)) {
                    System.out.println("Error: La contraseña debe tener entre 6 y 20 caracteres alfanuméricos.");
                } else {
                    usuario.setContrasena(contrasena);
                    break;
                }
            } else {
                break;
            }
        } while (true);

        do {
            System.out.print("Ingrese nuevo correo (deje en blanco para no cambiar): ");
            String correo = entradaTeclado.nextLine().trim();
            if (!correo.isEmpty()) {
                if (!Validadores.esCorreoValido(correo)) {
                    System.out.println("Error: El correo debe ser válido (ej. usuario@gmail.com).");
                } else {
                    usuario.setCorreo(correo);
                    break;
                }
            } else {
                break;
            }
        } while (true);

        System.out.println("Rol: "+usuario.getRol().getDescripcion()+" (no editable)");

        return usuario;
    }

    public boolean eliminar() {
        if (universidad.getNumUsuarios() == 0) {
            System.out.println("Error: No hay usuarios disponibles para eliminar.");
            return false;
        }

        System.out.println("\n=== ELIMINAR USUARIO ===");
        System.out.println("Lista de usuarios actuales:");
        System.out.println(universidad.consultarUsuarios());

        int pos;
        do {
            System.out.print("Ingrese la posición del usuario a eliminar: ");
            if (entradaTeclado.hasNextInt()) {
                pos = entradaTeclado.nextInt() - 1;
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
        System.out.print("¿Está seguro de eliminar el usuario con ID " + usuario.getIdUsuario() + "? (s/n): ");
        String confirmacion = entradaTeclado.nextLine().toLowerCase();
        if (confirmacion.equals("s")) {
            return universidad.eliminarUsuario(pos);
        }
        System.out.println("Eliminación cancelada.");
        return false;
    }
}