package pl.mzolkiewski.simplestock.exchanges;

/**
 *
 * @author Martin
 */
public interface IExchangeService {
    Exchange create(String symbol);
    
    boolean has(String symbol);
    
    Exchange getOne(String symbol);
}
