package stocksquery;

import java.util.List;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class StocksQueryApplication {

	private static String stockSymbol;
	
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		SpringApplication.run(StocksQueryApplication.class, args);
		
		while (!false) {
		System.out.println("Please enter a stock symbol");
		stockSymbol = input.next();
		RestTemplate template = new RestTemplate();;
		Data data = template.getForObject(
				"https://finnhub.io/api/v1/stock/insider-transactions?symbol=" + stockSymbol + "&token=c683tuqad3iagio36uj0", Data.class);
		
		ResponseEntity<List<String>> peersResponse = template.exchange("https://finnhub.io/api/v1/stock/peers?symbol=CRM&token=c683tuqad3iagio36uj0",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {
        });
		
		List<String> peers = peersResponse.getBody();
		System.out.println(peers);
		System.out.println(data);
		}
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}