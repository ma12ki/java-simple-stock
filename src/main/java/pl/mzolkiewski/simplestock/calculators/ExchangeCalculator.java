package pl.mzolkiewski.simplestock.calculators;

import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import pl.mzolkiewski.simplestock.exchanges.Exchange;

/**
 *
 * @author Martin
 */
public class ExchangeCalculator implements IExchangeCalculator {
    private IStockCalculator stockCalculator;
    
    @Inject
    public ExchangeCalculator(IStockCalculator stockCalculator) {
        this.stockCalculator = stockCalculator;
    }

    @Override
    public double allShareIndex(String exchangeSymbol) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
