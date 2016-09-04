package pl.mzolkiewski.simplestock.calculators;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import pl.mzolkiewski.simplestock.exchanges.Exchange;
import pl.mzolkiewski.simplestock.exchanges.IExchangeService;
import pl.mzolkiewski.simplestock.stocks.AbstractStock;
import pl.mzolkiewski.simplestock.stocks.IStockService;

/**
 *
 * @author Martin
 */
@Singleton
public class ExchangeCalculator implements IExchangeCalculator {
    private IStockService stockService;
    private IStockCalculator stockCalculator;
    
    @Inject
    public ExchangeCalculator(IStockService stockService, IStockCalculator stockCalculator) {
        this.stockService = stockService;
        this.stockCalculator = stockCalculator;
    }

    @Override
    public double allShareIndex(String exchangeSymbol) {
        List<AbstractStock> stocks = getStocks(exchangeSymbol);
        
        if (stocks.isEmpty()) {
            return 0;
        }
        
        double[] prices = new double[stocks.size()];
        
        for (int i = 0; i < stocks.size(); i++) {
            prices[i] = stockCalculator.price(stocks.get(i).getStockSymbol());
        }
        
        return Calculator.geometricMean(prices);
    }
    
    private List<AbstractStock> getStocks(String exchangeSymbol) {
        return stockService.getByExchangeSymbol(exchangeSymbol);
    }
    
}
