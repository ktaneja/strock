package com.stroke.data.download;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.stroke.model.Stock;
import com.stroke.model.StockQuote;
import com.stroke.model.data.DAO.StockDAOImpl;
import com.stroke.model.data.DAO.StockQuoteDAOImpl;

import au.com.bytecode.opencsv.CSVReader;

public class DownloadData {
	
	public static void main(String[] args) throws IOException {
		DownloadData dd = new DownloadData();
		List<String[]> tickersData = dd.getStockTickers();
		List<Stock> stocks = new ArrayList<Stock>();
		
		for (String[] data : tickersData) {
			if(tickersData.indexOf(data) >0)
				stocks.add(new Stock(data));
		}
		insertTickersData(stocks);
		int i=0;
		for (Stock stock : stocks) {
			System.out.println("Downloading " + stock.getSymbol() + " " + i++ + "/" + stocks.size());
			List<String[]> stockHistoricalData = dd.downloadDeltaYahooData(stock.getSymbol());
			List<StockQuote> historicalQuotes = new ArrayList<StockQuote>();
			for (String[] data : stockHistoricalData) {
				if(stockHistoricalData.indexOf(data) == 0)
					continue;
				historicalQuotes.add(new StockQuote(stock.getSymbol(), data));
			}
			insertHistoricalData(historicalQuotes);
		}
	}
	
	
	
	private static void insertHistoricalData(List<StockQuote> quotes) {
		StockQuoteDAOImpl stockQuoteDAO = new StockQuoteDAOImpl();
		for (StockQuote stockQuote : quotes) {
			stockQuoteDAO.insertStockQuote(stockQuote);
		}
		
	}

	private static void insertTickersData(List<Stock> stocks) {
		StockDAOImpl stockDAO = new StockDAOImpl();
		for (Stock stock : stocks) {
			stockDAO.insertStock(stock);
		}
		
	}
	
	public List<String[]> getStockTickers() throws IOException{
		List<String[]> nasdaqData = getStockTickers("nasdaq");
		List<String[]> nyseData = getStockTickers("nyse");
		nasdaqData.addAll(nyseData);
		return nasdaqData;
	}
	
	

	public List<String[]> getStockTickers(String exchange) throws IOException{
		URL url = null;
		try {
			url = new URL("http://www.nasdaq.com/screening/companies-by-name.aspx?letter=0&exchange=" + exchange + "&render=download");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return getDataFromURL(url);
	}
	
	
	
	public List<String[]> downloadYahooDataForSymbol(String symbol) throws IOException {
		String urlString  = "http://ichart.finance.yahoo.com/table.csv?s=" + symbol.trim() + "&a=01&b=1&c=2000&d=01&e=1&f=2013&g=d&ignore=.csv";
		URL url = null;
		try {
			url = new URL(urlString);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		List<String[]> data = getDataFromURL(url);
		return data;
	}
	
	public List<String[]> downloadDeltaYahooData(String symbol) throws IOException {
		StockQuoteDAOImpl quotes = new StockQuoteDAOImpl();
		GregorianCalendar latestDate = quotes.getLatestDateInserted(symbol);
		GregorianCalendar today = new GregorianCalendar();
		// We dont have any data
		if(latestDate == null){
			return downloadYahooDataForSymbol(symbol);
		}
		// We have the latest data
		if(latestDate != null && today.get(Calendar.YEAR) == latestDate.get(Calendar.YEAR) && 
				today.get(Calendar.MONTH) == latestDate.get(Calendar.MONTH) && 
				today.get(Calendar.DAY_OF_MONTH) == latestDate.get(Calendar.DAY_OF_MONTH))
			return new ArrayList<String[]>();
		// We have some data
			latestDate.set(Calendar.DAY_OF_MONTH, latestDate.get(Calendar.DAY_OF_MONTH) +1);
		String urlString = contructYahooURL(latestDate, symbol);
		URL url = null;
		try {
			url = new URL(urlString);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		List<String[]> data = getDataFromURL(url);
		return data;
	}
	
	


	private String contructYahooURL(GregorianCalendar latestDate, String symbol) {
		String urlString  = "http://ichart.finance.yahoo.com/table.csv?s=" + symbol ;
		urlString  =	urlString + 	"&a=" + (latestDate.get(Calendar.MONTH)) + "&b=" + 
				latestDate.get(Calendar.DAY_OF_MONTH)  + "&c=" + latestDate.get(Calendar.YEAR) +"&g=d&ignore=.csv";
		
		return urlString;
	}



	private List<String[]> getDataFromURL(URL url) throws IOException {
		List<String[]> data = new ArrayList<String[]>();
		URLConnection openConnection = null;
		try {
			openConnection = url.openConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try{
			InputStream inputStream = openConnection.getInputStream();
			CSVReader reader = new CSVReader(new InputStreamReader(inputStream),',', '"', 0);
			data = reader.readAll();
			
			inputStream.close();
			reader.close();
		}catch(IOException e){
			System.out.println("No data found from URL: " + url);
		}
		return data;
	}
	

}

