package pl.mzolkiewski.simplestock.exchanges;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Martin
 */
public class ExchangeServiceTest {
    private ExchangeService service;
    
    @Before
    public void setUp() {
        service = new ExchangeService();
    }
    
    @After
    public void tearDown() {
        service = null;
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testCreate() {
        String exchangeSymbol = "xD";
        Exchange exchange = service.create(exchangeSymbol);
        
        assertEquals(exchangeSymbol, exchange.getSymbol());
    }
    
    @Test
    public void testGetOne() {
        String exchangeSymbol = "xD";
        service.create(exchangeSymbol);
        
        Exchange exchange = service.getOne(exchangeSymbol);
        
        assertNotNull(exchange);
    }
}
