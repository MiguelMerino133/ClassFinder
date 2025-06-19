package ec.edu.uce.classfinder.gui;

import ec.edu.uce.classfinder.dominio.Universidad;
import ec.edu.uce.classfinder.dominio.Usuario;
import ec.edu.uce.classfinder.util.Validadores;
import java.util.Scanner;

public class GUIValidarUsuario {
    private Scanner entradaTeclado = new Scanner(System.in);
    private Universidad universidad;

    public GUIValidarUsuario(Universidad universidad) {
        this.universidad = universidad;
    }

    public String[] validar() {
        System.out.println("\n=== VALIDAR CREDENCIALES ===");
        String[] credenciales = new String[2];

        while (true) {
            System.out.print("Ingrese ID del usuario (Ej: USR-001): ");
            credenciales[0] = entradaTeclado.nextLine();
            if (!Validadores.esIdValido(credenciales[0])) {
                System.out.println("ID inválido. Use formato  (USR-001).");
                continue;
            }

            Usuario usuario = universidad.buscarUsuario(credenciales[0]);
            if (usuario == null) {
                System.out.println("Usuario no registrado.");
                return null;
            }

            while (true) {
                System.out.print("Ingrese contraseña (Ej: Pass123!): ");
                credenciales[1] = entradaTeclado.nextLine();
                if (!Validadores.esContrasenaValida(credenciales[1])) {
                    System.out.println("Contraseña inválida. Use 6-20 caracteres alfanuméricos y símbolos .@-_!%+*.");
                    continue;
                }

                if (usuario.getContrasena().equals(credenciales[1])) {
                    System.out.println("Credenciales válidas. Procediendo...");
                    return credenciales;
                } else {
                    System.out.println("Contraseña incorrecta.");
                    return null;
                }
            }
        }
    }
}