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
 * This is where we can interact with the whole app through one object.
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
        String exchangeSymbol = "GBCE";
        app.getExchangeService().create(exchangeSymbol);
        
        createStockWithTrades(new CommonStock("TEA", exchangeSymbol, 100,  0));
        createStockWithTrades(new CommonStock("POP", exchangeSymbol, 100,  8));
        createStockWithTrades(new CommonStock("ALE", exchangeSymbol,  60,  23));
        createStockWithTrades(new PreferredStock("GIN", exchangeSymbol, 100,  8, 0.02));
        createStockWithTrades(new CommonStock("JOE", exchangeSymbol, 250,  13));
                
        double allShareIndex = app.getExchangeCalculator().allShareIndex(exchangeSymbol);
        
        assertEquals(25.0, allShareIndex, 0.001);
    }
    
    @Test
    public void testAllShareIndex_noStocks() {
        double allShareIndex = app.getExchangeCalculator().allShareIndex("GBCE");
        
        assertEquals(0, allShareIndex, 0);
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
