package pl.mzolkiewski.simplestock.exchanges;

import com.google.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Martin
 */
@Singleton
public class ExchangeService implements IExchangeService {
    private List<Exchange> exchanges = new ArrayList<>();
    
    @Override
    public Exchange create(String symbol) {
        Exchange exchange = new Exchange(symbol);
        exchanges.add(exchange);
        
        return exchange;
    }

    @Override
    public Exchange getOne(String symbol) {
        return exchanges.stream()
                .filter(e -> e.getSymbol().equals(symbol))
                .findFirst()
                .get();
    }
    
}
