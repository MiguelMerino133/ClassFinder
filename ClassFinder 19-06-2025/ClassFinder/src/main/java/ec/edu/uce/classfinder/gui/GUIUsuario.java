
package ec.edu.uce.classfinder.gui;

import ec.edu.uce.classfinder.dominio.Rol;
import ec.edu.uce.classfinder.dominio.Universidad;
import ec.edu.uce.classfinder.dominio.Usuario;
import ec.edu.uce.classfinder.util.Validadores;
import java.util.Scanner;

public class GUIUsuario {
    private Scanner entradaTeclado = new Scanner(System.in);
    private Universidad universidad;

    public GUIUsuario() {
        this.universidad = Universidad.getInstance();
    }

    public Usuario registrar() {
        System.out.println("REGISTRAR USUARIO");
        String nombre;
        while (true) {
            System.out.print("Ingrese su nombre (Ej: Juan Lopez): ");
            nombre = entradaTeclado.nextLine();
            if (Validadores.esTextoValido(nombre)) break;
            System.out.println("Nombre inválido. Use solo letras y espacios, máx. 75 caracteres.");
        }

        String contrasena;
        while (true) {
            System.out.print("Ingrese su contraseña (Ej: Pass123!): ");
            contrasena = entradaTeclado.nextLine();
            if (Validadores.esContrasenaValida(contrasena)) break;
            System.out.println("Contraseña inválida. Use 6-20 caracteres alfanuméricos y símbolos .@-_!%+*.");
        }

        String correo;
        while (true) {
            System.out.print("Ingrese su correo (Ej: juan@gmail.com): ");
            correo = entradaTeclado.nextLine();
            if (Validadores.esCorreoValido(correo)) break;
            System.out.println("Correo inválido. Use dominios @gmail.com o @hotmail.com.");
        }

        String cedula;
        while (true) {
            System.out.print("Ingrese su cédula (Ej: 1728803246): ");
            cedula = entradaTeclado.nextLine();
            if (Validadores.esCedulaValida(cedula)) {
                if (universidad.buscarUsuarioPorCedula(cedula) == null) break;
                System.out.println("Cédula ya registrada.");
            } else {
                System.out.println("Cédula inválida. Debe tener 10 dígitos.");
            }
        }

        Rol rol;
        while (true) {
            System.out.println("Seleccione el rol:");
            for (Rol r : Rol.values()) {
                System.out.println(r.ordinal() + 1 + ". " + r);
            }
            System.out.print("Opción (Ej: 1): ");
            String opcionRol = entradaTeclado.nextLine();
            try {
                int opcionNum = Integer.parseInt(opcionRol);
                if (opcionNum >= 1 && opcionNum <= Rol.values().length) {
                    rol = Rol.values()[opcionNum - 1];
                    break;
                }
            } catch (NumberFormatException e) {
            }
            System.out.println("Rol inválido. Ingrese un número entre 1 y " + Rol.values().length + ".");
        }

        Usuario nuevoUsuario = new Usuario(nombre, contrasena, cedula, correo, rol);
        System.out.println(universidad.getUsuarioCRUD().nuevo(nuevoUsuario));
        return nuevoUsuario;
    }

    public void consultar() {
        while (true) {
            System.out.println("CONSULTAR USUARIO");
            System.out.print("Ingrese el ID del usuario (Ej: USR-001): ");
            String idUsuario = entradaTeclado.nextLine();
            if (Validadores.esIdValido(idUsuario)) {
                Object usuario = universidad.getUsuarioCRUD().buscarPorId(parseId(idUsuario));
                System.out.println(usuario != null ? usuario.toString() : "Usuario con ID " + idUsuario + " no encontrado.");
                return;
            }
            System.out.println("ID inválido. Use formato (USR-001).");
        }
    }

    public void consultarTodos() {
        System.out.println("LISTA DE USUARIOS");
        System.out.println(universidad.getUsuarioCRUD().listar());
    }

