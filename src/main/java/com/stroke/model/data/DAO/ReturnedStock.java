package com.stroke.model.data.DAO;

import java.util.GregorianCalendar;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.stroke.model.Stock;
import com.stroke.model.StockQuote;

public class ReturnedStock {

	@Test
	public void test() {
		
		StockDAOImpl stock = new StockDAOImpl();
		Stock returnedStock = stock.getStockBySymbol("AAPL");
		//System.out.println(returnedStock.toString());
		Assert.assertEquals(returnedStock.toString(), "symbol : AAPL, name : Apple Inc., marketCap : 478870669761.534, "
				+ "IPOyear : 1980, sector : Technology, industry : Computer Manufacturing, "
				+ "summaryURL : http://www.nasdaq.com/symbol/aapl");
	}
	
	@Test
	public void test2() {
		StockQuoteDAOImpl stockQuote = new StockQuoteDAOImpl();
		StockQuote returnedStock = stockQuote.getStockQuote("AAPL", new GregorianCalendar(2008, 04, 02));
		System.out.println(returnedStock.toString());
		
	
}
}