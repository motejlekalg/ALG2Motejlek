package orderingsystem.file;

import java.io.File;
import java.io.IOException;
import java.util.List;
import orderingsystem.app.Item;
import orderingsystem.app.LogEntry;

/**
 *
 * @author Martin Motejlek
 */
public abstract class FileHandler {
    
    public abstract List<Item> loadItems(File file) throws IOException;
    public abstract List<LogEntry> loadLog(File file) throws IOException;
    public abstract void writeItems(File file, List<Item> itemList) throws IOException;
    public abstract void writeLog(File file, List<LogEntry> log) throws IOException;
    
}
