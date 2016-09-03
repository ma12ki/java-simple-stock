package pl.mzolkiewski.simplestock.calculators;

import java.util.Arrays;
import java.util.List;
import org.javatuples.Pair;

/**
 * Pure functions for calculating the necessary values.
 *
 * @author Martin
 */
public class Calculator {
    public static double commonDividendYield(double lastDividend, double tickerPrice) {
        if (tickerPrice == 0) {
            return 0;
        }
        
        return lastDividend / tickerPrice;
    }
    
    public static double preferredDividendYield(double fixedDividend, double parValue, double tickerPrice) {
        if (tickerPrice == 0) {
            return 0;
        }
        
        return (fixedDividend * parValue) / tickerPrice;
    }
    
    public static double PERatio(double tickerPrice, double dividend) {
        if (dividend == 0) {
            return 0;
        }
        
        return tickerPrice / dividend;
    }
    
    // could also be done using logarithms
    public static double geometricMean(double[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        
        double product = Arrays
                .stream(prices)
                .reduce((a, b) -> a * b)
                .orElse(0);
        
        return Math.pow(product, 1.0 / (double) prices.length);
    }
    
    // I'm assuming (perhaps incorrectly) that ticker price = stock price
    public static double stockPrice(double[] tradePrices, double[] quantities) {
        double denominator = Arrays.stream(quantities).sum();
        
        if (denominator == 0) {
            return 0;
        }
        
        double numerator = 0;
        for (int i = 0; i < tradePrices.length; i++) {
            numerator += tradePrices[i] * quantities[i];
        }
        
        return numerator / denominator;
    }
}
