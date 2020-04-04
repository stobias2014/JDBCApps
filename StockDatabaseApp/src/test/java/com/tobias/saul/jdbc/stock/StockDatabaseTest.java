package com.tobias.saul.jdbc.stock;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.BeforeClass;
import org.junit.Test;

import com.tobias.saul.jdbc.stock.dao.StockDao;
import com.tobias.saul.jdbc.stock.factory.StockFactory;
import com.tobias.saul.jdbc.stock.pojos.Stock;

public class StockDatabaseTest {
	
	static StockFactory sf;
	static StockDao stockDao;
	Stock stock;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sf = new StockFactory();
		stockDao = new StockDao();
	}
	
	
	@Test
	public void test_WhenCreateStockCalled_CreateStockObjectWithZeroIDToStockFactory() {
		stock = sf.createStock();
		
		assertEquals(StockFactory.getStockId(), stock.getStockId());
	}
	
	@Test
	public void test_WhenCreateStockCalledTwice_CreateStockObjectWithMatchingIDToStockFactory() {
		stock = sf.createStock();
		stock = sf.createStock();
		
		assertEquals(StockFactory.getStockId(), stock.getStockId());
	}
	
	@Test
	public void test_WhenCreateStockCalled_CreateStockObjectWithMatchingFields() {
		String symbol = "aaa";
		int quantity = 0;
		BigDecimal price = new BigDecimal(0.00);
		
		stock = sf.createStock(symbol, quantity, price);
		
		assertEquals(StockFactory.getStockId(), stock.getStockId());
		assertEquals(symbol, stock.getSymbol());
		assertEquals(quantity, stock.getQuantity());
		assertEquals(price, stock.getPrice());
	}
	
	@Test
	public void test_AddStockToDatabase() {
		stock = sf.createStock();
		
		stockDao.add(stock);
		
		assertEquals(stockDao.get(stock.getStockId()), stock);
	}

}
