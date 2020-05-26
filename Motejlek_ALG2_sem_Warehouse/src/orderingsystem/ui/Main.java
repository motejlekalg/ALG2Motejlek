package orderingsystem.ui;

import java.util.Scanner;
import orderingsystem.app.Warehouse;
import orderingsystem.utils.listgenerator.DefaultSort;
import orderingsystem.utils.listgenerator.ListGenerator;

/**
 *
 * @author Martin Motejlek
 */
public class Main {

    private static Scanner sc = new Scanner(System.in);
    private static Warehouse warehouse = new Warehouse();

    public static void main(String[] args) {
        warehouse.addItemType("JBC", "Jablko");
        warehouse.addItemType("JBDfffffff", "Hruška");
        warehouse.addItemType("Z", "Hrueka");
        warehouse.commitTransaction("JBC", 5, "ahoj");
        //app.getWarehouse().remove("JBD");
        System.out.println(OutputGenerator.genWarehouseTable(warehouse.getWarehouseList(new ListGenerator().add(new DefaultSort()))));
        System.out.println();
        System.out.println(OutputGenerator.genLogTable(warehouse.getLog()));
    }
    
    

}
