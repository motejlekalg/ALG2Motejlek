
import java.time.LocalDateTime;
import java.time.Month;
import orderingsystem.app.LogEntry;
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
public class LogEntryTest {
    
    public LogEntryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void standardValues() {
        LogEntry a = new LogEntry("kód", "Název", 5);
        LogEntry b = new LogEntry("Kód", "Dlouhý název", -10);
        LogEntry c = new LogEntry("7605abc", "42 trpaslíků", -30);
        
        assertTrue(a.getCode().equals("kód"));
        assertTrue(b.getCode().equals("Kód"));
        assertTrue(c.getCode().equals("7605abc"));
        
        assertTrue(a.getName().equals("Název"));
        assertTrue(b.getName().equals("Dlouhý název"));
        assertTrue(c.getName().equals("42 trpaslíků"));
        
        assertTrue(a.getQuantityChange() == 5);
        assertTrue(b.getQuantityChange() == -10);
        assertTrue(c.getQuantityChange() == -30);
    }
    
    @Test
    public void standardValuesTime() {
        LocalDateTime timeA = LocalDateTime.of(2020, Month.MAY, 28, 23, 0, 56);
        LocalDateTime timeB = LocalDateTime.of(2017, Month.DECEMBER, 12, 10, 35, 0);
        LocalDateTime timeC = LocalDateTime.of(2021, Month.JANUARY, 1, 7, 20, 12);
        
        LogEntry a = new LogEntry(timeA, "kód", "Název", 5);
        LogEntry b = new LogEntry(timeB, "Kód", "Dlouhý název", -10);
        LogEntry c = new LogEntry(timeC, "7605abc", "42 trpaslíků", -30);
        
        assertTrue(a.getCode().equals("kód"));
        assertTrue(b.getCode().equals("Kód"));
        assertTrue(c.getCode().equals("7605abc"));
        
        assertTrue(a.getName().equals("Název"));
        assertTrue(b.getName().equals("Dlouhý název"));
        assertTrue(c.getName().equals("42 trpaslíků"));
        
        assertTrue(a.getQuantityChange() == 5);
        assertTrue(b.getQuantityChange() == -10);
        assertTrue(c.getQuantityChange() == -30);
        
        assertTrue(a.getTimestamp().equals(timeA));
        assertTrue(b.getTimestamp().equals(timeB));
        assertTrue(c.getTimestamp().equals(timeC));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void nullCode() {
        new LogEntry(null, "neprojde", -10);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void nullName() {
        new LogEntry("neprojde", null, 100);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void nullAll() {
        new LogEntry(null, null, 30);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void nullCodeT() {
        LocalDateTime time = LocalDateTime.of(2020, Month.MAY, 28, 23, 0, 56);
        new LogEntry(time, null, "neprojde", -10);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void nullNameT() {
        LocalDateTime time = LocalDateTime.of(2020, Month.MAY, 28, 23, 0, 56);
        new LogEntry(time, "neprojde", null, 100);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void nullAllT() {
        LocalDateTime time = LocalDateTime.of(2020, Month.MAY, 28, 23, 0, 56);
        new LogEntry(time, null, null, 30);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void nullTime() {
        new LogEntry(null, "neprojde", "vůbec", 70);
    }
    
}
