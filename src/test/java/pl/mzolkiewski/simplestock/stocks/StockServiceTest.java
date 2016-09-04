package pl.mzolkiewski.simplestock.stocks;

import java.util.List;
import java.util.NoSuchElementException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Matchers;
import org.mockito.Mockito;
import pl.mzolkiewski.simplestock.exchanges.ExchangeService;

/**
 *
 * @author Martin
 */
public class StockServiceTest {
    private ExchangeService mockExchangeService;
    private StockService service;
    
    @Before
    public void setUp() {
        mockExchangeService = Mockito.mock(ExchangeService.class);
        service = new StockService(mockExchangeService);
    }
    
    @After
    public void tearDown() {
        mockExchangeService = null;
        service = null;
    }

    @Test
    public void testCreate_commonStock() {
        Mockito.when(mockExchangeService.has(Matchers.anyString())).thenReturn(true);
        
        String stockSymbol = "LOL";
        String exchangeSymbol = "MAO";
        double parValue = 100.0;
        double lastDividend = 10.0;
        CommonStock stock = service.create(stockSymbol, exchangeSymbol, parValue, lastDividend);
        
        assertEquals(stockSymbol, stock.getStockSymbol());
        assertEquals(exchangeSymbol, stock.getExchangeSymbol());
        assertEquals(parValue, stock.getParValue(), 0);
        assertEquals(lastDividend, stock.getLastDividend(), 0);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testCreate_commonStock_noExchange() {
        Mockito.when(mockExchangeService.has(Matchers.anyString())).thenReturn(false);

        service.create("LOL", "MAO", 1, 1);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testCreate_commonStock_duplicate() {
        Mockito.when(mockExchangeService.has(Matchers.anyString())).thenReturn(true);

        service.create("LOL", "MAO", 1, 1);
        service.create("LOL", "MAO", 1, 1);
    }
    
    @Test
    public void testCreate_preferredStock() {
        Mockito.when(mockExchangeService.has(Matchers.anyString())).thenReturn(true);
        
        String stockSymbol = "LOL";
        String exchangeSymbol = "MAO";
        double parValue = 100.0;
        double lastDividend = 10.0;
        double fixedDividend = 0.02;
        PreferredStock stock = service.create(stockSymbol, exchangeSymbol, parValue, lastDividend, fixedDividend);
        
        assertEquals(stockSymbol, stock.getStockSymbol());
        assertEquals(exchangeSymbol, stock.getExchangeSymbol());
        assertEquals(parValue, stock.getParValue(), 0);
        assertEquals(lastDividend, stock.getLastDividend(), 0);
        assertEquals(fixedDividend, stock.getFixedDividend(), 0);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testCreate_preferredStock_noExchange() {
        Mockito.when(mockExchangeService.has(Matchers.anyString())).thenReturn(false);

        service.create("LOL", "MAO", 1, 1, 1);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testCreate_preferredStock_duplicate() {
        Mockito.when(mockExchangeService.has(Matchers.anyString())).thenReturn(true);

        service.create("LOL", "MAO", 1, 1, 1);
        service.create("LOL", "MAO", 1, 1, 1);
    }
    
    @Test
    public void testHas_true() {
        Mockito.when(mockExchangeService.has(Matchers.anyString())).thenReturn(true);
        
        String stockSymbol = "LOL";
        PreferredStock stock = service.create(stockSymbol, "MAO", 1, 1, 1);
        
        assertTrue(service.has(stockSymbol));
    }
    
    @Test
    public void testHas_false() {        
        assertFalse(service.has("xD"));
    }
    
    @Test
    public void testGetByStockSymbol() {
        Mockito.when(mockExchangeService.has(Matchers.anyString())).thenReturn(true);
        
        String stockSymbol = "LOL";
        service.create(stockSymbol, "MAO", 1, 1);
        
        AbstractStock stock = service.getByStockSymbol(stockSymbol);
        
        assertNotNull(stock);
        assertEquals(stockSymbol, stock.getStockSymbol());
    }
    
    @Test(expected=NoSuchElementException.class)
    public void testGetByStockSymbol_missing() {
        AbstractStock stock = service.getByStockSymbol("xD");
    }
    
    @Test
    public void testGetByExchangeSymbol() {
        Mockito.when(mockExchangeService.has(Matchers.anyString())).thenReturn(true);
        
        String exchangeSymbol = "MAO";
        service.create("LOL", exchangeSymbol, 1, 1);
        service.create("ROFL", exchangeSymbol, 2, 2);
        service.create("xD", "OTHER", 2, 2);
        
        List<AbstractStock> stock = service.getByExchangeSymbol(exchangeSymbol);
        
        assertEquals(2, stock.size());
    }
    
    @Test
    public void testGetByExchangeSymbol_empty() {
        List<AbstractStock> stock = service.getByExchangeSymbol("MAO");
        
        assertEquals(0, stock.size());
    }
}
