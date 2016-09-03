package pl.mzolkiewski.simplestock.stocks;

/**
 *
 * @author Martin
 */
public class CommonStock extends AbstractStock {
    public CommonStock(String stockSymbol, String exchangeSymbol, double lastDividend, double parValue) {
        super(stockSymbol, exchangeSymbol, lastDividend, parValue);
    }
}
