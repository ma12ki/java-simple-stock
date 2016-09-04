package pl.mzolkiewski.simplestock.calculators;

/**
 *
 * @author Martin
 */
public interface IStockCalculator {
    
    double price(String StockSymbol);
    
    double PERatio(String stockSymbol);
    
    double dividendYield(String stockSymbol);
    
}
