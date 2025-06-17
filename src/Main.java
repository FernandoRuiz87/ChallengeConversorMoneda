import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        System.out.println("Bienvenido al conversor de monedas.");

        while (!salir) {
            System.out.println("\nMenú principal:");
            System.out.println("1. Consultar moneda");
            System.out.println("2. Ver historial");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");
            String seleccion = sc.nextLine();

            switch (seleccion) {
                case "1":
                    Conversor conversor = new Conversor();
                    conversor.consultarMoneda();
                    break;

                case "2":
                    System.out.println("\n-- Historial de conversiones --");
                    Historial historial = new Historial();
                    historial.consultarHistorial();
                    break;

                case "3":
                    salir = true;
                    System.out.println("¡Hasta luego!");
                    break;

                default:
                    System.out.println("Opción inválida. Elige una opción del 1 al 3.");
            }
        }
    }
}