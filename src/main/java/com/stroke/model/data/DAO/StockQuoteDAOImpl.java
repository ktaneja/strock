package com.stroke.model.data.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.stroke.model.Stock;
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
		StockQuote stockQuote = new StockQuote();
		DBConnection conn = DBConnection.getDbCon();
		String selectQuery = "select * from historical_data where symbol = '"
								+ symbol + "' and date = '" + getStringFromDate(date) + "';";
		try {
			ResultSet	rs = conn.query(selectQuery);
			while(rs.next()){			
				stockQuote.setSymbol(rs.getString("Symbol"));
				stockQuote.setDate(getDateFromString(rs.getString("date")));
				stockQuote.setOpenPrice(rs.getDouble("openPrice"));
				stockQuote.setClosePrice(rs.getDouble("closePrice"));
				stockQuote.setDayHigh(rs.getDouble("dayHigh"));
				stockQuote.setDayLow(rs.getDouble("dayLow"));
				stockQuote.setVolume(rs.getInt("volume"));
				stockQuote.setAdjClose(rs.getDouble("adjClose"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stockQuote;
	}
	
	
	

	

	public List<StockQuote> getStockQuotes(String symbol,
			GregorianCalendar fromDate, GregorianCalendar toDate) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public static String getStringFromDate(GregorianCalendar date){
		String result =  date.get(Calendar.YEAR) + "-"
				+ (date.get(Calendar.MONTH)) + "-"
				+ date.get(Calendar.DAY_OF_MONTH);
		return result;
	}
	
	public GregorianCalendar getDateFromString(String date) {
		String[] dateParts = date.split("-");
		GregorianCalendar returnedDate = new GregorianCalendar(
				Integer.parseInt(dateParts[0]),
				Integer.parseInt(dateParts[1]),
				Integer.parseInt(dateParts[2]));
		return returnedDate;
	}
}
