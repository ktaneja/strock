package com.stroke.model;

import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.math.linear.Array2DRowRealMatrix;

public class QuoteMatrix {
	private String symbol;
	private List<GregorianCalendar> dates;
	private List<String> columns;
	private Array2DRowRealMatrix matrix;
	
	
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public List<GregorianCalendar> getDates() {
		return dates;
	}
	public void setDates(List<GregorianCalendar> dates) {
		this.dates = dates;
	}
	public List<String> getColumns() {
		return columns;
	}
	public void setColumns(List<String> columns) {
		this.columns = columns;
	}
	public Array2DRowRealMatrix getMatrix() {
		return matrix;
	}
	public void setMatrix(Array2DRowRealMatrix matrix) {
		this.matrix = matrix;
	}
	
	
	
}
