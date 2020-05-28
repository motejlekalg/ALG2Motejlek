
import orderingsystem.app.Item;
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
public class ItemTest {
    
    public ItemTest() {
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
        Item a = new Item("kód", "Velmi dlouhé jméno");
        Item b = new Item("CODE", "name", 0);
        Item c = new Item("5", "42", 5);
        
        assertTrue(a.getCode().equals("kód"));
        assertTrue(b.getCode().equals("CODE"));
        assertTrue(c.getCode().equals("5"));
        
        assertTrue(a.getName().equals("Velmi dlouhé jméno"));
        assertTrue(b.getName().equals("name"));
        assertTrue(c.getName().equals("42"));
        
        assertTrue(a.getQuantity() == 0);
        assertTrue(b.getQuantity() == 0);
        assertTrue(c.getQuantity() == 5);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void spaceInCode() {
        new Item("kód s mezerami", "neprojde");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void emptyCode() {
        new Item("", "neprojde");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void emptyName() {
        new Item("neprojde", "");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void emptyAll() {
        new Item("", "");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void nullCode() {
        new Item(null, "neprojde");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void nullName() {
        new Item("neprojde", null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void nullAll() {
        new Item(null, null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void spaceInCodeQ() {
        new Item("kód s mezerami", "neprojde", 2);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void emptyCodeQ() {
        new Item("", "neprojde", 10);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void emptyNameQ() {
        new Item("neprojde", "", 100);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void emptyAllQ() {
        new Item("", "", 5);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void nullCodeQ() {
        new Item(null, "neprojde", 4);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void nullNameQ() {
        new Item("neprojde", null, 0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void nullAllQ() {
        new Item(null, null, 3);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void NegativeQuantity() {
        new Item("neprojde", "Vůbec", -5);
    }
    
}
