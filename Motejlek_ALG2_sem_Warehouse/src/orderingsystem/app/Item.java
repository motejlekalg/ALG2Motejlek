package orderingsystem.app;

/**
 * A class containing data about an item type.
 *
 * @author Martin Motejlek
 */
public class Item implements Comparable<Item> {

    private final String code;
    private final String name;
    private int quantity;

    /**
     * Item constructor.
     *
     * Code cannot be empty and it cannot contain whitespace. Name cannot be
     * empty. Quantity cannot be negative. Parameters cannot be null.
     *
     * @param code code of the item
     * @param name name of the item
     * @param quantity initial quantity of the item
     */
    public Item(String code, String name, int quantity) {
        if (code == null) {
            throw new IllegalArgumentException("Kód nesmí být null.");
        }
        if (code.length() == 0) {
            throw new IllegalArgumentException("Kód nesmí být prázdný řetězec.");
        }
        if (code.matches(".*[\\s]+.*")) {
            throw new IllegalArgumentException("Kód nesmí obsahovat bílé znaky.");
        }

        if (name == null) {
            throw new IllegalArgumentException("Název nesmí být null.");
        }
        if (name.length() == 0) {
            throw new IllegalArgumentException("Název nesmí být prázdný řetězec.");
        }
        
        if (quantity < 0) {
            throw new IllegalArgumentException("Množství nesmí být záporné.");
        }

        this.code = code;
        this.name = name;
        this.quantity = quantity;
    }

    /**
     * Item constructor. Initial quantity is 0.
     *
     * Code cannot be empty and it cannot contain whitespace. Name cannot be
     * empty. Parameters cannot be null.
     *
     * @param code code of the item
     * @param name name of the item
     */
    public Item(String code, String name) {
        this(code, name, 0);
    }

    /**
     * Item constructor. Creates a copy of an existing item.
     *
     * @param item an item to copy
     */
    public Item(Item item) {
        this.code = item.getCode();
        this.name = item.getName();
        this.quantity = item.getQuantity();
    }

    /**
     * Increases the quantity of the item. The change of quantity cannot be
     * negative.
     *
     * @param quantity an increase in quantity
     */
    public void increaseQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Množství nesmí být záporné.");
        }
        this.quantity += quantity;
    }

    /**
     * Reduces the quantity of the item unless the resulting quantity would be
     * negative. The change of quantity cannot be negative.
     *
     * @param quantity a decrease in quantity
     * @return true if successful, false otherwise
     */
    public boolean reduceQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Množství nesmí být záporné.");
        }
        if (this.quantity >= quantity) {
            this.quantity -= quantity;
            return true;
        }
        return false;
    }

    /**
     * Returns the code of this item.
     *
     * @return the code of this item
     */
    public String getCode() {
        return code;
    }

    /**
     * Returns the name of this item.
     *
     * @return the name of this item
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the quantity of this item.
     *
     * @return the quantity of this item.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Compares two instances of Item by name. Ignores case.
     *
     * @param o Item to compare
     * @return 0 if the names are lexicographically equal, a value less than 0
     * if the name of this item is lexicographically less, and a value greater
     * than 0 if the name of this item is lexicographically greater (all options
     * ignoring case)
     */
    @Override
    public int compareTo(Item o) {
        return this.name.compareToIgnoreCase(o.name);
    }

}
