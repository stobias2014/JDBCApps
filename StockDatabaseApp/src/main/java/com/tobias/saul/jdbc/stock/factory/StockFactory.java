package com.tobias.saul.jdbc.stock.factory;

import java.math.BigDecimal;

import com.tobias.saul.jdbc.stock.pojos.Stock;

public class StockFactory {
	
	private static long stockId = 0;
	
	public static long getStockId() {
		return stockId;
	}
	
	public Stock createStock() {
		stockId++;
		Stock stock = new Stock(stockId);
		
		return stock;
	}

	public Stock createStock(String symbol, int quantity, BigDecimal price) {
		stockId++;
		Stock stock = new Stock(stockId, symbol, quantity, price);
		return stock;
	}

}
