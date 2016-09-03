package pl.mzolkiewski.simplestock.stocks;

import java.util.List;

/**
 *
 * @author Martin
 */
public interface IStockService {
    
    CommonStock create(String stockSymbol, String exchangeSymbol, double lastDividend, double parValue);
    
    PreferredStock create(String symbol, String exchangeSymbol, double lastDividend, double fixedDividend, double parValue);
    
    AbstractStock getByStockSymbol(String stockSymbol);
    
    List<AbstractStock> getByExchangeSymbol(String exchangeSymbol);
}
