package pl.mzolkiewski.simplestock.exchanges;

/**
 *
 * @author Martin
 */
public interface IExchangeStore {
    Exchange create(String symbol);
    
    Exchange getOne(String symbol);
}
