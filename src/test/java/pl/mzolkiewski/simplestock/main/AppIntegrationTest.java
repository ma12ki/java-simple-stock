package pl.mzolkiewski.simplestock.main;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.experimental.categories.Category;
import pl.mzolkiewski.simplestock.injector.AppInjector;
import pl.mzolkiewski.simplestock.markers.IntegrationTest;

/**
 *
 * @author Martin
 */
@Category(IntegrationTest.class)
public class AppIntegrationTest {
    private Injector injector;
    private App app;
    
    @Before
    public void setUp() {
        injector = Guice.createInjector(new AppInjector());
        app = injector.getInstance(App.class);
    }
    
    @After
    public void tearDown() {
        injector = null;
        app = null;
    }

    @Test
    public void testApp() {
        
    }
}
