package pl.mzolkiewski.simplestock.stocks;

/**
 *
 * @author Martin
 */
public class PreferredStock extends AbstractStock {
    private double fixedDividend;

    public PreferredStock(String symbol, String exchangeSymbol, double parValue, double lastDividend, double fixedDividend) {
        super(symbol, exchangeSymbol, parValue, lastDividend);
        this.fixedDividend = fixedDividend;
    }

    public double getFixedDividend() {
        return fixedDividend;
    }
}
