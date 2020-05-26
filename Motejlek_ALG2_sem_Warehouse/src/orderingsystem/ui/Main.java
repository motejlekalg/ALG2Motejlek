package orderingsystem.ui;

import java.util.Scanner;
import orderingsystem.app.Warehouse;
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
            String[] command = getInput();
            if (command.length == 0) {
                unknown();
            } else {
                switch (command[0]) {
                    case "view":
                        view(command);
                        break;
                    case "add":
                        add(command);
                        break;
                    case "transaction":
                        transaction(command);
                        break;
                    case "exit":
                        keepRunning = false;
                        break;
                    default:
                        unknown();
                }
            }
        }
    }
    
    private static String[] getInput() {
        System.out.print("> ");
        return sc.nextLine().split("[\\s]+");
    }
    
    private static void view(String[] command) {
        if (command.length <= 1) {
            unknown();
        } else {
            switch (command[1]) {
                case "items":
                    viewItems(command);
                    break;
                case "log":
                    viewLog(command);
                    break;
                default:
                    unknown();
            }
        }
    }
    
    private static void viewItems(String[] command) {
        System.out.println(OutputGenerator.genItemsTable(warehouse.getItemList(new ListGenerator<>())));
    }
    
    private static void viewLog(String[] command) {
        System.out.println(OutputGenerator.genLogTable(warehouse.getLog(new ListGenerator<>())));
    }
    
    private static void add(String[] command) {
        if (command.length <= 2) {
            unknown();
        } else {
            try {
                if (warehouse.addItemType(command[1], command[2])) {
                    System.out.println("Položka přidána.");
                } else {
                    System.out.println("Položka s daným kódem se již ve skladu nachází.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Neplatné parametry.");
            }
        }
    }

    private static void transaction(String[] command) {
        if (command.length <= 2) {
            unknown();
        } else {
            int quantityChange;
            try {
                quantityChange = Integer.parseInt(command[2]);
            } catch (NumberFormatException e) {
                System.out.println("Neplaný počet.");
                return;
            }
            try {
                if (warehouse.commitTransaction(command[1], quantityChange)) {
                    System.out.println("Transakce provedena.");
                } else {
                    System.out.println("Nelze provést transakci.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Neplatné parametry.");
            }
        }
    }

    private static void unknown() {
        System.out.println("Neplatný příkaz.");
    }

}
