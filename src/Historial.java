import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Historial {
    public void guardarHistorial(String contenido) throws IOException {
        FileWriter fw = new FileWriter("historial.txt", true);
        fw.write(contenido + System.lineSeparator());
        fw.close();
    }
    public void consultarHistorial() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("Historial.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
