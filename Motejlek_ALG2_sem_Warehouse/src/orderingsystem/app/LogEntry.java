package orderingsystem.app;

import java.time.LocalDateTime;

/**
 * A class representing a transaction log entry.
 *
 * @author Martin Motejlek
 */
public class LogEntry {

    private final LocalDateTime timestamp;
    private final String code;
    private final String name;
    private final int quantityChange;

    /**
     * Log entry constructor.
     *
     * @param timestamp the timestamp of the log entry
     * @param code the code of the item which was the subject of the transaction
     * @param name the name of the item which was the subject of the transaction
     * @param quantityChange the change in quantity
     */
    public LogEntry(LocalDateTime timestamp, String code, String name, int quantityChange) {
        if (code == null) {
            throw new IllegalArgumentException("Kód položky nesmí být null.");
        }
        if (name == null) {
            throw new IllegalArgumentException("Název položky nesmí být null.");
        }

        this.timestamp = timestamp;
        this.code = code;
        this.name = name;
        this.quantityChange = quantityChange;
    }

    /**
     * Log entry constructor. Sets the current time with precision to seconds as
     * the timestamp of the log entry.
     *
     * @param code the code of the item which was the subject of the transaction
     * @param name the name of the item which was the subject of the transaction
     * @param quantityChange the change in quantity
     */
    public LogEntry(String code, String name, int quantityChange) {
        this(LocalDateTime.now().withNano(0), code, name, quantityChange);
    }

    /**
     * Returns the timestamp of the log entry.
     *
     * @return timestamp of the log entry
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Returns the code of the item which was the subject of the transaction.
     *
     * @return the code of the item
     */
    public String getCode() {
        return code;
    }

    /**
     * Returns the name of the item which was the subject of the transaction.
     *
     * @return the name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the change in quantity.
     *
     * @return the change in quantity
     */
    public int getQuantityChange() {
        return quantityChange;
    }

}
