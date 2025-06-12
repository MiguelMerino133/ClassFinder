package ec.edu.uce.classfinder.gui;

import ec.edu.uce.classfinder.dominio.Universidad;
import ec.edu.uce.classfinder.dominio.Usuario;
import java.util.Scanner;

/**
 * Interfaz gráfica para validar las credenciales de un usuario en el sistema ClassFinder.
 * Utiliza {@link Universidad} para verificar los usuarios registrados.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class GUIValidarUsuario {

    private Scanner entradaTeclado = new Scanner(System.in);
    private Universidad universidad;

    /**
     * Constructor que recibe una instancia de {@link Universidad}.
     * @param universidad instancia de la universidad para gestionar los datos
     */
    public GUIValidarUsuario(Universidad universidad) {
        this.universidad = universidad;
    }

    /**
     * Solicita y valida las credenciales del usuario.
     * @return un arreglo con [ID, contraseña] si las credenciales son correctas
     */
    public String[] validar() {
        System.out.println("\n=== VALIDAR CREDENCIALES ===");
        String[] credenciales = new String[2];

        boolean idValido = false;
        boolean contrasenaValida = false;
        boolean credencialesCorrectas = false;

        while (!credencialesCorrectas) {
            while (!idValido) {
                System.out.print("Ingrese ID del usuario (formato USR-001): ");
                credenciales[0] = entradaTeclado.nextLine();
                if (credenciales[0].isEmpty()) {
                    System.out.println("Error: El ID no puede estar vacío.");
                } else if (!credenciales[0].matches("USR-\\d{3}")) {
                    System.out.println("Error: El ID debe seguir el formato USR-001.");
                } else {
                    idValido = true;
                }
            }

            while (!contrasenaValida) {
                System.out.print("Ingrese contraseña (mín. 6 caracteres): ");
                credenciales[1] = entradaTeclado.nextLine();
                if (credenciales[1].isEmpty()) {
                    System.out.println("Error: La contraseña no puede estar vacía.");
                } else if (credenciales[1].length() < 6) {
                    System.out.println("Error: La contraseña debe tener al menos 6 caracteres.");
                } else {
                    contrasenaValida = true;
                }
            }

            boolean encontrado = false;
            for (int i = 0; i < universidad.getNumUsuarios(); i++) {
                Usuario usuario = universidad.getUsuarios()[i];
                if (usuario.getIdUsuario().equals(credenciales[0]) && usuario.getContrasena().equals(credenciales[1])) {
                    encontrado = true;
                    credencialesCorrectas = true;
                    break;
                }
            }

            if (!encontrado) {
                System.out.println("Error: Credenciales incorrectas. Intente de nuevo.");
                idValido = false;
                contrasenaValida = false;
            }
        }

        System.out.println("Credenciales válidas. Procediendo...");
        return credenciales;
    }
}