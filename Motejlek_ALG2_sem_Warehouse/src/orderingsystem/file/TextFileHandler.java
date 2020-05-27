package orderingsystem.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import orderingsystem.app.Item;
import orderingsystem.app.LogEntry;
import orderingsystem.utils.CorruptedFileException;

/**
 *
 * @author Martin Motejlek
 */
public class TextFileHandler extends FileHandler {
    
    private static final DateTimeFormatter DTF_LOG_DATE =
            DateTimeFormatter.ofPattern("uuuu-MM-dd");
    private static final DateTimeFormatter DTF_LOG_TIME =
            DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    public Collection<Item> loadItems(File file) throws IOException {
        Collection<Item> items = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("[\\s]+", 3);
                int quantity = Integer.parseInt(parts[0]);
                String code = parts[1];
                String name = parts[2];

                Item item = new Item(code, name, quantity);
                items.add(item);
            }
        } catch (NumberFormatException e) {
            throw new CorruptedFileException(e.toString());
        }
        return items;
    }

    @Override
    public Collection<LogEntry> loadTransactionLog(File file)
            throws IOException {
        Collection<LogEntry> log = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("[\\s]+", 5);
                LocalDateTime timestamp =
                        LocalDateTime.of(
                                LocalDate.parse(parts[0], DTF_LOG_DATE),
                                LocalTime.parse(parts[1], DTF_LOG_TIME)
                        );
                int quantityChange = Integer.parseInt(parts[2]);
                String itemCode = parts[3];
                String itemName = parts[4];
                
                LogEntry entry = new LogEntry(timestamp,itemCode, itemName, 
                        quantityChange);
                log.add(entry);
            }
        } catch (NumberFormatException e) {
            throw new CorruptedFileException(e.toString());
        }
        return log;
    }

    @Override
    public void writeItems(File file, Collection<Item> items)
            throws IOException {
        try (PrintWriter pw =
                new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
            for (Item item : items) {
                pw.format("%d %s %s",
                        item.getQuantity(),
                        item.getCode(),
                        item.getName());
            }
        }
    }

    @Override
    public void writeTransactionLog(File file, Collection<LogEntry> log)
            throws IOException {
        try (PrintWriter pw =
                new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
            for (LogEntry entry : log) {
                pw.format("%s %s %+d %s %s",
                        entry.getTimestamp().format(DTF_LOG_DATE),
                        entry.getTimestamp().format(DTF_LOG_TIME),
                        entry.getQuantityChange(),
                        entry.getItemCode(),
                        entry.getItemName()
                );
            }
        }
    }
    
}
