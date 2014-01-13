package com.stroke.model.data.DAO;

import java.util.GregorianCalendar;
import java.util.List;

import com.stroke.model.StockQuote;

public interface StockQuoteDAO {
	public void insertStockQuote(StockQuote quote);
	public StockQuote getStockQuote(String symbol, GregorianCalendar date);
	public List<StockQuote> getStockQuotes(String symbol, GregorianCalendar fromDate, GregorianCalendar toDate);
}
