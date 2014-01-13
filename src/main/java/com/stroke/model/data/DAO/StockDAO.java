package com.stroke.model.data.DAO;

import com.stroke.model.Stock;

public interface StockDAO {
	public void insertStock(Stock stock);
	public Stock getStockBySymbol(String symbol);
}
