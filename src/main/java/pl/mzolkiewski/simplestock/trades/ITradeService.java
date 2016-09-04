package pl.mzolkiewski.simplestock.trades;

import java.sql.Timestamp;
import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author Martin
 */
public interface ITradeService {
    
    Trade create(String stockSymbol, TradeType tradeType, int quantity, double price);
    
    Trade create(String stockSymbol, TradeType tradeType, int quantity, double price, Timestamp timestamp);
    
    List<Trade> getList(Predicate<Trade> p);

    Predicate<Trade> hasStockSymbol(String stockSymbol);

    Predicate<Trade> isCreatedAfter(Timestamp timestamp);
    
}
