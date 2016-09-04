package pl.mzolkiewski.simplestock.trades;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.experimental.categories.Category;
import pl.mzolkiewski.simplestock.exchanges.ExchangeService;
import pl.mzolkiewski.simplestock.injector.AppInjector;
import pl.mzolkiewski.simplestock.markers.IntegrationTest;
import pl.mzolkiewski.simplestock.stocks.StockService;

/**
 *
 * @author Martin
 */
@Category(IntegrationTest.class)
public class TradeServiceIntegrationTest {
    private Injector injector;
    private TradeService service;
    private StockService stockService;
    private ExchangeService exchangeService;
    
    @Before
    public void setUp() {
        injector = Guice.createInjector(new AppInjector());
        service = injector.getInstance(TradeService.class);
        stockService = injector.getInstance(StockService.class);
        exchangeService = injector.getInstance(ExchangeService.class);
    }
    
    @After
    public void tearDown() {
        injector = null;
        service = null;
        stockService = null;
        exchangeService = null;
    }

    @Test
    public void testCreate() {
        String exchangeSymbol = "MAO";
        exchangeService.create(exchangeSymbol);
        
        String stockSymbol = "LOL";
        stockService.create(stockSymbol, exchangeSymbol, 1, 1);
        
        service.create(stockSymbol, TradeType.BUY, 1, 1);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testCreate_noStock() {
        service.create("LOL", TradeType.BUY, 1, 1);
    }
}
