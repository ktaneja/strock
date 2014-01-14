package com.stroke.model.data.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.stroke.model.StockQuote;

@Service
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
			e.printStackTrace();
		}
		return stockQuote;
	}
	


	public List<StockQuote> getStockQuotes(String symbol,
			GregorianCalendar fromDate, GregorianCalendar toDate) {
		List<StockQuote> stocksList = new LinkedList<StockQuote>();
		DBConnection conn = DBConnection.getDbCon();
		String selectQuery = "select * from historical_data where symbol = '"
				+ symbol + "' and date >='" + getStringFromDate(fromDate)
				+ "' and date <='" + getStringFromDate(toDate) + "';";
		try {
			ResultSet rs = conn.query(selectQuery);
			while (rs.next()) {
				StockQuote quote = new StockQuote();
				quote.setSymbol(rs.getString("Symbol"));
				quote.setDate(getDateFromString(rs.getString("date")));
				quote.setOpenPrice(rs.getDouble("openPrice"));
				quote.setClosePrice(rs.getDouble("closePrice"));
				quote.setDayHigh(rs.getDouble("dayHigh"));
				quote.setDayLow(rs.getDouble("dayLow"));
				quote.setVolume(rs.getInt("volume"));
				quote.setAdjClose(rs.getDouble("adjClose"));
				stocksList.add(quote);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stocksList;
	}

	public boolean isStockQuoteInsertedInDB(String symbol){
		String query = "Select count(*) as count from historical_data where symbol = '" + symbol +"'";
		DBConnection conn = DBConnection.getDbCon();
		try {
			ResultSet rs = conn.query(query);
			if(rs.next()){
				if(rs.getInt("count") > 0)
					return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}
	
	public GregorianCalendar getLatestDateInserted(String symbol){
		DBConnection conn = DBConnection.getDbCon();
		String query = "Select max(date) as maxdate from historical_data where symbol = '" + symbol +"'";
		try {
			ResultSet rs = conn.query(query);
			if(rs.next()){
				if(rs.getString("maxdate") != null)
					return getDateFromString(rs.getString("maxdate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static String getStringFromDate(GregorianCalendar date){
		int month = date.get(Calendar.MONTH)+1;
		String result =  date.get(Calendar.YEAR) + "-"
				+  month + "-"
				+ date.get(Calendar.DAY_OF_MONTH);
		return result;
	}
	
	public GregorianCalendar getDateFromString(String date) {
		String[] dateParts = date.split("-");
		GregorianCalendar returnedDate = new GregorianCalendar(
				Integer.parseInt(dateParts[0]),
				Integer.parseInt(dateParts[1])-1,
				Integer.parseInt(dateParts[2]));
		return returnedDate;
	}
}
