
import java.util.List;
import orderingsystem.app.Item;
import orderingsystem.app.Warehouse;
import orderingsystem.utils.listgenerator.DefaultSort;
import orderingsystem.utils.listgenerator.ListGenerator;
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
    public void StandardValues() {
        assertTrue(w.addItem("CIB", "cibule"));
        assertTrue(w.addItem("Á", "jablko"));
        assertTrue(w.addItem("itemCode", "Hruška"));
        assertTrue(w.addItem("125sdz", "stará dřevěná židle"));
        
        List<Item> list = new ListGenerator<>().add(new DefaultSort()).generate(w.getItemList());
        assertTrue(list.size() == 4);
    }
    
    @Test
    public void RepeatedValues() {
        assertTrue(w.addItem("CODE", "nazev neceho"));
        assertFalse(w.addItem("CODE", "nazev neceho"));
        assertFalse(w.addItem("CODE", "nazev neceho jineho"));
        assertTrue(w.addItem("CODE2", "nazev neceho"));
        
        List<Item> list = new ListGenerator<>().add(new DefaultSort()).generate(w.getItemList());
        assertTrue(list.size() == 2);
    }
    
}
