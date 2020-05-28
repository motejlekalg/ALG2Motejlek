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
import java.util.List;
import orderingsystem.app.Item;
import orderingsystem.app.LogEntry;

/**
 * A class which loads and saves warehouse-related data using binary files.
 * 
 * @author Martin Motejlek
 */
public class BinaryFileHandler extends FileHandler {

    @Override
    public List<Item> loadItems(File file) throws IOException {
        List<Item> itemList = new ArrayList<>();
        try (DataInputStream dis
                = new DataInputStream(new FileInputStream(file))) {
            boolean isEOF = false;
            while (!isEOF) {
                try {
                    String code = dis.readUTF();
                    String name = dis.readUTF();
                    int quantity = dis.readInt();

                    Item item = new Item(code, name, quantity);
                    itemList.add(item);
                } catch (EOFException e) {
                    isEOF = true;
                }
            }
        }
        return itemList;
    }

    @Override
    public List<LogEntry> loadLog(File file) throws IOException {
        List<LogEntry> log = new ArrayList<>();
        try (DataInputStream dis
                = new DataInputStream(new FileInputStream(file))) {
            boolean isEOF = false;
            while (!isEOF) {
                try {
                    long epochDay = dis.readLong();
                    long seconds = dis.readInt();
                    String code = dis.readUTF();
                    String name = dis.readUTF();
                    int quantityChange = dis.readInt();

                    LocalDateTime timestamp
                            = LocalDateTime.of(
                                    LocalDate.ofEpochDay(epochDay),
                                    LocalTime.ofSecondOfDay(seconds)
                            );
                    LogEntry entry = new LogEntry(timestamp, code, name,
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
    public void writeItems(File file, List<Item> itemList) throws IOException {
        try (DataOutputStream dos
                = new DataOutputStream(new FileOutputStream(file))) {
            for (Item item : itemList) {
                dos.writeUTF(item.getCode());
                dos.writeUTF(item.getName());
                dos.writeInt(item.getQuantity());
            }
        }
    }

    @Override
    public void writeLog(File file, List<LogEntry> log) throws IOException {
        try (DataOutputStream dos
                = new DataOutputStream(new FileOutputStream(file))) {
            for (LogEntry entry : log) {
                dos.writeLong(entry.getTimestamp().toLocalDate().toEpochDay());
                dos.writeInt(entry.getTimestamp().toLocalTime().toSecondOfDay());
                dos.writeUTF(entry.getCode());
                dos.writeUTF(entry.getName());
                dos.writeInt(entry.getQuantityChange());
            }
        }
    }

}
