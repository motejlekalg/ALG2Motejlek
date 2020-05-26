package orderingsystem.file;

import java.io.File;
import java.util.Collection;
import orderingsystem.app.Item;
import orderingsystem.app.LogEntry;

/**
 *
 * @author Martin Motejlek
 */
public abstract class FileHandler {
    
    public abstract Collection<Item> loadItems(File file);
    public abstract Collection<LogEntry> loadTransactionLog(File file);
    public abstract void writeItems(File file, Collection<Item> items);
    public abstract void writeTransactionLog(File file, Collection<LogEntry> log);
    
}
