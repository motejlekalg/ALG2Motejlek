
import java.util.List;
import orderingsystem.app.LogEntry;
import orderingsystem.app.Warehouse;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Martin Motejlek
 */
public class TransactionTest {
    
    private Warehouse w;
    
    public TransactionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        w = new Warehouse();
        w.addItem("jablko", "Jablko");
        w.addItem("hruska", "Hruška");
        w.addItem("zidle", "Stará židle");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void standardSituation() {
        assertTrue(w.commitTransaction("jablko", 5));
        assertTrue(w.commitTransaction("hruska", 100));
        assertTrue(w.commitTransaction("jablko", -3));
        assertTrue(w.commitTransaction("zidle", 1));
        
        assertTrue(w.getItem("hruska").getQuantity() == 100);
        assertTrue(w.getItem("jablko").getQuantity() == 2);
        assertTrue(w.getItem("zidle").getQuantity() == 1);
        
        List<LogEntry> log = w.getLog();
        assertTrue(log.get(0).getCode().equals("jablko"));
        assertTrue(log.get(1).getCode().equals("hruska"));
        assertTrue(log.get(2).getCode().equals("jablko"));
        assertTrue(log.get(3).getCode().equals("zidle"));
        
        assertTrue(log.get(0).getName().equals("Jablko"));
        assertTrue(log.get(1).getName().equals("Hruška"));
        assertTrue(log.get(2).getName().equals("Jablko"));
        assertTrue(log.get(3).getName().equals("Stará židle"));
        
        assertTrue(log.get(0).getQuantityChange() == 5);
        assertTrue(log.get(1).getQuantityChange() == 100);
        assertTrue(log.get(2).getQuantityChange() == -3);
        assertTrue(log.get(3).getQuantityChange() == 1);
    }
    
    @Test
    public void limitDecreasing() {        
        assertTrue(w.commitTransaction("jablko", 10));
        assertTrue(w.commitTransaction("jablko", -5));
        assertFalse(w.commitTransaction("jablko", -6));
        assertTrue(w.commitTransaction("jablko", -5));
        
        assertTrue(w.getItem("jablko").getQuantity() == 0);
        
        List<LogEntry> log = w.getLog();
        assertTrue(log.size() == 3);
        
        assertTrue(log.get(0).getCode().equals("jablko"));
        assertTrue(log.get(1).getCode().equals("jablko"));
        assertTrue(log.get(2).getCode().equals("jablko"));
        
        assertTrue(log.get(0).getName().equals("Jablko"));
        assertTrue(log.get(1).getName().equals("Jablko"));
        assertTrue(log.get(2).getName().equals("Jablko"));
        
        assertTrue(log.get(0).getQuantityChange() == 10);
        assertTrue(log.get(1).getQuantityChange() == -5);
        assertTrue(log.get(2).getQuantityChange() == -5);
    }
}
