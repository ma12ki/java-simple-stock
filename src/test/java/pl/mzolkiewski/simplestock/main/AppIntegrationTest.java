package pl.mzolkiewski.simplestock.main;

import com.google.inject.Guice;
import com.google.inject.Injector;
import java.sql.Timestamp;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import pl.mzolkiewski.simplestock.injector.AppInjector;
import pl.mzolkiewski.simplestock.markers.IntegrationTest;
import pl.mzolkiewski.simplestock.stocks.AbstractStock;
import pl.mzolkiewski.simplestock.stocks.CommonStock;
import pl.mzolkiewski.simplestock.stocks.PreferredStock;
import pl.mzolkiewski.simplestock.trades.TradeType;

/**
 *
 * @author Martin
 */
@Category(IntegrationTest.class)
public class AppIntegrationTest {
    private Injector injector;
    private App app;
    
    @Before
    public void setUp() {
        injector = Guice.createInjector(new AppInjector());
        app = injector.getInstance(App.class);
    }
    
    @After
    public void tearDown() {
        injector = null;
        app = null;
    }
    
    @Test
    public void testAllShareIndex() {
        String exchangeSymbol = "MAO";
        setUpData(exchangeSymbol);
        setUpData("OTHER");
        
        double allShareIndex = app.getExchangeCalculator().allShareIndex(exchangeSymbol);
        
        assertEquals(25.0, allShareIndex, 0);
    }
    
    @Test
    public void testAllShareIndex_noStocks() {
        double allShareIndex = app.getExchangeCalculator().allShareIndex("MAO");
        
        assertEquals(0, allShareIndex, 0);
    }

    private void setUpData(String exchangeSymbol) {
        app.getExchangeService().create(exchangeSymbol);
        
        createStockWithTrades(new PreferredStock("LOL" + exchangeSymbol, exchangeSymbol, 100, 10, 1));
        createStockWithTrades(new CommonStock("xD" + exchangeSymbol, exchangeSymbol, 100, 10));
    }
    
    private void createStockWithTrades(AbstractStock stock) {
        if (stock instanceof CommonStock) {
            app.getStockService().create(stock.getStockSymbol(), stock.getExchangeSymbol(), stock.getParValue(), stock.getLastDividend());
        } else if (stock instanceof PreferredStock) {
            app.getStockService().create(stock.getStockSymbol(), stock.getExchangeSymbol(), stock.getParValue(), stock.getLastDividend(), ((PreferredStock) stock).getFixedDividend());
        }
        app.getTradeService().create(stock.getStockSymbol(), TradeType.BUY, 5, 20);
        app.getTradeService().create(stock.getStockSymbol(), TradeType.BUY, 5, 30);
        app.getTradeService().create(stock.getStockSymbol(), TradeType.BUY, 100, 1000, new Timestamp(System.currentTimeMillis() - 100 * 60 * 1000));
    }
}
