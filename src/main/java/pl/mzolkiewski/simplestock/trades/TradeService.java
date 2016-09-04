package pl.mzolkiewski.simplestock.trades;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import pl.mzolkiewski.simplestock.stocks.IStockService;

/**
 *
 * @author Martin
 */
@Singleton
public class TradeService implements ITradeService {
    private List<Trade> trades = new ArrayList<>();
    private IStockService stockService;
    
    @Inject
    public TradeService(IStockService stockService) {
        this.stockService = stockService;
    }
    
    @Override
    public Trade create(String stockSymbol, TradeType tradeType, int quantity, double price) {
        return this.create(stockSymbol, tradeType, quantity, price, new Timestamp(System.currentTimeMillis()));
    }
    
    @Override
    public Trade create(String stockSymbol, TradeType tradeType, int quantity, double price, Timestamp timestamp) {
        verifyStockExists(stockSymbol);
        
        Trade trade = new Trade(stockSymbol, tradeType, quantity, price, timestamp);
        trades.add(trade);
        
        return trade;
    }
    
    private void verifyStockExists(String stockSymbol) {
        if (!stockService.has(stockSymbol)) {
            throw new IllegalArgumentException("Unknown stock symbol: " + stockSymbol);
        }
    }

    @Override
    public List<Trade> getList(Predicate<Trade> p) {
        return this.trades.stream()
                .filter(p)
                .collect(Collectors.<Trade>toList());
    }

    @Override
    public Predicate<Trade> hasStockSymbol(String stockSymbol) {
        return t -> t.getStockSymbol().equals(stockSymbol);
    }

    @Override
    public Predicate<Trade> isCreatedAfter(Timestamp timestamp) {
        return t -> t.getTimestamp().after(timestamp);
    }
}