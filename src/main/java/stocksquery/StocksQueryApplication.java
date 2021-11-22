package stocksquery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

	private static String userInput;
	
	public static void main(String[] args) throws SQLException {
		
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		SpringApplication.run(StocksQueryApplication.class, args);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		String dateTime = dtf.format(now) + "";
		
		while (!false) {
		System.out.println("Please enter a stock symbol or enter \"history\" to show previous searches");
		userInput = input.next().toUpperCase();
		RestTemplate template = new RestTemplate();;
		Data data = template.getForObject(
				"https://finnhub.io/api/v1/stock/insider-transactions?symbol=" + userInput + "&token=sandbox_c683tuqad3iagio36ujg", Data.class);
		Quote quote = template.getForObject(
				"https://finnhub.io/api/v1/quote?symbol=" + userInput + "&token=sandbox_c683tuqad3iagio36ujg", Quote.class);
		
		ResponseEntity<List<String>> peersResponse = template.exchange("https://finnhub.io/api/v1/stock/peers?symbol=" + userInput + "&token=sandbox_c683tuqad3iagio36ujg",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>() {
        });
		
		//connect using jdbc
		String showAllRecords = "SELECT * FROM searchRecord";
		String connectionUrl = "jdbc:mysql://localhost:3306/stockQuery?serverTimezone=UTC";
		String res = "";
		String addRecord = "INSERT INTO searchRecord VALUES(" + "'" + userInput + "'" + ", " + quote.getC() + ", " + "'" + dateTime + "'" + ");"; //for adding records

		Connection conn = DriverManager.getConnection(connectionUrl, "root", "legacy85"); 
		        PreparedStatement ps = conn.prepareStatement(showAllRecords); 
		        ResultSet rs = ps.executeQuery(); 

		        while (rs.next()) {
		            String symbolRecord = rs.getString("Symbol");
		            Double priceRecord = rs.getDouble("Price");
		            String dateTimeRecord = rs.getString("DateTime");
		            res += symbolRecord + " " + priceRecord + " " + dateTimeRecord;
		        }
		if (userInput.toUpperCase().equals("HISTORY")) {
			System.out.println("Previous searches: " + res);
			} else {
				List<String> peers = peersResponse.getBody();
				System.out.println("Similar Companies " + peers);
				System.out.println("List of notable trades " + data);
				System.out.println(quote.toString());
				Statement stmt = conn.createStatement(); //for adding records
				stmt.executeUpdate(addRecord); //for adding records
			}
		}
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}