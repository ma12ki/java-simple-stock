package pl.mzolkiewski.simplestock.stocks;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import pl.mzolkiewski.simplestock.exchanges.ExchangeService;
import pl.mzolkiewski.simplestock.injector.AppInjector;
import pl.mzolkiewski.simplestock.markers.IntegrationTest;

/**
 *
 * @author Martin
 */
@Category(IntegrationTest.class)
public class StockServiceIntegrationTest {
    private Injector injector;
    private StockService service;
    private ExchangeService exchangeService;
    
    @Before
    public void setUp() {
        injector = Guice.createInjector(new AppInjector());
        service = injector.getInstance(StockService.class);
        exchangeService = injector.getInstance(ExchangeService.class);
    }
    
    @After
    public void tearDown() {
        injector = null;
        service = null;
        exchangeService = null;
    }

    @Test
    public void testCreate() {
        String exchangeSymbol = "MAO";
        exchangeService.create(exchangeSymbol);
        
        service.create("LOL", exchangeSymbol, 1, 1);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testCreate_noExchange() {
        service.create("LOL", "MAO", 1, 1);
    }
}
