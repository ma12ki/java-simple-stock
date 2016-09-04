package pl.mzolkiewski.simplestock.exchanges;

import com.google.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *
 * @author Martin
 */
@Singleton
public class ExchangeService implements IExchangeService {
    private List<Exchange> exchanges = new ArrayList<>();
    
    @Override
    public Exchange create(String symbol) {
        if (this.has(symbol)) {
            throw new IllegalArgumentException(symbol);
        }
        
        Exchange exchange = new Exchange(symbol);
        exchanges.add(exchange);
        
        return exchange;
    }
    
    public boolean has(String symbol) {
        try {
            this.getOne(symbol);
            return true;
        } catch(NoSuchElementException e) {
            return false;
        }
    }

    @Override
    public Exchange getOne(String symbol) {
        return exchanges.stream()
                .filter(e -> e.getSymbol().equals(symbol))
                .findFirst()
                .get();
    }
    
}
