package com.stroke.model.data.DAO;

import java.sql.SQLException;

import com.stroke.model.Stock;

public class StockDAOImpl implements StockDAO{

	public void insertStock(Stock stock) {
		DBConnection conn = DBConnection.getDbCon();
		String insertQuery;
		if (stock.getIPOyear().equals("n/a")) {
			insertQuery = "Insert into Stock values ('" + stock.getSymbol()
					+ "','" + stock.getName() + "', " + stock.getMarketCap()
					+ ", " + "NULL" + ", '" + stock.getSector() + "', '"
					+ stock.getIndustry() + "','" + stock.getSummaryURL()
					+ "')";
		}
		else{
			insertQuery = "Insert into Stock values ('" + stock.getSymbol()
					+ "','" + stock.getName() + "', " + stock.getMarketCap()
					+ ", " +

					stock.getIPOyear() + ", '" + stock.getSector() + "', '"
					+ stock.getIndustry() + "','" + stock.getSummaryURL()
					+ "')";
		}
		try {
			conn.insert(insertQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Stock getStockBySymbol(String symbol) {
		// TODO Auto-generated method stub
		return null;
	}

}
