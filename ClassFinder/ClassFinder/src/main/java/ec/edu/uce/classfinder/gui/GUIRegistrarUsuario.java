package ec.edu.uce.classfinder.gui;

import ec.edu.uce.classfinder.dominio.Rol;
import ec.edu.uce.classfinder.dominio.Usuario;
import ec.edu.uce.classfinder.util.Validadores;
import java.util.Scanner;

/**
 * Interfaz gráfica para registrar un usuario en el sistema ClassFinder.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class GUIRegistrarUsuario {

    private Scanner entradaTeclado = new Scanner(System.in);

    /**
     * Permite registrar un nuevo usuario.
     * @return el usuario creado con los datos ingresados
     */
    public Usuario registrar() {
        System.out.println("\n=== REGISTRAR USUARIO ===");
        Usuario usuario = new Usuario();
        String idUsuario, nombre, contrasena, cedulaIdentidad, correo, rol;

        do {
            System.out.print("Ingrese ID del usuario (formato USR-001): ");
            idUsuario = entradaTeclado.nextLine();
            if (!Validadores.esIdValido(idUsuario)) {
                System.out.println("Error: El ID debe tener el formato USR-001.");
            }
        } while (!Validadores.esIdValido(idUsuario));
        usuario.setIdUsuario(idUsuario);

        do {
            System.out.print("Ingrese nombre del usuario: ");
            nombre = entradaTeclado.nextLine();
            if (!Validadores.esTextoValido(nombre)) {
                System.out.println("Error: El nombre no puede estar vacío.");
            }
        } while (!Validadores.esTextoValido(nombre));
        usuario.setNombre(nombre);

        do {
            System.out.print("Ingrese contraseña (mínimo 6 caracteres): ");
            contrasena = entradaTeclado.nextLine();
            if (!Validadores.esContrasenaValida(contrasena)) {
                System.out.println("Error: La contraseña debe tener al menos 6 caracteres.");
            }
        } while (!Validadores.esContrasenaValida(contrasena));
        usuario.setContrasena(contrasena);

        do {
            System.out.print("Ingrese cédula de identidad (10 dígitos): ");
            cedulaIdentidad = entradaTeclado.nextLine();
            if (!Validadores.esCedulaValida(cedulaIdentidad)) {
                System.out.println("Error: La cédula debe ser un número de 10 dígitos.");
            }
        } while (!Validadores.esCedulaValida(cedulaIdentidad));
        usuario.setCedulaIdentidad(cedulaIdentidad);

        do {
            System.out.print("Ingrese correo electrónico: ");
            correo = entradaTeclado.nextLine();
            if (!Validadores.esCorreoValido(correo)) {
                System.out.println("Error: El correo debe ser válido (ej. usuario@dominio.com).");
            }
        } while (!Validadores.esCorreoValido(correo));
        usuario.setCorreo(correo);

        int opcionRol;
        do {
            System.out.println("\nSeleccione el rol:");
            System.out.println("1. Estudiante");
            System.out.println("2. Docente");
            System.out.println("3. Administrador");
            System.out.print("Ingrese el número de la opción: ");
            if (entradaTeclado.hasNextInt()) {
                opcionRol = entradaTeclado.nextInt();
                entradaTeclado.nextLine(); // Limpiar buffer
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
                break;
            } else {
                System.out.println("Error: Debe ingresar un número válido.");
                entradaTeclado.nextLine();
            }
        } while (true);

        return usuario;
    }
}
