package ec.edu.uce.classfinder.consola;

import java.util.Scanner;

public class Menu {

    public void mostrarMenuPrincipal(){
        Scanner entradaTeclado = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("=== MENÚ PRINCIPAL ===");
            System.out.println("1. Ingresar al Sistema");
            System.out.println("2. Gestionar Usuarios");
            System.out.println("3. Gestionar Reservas");
            System.out.println("4. Gestionar Espacios");
            System.out.println("5. Gestionar Lugares");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = entradaTeclado.nextInt();

            switch (opcion) {
                case 1:
                    SubMenuIngresarSistema subMenuIngresar = new SubMenuIngresarSistema();
                    subMenuIngresar.mostrar();
                    break;
                case 2:
                    SubMenuUsuarios subMenuUsuarios = new SubMenuUsuarios();
                    subMenuUsuarios.mostrar();
                    break;
                case 3:
                    SubMenuReservas subMenuReservas = new SubMenuReservas();
                    subMenuReservas.mostrar();
                    break;
                case 4:
                    SubMenuEspacios subMenuEspacios = new SubMenuEspacios();
                    subMenuEspacios.mostrar();
                    break;
                case 5:
                    SubMenuLugares subMenuLugares = new SubMenuLugares();
                    subMenuLugares.mostrar();
                    break;
                case 6:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }

        } while (opcion != 6);


    }
}
