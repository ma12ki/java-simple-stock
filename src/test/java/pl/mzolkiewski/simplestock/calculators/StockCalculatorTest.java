package pl.mzolkiewski.simplestock.calculators;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Matchers;
import org.mockito.Mockito;
import pl.mzolkiewski.simplestock.stocks.AbstractStock;
import pl.mzolkiewski.simplestock.stocks.CommonStock;
import pl.mzolkiewski.simplestock.stocks.PreferredStock;
import pl.mzolkiewski.simplestock.stocks.StockService;
import pl.mzolkiewski.simplestock.trades.Trade;
import pl.mzolkiewski.simplestock.trades.TradeService;
import pl.mzolkiewski.simplestock.trades.TradeType;

/**
 *
 * @author Martin
 */
public class StockCalculatorTest {
    private StockService mockStockService;
    private TradeService mockTradeService;
    private StockCalculator calculator;
    
    @Before
    public void setUp() {
        mockStockService = Mockito.mock(StockService.class);
        mockTradeService = Mockito.mock(TradeService.class);
        calculator = new StockCalculator(mockStockService, mockTradeService);
    }
    
    @After
    public void tearDown() {
        mockStockService = null;
        mockTradeService = null;
        calculator = null;
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
        List<Trade> trades = new ArrayList<>();
        trades.add(new Trade(stock.getStockSymbol(), TradeType.BUY, 5, 20));
        trades.add(new Trade(stock.getStockSymbol(), TradeType.SELL, 5, 30));
        
        Predicate<Trade> predicate = (o -> true);
        
        Mockito.when(mockTradeService.getList(Matchers.any())).thenReturn(trades);
        Mockito.when(mockTradeService.hasStockSymbol(stock.getStockSymbol())).thenReturn(predicate);
        Mockito.when(mockTradeService.isCreatedAfter(Matchers.any())).thenReturn(predicate);
        Mockito.when(mockStockService.getByStockSymbol(Matchers.anyString())).thenReturn(stock);
    }
    
}
