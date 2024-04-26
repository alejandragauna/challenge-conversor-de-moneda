package servicios;

import api.ApiResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiService {
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/865857da277f5fe11edb84ea/latest/USD";

    public static ApiResponse obtenerApiResponse() {
        String respuesta = obtenerRespuestaApi();
        return ApiResponse.fromJson(respuesta);
    }

    private static String obtenerRespuestaApi() {
        String respuesta = "";

        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            respuesta = response.body();
        } catch (IOException | InterruptedException e) {
            System.out.println("Ocurrió un error al obtener la respuesta de la API: " + e.getMessage());
        }

        return respuesta;
    }
}





