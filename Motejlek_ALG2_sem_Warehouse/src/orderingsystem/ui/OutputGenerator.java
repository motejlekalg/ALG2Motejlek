package orderingsystem.ui;

import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import orderingsystem.app.Item;
import orderingsystem.app.LogEntry;

/**
 * A library class for generating tables of warehouse-related data.
 * 
 * @author Martin Motejlek
 */
public class OutputGenerator {

    private static final DateTimeFormatter DTF_TIMESTAMP = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");

    /**
     * Generates a table from a list of items.
     * @param list the list of items
     * @return the generated table
     */
    public static String genItemsTable(List<Item> list) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Seznam položek:%n"));
        for (Iterator<Item> iter = list.iterator(); iter.hasNext();) {
            Item item = iter.next();
            sb.append(genItemRow(item));
            if (iter.hasNext()) {
                sb.append(String.format("%n"));
            }
        }
        return sb.toString();
    }

    /**
     * Generates a table row from an item.
     * @param item the item
     * @return the generated table row
     */
    public static String genItemRow(Item item) {
        return String.format(
                "%-10s %5d %s",
                item.getCode(),
                item.getQuantity(),
                item.getName()
        );
    }

    /**
     * Generates a table from a list of log entries.
     * @param list the list of log entries
     * @return the generated table
     */
    public static String genLogTable(List<LogEntry> list) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Log:%n"));
        for (Iterator<LogEntry> iter = list.iterator(); iter.hasNext();) {
            LogEntry entry = iter.next();
            sb.append(genLogRow(entry));
            if (iter.hasNext()) {
                sb.append(String.format("%n"));
            }
        }
        return sb.toString();
    }

    /**
     * Generates a table row from a log entry.
     * @param entry the log entry
     * @return the generated table row
     */
    public static String genLogRow(LogEntry entry) {
        return String.format(
                "%s %+6d %-10s %s",
                entry.getTimestamp().format(DTF_TIMESTAMP),
                entry.getQuantityChange(),
                entry.getCode(),
                entry.getName()
        );
    }

}
