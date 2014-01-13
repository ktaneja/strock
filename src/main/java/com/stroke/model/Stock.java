package com.stroke.model;


public class Stock {
	private String symbol;
	private String name;
	private String marketCap;
	private String IPOyear;
	private String sector;
	private String industry;
	private String summaryURL;
	
	public Stock(){

	}
	
	public Stock(String symbol, String name, String marketCap, String IPOyear,
	String sector, String industry, String summaryURL){
		this.symbol = symbol;
		this.name = name;
		this.marketCap = marketCap;
		this.IPOyear = IPOyear;
		this.sector = sector;
		this.industry = industry;
		this.summaryURL = summaryURL;
	}
	
	/**
	 * Constructs a Stock Object from data returned by NASDAQ API that returns
	 * Symbol, Name, LastSale, MarketCap, ADR TSO, IPOyear, Sector, industry, Summary, Quote
	 * @param stockData
	 */
	public Stock(String[] stockDataFromNasdaq){
		this.symbol = stockDataFromNasdaq[0];
		this.name = stockDataFromNasdaq[1];
		this.marketCap = stockDataFromNasdaq[3];
		this.IPOyear = stockDataFromNasdaq[5];
		this.sector = stockDataFromNasdaq[6];
		this.industry = stockDataFromNasdaq[7];;
		this.summaryURL = stockDataFromNasdaq[8];;
	}
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMarketCap() {
		return marketCap;
	}
	public void setMarketCap(String marketCap) {
		this.marketCap = marketCap;
	}
	public String getIPOyear() {
		return IPOyear;
	}
	public void setIPOyear(String iPOyear) {
		this.IPOyear = iPOyear;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getSummaryURL() {
		return summaryURL;
	}
	public void setSummaryURL(String summaryURL) {
		this.summaryURL = summaryURL;
	}
	
	public String toString() {
		String resultSet = "symbol : " + this.getSymbol() + ", name : "
				+ this.getName() + ", marketCap : " + this.getMarketCap()
				+ ", IPOyear : " + this.getIPOyear() + ", sector : "
				+ this.getSector() + ", industry : " + this.getIndustry()
				+ ", summaryURL : " + this.getSummaryURL();
		return resultSet;
	}
}
