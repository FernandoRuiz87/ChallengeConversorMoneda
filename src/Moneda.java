import java.io.IOException;
import java.util.Map;

public record Moneda(
        String time_last_update_utc,
        String base_code,
        Map<String,Double> conversion_rates
) {
    public double convertirA(String codigoDestino, double cantidad) throws IOException {
        Double tasa = conversion_rates.get(codigoDestino);
        if (tasa == null) {
            throw new IllegalArgumentException("La moneda destino no es válida: " + codigoDestino);
        }
        String fecha = java.time.LocalDateTime.now().toString();
        Historial generador = new Historial();
        generador.guardarHistorial("[" + fecha + "] - " + "Conversion: " + cantidad + " " + base_code + " a "
                + codigoDestino + " a una tasa de "
                + tasa + " - total: "
                + (cantidad * tasa));
        return cantidad * tasa;
    }
}
