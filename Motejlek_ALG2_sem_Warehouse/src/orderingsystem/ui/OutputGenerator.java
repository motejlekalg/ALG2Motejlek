package orderingsystem.ui;

import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import orderingsystem.app.Item;
import orderingsystem.app.LogEntry;

/**
 *
 * @author Martin Motejlek
 */
public class OutputGenerator {

    private static final DateTimeFormatter DTF_TIMESTAMP = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");

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

    public static String genItemRow(Item item) {
        return String.format(
                "%-10s %5d %s",
                item.getCode(),
                item.getQuantity(),
                item.getName()
        );
    }

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
