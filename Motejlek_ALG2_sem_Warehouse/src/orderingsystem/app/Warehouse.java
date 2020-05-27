package orderingsystem.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import orderingsystem.utils.CorruptedDataException;

/**
 * A class representing a warehouse with a transaction log.
 *
 * @author Martin Motejlek
 */
public class Warehouse {

    private Map<String, Item> items;
    private List<LogEntry> log;

    /**
     * Warehouse constructor. Creates an empty warehouse.
     */
    public Warehouse() {
        items = new HashMap<>();
        log = new ArrayList<>();
    }

    /**
     * Warehouse constructor.
     *
     * @param itemList initial items
     * @param log initial log entries
     */
    public Warehouse(List<Item> itemList, List<LogEntry> log) {
        this.items = new HashMap<>();
        for (Item item : itemList) {
            Item copy = new Item(item);
            if (this.items.putIfAbsent(copy.getCode(), copy) != null) {
                throw new CorruptedDataException(
                        "Kód \"" + copy.getCode()
                        + "\" odkazuje na více položek.");
            }
        }
        this.log = new ArrayList<>(log);
    }

    /**
     * Introduces a new type of item to the warehouse.
     *
     * @param code item code used as identificator
     * @param name name of the item
     * @return true if a new type of an item was added, false otherwise
     */
    public boolean addItem(String code, String name) {
        Item entry = new Item(code, name);
        if (items.putIfAbsent(entry.getCode(), entry) == null) {
            return true;
        }
        return false;
    }

    /**
     * Commits a transaction. Modifies quantities of items in the warehouse and
     * generates a log entry.
     *
     * @param code identificator of the item
     * @param quantityChange change of quantity of the item
     * @return true if the transacaction was successful, false otherwise
     */
    public boolean commitTransaction(String code, int quantityChange) {
        Item item = items.get(code);
        if (item == null) {
            throw new IllegalArgumentException("Kód \"" + code + "\" neodkazuje na žádnou položku.");
        }

        if (quantityChange > 0) {
            item.increaseQuantity(quantityChange);
        } else if (quantityChange < 0) {
            if (!item.reduceQuantity(-quantityChange)) {
                return false;
            }
        } else {
            throw new IllegalArgumentException("Změna počtu kusů musí být různá od 0.");
        }

        log.add(new LogEntry(item.getCode(), item.getName(), quantityChange));
        return true;
    }

    public Item getItem(String code) {
        Item item = items.get(code);
        if (item == null) {
            throw new IllegalArgumentException("Kód \"" + code + "\" neodkazuje na žádnou položku.");
        }
        return new Item(item);
    }

    public List<Item> getItemList() {
        List<Item> itemList = new ArrayList<>();
        for (Item item : items.values()) {
            itemList.add(new Item(item));
        }
        Collections.sort(itemList);
        return itemList;
    }

    public List<LogEntry> getLog() {
        return new ArrayList(log);
    }

}
