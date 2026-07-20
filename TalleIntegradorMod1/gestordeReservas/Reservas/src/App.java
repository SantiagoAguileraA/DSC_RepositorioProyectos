import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            Menu.mostrarMenu();
            opcion = Menu.leerOpcion(sc);

            switch (opcion) {
                case 1:
                    Operaciones.agendar(sc);
                    break;
                case 2:
                    Operaciones.listar();
                    break;
                case 3:
                    Operaciones.cancelar(sc);
                    break;
                case 4:
                    Operaciones.reporte();
                    break;
                case 5:
                    System.out.println("\n¡Gracias por usar el Gestor de Reservas de Marta Peluquería! Hasta pronto.");
                    break;
                default:
                    System.out.println("\n⚠️  Opción inválida. Elige un número entre 1 y 5.");
            }

        } while (opcion != 5);

        sc.close();
    }
}