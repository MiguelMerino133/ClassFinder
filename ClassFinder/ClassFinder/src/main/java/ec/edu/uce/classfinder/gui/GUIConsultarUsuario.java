package ec.edu.uce.classfinder.gui;

import ec.edu.uce.classfinder.dominio.Universidad;
import ec.edu.uce.classfinder.dominio.Usuario;
import ec.edu.uce.classfinder.util.Validadores;
import java.util.Scanner;

/**
 * Interfaz gráfica para consultar un usuario en el sistema ClassFinder.
 * Utiliza {@link Universidad} para buscar los usuarios.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class GUIConsultarUsuario {

    private Scanner entradaTeclado = new Scanner(System.in);
    private Universidad universidad;

    /**
     * Constructor que recibe una instancia de {@link Universidad}.
     * @param universidad instancia de la universidad para gestionar los datos
     */
    public GUIConsultarUsuario(Universidad universidad) {
        this.universidad = universidad;
    }

    /**
     * Solicita el ID del usuario a consultar.
     * @return el ID del usuario ingresado
     */
    public String consultar() {
        System.out.println("\n=== CONSULTAR USUARIO ===");
        String idUsuario;

        do {
            System.out.print("Ingrese ID del usuario a consultar (formato USR-001): ");
            idUsuario = entradaTeclado.nextLine();
            if (!Validadores.esIdValido(idUsuario)) {
                System.out.println("Error: El ID debe tener el formato USR-001.");
            }
        } while (!Validadores.esIdValido(idUsuario));

        return idUsuario;
    }

    /**
     * Muestra los detalles de un usuario basado en su ID.
     * @param idUsuario ID del usuario a consultar
     */
    public void mostrarUsuario(String idUsuario) {
        boolean encontrado = false;
        for (int i = 0; i < universidad.getNumUsuarios(); i++) {
            Usuario usuario = universidad.getUsuarios()[i];
            if (usuario.getIdUsuario().equals(idUsuario)) {
                System.out.println("Consulta de Usuario - ID: " + idUsuario.toUpperCase());
                System.out.println("Nombre: " + usuario.getNombre());
                System.out.println("Cédula: " + usuario.getCedulaIdentidad());
                System.out.println("Correo: " + usuario.getCorreo());
                System.out.println("Rol: " + usuario.getRol());
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Usuario con ID " + idUsuario + " no encontrado.");
        }
    }
}