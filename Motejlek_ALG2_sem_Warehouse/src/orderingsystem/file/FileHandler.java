package orderingsystem.file;

import java.io.File;
import java.io.IOException;
import java.util.List;
import orderingsystem.app.Item;
import orderingsystem.app.LogEntry;

/**
 * An interface for classes loading and saving warehouse-related data.
 *
 * @author Martin Motejlek
 */
public abstract class FileHandler {

    /**
     * Loads a list of items from a specified file.
     *
     * @param file a file to load data from
     * @return the loaded list of items
     * @throws IOException
     */
    public abstract List<Item> loadItems(File file) throws IOException;

    /**
     * Loads a log from a specified file.
     *
     * @param file a file to load data from
     * @return the loaded log
     * @throws IOException
     */
    public abstract List<LogEntry> loadLog(File file) throws IOException;

    /**
     * Saves a list of items to a specified file.
     *
     * @param file the file to save to
     * @param itemList the list of items to save
     * @throws IOException
     */
    public abstract void writeItems(File file, List<Item> itemList) throws IOException;

    /**
     * Saves a log to a specified file.
     *
     * @param file the file to save to
     * @param log the log to save
     * @throws IOException
     */
    public abstract void writeLog(File file, List<LogEntry> log) throws IOException;

}
