package orderingsystem.app;

/**
 * A class representing an item.
 * @author Martin Motejlek
 */
public class Item implements Comparable<Item> {
    
    private final String code;
    private final String name;
    private int quantity;
    
    public Item(String code, String name) {
        if (code == null) {
            throw new IllegalArgumentException("Code cannot be null.");
        }
        if (code.length() == 0) {
            throw new IllegalArgumentException("Code cannot be an empty string.");
        }
        
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null.");
        }
        if (name.length() == 0) {
            throw new IllegalArgumentException("Name cannot be an empty string.");
        }
        
        this.code = code;
        this.name = name;
    }
    
    public Item(Item item) {
        this.code = item.getCode();
        this.name = item.getName();
        this.quantity = item.getQuantity();
    }
    
    public void addQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
        this.quantity += quantity;
    }
    
    public boolean subtractQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
        if (this.quantity >= quantity) {
            this.quantity -= quantity;
            return true;
        } 
        return false;
    }
    
    public String getCode() {
        return code;
    }


    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public int compareTo(Item o) {
        return this.name.compareTo(o.name);
    }
    
}
