package pl.mzolkiewski.simplestock.injector;

import com.google.inject.AbstractModule;
import pl.mzolkiewski.simplestock.calculators.ExchangeCalculator;
import pl.mzolkiewski.simplestock.calculators.IExchangeCalculator;
import pl.mzolkiewski.simplestock.calculators.IStockCalculator;
import pl.mzolkiewski.simplestock.calculators.StockCalculator;
import pl.mzolkiewski.simplestock.exchanges.ExchangeService;
import pl.mzolkiewski.simplestock.exchanges.IExchangeService;
import pl.mzolkiewski.simplestock.stocks.IStockService;
import pl.mzolkiewski.simplestock.stocks.StockService;
import pl.mzolkiewski.simplestock.trades.ITradeService;
import pl.mzolkiewski.simplestock.trades.TradeService;

/**
 *
 * @author Martin
 */
public class AppInjector extends AbstractModule {

    @Override
    protected void configure() {
        bind(ITradeService.class).to(TradeService.class);
        bind(IStockService.class).to(StockService.class);
        bind(IExchangeService.class).to(ExchangeService.class);
        bind(IStockCalculator.class).to(StockCalculator.class);
        bind(IExchangeCalculator.class).to(ExchangeCalculator.class);
    }
}
