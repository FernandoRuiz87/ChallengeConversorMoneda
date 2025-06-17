import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashSet;
import java.util.Set;

public class ConsultaAPI {

    // Conjunto donde se almacenan los códigos de moneda válidos para futuras validaciones
    private Set<String> codigosValidos = new HashSet<>();

    // Metodo getter para acceder al conjunto de códigos válidos desde fuera de la clase
    public Set<String> getCodigosValidos() {
        return codigosValidos;
    }

    // Metodo para mostrar en consola todas las monedas disponibles obtenidas desde la API
    public void mostrarMonedasDisponibles(){
        URI url = URI.create("https://v6.exchangerate-api.com/v6/16d538f4e88a8b0980819f38/codes");

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();

            // Parsear el cuerpo de la respuesta como un objeto JSON
            JsonObject json = JsonParser.parseString(body).getAsJsonObject();

            // Extraer el array de códigos soportados
            JsonArray codigos = json.getAsJsonArray("supported_codes");

            System.out.println("*********************************************************************");
            System.out.println("Códigos de moneda disponibles:");

            // Recorrer cada entrada del array y mostrar código y nombre
            for (int i = 0; i < codigos.size(); i++) {
                JsonArray pair = codigos.get(i).getAsJsonArray(); // Cada par contiene código y nombre
                String codigo = pair.get(0).getAsString();
                String nombre = pair.get(1).getAsString();
                codigosValidos.add(codigo); // Guardar código para validaciones posteriores
                System.out.println("\t" + codigo + " - " + nombre);
            }
            System.out.println("*********************************************************************");

        } catch (Exception e) {
            throw new RuntimeException("Hubo un error al consultar la API: " + e.getMessage(), e);
        }
    }

    // Metodo para consultar las tasas de conversión de una moneda específica
    public Moneda consultarMoneda(String moneda){
        URI url = URI.create("https://v6.exchangerate-api.com/v6/16d538f4e88a8b0980819f38/latest/" + moneda);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Si la respuesta fue exitosa (código 200), parsear el JSON en un objeto `Moneda`
            if (response.statusCode() == 200) {
                return new Gson().fromJson(response.body(), Moneda.class);
            } else {
                // Si la API responde con otro código, lanzar error personalizado
                throw new RuntimeException("Error al consultar la moneda: " + response.statusCode());
            }

        } catch (Exception e) {
            // Captura de errores generales de red o parseo
            throw new RuntimeException("Hubo un error al consultar la moneda: " + e.getMessage(), e);
        }
    }
}
