import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeRateService {

    private static final String EXCHANGE_RATE_API_KEY = "666515ec07221c21b27b97b8";
    private static final String EXCHANGE_RATE_API_URL = "https://v6.exchangerate-api.com/v6/%s/latest/%s";

    public String getExchangeRate(String baseCurrency, String targetCurrency) {
        String url = String.format(EXCHANGE_RATE_API_URL, EXCHANGE_RATE_API_KEY, baseCurrency);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
            double rate = jsonResponse.getAsJsonObject("conversion_rates").get(targetCurrency).getAsDouble();
            return String.format("The exchange rate from %s to %s is %.2f.", baseCurrency, targetCurrency, rate);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Error retrieving exchange rate information.";
        }
    }
}