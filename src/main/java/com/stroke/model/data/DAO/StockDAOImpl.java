package com.stroke.model.data.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.stroke.model.Stock;

@Service
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
		Stock stock = new Stock();
		DBConnection conn = DBConnection.getDbCon();
		String selectQuery = "select * from stock where symbol = '" + symbol + "';";	
		try {
			ResultSet	rs = conn.query(selectQuery);
			while(rs.next()){			
				stock.setSymbol(rs.getString("Symbol"));
				stock.setName(rs.getString("name"));
				stock.setMarketCap(rs.getString("marketCap"));
				stock.setIPOyear(rs.getString("IPOyear"));
				stock.setSector(rs.getString("sector"));
				stock.setIndustry(rs.getString("industry"));
				stock.setSummaryURL(rs.getString("summaryURL"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stock;
	}

}
