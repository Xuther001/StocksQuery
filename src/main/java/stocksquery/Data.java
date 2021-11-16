package stocksquery;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {

	private String symbol;
	private Insider[] data;

	public Data() {
	}

	public String getSymbol() {
		return this.symbol;
	}

	public Insider[] getData() {
		return this.data;
	}

	@Override
	public String toString() {
		return "Symbol: " + symbol +
				" Insider: " + Arrays.toString(data);
	}
}