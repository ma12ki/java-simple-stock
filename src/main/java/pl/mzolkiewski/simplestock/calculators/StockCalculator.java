package pl.mzolkiewski.simplestock.calculators;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import pl.mzolkiewski.simplestock.stocks.IStockStore;
import pl.mzolkiewski.simplestock.trades.ITradeStore;

/**
 *
 * @author Martin
 */
@Singleton
public class StockCalculator implements IStockCalculator {
    private ITradeStore tradeStore;
    private IStockStore stockStore;
    
    @Inject
    public StockCalculator(ITradeStore tradeStore, IStockStore stockStore) {
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
