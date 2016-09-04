package pl.mzolkiewski.simplestock.stocks;

/**
 *
 * @author Martin
 */
public abstract class AbstractStock {
    private String stockSymbol;
    private String exchangeSymbol;
    private double parValue;
    private double lastDividend;
    
    public AbstractStock(String stockSymbol, String exchangeSymbol, double parValue, double lastDividend) {
        this.stockSymbol = stockSymbol;
        this.exchangeSymbol = exchangeSymbol;
        this.parValue = parValue;
        this.lastDividend = lastDividend;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }
    
    public String getExchangeSymbol() {
        return exchangeSymbol;
    }

    public double getParValue() {
        return parValue;
    }
    
    public double getLastDividend() {
        return lastDividend;
    }
}
