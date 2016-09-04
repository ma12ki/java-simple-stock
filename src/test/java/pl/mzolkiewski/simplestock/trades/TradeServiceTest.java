package pl.mzolkiewski.simplestock.trades;

import java.sql.Timestamp;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Matchers;
import org.mockito.Mockito;
import pl.mzolkiewski.simplestock.stocks.StockService;

/**
 *
 * @author Martin
 */
public class TradeServiceTest {
    private StockService mockStockService;
    private TradeService service;
    
    @Before
    public void setUp() {
        mockStockService = Mockito.mock(StockService.class);
        service = new TradeService(mockStockService);
    }
    
    @After
    public void tearDown() {
        mockStockService = null;
        service = null;
    }

    @Test
    public void testCreate() {
        Mockito.when(mockStockService.has(Matchers.anyString())).thenReturn(true);
        
        String stockSymbol = "LOL";
        TradeType tradeType = TradeType.BUY;
        int quantity = 10;
        double price = 20.0;
        Trade trade = service.create(stockSymbol, tradeType, quantity, price);
        
        assertEquals(stockSymbol, trade.getStockSymbol());
        assertEquals(tradeType, trade.getTradeType());
        assertEquals(quantity, trade.getQuantity());
        assertEquals(price, trade.getPrice(), 0);
        assertTrue(trade.getTimestamp().after(new Timestamp(System.currentTimeMillis() - 100)));
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testCreate_noStock() {
        Mockito.when(mockStockService.has(Matchers.anyString())).thenReturn(false);
        
        service.create("LOL", TradeType.BUY, 1, 1);
    }
    
    @Test
    public void testCreate_withTimestamp() {
        Mockito.when(mockStockService.has(Matchers.anyString())).thenReturn(true);
        
        String stockSymbol = "LOL";
        TradeType tradeType = TradeType.BUY;
        int quantity = 10;
        double price = 20.0;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Trade trade = service.create(stockSymbol, tradeType, quantity, price, timestamp);
        
        assertEquals(stockSymbol, trade.getStockSymbol());
        assertEquals(tradeType, trade.getTradeType());
        assertEquals(quantity, trade.getQuantity());
        assertEquals(price, trade.getPrice(), 0);
        assertEquals(timestamp, trade.getTimestamp());
    }
    
    @Test
    public void testGetList_byStockSymbol() {
        Mockito.when(mockStockService.has(Matchers.anyString())).thenReturn(true);
        
        String stockSymbol = "LOL";
        service.create(stockSymbol, TradeType.BUY, 1, 1);
        service.create(stockSymbol, TradeType.SELL, 2, 2);
        service.create("MAO", TradeType.SELL, 2, 2);
        
        List<Trade> trades = service.getList(service.hasStockSymbol(stockSymbol));
        
        assertEquals(2, trades.size());
    }
    
    @Test
    public void testGetList_byTimestamp() {
        Mockito.when(mockStockService.has(Matchers.anyString())).thenReturn(true);
        
        long nowMillis = System.currentTimeMillis();
        long minutesMillis = 60 * 1000;
        Timestamp t15MinAgo = new Timestamp(nowMillis - 15 * minutesMillis);
        
        String stockSymbol = "LOL";
        service.create(stockSymbol, TradeType.BUY, 1, 1, new Timestamp(nowMillis));
        service.create(stockSymbol, TradeType.SELL, 2, 2, new Timestamp(nowMillis - 20 * minutesMillis));
        
        List<Trade> trades = service.getList(service.isCreatedAfter(t15MinAgo));
        
        assertEquals(1, trades.size());
    }
}
