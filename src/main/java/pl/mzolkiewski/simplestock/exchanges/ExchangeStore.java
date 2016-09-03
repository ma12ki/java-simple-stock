package pl.mzolkiewski.simplestock.exchanges;

import com.google.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Martin
 */
@Singleton
public class ExchangeStore implements IExchangeStore {
    private List<Exchange> exchanges = new ArrayList<>();
    
    @Override
    public Exchange create(String symbol) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Exchange getOne(String symbol) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
