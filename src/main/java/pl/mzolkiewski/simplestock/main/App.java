package pl.mzolkiewski.simplestock.main;

import com.google.inject.Inject;
import pl.mzolkiewski.simplestock.calculators.IExchangeCalculator;
import pl.mzolkiewski.simplestock.calculators.IStockCalculator;
import pl.mzolkiewski.simplestock.exchanges.IExchangeService;
import pl.mzolkiewski.simplestock.stocks.IStockService;
import pl.mzolkiewski.simplestock.trades.ITradeService;

/**
 *
 * @author Martin
 */
public class App {
    private IExchangeService exchangeService;
    private IExchangeCalculator exchangeCalculator;
    private IStockService stockService;
    private IStockCalculator stockCalculator;
    private ITradeService tradeService;

    @Inject
    public App(IExchangeService exchangeService, IExchangeCalculator exchangeCalculator, IStockService stockService, IStockCalculator stockCalculator, ITradeService tradeService) {
        this.exchangeService = exchangeService;
        this.exchangeCalculator = exchangeCalculator;
        this.stockService = stockService;
        this.stockCalculator = stockCalculator;
        this.tradeService = tradeService;
    }

    public IExchangeService getExchangeService() {
        return exchangeService;
    }

    public IExchangeCalculator getExchangeCalculator() {
        return exchangeCalculator;
    }

    public IStockService getStockService() {
        return stockService;
    }

    public IStockCalculator getStockCalculator() {
        return stockCalculator;
    }

    public ITradeService getTradeService() {
        return tradeService;
    }
}
