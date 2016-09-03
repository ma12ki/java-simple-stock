package pl.mzolkiewski.simplestock.trades;

import java.sql.Timestamp;
import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author Martin
 */
public interface ITradeStore {
    
    Trade create(String stockSymbol, TradeType tradeType, int quantity, double price, Timestamp timestamp);
    
    Trade create(String stockSymbol, TradeType tradeType, int quantity, double price);
    
    List<Trade> getList(Predicate<Trade> p);

    Predicate<Trade> hasStockSymbol(String stockSymbol);

    Predicate<Trade> isCreatedAfter(Timestamp timestamp);
    
}
