package com.stroke.data.download;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
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
		for (Stock stock : stocks) {
			
			List<String[]> stockHistoricalData = dd.downloadYahooDataForSymbol(stock.getSymbol());
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
		URL url = null;
		try {
			url = new URL("http://www.nasdaq.com/screening/companies-by-name.aspx?letter=0&exchange=nasdaq&render=download");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getDataFromURL(url);
	}
	
	public List<String[]> downloadYahooDataForSymbol(String symbol) throws IOException {
		String urlString  = "http://ichart.finance.yahoo.com/table.csv?s=" + symbol + "&a=01&b=1&c=2000&d=01&e=1&f=2013&g=d&ignore=.csv";
		URL url = null;
		try {
			url = new URL(urlString);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		List<String[]> data = getDataFromURL(url);
		return data;
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
			for (String[] stringArray : data) {
				for (String str : stringArray)
					System.out.println(str);
				System.out.println();
			}
			inputStream.close();
			reader.close();
		}catch(FileNotFoundException e){
			System.out.println("No data found from URL: " + url);
		}
		
		
		
		
		
		return data;
	}
	

}

