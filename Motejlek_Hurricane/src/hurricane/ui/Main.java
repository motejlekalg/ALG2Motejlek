package hurricane.ui;

import hurricane.app.App;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Martin Motejlek
 */
public class Main {

    private static final String PATH = "hurricanedata.txt";
            
    private static final Scanner sc = new Scanner(System.in);
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        App app;
        try {
            app = new App(PATH);
        } catch (IOException e) {
            System.out.println("Načtení dat se nezdařilo.");
            return;
        }
        
        System.out.println("Zadejte roky od, do:");
        int yearStart = sc.nextInt();
        int yearEnd = sc.nextInt();
        System.out.println(app.viewListInTimeRange(yearStart, yearEnd));
        
        System.out.println();
        
        System.out.println("Zadjte název hurikánu:");
        String name = sc.next();
        System.out.println(app.viewSpeedCatOf(name));
        
        System.out.println();
        
        System.out.println(app.viewSortedBySpeed());
    }
    
}
