package pl.mzolkiewski.simplestock.calculators;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.sql.Timestamp;
import java.util.List;
import pl.mzolkiewski.simplestock.stocks.AbstractStock;
import pl.mzolkiewski.simplestock.stocks.CommonStock;
import pl.mzolkiewski.simplestock.stocks.IStockService;
import pl.mzolkiewski.simplestock.stocks.PreferredStock;
import pl.mzolkiewski.simplestock.trades.ITradeService;
import pl.mzolkiewski.simplestock.trades.Trade;

/**
 *
 * @author Martin
 */
@Singleton
public class StockCalculator implements IStockCalculator {
    private IStockService stockService;
    private ITradeService tradeService;
    
    @Inject
    public StockCalculator(IStockService stockService, ITradeService tradeService) {
        this.stockService = stockService;
        this.tradeService = tradeService;
    }

    @Override
    public double price(String stockSymbol) {
        AbstractStock stock = getStock(stockSymbol);
        List<Trade> trades = this.getTradesForLast15Min(stock);
        
        // seems better than returning 0
        if (trades.isEmpty()) {
            return stock.getParValue();
        }
        
        double[] tradePrices = new double[trades.size()];
        double[] quantities = new double[trades.size()];
        
        for (int i = 0; i < trades.size(); i++) {
            tradePrices[i] = trades.get(i).getPrice();
            quantities[i] = trades.get(i).getQuantity();
        }
        
        return Calculator.stockPrice(tradePrices, quantities);
    }
    
    private AbstractStock getStock(String stockSymbol) {
        return stockService.getByStockSymbol(stockSymbol);
    }
    
    private List<Trade> getTradesForLast15Min(AbstractStock stock) {
        long fifteenMinutesMillis = 15 * 60 * 1000;
        Timestamp t15MinAgo = new Timestamp(System.currentTimeMillis() - fifteenMinutesMillis);
        
        return tradeService.getList(
                tradeService.hasStockSymbol(stock.getStockSymbol())
                    .and(tradeService.isCreatedAfter(t15MinAgo))
        );
    }

    @Override
    public double PERatio(String stockSymbol) {
        AbstractStock stock = getStock(stockSymbol);
        return Calculator.PERatio(price(stockSymbol), stock.getLastDividend());
    }
    
    @Override
    public double dividendYield(String stockSymbol) {
        AbstractStock stock = getStock(stockSymbol);
        
        if (stock instanceof CommonStock) {
            return commonDividendYield((CommonStock) stock);
        }
        if (stock instanceof PreferredStock) {
            return preferredDividendYield((PreferredStock) stock);
        }
        throw new IllegalArgumentException("Unknown stock type");
    }
    
    private double commonDividendYield(CommonStock stock) {
        return Calculator.commonDividendYield(stock.getLastDividend(), price(stock.getStockSymbol()));
    }
    
    private double preferredDividendYield(PreferredStock stock) {
        return Calculator.preferredDividendYield(stock.getFixedDividend(), stock.getParValue(), price(stock.getStockSymbol()));
    }
    
}
