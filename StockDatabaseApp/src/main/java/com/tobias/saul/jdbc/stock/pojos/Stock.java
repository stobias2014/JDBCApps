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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + quantity;
		result = prime * result + (int) (stockId ^ (stockId >>> 32));
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (quantity != other.quantity)
			return false;
		if (stockId != other.stockId)
			return false;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		return true;
	}
	
	

}
