package pl.mzolkiewski.simplestock.calculators;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import pl.mzolkiewski.simplestock.stocks.IStockService;
import pl.mzolkiewski.simplestock.trades.ITradeService;

/**
 *
 * @author Martin
 */
@Singleton
public class StockCalculator implements IStockCalculator {
    private ITradeService tradeStore;
    private IStockService stockStore;
    
    @Inject
    public StockCalculator(ITradeService tradeStore, IStockService stockStore) {
        this.tradeStore = tradeStore;
        this.stockStore = stockStore;
    }

    @Override
    public double dividendYield(String stockSymbol) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double PERatio(String stockSymbol) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double price(String StockSymbol) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
