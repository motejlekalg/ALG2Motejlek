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
import orderingsystem.utils.listgenerator.SortItemsByQuantity;

/**
 * Application UI.
 * @author Martin Motejlek
 */
public class Main {

    private static final String INPUT_ENCODING = "Windows-1250";
    
    private static Scanner sc = new Scanner(System.in, INPUT_ENCODING);
    private static Warehouse warehouse = new Warehouse();

    /**
     * Starts the application.
     * @param args unused
     */
    public static void main(String[] args) {
        inputLoop();
    }

    private static void inputLoop() {
        boolean keepRunning = true;
        System.out.println("Zadejte příkaz \"help\" pro vypsání nápovědy.");
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
                help();
                break;
            case "exit":
                keepRunning = false;
                break;
            default:
                System.out.println("Neznámý příkaz.");
        }
        return keepRunning;
    }

    private static void viewItem(Queue<String> command) {
        if (command.peek() == null) {
            System.out.println("Nedostatek parametrů.");
            return;
        }
        String code = command.poll();
        if (command.peek() != null) {
            System.out.println("Příliš mnoho parametrů.");
            return;
        }
        Item item;
        try {
            item = warehouse.getItem(code);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println(OutputGenerator.genItemRow(item));
    }

    private static void viewItems(Queue<String> command) {
        ListGenerator<Item> listGen = new ListGenerator<>();
        if (command.peek() != null) {
            switch (command.poll()) {
                case "sort-by-quantity":
                    listGen.add(new SortItemsByQuantity());
                    break;
                default:
                    System.out.println("Neplatný parametr.");
                    return;
            }
        }
        if (command.peek() != null) {
            System.out.println("Příliš mnoho parametrů.");
            return;
        }
        System.out.println(
                OutputGenerator.genItemsTable(listGen.generate(warehouse.getItemList()))
        );
    }

    private static void viewLog(Queue<String> command) {
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
        if (command.peek() != null) {
            System.out.println("Příliš mnoho parametrů.");
            return;
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
            System.out.println(e.getMessage());
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

    private static void help() {
        System.out.println("Seznam příkazů:");
        System.out.println("load <název> <koncovka>");
        System.out.println("    Načte soubory <název>.items.<koncovka> a <název>.log.<koncovka>.");
        System.out.println("save <název> <koncovka>");
        System.out.println("    Uloží soubory <název>.items.<koncovka> a <název>.log.<koncovka>.");
        System.out.println("item <kód>");
        System.out.println("    Vypíše informace o zvolené položce.");
        System.out.println("items [sort-by-quantity]");
        System.out.println("    Vypíše informace o všech položkách setříděné abecedně podle názvu.");
        System.out.println("    sort-by-quantity : setřídí vzestupně podle počtu kusů");
        System.out.println("log [filter-code <kód>]");
        System.out.println("    Vypíše log transakcí setříděný vzestupně podle času.");
        System.out.println("    filter-code : vypíše pouze transakce položky se zadaným kódem");
        System.out.println("add <kód> <název>");
        System.out.println("    Zaeviduje novou položku do skladu. Název může být víceslovný.");
        System.out.println("change <kód> <změna množství>");
        System.out.println("    Provede transakci. Záporné množství pro transakci směrem ze skladu,");
        System.out.println("    kladné množství pro transakci směrem do skladu.");
        System.out.println("help");
        System.out.println("    Vypíše tuto nápovědu.");
    }
    
}
