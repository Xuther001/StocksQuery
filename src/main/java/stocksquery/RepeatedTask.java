package stocksquery;

import java.sql.SQLException;
import java.util.TimerTask;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RepeatedTask extends TimerTask {
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for (String e: StocksQueryApplication.watchList) {
		try {
			StocksQueryApplication.saveStockData(e);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		}
		System.out.println("Hourly update of watch list executed. Next hourly update in 60 mins.");
	}
}