    public Usuario editar() {
        while (true) {
            System.out.println("EDITAR USUARIO");
            System.out.print("Ingrese el ID del usuario a editar (Ej: USR-001): ");
            String idUsuario = entradaTeclado.nextLine();
            if (!Validadores.esIdValido(idUsuario)) {
                System.out.println("ID inválido. Use formato (USR-001).");
                continue;
            }
            Object usuarioObj = universidad.getUsuarioCRUD().buscarPorId(parseId(idUsuario));
            if (!(usuarioObj instanceof Usuario)) {
                System.out.println("Usuario con ID " + idUsuario + " no encontrado.");
                return null;
            }
            Usuario usuario = (Usuario) usuarioObj;
            System.out.println("Usuario encontrado: " + usuario.toString());
            System.out.println("Deje en blanco si no desea modificar un campo.");

            System.out.print("Nuevo nombre [" + usuario.getNombre() + "] (Ej: Juan Lopez): ");
            String nombre = entradaTeclado.nextLine();
            String nuevoNombre = nombre.isBlank() ? usuario.getNombre() : nombre;
            if (!nombre.isBlank() && !Validadores.esTextoValido(nombre)) {
                System.out.println("Nombre inválido, se mantiene el actual.");
                nuevoNombre = usuario.getNombre();
            }

            System.out.print("Nueva contraseña [" + usuario.getContrasena() + "] (Ej: Pass123!): ");
            String contrasena = entradaTeclado.nextLine();
            String nuevaContrasena = contrasena.isBlank() ? usuario.getContrasena() : contrasena;
            if (!contrasena.isBlank() && !Validadores.esContrasenaValida(contrasena)) {
                System.out.println("Contraseña inválida, se mantiene el actual.");
                nuevaContrasena = usuario.getContrasena();
            }

            System.out.print("Nuevo correo [" + usuario.getCorreo() + "] (Ej: juan@gmail.com): ");
            String correo = entradaTeclado.nextLine();
            String nuevoCorreo = correo.isBlank() ? usuario.getCorreo() : correo;
            if (!correo.isBlank() && !Validadores.esCorreoValido(correo)) {
                System.out.println("Correo inválido, se mantiene el actual.");
                nuevoCorreo = usuario.getCorreo();
            }

            String nuevaCedula = usuario.getCedulaIdentidad();

            Rol nuevoRol = usuario.getRol();
            System.out.println("Seleccione el nuevo rol (o presione Enter para mantener [" + usuario.getRol() + "]):");
            for (Rol r : Rol.values()) {
                System.out.println(r.ordinal() + 1 + ". " + r);
            }
            System.out.print("Opción (Ej: 1): ");
            String opcionRol = entradaTeclado.nextLine();
            if (!opcionRol.isBlank()) {
                try {
                    int opcionNum = Integer.parseInt(opcionRol);
                    if (opcionNum >= 1 && opcionNum <= Rol.values().length) {
                        nuevoRol = Rol.values()[opcionNum - 1];
                    } else {
                        System.out.println("Rol inválido, se mantiene el actual.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Rol inválido, se mantiene el actual.");
                }
            }

            // Actualizar el objeto existente
            usuario.setNombre(nuevoNombre);
            usuario.setContrasena(nuevaContrasena);
            usuario.setCorreo(nuevoCorreo);
            usuario.setRol(nuevoRol);

            String resultado = universidad.getUsuarioCRUD().editar(usuario);
            System.out.println(resultado);
            if (resultado.contains("exitosamente")) {
                return usuario;
            } else {
                System.out.println("No se pudo actualizar el usuario.");
                return null;
            }
        }
    }

    public boolean eliminar() {
        while (true) {
            System.out.println("ELIMINAR USUARIO");
            System.out.print("Ingrese el ID del usuario a eliminar (Ej: USR-001): ");
            String idUsuario = entradaTeclado.nextLine();
            if (Validadores.esIdValido(idUsuario)) {
                Object usuarioObj = universidad.getUsuarioCRUD().buscarPorId(parseId(idUsuario));
                if (!(usuarioObj instanceof Usuario)) {
                    System.out.println("Usuario con ID " + idUsuario + " no encontrado.");
                    return false;
                }
                System.out.println(universidad.getUsuarioCRUD().borrar(usuarioObj));
                return true;
            }
            System.out.println("ID inválido. Use formato (USR-001).");
        }
    }

    private Integer parseId(String id) {
        try {
            return Integer.parseInt(id.replace("USR-", ""));
        } catch (NumberFormatException e) {
            return null;
        }
    }
}