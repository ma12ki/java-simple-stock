package pl.mzolkiewski.simplestock.stocks;

/**
 *
 * @author Martin
 */
public abstract class AbstractStock {
    private String stockSymbol;
    private String exchangeSymbol;
    private double lastDividend;
    private double parValue;
    
    public AbstractStock(String stockSymbol, String exchangeSymbol, double lastDividend, double parValue) {
        this.stockSymbol = stockSymbol;
        this.exchangeSymbol = exchangeSymbol;
        this.lastDividend = lastDividend;
        this.parValue = parValue;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }
    
    public String getExchangeSymbol() {
        return exchangeSymbol;
    }

    public double getLastDividend() {
        return lastDividend;
    }

    public double getParValue() {
        return parValue;
    }
}
