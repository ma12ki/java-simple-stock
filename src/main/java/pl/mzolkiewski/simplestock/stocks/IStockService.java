package pl.mzolkiewski.simplestock.stocks;

import java.util.List;

/**
 *
 * @author Martin
 */
public interface IStockService {
    
    CommonStock create(String stockSymbol, String exchangeSymbol, double parValue, double lastDividend);
    
    PreferredStock create(String stockSymbol, String exchangeSymbol, double parValue, double lastDividend, double fixedDividend);
    
    boolean has(String stockSymbol);
    
    AbstractStock getByStockSymbol(String stockSymbol);
    
    List<AbstractStock> getByExchangeSymbol(String exchangeSymbol);
}
