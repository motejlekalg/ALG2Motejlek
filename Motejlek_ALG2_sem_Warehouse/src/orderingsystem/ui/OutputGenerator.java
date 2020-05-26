package orderingsystem.ui;

import java.time.format.DateTimeFormatter;
import java.util.List;
import orderingsystem.app.Item;
import orderingsystem.app.LogEntry;

/**
 *
 * @author Martin Motejlek
 */
public class OutputGenerator {
    
    private static final DateTimeFormatter DTF_TIMESTAMP = DateTimeFormatter.ofPattern("uuuu-mm-dd HH:mm:ss");
    
    public static String genWarehouseTable(List<Item> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("Warehouse");
        for (Item item : list) {
            sb.append(String.format(
                    "%n%-10s %5d %s",
                    item.getCode(),
                    item.getQuantity(),
                    item.getName()
            ));
        }
        return sb.toString();
    }
    
    public static String genLogTable(List<LogEntry> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("Log");
        for (LogEntry entry : list) {
            sb.append(String.format(
                    "%n%s %+5d %s (%s)%n    Description: %s",
                    entry.getTimestamp().format(DTF_TIMESTAMP),
                    entry.getQuantityChange(),
                    entry.getItemName(),
                    entry.getItemCode(),
                    entry.getDescription()
            ));
        }
        return sb.toString();
    }
    
}
