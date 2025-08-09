import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Conversor {
    String API_KEY;
    HttpClient client;

    public Conversor() {
        this.API_KEY = System.getenv("API_KEY");
        this.client = HttpClient.newHttpClient();
    }

    public double convertir(Monedas monedaInicial, Monedas monedaFinal, double valor){
        RespuestaConversor respuesta = this.pedirValorDeConversion(monedaInicial, monedaFinal);
        if (respuesta == null) {
            return -1;
        }
        return valor * respuesta.conversion_rate();
    }

    private RespuestaConversor pedirValorDeConversion(Monedas monedaInicial, Monedas monedaFinal){
        String direccion = "https://v6.exchangerate-api.com/v6/"
                +API_KEY
                +"/pair/"+ monedaInicial.toString()
                +"/"+ monedaFinal.toString();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccion))
                .build();

        HttpResponse<String> response = null;
        try {
            response = this.client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new Gson().fromJson(response.body(), RespuestaConversor.class);
    }
}
