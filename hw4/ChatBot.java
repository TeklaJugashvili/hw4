import java.util.Scanner;

public class ChatBot {
    
    private final WeatherService weatherService;
    private final ExchangeRateService exchangeRateService;
    
    public ChatBot() {
        weatherService = new WeatherService();
        exchangeRateService = new ExchangeRateService();
    }

    public static void main(String[] args) {
        ChatBot bot = new ChatBot();
        bot.start();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the ChatBot!");

        while (true) {
            System.out.println("Please choose an option: 1. Weather 2. Currency Exchange 3. Exit");
            int option = scanner.nextInt();
            scanner.nextLine(); 

            if (option == 1) {
                System.out.println("Enter the city name:");
                String city = scanner.nextLine();
                String weather = weatherService.getWeather(city);
                System.out.println(weather);
            } else if (option == 2) {
                System.out.println("Enter the base currency (e.g., USD):");
                String baseCurrency = scanner.nextLine();
                System.out.println("Enter the target currency (e.g., EUR):");
                String targetCurrency = scanner.nextLine();
                String exchangeRate = exchangeRateService.getExchangeRate(baseCurrency, targetCurrency);
                System.out.println(exchangeRate);
            } else if (option == 3) {
                System.out.println("Goodbye!");
                break;
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}
