import java.util.Scanner;

public class Menu {

    public static void mostrarMenu() {
        System.out.println("\n=========================================");
        System.out.println("   GESTOR DE RESERVAS - MARTA PELUQUERIA");
        System.out.println("=========================================");
        System.out.println("1. Agendar una reserva");
        System.out.println("2. Listar reservas del día");
        System.out.println("3. Cancelar una reserva");
        System.out.println("4. Ver reporte del día");
        System.out.println("5. Salir");
        System.out.println("=========================================");
        System.out.print("Elige una opción: ");
    }

    public static int leerOpcion(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.print("⚠️  Ingresa un número válido: ");
            sc.next();
        }
        int opcion = sc.nextInt();
        sc.nextLine(); 
        return opcion;
    }
}
