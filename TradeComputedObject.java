package com.trade.sample;

public class TradeComputedObject {
	
	private String symbol;
	private String exchange;
	private String minimum;
	private String average;
	private String maximum;
	private int total;
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getExchange() {
		return exchange;
	}
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	public String getMinimum() {
		return minimum;
	}
	public void setMinimum(String minimum) {
		this.minimum = minimum;
	}
	public String getAverage() {
		return average;
	}
	public void setAverage(String average) {
		this.average = average;
	}
	public String getMaximum() {
		return maximum;
	}
	public void setMaximum(String maximum) {
		this.maximum = maximum;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "TradeComputedObject [symbol=" + symbol + ", exchange=" + exchange + ", minimum=" + minimum
				+ ", average=" + average + ", maximum=" + maximum + ", total=" + total + "]";
	}
}
