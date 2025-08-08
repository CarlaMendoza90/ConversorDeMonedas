import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {
        String API_KEY = System.getenv("API_KEY");
        String direccion = "https://v6.exchangerate-api.com/v6/"+API_KEY+"/pair/EUR/GBP";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccion))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.body());
    }
}
