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
    private final String description;
    
    public LogEntry(String itemCode, String itemName, int quantityChange, String description) {
        if (itemCode == null) {
            throw new IllegalArgumentException("Code of the item cannot be null.");
        }
        if (itemName == null) {
            throw new IllegalArgumentException("Name of the item cannot be null.");
        }
        if (description == null) {
            throw new IllegalArgumentException("Description cannot be null.");
        }
        
        this.timestamp = LocalDateTime.now();
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.quantityChange = quantityChange;
        this.description = description;
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

    public String getDescription() {
        return description;
    }
    
}
