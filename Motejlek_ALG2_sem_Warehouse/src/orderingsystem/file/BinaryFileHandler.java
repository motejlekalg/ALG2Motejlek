package orderingsystem.file;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import orderingsystem.app.Item;
import orderingsystem.app.LogEntry;

/**
 *
 * @author Martin Motejlek
 */
public class BinaryFileHandler extends FileHandler {

    @Override
    public Collection<Item> loadItems(File file) throws IOException {
        Collection<Item> items = new ArrayList<>();
        try (DataInputStream dis =
                new DataInputStream(new FileInputStream(file))) {
            boolean isEOF = false;
            while (!isEOF) {
                try {
                    String code = dis.readUTF();
                    String name = dis.readUTF();
                    int quantity = dis.readInt();
                    
                    Item item = new Item(code, name, quantity);
                    items.add(item);                    
                } catch (EOFException e) {
                    isEOF = true;
                }
            }
        }
        return items;
    }

    @Override
    public Collection<LogEntry> loadTransactionLog(File file)
            throws IOException {
        Collection<LogEntry> log = new ArrayList<>();
        try (DataInputStream dis =
                new DataInputStream(new FileInputStream(file))) {
            boolean isEOF = false;
            while (!isEOF) {
                try {
                    long epochDay = dis.readLong();
                    long seconds = dis.readInt();
                    String itemCode = dis.readUTF();
                    String itemName = dis.readUTF();
                    int quantityChange = dis.readInt();
                    
                    LocalDateTime timestamp =
                            LocalDateTime.of(
                                    LocalDate.ofEpochDay(epochDay),
                                    LocalTime.ofSecondOfDay(seconds)
                            );
                    LogEntry entry = new LogEntry(timestamp, itemCode, itemName,
                            quantityChange);
                    log.add(entry);                    
                } catch (EOFException e) {
                    isEOF = true;
                }
            }
        }
        return log;
    }

    @Override
    public void writeItems(File file, Collection<Item> items)
            throws IOException {
        try (DataOutputStream dos =
                new DataOutputStream(new FileOutputStream(file))) {
            for (Item item : items) {
                dos.writeUTF(item.getCode());
                dos.writeUTF(item.getName());
                dos.writeInt(item.getQuantity());
            }
        }
    }

    @Override
    public void writeTransactionLog(File file, Collection<LogEntry> log)
            throws IOException {
        try (DataOutputStream dos =
                new DataOutputStream(new FileOutputStream(file))) {
            for (LogEntry entry : log) {
                dos.writeLong(entry.getTimestamp().toLocalDate().toEpochDay());
                dos.writeInt(entry.getTimestamp().toLocalTime().toSecondOfDay());
                dos.writeUTF(entry.getItemCode());
                dos.writeUTF(entry.getItemName());
                dos.writeInt(entry.getQuantityChange());
            }
        }
    }

}
