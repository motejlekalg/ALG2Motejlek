package orderingsystem.app;

import java.time.LocalDateTime;

/**
 *
 * @author Martin Motejlek
 */
public class LogEntry {
    
    private final LocalDateTime timestamp;
    private final String itemCode;
    private final String itemName;
    private final int quantityChange;
    
    public LogEntry(LocalDateTime timestamp, String itemCode, String itemName, int quantityChange) {
        if (itemCode == null) {
            throw new IllegalArgumentException("Code of the item cannot be null.");
        }
        if (itemName == null) {
            throw new IllegalArgumentException("Name of the item cannot be null.");
        }
        
        this.timestamp = timestamp;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.quantityChange = quantityChange;
    }
    
    public LogEntry(String itemCode, String itemName, int quantityChange) {
        this(LocalDateTime.now().withNano(0), itemCode, itemName, quantityChange);
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getItemCode() {
        return itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantityChange() {
        return quantityChange;
    }
    
}
