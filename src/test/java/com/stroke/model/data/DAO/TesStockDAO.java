package com.stroke.model.data.DAO;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.stroke.model.Stock;
import com.stroke.model.StockQuote;

public class TesStockDAO {

	@Test
	public void test() {

		StockDAOImpl stock = new StockDAOImpl();
		Stock returnedStock = stock.getStockBySymbol("AAPL");
		Assert.assertEquals(
				returnedStock.toString(),
				"symbol : AAPL, name : Apple Inc., marketCap : 475676229066.84, "
						+ "IPOyear : 1980, sector : Technology, industry : Computer Manufacturing, "
						+ "summaryURL : http://www.nasdaq.com/symbol/aapl");
	}

	@Test
	public void test2() {
		StockQuoteDAOImpl stockQuote = new StockQuoteDAOImpl();
		StockQuote returnedStock = stockQuote.getStockQuote("AAPL",
				new GregorianCalendar(2008, 04, 02));
		System.out.println(returnedStock.toString());

	}

	@Test
	public void test3() {
		StockQuoteDAOImpl stockQuote = new StockQuoteDAOImpl();
		List<StockQuote> returnedList = stockQuote.getStockQuotes("AAPL",
				new GregorianCalendar(2008, 2, 02), new GregorianCalendar(2008,
						3, 02));
		for (StockQuote returnedStock : returnedList) {
			System.out.println(returnedStock.toString());
		}
	}
	
	@Test
	public void testIsStockInserted() {
		StockQuoteDAOImpl stockQuote = new StockQuoteDAOImpl();
		boolean isInserted = stockQuote.isStockQuoteInsertedInDB("AAPL");
		Assert.assertEquals(true, isInserted);
	}
	
	@Test
	public void testIsStockInserted2() {
		StockQuoteDAOImpl stockQuote = new StockQuoteDAOImpl();
		boolean isInserted = stockQuote.isStockQuoteInsertedInDB("ABCDF");
		Assert.assertEquals(false, isInserted);
	}
	
	@Test
	public void testGetLatestDateInserted(){
		StockQuoteDAOImpl stockQuote = new StockQuoteDAOImpl();
		GregorianCalendar latestDate = stockQuote.getLatestDateInserted("AAPL");
		Assert.assertEquals(2013, latestDate.get(Calendar.YEAR));
	}
	
	@Test
	public void testGetLatestDateInserted2(){
		StockQuoteDAOImpl stockQuote = new StockQuoteDAOImpl();
		GregorianCalendar latestDate = stockQuote.getLatestDateInserted("ABCDF");
		Assert.assertNull(latestDate);
	}

}