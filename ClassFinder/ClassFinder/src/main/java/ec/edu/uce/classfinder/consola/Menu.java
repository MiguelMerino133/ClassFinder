package ec.edu.uce.classfinder.consola;

import ec.edu.uce.classfinder.dominio.Universidad;
import ec.edu.uce.classfinder.util.Validadores;
import java.text.ParseException;
import java.util.Scanner;

/**
 * Representa el menú principal del sistema ClassFinder.
 * Gestiona la navegación entre los submenús disponibles.
 * @author Grupo 7: Merino Miguel, Quinatoa Mishell
 */
public class Menu {

    private Universidad universidad;

    /**
     * Constructor por defecto.
     * Inicializa una instancia de {@link Universidad} para gestionar los datos.
     */
    public Menu() {
        this.universidad = new Universidad();
    }

    /**
     * Muestra el menú principal y maneja las opciones del usuario.
     * @throws ParseException si ocurre un error al parsear fechas
     */
    public void mostrarMenuPrincipal() throws ParseException {
        Scanner entradaTeclado = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("=== MENÚ PRINCIPAL ===");
            System.out.println("1. Ingresar al Sistema");
            System.out.println("2. Gestionar Usuarios");
            System.out.println("3. Gestionar Lugares");
            System.out.println("4. Gestionar Espacios");
            System.out.println("5. Gestionar Reservas");
            System.out.println("6. Salir");
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
                    SubMenuIngresarSistema subMenuIngresar = new SubMenuIngresarSistema(universidad);
                    subMenuIngresar.mostrar();
                    break;
                case 2:
                    SubMenuUsuarios subMenuUsuarios = new SubMenuUsuarios(entradaTeclado, universidad);
                    subMenuUsuarios.mostrarMenuUsuarios();
                    break;
                case 3:
                    SubMenuLugares subMenuLugares = new SubMenuLugares(entradaTeclado, universidad);
                    subMenuLugares.mostrarMenuLugares();
                    break;
                case 4:
                    SubMenuEspacios subMenuEspacios = new SubMenuEspacios(entradaTeclado, universidad);
                    subMenuEspacios.mostrarMenuEspacios();
                    break;
                case 5:
                    SubMenuReservas subMenuReservas = new SubMenuReservas(entradaTeclado, universidad);
                    subMenuReservas.mostrarMenuReservas();
                    break;
                case 6:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }

        } while (opcion != 6);
    }

    /**
     * Método principal para iniciar la aplicación.
     * @param args argumentos de la línea de comandos
     * @throws ParseException si ocurre un error al parsear fechas
     */
    public static void main(String[] args) throws ParseException {
        Menu menu = new Menu();
        menu.mostrarMenuPrincipal();
    }
}