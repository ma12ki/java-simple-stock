package pl.mzolkiewski.simplestock.exchanges;

/**
 *
 * @author Martin
 */
public class Exchange {
    private String symbol;
    
    public Exchange(String symbol) {
        this.symbol = symbol;
    }
    
    public String getSymbol() {
        return this.symbol;
    }
}
