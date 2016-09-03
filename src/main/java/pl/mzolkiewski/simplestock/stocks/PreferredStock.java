package pl.mzolkiewski.simplestock.stocks;

/**
 *
 * @author Martin
 */
public class PreferredStock extends AbstractStock {
    private double fixedDividend;

    public PreferredStock(String symbol, String exchangeSymbol, double lastDividend, double fixedDividend, double parValue) {
        super(symbol, exchangeSymbol, lastDividend, parValue);
        this.fixedDividend = fixedDividend;
    }

    public double getFixedDividend() {
        return fixedDividend;
    }
}
