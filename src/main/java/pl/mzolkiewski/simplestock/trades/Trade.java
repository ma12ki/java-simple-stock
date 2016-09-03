package pl.mzolkiewski.simplestock.trades;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Martin
 */
public class Trade {
    private String stockSymbol;
    private TradeType tradeType;
    private int quantity;
    private double price;
    private Timestamp timestamp;
    
    public Trade(String stockSymbol, TradeType tradeType, int quantity, double price, Timestamp timestamp) {
        this.stockSymbol = stockSymbol;
        this.tradeType = tradeType;
        this.quantity = quantity;
        this.price = price;
        this.timestamp = timestamp;
    }
    
    public Trade(String stockSymbol, TradeType tradeType, int quantity, double price) {
        this.stockSymbol = stockSymbol;
        this.tradeType = tradeType;
        this.quantity = quantity;
        this.price = price;
        this.timestamp = new Timestamp((new Date()).getTime());
    }
    
    public String getStockSymbol() {
        return stockSymbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
}
