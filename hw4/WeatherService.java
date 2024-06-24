import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherService {

    private static final String WEATHER_API_KEY = "36f3c2a33b7cc714e7833f3d524a250c";
    private static final String WEATHER_API_URL = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric";

    public String getWeather(String city) {
        String url = String.format(WEATHER_API_URL, city, WEATHER_API_KEY);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
            String weatherDescription = jsonResponse.getAsJsonArray("weather").get(0).getAsJsonObject().get("description").getAsString();
            double temperature = jsonResponse.getAsJsonObject("main").get("temp").getAsDouble();
            return String.format("The weather in %s is currently %s with a temperature of %.1f°C.", city, weatherDescription, temperature);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Error retrieving weather information.";
        }
    }
}