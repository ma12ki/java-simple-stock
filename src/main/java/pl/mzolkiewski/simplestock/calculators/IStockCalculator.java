package pl.mzolkiewski.simplestock.calculators;

/**
 *
 * @author Martin
 */
public interface IStockCalculator {
    double dividendYield(String stockSymbol);
    
    double PERatio(String stockSymbol);
    
    double price(String StockSymbol);
}
