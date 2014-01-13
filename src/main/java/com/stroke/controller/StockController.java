package com.stroke.controller;

import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.stroke.model.Stock;
import com.stroke.model.StockQuote;
import com.stroke.model.data.DAO.StockDAO;
import com.stroke.model.data.DAO.StockQuoteDAO;

@Controller
public class StockController {
	
	StockQuoteDAO quoteService;
	StockDAO stockService;
	
	@Autowired
	public StockController(StockDAO stockDAO, StockQuoteDAO quoteDAO){
		this.stockService = stockDAO;
		this.quoteService = quoteDAO;
	}
	
	@RequestMapping("quote/{symbol}")
    @ResponseBody
	public StockQuote getQuote(@PathVariable String symbol, @RequestParam int from, @RequestParam(required = false) String to){
		StockQuote quote = new StockQuote(symbol, new GregorianCalendar(), from,5,5,5,5,5);
		return quote;
	}
	
	@RequestMapping("stock/{symbol}")
    @ResponseBody
	public Stock getStockDetails(@PathVariable String symbol){
		Stock stock = stockService.getStockBySymbol(symbol);
		return stock;
	}

}
