package pl.mzolkiewski.simplestock.calculators;

import com.google.inject.Guice;
import com.google.inject.Injector;
import java.sql.Timestamp;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.experimental.categories.Category;
import pl.mzolkiewski.simplestock.exchanges.ExchangeService;
import pl.mzolkiewski.simplestock.injector.AppInjector;
import pl.mzolkiewski.simplestock.markers.IntegrationTest;
import pl.mzolkiewski.simplestock.stocks.AbstractStock;
import pl.mzolkiewski.simplestock.stocks.CommonStock;
import pl.mzolkiewski.simplestock.stocks.PreferredStock;
import pl.mzolkiewski.simplestock.stocks.StockService;
import pl.mzolkiewski.simplestock.trades.TradeService;
import pl.mzolkiewski.simplestock.trades.TradeType;

/**
 *
 * @author Martin
 */
@Category(IntegrationTest.class)
public class ExchangeCalculatorIntegrationTest {
    private Injector injector;
    private ExchangeCalculator calculator;
    private TradeService tradeService;
    private StockService stockService;
    private ExchangeService exchangeService;
    
    @Before
    public void setUp() {
        injector = Guice.createInjector(new AppInjector());
        calculator = injector.getInstance(ExchangeCalculator.class);
        tradeService = injector.getInstance(TradeService.class);
        stockService = injector.getInstance(StockService.class);
        exchangeService = injector.getInstance(ExchangeService.class);
    }
    
    @After
    public void tearDown() {
        injector = null;
        calculator = null;
        tradeService = null;
        stockService = null;
        exchangeService = null;
    }
    
    @Test
    public void testAllShareIndex() {
        String exchangeSymbol = "MAO";
        setUpMockData(exchangeSymbol);
        setUpMockData("OTHER");
        
        double allShareIndex = calculator.allShareIndex(exchangeSymbol);
        
        assertEquals(25.0, allShareIndex, 0);
    }
    
    @Test
    public void testAllShareIndex_noStocks() {
        double allShareIndex = calculator.allShareIndex("MAO");
        
        assertEquals(0, allShareIndex, 0);
    }

    private void setUpMockData(String exchangeSymbol) {
        exchangeService.create(exchangeSymbol);
        
        createStockWithTrades(new PreferredStock("LOL" + exchangeSymbol, exchangeSymbol, 100, 10, 1));
        createStockWithTrades(new CommonStock("xD" + exchangeSymbol, exchangeSymbol, 100, 10));
    }
    
    private void createStockWithTrades(AbstractStock stock) {
        if (stock instanceof CommonStock) {
            stockService.create(stock.getStockSymbol(), stock.getExchangeSymbol(), stock.getParValue(), stock.getLastDividend());
        } else if (stock instanceof PreferredStock) {
            stockService.create(stock.getStockSymbol(), stock.getExchangeSymbol(), stock.getParValue(), stock.getLastDividend(), ((PreferredStock) stock).getFixedDividend());
        }
        tradeService.create(stock.getStockSymbol(), TradeType.BUY, 5, 20);
        tradeService.create(stock.getStockSymbol(), TradeType.BUY, 5, 30);
        tradeService.create(stock.getStockSymbol(), TradeType.BUY, 100, 1000, new Timestamp(System.currentTimeMillis() - 100 * 60 * 1000));
    }
}
