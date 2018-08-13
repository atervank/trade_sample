package com.trade.sample;

public class TradeObj {
	
	private String symbol;
	private String exchange;
	private float price;
	private int quantity;
	
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
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "TradeObj [symbol=" + symbol + ", exchange=" + exchange + ", price=" + price + ", quantity=" + quantity
				+ "]";
	}
}
