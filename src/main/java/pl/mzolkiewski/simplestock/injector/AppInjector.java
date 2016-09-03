package pl.mzolkiewski.simplestock.injector;

import com.google.inject.AbstractModule;
import pl.mzolkiewski.simplestock.calculators.ExchangeCalculator;
import pl.mzolkiewski.simplestock.calculators.IExchangeCalculator;
import pl.mzolkiewski.simplestock.calculators.IStockCalculator;
import pl.mzolkiewski.simplestock.calculators.StockCalculator;
import pl.mzolkiewski.simplestock.exchanges.ExchangeStore;
import pl.mzolkiewski.simplestock.exchanges.IExchangeStore;
import pl.mzolkiewski.simplestock.stocks.IStockStore;
import pl.mzolkiewski.simplestock.stocks.StockStore;
import pl.mzolkiewski.simplestock.trades.ITradeStore;
import pl.mzolkiewski.simplestock.trades.TradeStore;

/**
 *
 * @author Martin
 */
public class AppInjector extends AbstractModule {

    @Override
    protected void configure() {
        bind(ITradeStore.class).to(TradeStore.class);
        bind(IStockStore.class).to(StockStore.class);
        bind(IExchangeStore.class).to(ExchangeStore.class);
        bind(IStockCalculator.class).to(StockCalculator.class);
        bind(IExchangeCalculator.class).to(ExchangeCalculator.class);
    }
}
