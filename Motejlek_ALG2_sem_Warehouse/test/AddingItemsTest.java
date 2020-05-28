
import java.util.List;
import orderingsystem.app.Item;
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
public class AddingItemsTest {
    
    private Warehouse w;
    
    public AddingItemsTest() {
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
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void standardValues() {
        //2
        assertTrue(w.addItem("Hruška", "hruška"));
        //1
        assertTrue(w.addItem("cibule", "Cibule"));
        //5
        assertTrue(w.addItem("sd-zidle", "Stará dřevěná židle"));
        //3        
        assertTrue(w.addItem("JABLKO", "Jablko"));
        //4
        assertTrue(w.addItem("5", "pět"));
        
        List<Item> list = w.getItemList();
        assertTrue(list.size() == 5);
        
        for (int i = 0; i < list.size(); i++) {
            assertTrue(list.get(i).getQuantity() == 0);
        }
        
        assertTrue(w.getItem("cibule").getCode().equals("cibule"));
        assertTrue(w.getItem("cibule").getName().equals("Cibule"));
        assertTrue(w.getItem("Hruška").getCode().equals("Hruška"));
        assertTrue(w.getItem("Hruška").getName().equals("hruška"));
        assertTrue(w.getItem("JABLKO").getCode().equals("JABLKO"));
        assertTrue(w.getItem("JABLKO").getName().equals("Jablko"));
        assertTrue(w.getItem("5").getCode().equals("5"));
        assertTrue(w.getItem("5").getName().equals("pět"));
        assertTrue(w.getItem("sd-zidle").getCode().equals("sd-zidle"));
        assertTrue(w.getItem("sd-zidle").getName().equals("Stará dřevěná židle"));
    }
    
    @Test
    public void repeatedValues() {
        assertTrue(w.addItem("CODE", "nazev neceho"));
        assertFalse(w.addItem("CODE", "nazev neceho"));
        assertFalse(w.addItem("CODE", "nazev neceho jineho"));
        assertTrue(w.addItem("CODE2", "nazev neceho"));
        
        List<Item> list = w.getItemList();
        assertTrue(list.size() == 2);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void spaceInCode() {
        w.addItem("kód s mezerami", "neprojde");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void emptyCode() {
        w.addItem("", "neprojde");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void emptyName() {
        w.addItem("neprojde", "");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void emptyAll() {
        w.addItem("", "");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void nullCode() {
        w.addItem(null, "neprojde");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void nullName() {
        w.addItem("neprojde", null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void nullAll() {
        w.addItem(null, null);
    }
    
}
