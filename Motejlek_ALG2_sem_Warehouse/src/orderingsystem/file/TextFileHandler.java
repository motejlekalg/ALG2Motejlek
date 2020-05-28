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
import java.util.List;
import orderingsystem.app.Item;
import orderingsystem.app.LogEntry;
import orderingsystem.utils.CorruptedDataException;

/**
 * A class which loads and saves warehouse-related data using text files.
 * 
 * @author Martin Motejlek
 */
public class TextFileHandler extends FileHandler {

    private static final DateTimeFormatter DTF_LOG_DATE
            = DateTimeFormatter.ofPattern("uuuu-MM-dd");
    private static final DateTimeFormatter DTF_LOG_TIME
            = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    public List<Item> loadItems(File file) throws IOException {
        List<Item> itemList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("[\\s]+", 3);
                int quantity = Integer.parseInt(parts[0]);
                String code = parts[1];
                String name = parts[2];

                Item item = new Item(code, name, quantity);
                itemList.add(item);
            }
        } catch (NumberFormatException e) {
            throw new CorruptedDataException(e.toString());
        }
        return itemList;
    }

    @Override
    public List<LogEntry> loadLog(File file) throws IOException {
        List<LogEntry> log = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("[\\s]+", 5);
                LocalDateTime timestamp
                        = LocalDateTime.of(
                                LocalDate.parse(parts[0], DTF_LOG_DATE),
                                LocalTime.parse(parts[1], DTF_LOG_TIME)
                        );
                int quantityChange = Integer.parseInt(parts[2]);
                String code = parts[3];
                String name = parts[4];

                LogEntry entry = new LogEntry(timestamp, code, name,
                        quantityChange);
                log.add(entry);
            }
        } catch (NumberFormatException e) {
            throw new CorruptedDataException(e.toString());
        }
        return log;
    }
    
    @Override
    public void writeItems(File file, List<Item> itemList) throws IOException {
        try (PrintWriter pw
                = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
            for (Item item : itemList) {
                pw.format("%d %s %s%n",
                        item.getQuantity(),
                        item.getCode(),
                        item.getName());
            }
        }
    }

    @Override
    public void writeLog(File file, List<LogEntry> log) throws IOException {
        try (PrintWriter pw
                = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
            for (LogEntry entry : log) {
                pw.format("%s %s %+d %s %s%n",
                        entry.getTimestamp().format(DTF_LOG_DATE),
                        entry.getTimestamp().format(DTF_LOG_TIME),
                        entry.getQuantityChange(),
                        entry.getCode(),
                        entry.getName()
                );
            }
        }
    }

}
