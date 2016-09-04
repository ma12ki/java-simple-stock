package pl.mzolkiewski.simplestock.calculators;

import com.google.inject.Guice;
import com.google.inject.Injector;
import java.sql.Timestamp;
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
public class StockCalculatorIntegrationTest {
    private Injector injector;
    private StockCalculator calculator;
    private TradeService tradeService;
    private StockService stockService;
    private ExchangeService exchangeService;
    
    @Before
    public void setUp() {
        injector = Guice.createInjector(new AppInjector());
        calculator = injector.getInstance(StockCalculator.class);
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
    public void testPrice() {
        CommonStock stock = new CommonStock("LOL", "xD", 100, 10);
        setUpMockData(stock);
        
        double price = calculator.price(stock.getStockSymbol());
        
        assertEquals(25, price, 0);
    }
    
    @Test
    public void testPERatio() {
        CommonStock stock = new CommonStock("LOL", "xD", 100, 10);
        setUpMockData(stock);
        
        double PERatio = calculator.PERatio(stock.getStockSymbol());
        
        assertEquals(2.5, PERatio, 0);
    }

    @Test
    public void testDividendYield_commonStock() {
        CommonStock stock = new CommonStock("LOL", "xD", 100, 10);
        setUpMockData(stock);
        
        double dividendYield = calculator.dividendYield(stock.getStockSymbol());
        
        assertEquals(.4, dividendYield, 0);
    }
    
    @Test
    public void testDividendYield_preferredStock() {
        PreferredStock stock = new PreferredStock("LOL", "xD", 100, 10, 1);
        setUpMockData(stock);
        
        double dividendYield = calculator.dividendYield(stock.getStockSymbol());
        
        assertEquals(4, dividendYield, 0);
    }

    private void setUpMockData(AbstractStock stock) {
        exchangeService.create(stock.getExchangeSymbol());
        
        createStockWithTrades(stock);
        createStockWithTrades(new CommonStock("xD", stock.getExchangeSymbol(), 1, 1));
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
