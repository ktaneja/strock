package com.stroke.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class StockQuote {
	
	private String symbol;
	private GregorianCalendar date;
	private double openPrice;
	private double closePrice;
	private double dayHigh;
	private double dayLow;
	private int volume;
	private double adjClose;
	
	public StockQuote(String symbol, GregorianCalendar date,
			double openPrice, double closePrice, double dayHigh,
			double dayLow, int volume, double adjClose){
		this.symbol = symbol;
		this.date = date;
		this.openPrice = openPrice;
		this.closePrice = closePrice;
		this.dayHigh = dayHigh;
		this.dayLow = dayLow;
		this.volume = volume;
		this.adjClose = adjClose;
	}
	
	public StockQuote(String symbol, String[] data){
		this.symbol = symbol;
		String[] dateParts = data[0].split("-");
		this.date = new GregorianCalendar(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1])-1, Integer.parseInt(dateParts[2]));
		System.out.println(date.get(Calendar.YEAR) + "-" + date.get(Calendar.MONTH) + "-" + date.get(Calendar.DAY_OF_MONTH));
		this.openPrice = Double.parseDouble(data[1]);
		this.closePrice = Double.parseDouble(data[4]);
		this.dayHigh = Double.parseDouble(data[2]);
		this.dayLow = Double.parseDouble(data[3]);
		this.volume = Integer.parseInt(data[5]);
		this.adjClose = Double.parseDouble(data[6]);
	}
	
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public GregorianCalendar getDate() {
		return date;
	}
	public void setDate(GregorianCalendar date) {
		this.date = date;
	}
	public double getOpenPrice() {
		return openPrice;
	}
	public void setOpenPrice(double openPrice) {
		this.openPrice = openPrice;
	}
	public double getClosePrice() {
		return closePrice;
	}
	public void setClosePrice(double closePrice) {
		this.closePrice = closePrice;
	}
	public double getDayHigh() {
		return dayHigh;
	}
	public void setDayHigh(double dayHigh) {
		this.dayHigh = dayHigh;
	}
	public double getDayLow() {
		return dayLow;
	}
	public void setDayLow(double dayLow) {
		this.dayLow = dayLow;
	}
	public int getVolume() {
		return volume;
	}
	public void setVolume(int volume) {
		this.volume = volume;
	}

	public double getAdjClose() {
		return adjClose;
	}
	
	public void setAdjClose(double adjClose) {
		this.adjClose = adjClose;
	}
	
	
}
