import java.util.Scanner;

public class Operaciones {

    private static final int CUPO_MAXIMO = 10;

    private static String[] clientes = new String[CUPO_MAXIMO];
    private static int[] horas = new int[CUPO_MAXIMO];
    private static int[] servicios = new int[CUPO_MAXIMO];

    private static int contador = 0; 

    public static void agendar(Scanner sc) {
        System.out.println("\n--- AGENDAR RESERVA ---");

        if (contador >= CUPO_MAXIMO) {
            System.out.println("⚠️  La agenda de hoy está completa. No hay más cupos.");
            return;
        }

        System.out.print("Nombre del cliente: ");
        String nombre = sc.nextLine();
        if (!Validador.nombreValido(nombre)) {
            System.out.println("⚠️  El nombre no puede estar vacío. Reserva cancelada.");
            return;
        }

        System.out.print("Hora (8 a 17): ");
        if (!sc.hasNextInt()) {
            System.out.println("⚠️  Debes ingresar un número. Reserva cancelada.");
            sc.nextLine();
            return;
        }
        int hora = sc.nextInt();
        sc.nextLine();

        if (!Validador.horaValida(hora)) {
            System.out.println("⚠️  La hora debe estar entre 8 y 17. Reserva cancelada.");
            return;
        }

        if (Validador.horaOcupada(horas, contador, hora)) {
            System.out.println("⚠️  Esa hora ya está ocupada. Elige otra.");
            return;
        }

        System.out.println("Servicios disponibles:");
        System.out.println("  1. Corte de cabello - $25.000");
        System.out.println("  2. Tinte           - $60.000");
        System.out.println("  3. Manicure         - $30.000");
        System.out.print("Código del servicio: ");
        if (!sc.hasNextInt()) {
            System.out.println("⚠️  Debes ingresar un número. Reserva cancelada.");
            sc.nextLine();
            return;
        }
        int servicio = sc.nextInt();
        sc.nextLine();

        if (!Validador.servicioValido(servicio)) {
            System.out.println("⚠️  Servicio inválido. Reserva cancelada.");
            return;
        }

        clientes[contador] = nombre.trim();
        horas[contador] = hora;
        servicios[contador] = servicio;
        contador++;

        System.out.println("✅ Reserva agendada con éxito para " + nombre.trim() + " a las " + hora + ":00.");
    }

    public static void listar() {
        System.out.println("\n--- RESERVAS DEL DÍA ---");

        if (contador == 0) {
            System.out.println("Aún no hay reservas.");
            return;
        }

        for (int i = 0; i < contador; i++) {
            System.out.println((i + 1) + ". " + clientes[i]
                    + " | Hora: " + horas[i] + ":00"
                    + " | Servicio: " + nombreServicio(servicios[i]));
        }
    }

    public static void cancelar(Scanner sc) {
        System.out.println("\n--- CANCELAR RESERVA ---");

        if (contador == 0) {
            System.out.println("No hay reservas para cancelar.");
            return;
        }

        listar();
        System.out.print("Número de reserva a cancelar: ");
        if (!sc.hasNextInt()) {
            System.out.println("⚠️  Debes ingresar un número.");
            sc.nextLine();
            return;
        }
        int numero = sc.nextInt();
        sc.nextLine();

        if (numero < 1 || numero > contador) {
            System.out.println("⚠️  Ese número de reserva no existe.");
            return;
        }

        int indice = numero - 1;

        for (int i = indice; i < contador - 1; i++) {
            clientes[i] = clientes[i + 1];
            horas[i] = horas[i + 1];
            servicios[i] = servicios[i + 1];
        }

        contador--;

        System.out.println("✅ Reserva #" + numero + " cancelada con éxito.");
    }

    public static void reporte() {
        System.out.println("\n--- REPORTE DEL DÍA ---");

        int totalCitas = contador;
        double totalFacturado = 0;

        for (int i = 0; i < contador; i++) {
            totalFacturado += precioServicio(servicios[i]);
        }

        System.out.println("Total de citas agendadas: " + totalCitas);
        System.out.printf("Total facturado: $%,.0f%n", totalFacturado);
    }

    // Traduce el código de servicio a su nombre legible
    private static String nombreServicio(int codigo) {
        switch (codigo) {
            case 1: return "Corte de cabello";
            case 2: return "Tinte";
            case 3: return "Manicure";
            default: return "Desconocido";
        }
    }

    private static double precioServicio(int codigo) {
        switch (codigo) {
            case 1: return 25000;
            case 2: return 60000;
            case 3: return 30000;
            default: return 0;
        }
    }
}