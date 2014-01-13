package com.stroke.model.data.DAO;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.stroke.model.StockQuote;

public class StockQuoteDAOImpl implements StockQuoteDAO{

	public void insertStockQuote(StockQuote stockQuote) {
		DBConnection conn = DBConnection.getDbCon();
		String insertQuery;
		insertQuery = "Insert into Historical_data values ('"
				+ stockQuote.getSymbol() + "', '"
				+ stockQuote.getDate().get(Calendar.YEAR) + "-"
				+ (stockQuote.getDate().get(Calendar.MONTH) + 1) + "-"
				+ stockQuote.getDate().get(Calendar.DAY_OF_MONTH) +

				"', " + stockQuote.getOpenPrice() + ", "
				+ stockQuote.getDayHigh() + ", " + stockQuote.getDayLow() +

				", " + stockQuote.getClosePrice() + ", "
				+ stockQuote.getVolume() + ", " + stockQuote.getAdjClose()
				+ ")";

		try {
			conn.insert(insertQuery);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}



	public StockQuote getStockQuote(String symbol, GregorianCalendar date) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<StockQuote> getStockQuotes(String symbol,
			GregorianCalendar fromDate, GregorianCalendar toDate) {
		// TODO Auto-generated method stub
		return null;
	}

}
