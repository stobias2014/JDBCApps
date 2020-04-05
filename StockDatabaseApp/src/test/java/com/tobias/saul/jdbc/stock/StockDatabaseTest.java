package com.tobias.saul.jdbc.stock;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;

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
	
	@Test
	public void test_DeleteStockFromDatabase() {
		stock = sf.createStock();
		
		stockDao.add(stock);
		
		stockDao.delete(stock.getStockId());
		
		assertNull(stockDao.get(stock.getStockId()));
	}
	
	@Test
	public void test_GetAllStocksWithSameSymbol() {
		stock = sf.createStock("aaa", 3, new BigDecimal(4.5));
		Stock stock1 = sf.createStock("bbb", 5, new BigDecimal(3.3));
		Stock stock2 = sf.createStock("aaa", 4, new BigDecimal(2.3));
		
		stockDao.add(stock);
		stockDao.add(stock1);
		stockDao.add(stock2);
		
		ArrayList<Stock> stocks = stockDao.get("aaa");
		
		assertTrue(stocks.contains(stockDao.get(stock2.getStockId())));
		
	}
	
	@Test
	public void test_UpdateStockSymbol() {
		stock = sf.createStock("aa", 34, new BigDecimal(5.6));
		
		stockDao.add(stock);
		
		stock = stockDao.get(stock.getStockId());
		
		stockDao.update(stock.getStockId(), "aaa");
		
		assertEquals("aaa", stockDao.get(stock.getStockId()).getSymbol());
	}

}
