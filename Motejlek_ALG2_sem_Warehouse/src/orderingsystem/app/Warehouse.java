package orderingsystem.app;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import orderingsystem.utils.NoSuchCodeException;
import orderingsystem.utils.listgenerator.ListGenerator;

/**
 *
 * @author Martin Motejlek
 */
public class Warehouse {

    private Map<String, Item> items;
    private List<LogEntry> log;

    public Warehouse() {
        items = new HashMap<>();
        log = new ArrayList<>();
    }

    public Warehouse(Collection<Item> items, Collection<LogEntry> log) {
        for (Item item : items) {
            Item copy = new Item(item);
            if (this.items.putIfAbsent(copy.getCode(), copy) != null) {
                throw new RuntimeException(
                        "Invalid source data: code \"" + copy.getCode()
                        + "\" is present multiple times.");
            };
        }
        this.log = new ArrayList<>(log);
    }

    public boolean addItemType(String code, String name) {
        Item entry = new Item(code, name);
        if (items.putIfAbsent(entry.getCode(), entry) == null) {
            return true;
        }
        return false;
    }

    public boolean commitTransaction(String itemCode, int quantityChange) {
        Item item = items.get(itemCode);
        if (item == null) {
            throw new NoSuchCodeException("Item with code \"" + itemCode + "\" does not exist.");
        }

        if (quantityChange > 0) {
            item.addQuantity(quantityChange);
        } else if (quantityChange < 0) {
            if (!item.subtractQuantity(-quantityChange)) {
                return false;
            }
        } else {
            throw new IllegalArgumentException("Quantity change must be different from zero.");
        }

        log.add(new LogEntry(item.getCode(), item.getName(), quantityChange));
        return true;
    }

    public List<Item> getItemList(ListGenerator<Item> listGen) {
        List<Item> copy = new ArrayList<>();
        for (Item item : items.values()) {
            copy.add(new Item(item));
        }
        return listGen.generate(copy);
    }

    public List<LogEntry> getLog(ListGenerator<Item> listGen) {
        return new ArrayList(log);
    }

}
