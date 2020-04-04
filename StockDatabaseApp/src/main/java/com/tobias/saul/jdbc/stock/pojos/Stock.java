package com.tobias.saul.jdbc.stock.pojos;

import java.math.BigDecimal;

public class Stock {
	
	private long stockId;
	private String symbol;
	private int quantity;
	private BigDecimal price;

	public Stock(long stockId) {
		this.stockId = stockId;
	}

	public Stock(long stockId, String symbol, int quantity, BigDecimal price) {
		this.stockId = stockId;
		this.symbol = symbol;
		this.quantity = quantity;
		this.price = price;
	}

	public long getStockId() {
		return this.stockId;
	}
	
	public String getSymbol() {
		return this.symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
