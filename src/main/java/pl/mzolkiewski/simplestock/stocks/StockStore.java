package pl.mzolkiewski.simplestock.stocks;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.List;
import pl.mzolkiewski.simplestock.exchanges.IExchangeStore;

/**
 *
 * @author Martin
 */
@Singleton
public class StockStore implements IStockStore {
    private IExchangeStore exchangeStore;
    
    @Inject
    public StockStore(IExchangeStore exchangeStore) {
        this.exchangeStore = exchangeStore;
    }
    
    @Override
    public CommonStock create(String stockSymbol, String exchangeSymbol, double lastDividend, double parValue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PreferredStock create(String symbol, String exchangeSymbol, double lastDividend, double fixedDividend, double parValue) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AbstractStock getByStockSymbol(String stockSymbol) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AbstractStock> getByExchangeSymbol(String exchangeSymbol) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
