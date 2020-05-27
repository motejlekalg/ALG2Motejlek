package orderingsystem.ui;

import orderingsystem.file.WarehouseLoaderSaver;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import orderingsystem.app.Item;
import orderingsystem.app.LogEntry;
import orderingsystem.app.Warehouse;
import orderingsystem.utils.CorruptedDataException;
import orderingsystem.utils.UnsupportedExtensionException;
import orderingsystem.utils.listgenerator.FilterLogByCode;
import orderingsystem.utils.listgenerator.ListGenerator;

/**
 *
 * @author Martin Motejlek
 */
public class Main {

    private static Scanner sc = new Scanner(System.in);
    private static Warehouse warehouse = new Warehouse();

    public static void main(String[] args) {
        inputLoop();
    }

    private static void inputLoop() {
        boolean keepRunning = true;
        while (keepRunning) {
            Queue<String> command = getInput();
            keepRunning = execute(command);
        }
    }

    private static Queue<String> getInput() {
        System.out.print("> ");
        return new LinkedList<>(Arrays.asList(sc.nextLine().split("[\\s]+")));
    }

    private static boolean execute(Queue<String> command) {
        boolean keepRunning = true;
        switch (command.poll()) {
            case "add":
                add(command);
                break;
            case "item":
                viewItem(command);
                break;
            case "items":
                viewItems(command);
                break;
            case "log":
                viewLog(command);
                break;
            case "change":
                transaction(command);
                break;
            case "load":
                load(command);
                break;
            case "save":
                save(command);
                break;
            case "help":
                help(command);
                break;
            case "exit":
                keepRunning = false;
                break;
            default:
                System.out.println("Neznámý příkaz.");
        }
        return keepRunning;
    }

    public static void viewItem(Queue<String> command) {
        if (command.peek() == null) {
            System.out.println("Nedostatek parametrů.");
            return;
        }
        String code = command.poll();
        Item item;
        try {
            item = warehouse.getItem(code);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println(OutputGenerator.genItemRow(item));
    }

    public static void viewItems(Queue<String> command) {
        ListGenerator<Item> listGen = new ListGenerator<>();
        System.out.println(
                OutputGenerator.genItemsTable(listGen.generate(warehouse.getItemList()))
        );
    }

    public static void viewLog(Queue<String> command) {
        ListGenerator<LogEntry> listGen = new ListGenerator<>();
        if (command.peek() != null) {
            switch (command.poll()) {
                case "filter-code":
                    if (command.peek() != null) {
                        listGen.add(new FilterLogByCode(command.poll()));
                    } else {
                        System.out.println("Nedosatek parametrů.");
                        return;
                    }
                    break;
                default:
                    System.out.println("Neplatný parametr.");
                    return;
            }
        }
        System.out.println(
                OutputGenerator.genLogTable(listGen.generate(warehouse.getLog()))
        );
    }

    private static void add(Queue<String> command) {
        if (command.size() < 2) {
            System.out.println("Nedostatek parametrů.");
            return;
        }
        String code = command.poll();
        StringBuilder name = new StringBuilder();
        while (command.peek() != null) {
            name.append(command.poll());
            if (command.peek() != null) {
                name.append(" ");
            }
        }
        try {
            if (warehouse.addItem(code, name.toString())) {
                System.out.println("Položka přidána.");
            } else {
                System.out.println("Položka s daným kódem se již ve skladu nachází.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void transaction(Queue<String> command) {
        if (command.size() != 2) {
            System.out.println("Špatný počet parametrů.");
            return;
        }
        String code = command.poll();
        String quantityChangeString = command.poll();
        int quantityChange;
        try {
            quantityChange = Integer.parseInt(quantityChangeString);
        } catch (NumberFormatException e) {
            System.out.println("Změna počtu kusů má být celé číslo.");
            return;
        }
        try {
            if (warehouse.commitTransaction(code, quantityChange)) {
                System.out.println("Transakce provedena.");
            } else {
                System.out.println("Ve skladu není dostatečný počet kusů pro provedení transakce.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }
    }

    private static void load(Queue<String> command) {
        if (command.size() != 2) {
            System.out.println("Špatný počet parametrů.");
            return;
        }

        String name = command.poll();
        String extension = command.poll();

        try {
            warehouse = WarehouseLoaderSaver.load(name, extension);
        } catch (UnsupportedExtensionException e) {
            System.out.println(e.getMessage());
        } catch (CorruptedDataException e) {
            System.out.println("Soubor neobsahuje platná data.");
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            System.out.println("Zadaný soubor neexistuje.");
        } catch (IOException e) {
            System.out.println("Chyba při čtení.");
        }
    }

    private static void save(Queue<String> command) {
        if (command.size() != 2) {
            System.out.println("Špatný počet parametrů.");
            return;
        }

        String name = command.poll();
        String extension = command.poll();

        try {
            WarehouseLoaderSaver.save(name, extension, warehouse);
        } catch (UnsupportedExtensionException e) {
            System.out.println("Nepodporovaná koncovka souboru.");
        } catch (IOException e) {
            System.out.println("Chyba při zápisu.");
        }
    }

    private static void help(Queue<String> command) {
        
    }

}
