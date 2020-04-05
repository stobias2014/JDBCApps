package com.tobias.saul.jdbc.stock.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tobias.saul.jdbc.stock.pojos.Stock;

public class StockDao {
	
	private static final String DB_URL = "jdbc:mysql://localhost:3306/local?serverTimezone=UTC";
	//private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	
	private static final String USER = "root";
	private static final String PASSWORD = "password";
	
	private static Connection conn = null;
	
	
	public StockDao() {
		try {
		DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
		
		} catch(SQLException se) {
			se.printStackTrace();
		}
	}
	

	public void add(Stock stock) {
		PreparedStatement ps = null;
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			
			String sql = "INSERT INTO stock (stockId, symbol, quantity, price) VALUES (?, ?, ?, ?)";
			ps = conn.prepareStatement(sql);
			
			ps.setLong(1, stock.getStockId());
			ps.setString(2, stock.getSymbol());
			ps.setInt(3, stock.getQuantity());
			ps.setBigDecimal(4, stock.getPrice());
			
			ps.executeUpdate();
			
			ps.close();
			conn.close();
			
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		
	}

	public Stock get(long stockId) {
		PreparedStatement ps = null;
		Stock stock = null;
		
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			
			String sql = "SELECT stockId, symbol, quantity, price FROM stock WHERE stockId=?";
			ps = conn.prepareStatement(sql);
			
			ps.setLong(1, stockId);
			
			ResultSet rs = ps.executeQuery();
			
			rs.first();
			
			long id = rs.getLong("stockId");
			String symbol = rs.getString("symbol");
			int quantity = rs.getInt("quantity");
			BigDecimal price = rs.getBigDecimal("price");
			
			stock = new Stock(id, symbol, quantity, price);
			
			rs.close();
			ps.close();
			conn.close();
			
			
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			} 
			
			try {
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		
		return stock;
	}


	public void delete(long stockId) {
		PreparedStatement ps = null;
		
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			
			String sql = "DELETE FROM stock WHERE stockId = ?";
			ps = conn.prepareStatement(sql);
			
			ps.setLong(1, stockId);
			
			ps.execute();
			
			ps.close();
			conn.close();
			
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		
	}


	public ArrayList<Stock> get(String symbol) {
		ArrayList<Stock> stocks = null;
		Stock stock = null;
		PreparedStatement ps = null;
		
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			stocks = new ArrayList<Stock>();
			stock = new Stock();
			
			String sql = "SELECT stockId, symbol, quantity, price FROM stock WHERE symbol = ?";
			
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, symbol);
			
			ResultSet rs = ps.executeQuery();
			
			
			while(rs.next()) {
				long id = rs.getLong("stockId");
				String s = rs.getString("symbol");
				int quantity = rs.getInt("quantity");
				BigDecimal price = rs.getBigDecimal("price");
				
				stock = new Stock(id, s, quantity, price);
				
				stock.setStockId(id);
				stock.setSymbol(s);
				stock.setQuantity(quantity);
				stock.setPrice(price);
				
				stocks.add(stock);
			}
			
			rs.close();
			ps.close();
			conn.close();
			
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		
		return stocks;
	}

}
