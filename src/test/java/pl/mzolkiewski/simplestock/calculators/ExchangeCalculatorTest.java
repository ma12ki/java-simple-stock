package pl.mzolkiewski.simplestock.calculators;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Matchers;
import org.mockito.Mockito;
import pl.mzolkiewski.simplestock.stocks.AbstractStock;
import pl.mzolkiewski.simplestock.stocks.CommonStock;
import pl.mzolkiewski.simplestock.stocks.StockService;

/**
 *
 * @author Martin
 */
public class ExchangeCalculatorTest {
    private StockService mockStockService;
    private StockCalculator mockStockCalculator;
    private ExchangeCalculator calculator;
    
    @Before
    public void setUp() {
        mockStockService = Mockito.mock(StockService.class);
        mockStockCalculator = Mockito.mock(StockCalculator.class);
        calculator = new ExchangeCalculator(mockStockService, mockStockCalculator);
    }
    
    @After
    public void tearDown() {
        mockStockService = null;
        mockStockCalculator = null;
        calculator = null;
    }

    @Test
    public void testAllShareIndex() {
        String exchangeSymbol = "MAO";
        List<AbstractStock> stocks = new ArrayList<>();
        stocks.add(new CommonStock("LOL", exchangeSymbol, 1, 1));
        stocks.add(new CommonStock("xD", exchangeSymbol, 1, 1));
        
        Mockito.when(mockStockService.getByExchangeSymbol(exchangeSymbol)).thenReturn(stocks);
        Mockito.when(mockStockCalculator.price(Matchers.anyString())).thenReturn(2.0, 8.0);
        
        double allShareIndex = calculator.allShareIndex(exchangeSymbol);
        
        assertEquals(4.0, allShareIndex, 0);
    }
    
    @Test
    public void testAllShareIndex_noStocks() {
        double allShareIndex = calculator.allShareIndex("MAO");
        
        assertEquals(0, allShareIndex, 0);
    }
}
