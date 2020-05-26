package orderingsystem.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import orderingsystem.utils.listgenerator.ListGenerator;

/**
 *
 * @author Martin Motejlek
 */
public class Warehouse {

    private Map<String, Item> items = new HashMap<>();
    private List<LogEntry> log = new ArrayList<>();

    public boolean addItemType(String code, String name) {
        Item entry = new Item(code, name);
        if (items.putIfAbsent(entry.getCode(), entry) == null) {
            return true;
        }
        return false;
    }
    
    public boolean removeItemType(String code) {
        if (items.containsKey(code)) {
            items.remove(code);
            return true;
        }
        return false;
    }
    
    public List<Item> getWarehouseList(ListGenerator<Item> listGen) {
        return listGen.generate(items.values());
    }
    
    public boolean commitTransaction(String itemCode, int quantityChange, String description) {
        Item item = items.get(itemCode);
        if (item == null) {
            return false;
        }
        
        if (quantityChange > 0) {
            item.addQuantity(quantityChange);
        } else if (quantityChange < 0) {
            if (!item.subtractQuantity(-quantityChange)) {
                return false;
            }
        } else {
            return false;
        }
        
        log.add(new LogEntry(item.getCode(), item.getName(), quantityChange, description));
        return true;
    }
    
    public List<LogEntry> getLog() {
        return new ArrayList(log);
    }

}
