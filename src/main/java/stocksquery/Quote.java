package stocksquery;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Quote {
	private double c;
	private double d;
	private double dp;
	private double h;
	private double l;
	private double o;
	private int pc;
	public Quote() {};
	
	public double getC() {
		return this.c;
	};
	
	public double getD() {
		return this.d;
	};
	
	public double getDp() {
		return this.dp;
	};
	
	public double getH() {
		return this.h;
	};
	
	public double getL() {
		return this.l;
	};
	
	public double getO() {
		return this.o;
	};
	
	public double getPc() {
		return this.pc;
	};
	
	public String toString() {
		return "Price: " + c + " Change: " + d + " Percent change: " + dp + "\n" +
				"Day High: " + h + " Day Low: " + l + "\n" +
				"Open price: " + o + " Privious closing price: " + pc;
	}
}
