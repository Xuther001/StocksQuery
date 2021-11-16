package stocksquery;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
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

	private static final Logger log = LoggerFactory.getLogger(StocksQueryApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(StocksQueryApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			Data data = restTemplate.getForObject(
					"https://finnhub.io/api/v1/stock/insider-transactions?symbol=CRM&token=c683tuqad3iagio36uj0", Data.class);
			log.info(data.toString());
			System.out.println(data);
			
			ResponseEntity<List<String>> peersResponse =
			        restTemplate.exchange("https://finnhub.io/api/v1/stock/peers?symbol=CRM&token=c683tuqad3iagio36uj0",
			                    HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {
			            });
			List<String> peers = peersResponse.getBody();
			System.out.println(peers);
		};
	}
}