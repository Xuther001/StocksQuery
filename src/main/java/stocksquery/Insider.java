package stocksquery;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Insider {
	
	private String name;
	private long share;
	private long change;
	private String filingDate;
	private String transactionDate;
	private String transactionCode;
	private long transactionPrice;
	
	public Insider() {};
	
	public String getName() {
		return this.name;
	}
	
	public long getShare() {
		return this.share;
	}
	
	public long getChange() {
		return this.change;
	}
	
	public String getFilingDate() {
		return this.filingDate;
	}
	
	public String getTransactionDate() {
		return this.transactionDate;
	}
	
	public String getTransactionCode() {
		return this.transactionCode;
	}
	
	public long getTransactionPrice() {
		return this.transactionPrice;
	}
	
	@Override
	public String toString() {
		return "Name: " + name +
				" Share: " + share +
				" Change: " + change +
				" Filing Date: " + filingDate +
				" Transaction Date: " + transactionDate +
				" Transaction Code: " + transactionCode +
				" Transaction Price: " + transactionPrice;
	}
}