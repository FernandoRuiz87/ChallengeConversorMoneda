import java.io.IOException;
import java.util.Scanner;

public class Conversor {
    private Scanner sc = new Scanner(System.in);
    public void consultarMoneda() {
        ConsultaAPI api = new ConsultaAPI();

        System.out.println("\nA continuación se mostrarán las monedas disponibles.\n");
        api.mostrarMonedasDisponibles();

        // Solicitar código de moneda base al usuario
        String codigo;
        do {
            System.out.print("\nIngresa el código de la moneda que deseas consultar: ");
            codigo = sc.nextLine().toUpperCase();

            if (!api.getCodigosValidos().contains(codigo)) {
                System.out.println("Código inválido. Intenta de nuevo.");
            }
        } while (!api.getCodigosValidos().contains(codigo));

        // Consultar tasas de conversión para esa moneda
        Moneda moneda = api.consultarMoneda(codigo);

        // Preguntar si quiere ver todas las conversiones o solo una específica
        System.out.print("\n¿Deseas ver todas las conversiones posibles (T) o convertir a una moneda específica (U)? [T/U]: ");
        String opcion = sc.nextLine().trim().toUpperCase();

        if (opcion.equals("T")) {
            mostrarTodasLasConversiones(moneda);
        } else {
            mostrarConversion(api, moneda);
        }
    }

    private void mostrarTodasLasConversiones(Moneda moneda) {
        System.out.printf("Conversiones disponibles para %s:\n", moneda.base_code());
        for (var entry : moneda.conversion_rates().entrySet()) {
            System.out.printf("\t%s: %.2f%n", entry.getKey(), entry.getValue());
        }
    }

    private void mostrarConversion(ConsultaAPI api, Moneda moneda){
        // Solicitar código de la moneda destino
        String destino;
        do {
            System.out.print("Ingresa el código de la moneda destino: ");
            destino = sc.nextLine().toUpperCase();

            if (!api.getCodigosValidos().contains(destino)) {
                System.out.println("Código inválido. Intenta de nuevo.");
            }
        } while (!api.getCodigosValidos().contains(destino));

        // Solicitar cantidad a convertir
        System.out.print("Ingresa la cantidad a convertir en " + moneda.base_code() + ": ");
        double cantidad;
        while (true) {
            try {
                cantidad = Double.parseDouble(sc.nextLine());
                if (cantidad < 0) {
                    System.out.println("La cantidad no puede ser negativa. Intenta de nuevo:");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingresa un número:");
            }
        }

        // Usar el metodo del record para convertir la cantidad
        try {
            double convertido = moneda.convertirA(destino, cantidad);
            System.out.printf("%.2f %s equivalen a %.2f %s%n", cantidad, moneda.base_code(), convertido, destino);
        } catch (IllegalArgumentException | IOException e) {
            System.out.println("Error en la conversión: " + e.getMessage());
        }
    }
}