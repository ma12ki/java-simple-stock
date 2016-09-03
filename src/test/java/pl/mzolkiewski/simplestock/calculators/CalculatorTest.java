package pl.mzolkiewski.simplestock.calculators;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Martin
 */
public class CalculatorTest {
    
    @Test
    public void testCommonDividendYield() {
        double lastDividend = 5;
        double tickerPrice = 40;
        double yield = Calculator.commonDividendYield(lastDividend, tickerPrice);
        
        assertEquals(0.125, yield, 0.0001);
    }
    
    @Test
    public void testCommonDividendYield_zeroTickerPrice() {
        double lastDividend = 5;
        double tickerPrice = 0;
        double yield = Calculator.commonDividendYield(lastDividend, tickerPrice);
        
        assertEquals(0, yield, 0);
    }
    
    @Test
    public void testPreferredDividendYield() {
        double fixedDividend = 0.05;
        double parValue = 100;
        double tickerPrice = 80;
        double yield = Calculator.preferredDividendYield(fixedDividend, parValue, tickerPrice);
        
        assertEquals(0.0625, yield, 0.0001);
    }
    
    @Test
    public void testPreferredDividendYield_zeroTickerPrice() {
        double fixedDividend = 0.05;
        double parValue = 100;
        double tickerPrice = 0;
        double yield = Calculator.preferredDividendYield(fixedDividend, parValue, tickerPrice);
        
        assertEquals(0, yield, 0);
    }
    
    @Test
    public void testPERatio() {
        double tickerPrice = 100;
        double dividend = 10;
        double ratio = Calculator.PERatio(tickerPrice, dividend);
        
        assertEquals(10, ratio, 0);
    }
    
    @Test
    public void testPERatio_zeroDividend() {
        double tickerPrice = 100;
        double dividend = 0;
        double ratio = Calculator.PERatio(tickerPrice, dividend);
        
        assertEquals(0, ratio, 0);
    }
    
    @Test
    public void testGeometricMean() {
        double[] prices = new double[]{2, 8};
        double gm = Calculator.geometricMean(prices);
        
        assertEquals(4, gm, 0);
    }
    
    @Test
    public void testGeometricMean_zeroLength() {
        double[] prices = new double[0];
        double gm = Calculator.geometricMean(prices);
        
        assertEquals(0, gm, 0);
    }
    
    @Test
    public void testStockPrice() {
        double[] tradePrices = new double[]{10, 5};
        double[] quantities = new double[]{10, 5};
        double stockPrice = Calculator.stockPrice(tradePrices, quantities);
        
        assertEquals(8.333, stockPrice, 0.001);
    }
    
    @Test
    public void testStockPrice_zeroQuantity() {
        double[] tradePrices = new double[0];
        double[] quantities = new double[0];
        double stockPrice = Calculator.stockPrice(tradePrices, quantities);
        
        assertEquals(0, stockPrice, 0);
    }
}
