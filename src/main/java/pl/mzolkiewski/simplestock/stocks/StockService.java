package pl.mzolkiewski.simplestock.stocks;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import pl.mzolkiewski.simplestock.exchanges.IExchangeService;

/**
 *
 * @author Martin
 */
@Singleton
public class StockService implements IStockService {
    private List<AbstractStock> stocks = new ArrayList<>();
    private IExchangeService exchangeService;
    
    @Inject
    public StockService(IExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }
    
    @Override
    public CommonStock create(String stockSymbol, String exchangeSymbol, double parValue, double lastDividend) {
        CommonStock stock = new CommonStock(stockSymbol, exchangeSymbol, parValue, lastDividend);
        this.save(stock);
        
        return stock;
    }

    @Override
    public PreferredStock create(String stockSymbol, String exchangeSymbol, double parValue, double lastDividend, double fixedDividend) {
        PreferredStock stock = new PreferredStock(stockSymbol, exchangeSymbol, parValue, lastDividend, fixedDividend);
        this.save(stock);
        
        return stock;
    }
    
    private void save(AbstractStock stock) {
        verifyExchangeExists(stock.getExchangeSymbol());
        verifyStockNotExists(stock.getStockSymbol());
        stocks.add(stock);
    }
    
    private void verifyExchangeExists(String exchangeSymbol) {
        if (!exchangeService.has(exchangeSymbol)) {
            throw new IllegalArgumentException("Unknown exchange: " + exchangeSymbol);
        }
    }
    
    private void verifyStockNotExists(String stockSymbol) {
        if (this.has(stockSymbol)) {
            throw new IllegalArgumentException("Duplicate stock symbol: " + stockSymbol);
        }
    }
    
    @Override
    public boolean has(String stockSymbol) {
        try {
            this.getByStockSymbol(stockSymbol);
            return true;
        } catch(NoSuchElementException e) {
            return false;
        }
    }

    @Override
    public AbstractStock getByStockSymbol(String stockSymbol) {
        return stocks.stream()
            .filter(s -> s.getStockSymbol().equals(stockSymbol))
            .findFirst()
            .get();
    }

    @Override
    public List<AbstractStock> getByExchangeSymbol(String exchangeSymbol) {
        return stocks.stream()
            .filter(s -> s.getExchangeSymbol().equals(exchangeSymbol))
            .collect(Collectors.<AbstractStock>toList());
    }
    
}
