package orderingsystem.file;

import java.io.File;
import java.io.IOException;
import java.util.List;
import orderingsystem.app.Item;
import orderingsystem.app.LogEntry;
import orderingsystem.app.Warehouse;
import orderingsystem.utils.UnsupportedExtensionException;

/**
 * Static class for loading and saving warehouse state.
 * 
 * @author Martin Motejlek
 */
public class WarehouseLoaderSaver {

    private static final String DATA_DIR = "data";
    private static final String ITEMS_POSTFIX = ".items";
    private static final String LOG_POSTFIX = ".log";
    private static final String TEXT_EXTENSION = "txt";
    private static final String BIN_EXTENSION = "dat";

    private static File getItemsFile(String name, String extension) {
        return new File(DATA_DIR, name + ITEMS_POSTFIX + "." + extension);
    }

    private static File getLogFile(String name, String extension) {
        return new File(DATA_DIR, name + LOG_POSTFIX + "." + extension);
    }

    private static FileHandler getFileHandler(String extension) {
        switch (extension) {
            case TEXT_EXTENSION:
                return new TextFileHandler();
            case BIN_EXTENSION:
                return new BinaryFileHandler();
            default:
                throw new UnsupportedExtensionException(
                        "Přípona \"" + extension + "\" není podporována.");
        }
    }

    /**
     * Loads warehouse data from files
     * name.items.extension
     * name.log.extension
     * 
     * @param name
     * @param extension
     * @return an instance of Warehouse
     * @throws IOException 
     */
    public static Warehouse load(String name, String extension) throws IOException {
        FileHandler fh = getFileHandler(extension);

        File itemsFile = getItemsFile(name, extension);
        File logFile = getLogFile(name, extension);

        List<Item> itemList = fh.loadItems(itemsFile);
        List<LogEntry> log = fh.loadLog(logFile);

        return new Warehouse(itemList, log);
    }

    /**
     * Saves warehouse data to files
     * name.items.extension
     * name.log.extension
     * 
     * @param name
     * @param extension
     * @param warehouse an instance of Warehouse to save
     * @throws IOException 
     */
    public static void save(String name, String extension,
            Warehouse warehouse) throws IOException {
        FileHandler fh = getFileHandler(extension);

        File itemsFile = getItemsFile(name, extension);
        File logFile = getLogFile(name, extension);

        fh.writeItems(itemsFile, warehouse.getItemList());
        fh.writeLog(logFile, warehouse.getLog());
    }

}
