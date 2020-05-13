package hurricane.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Month;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author Martin Motejlek
 */
public class DataLoader {
    
    private static final DateTimeFormatter DTF_MONTH
            = DateTimeFormatter.ofPattern("MMM", Locale.US);
    
    public static HurricaneDataset load(String path) throws FileNotFoundException {
        File source = new File(path);
        Scanner sc = new Scanner(source);
        LinkedList<Hurricane> list = new LinkedList<>();
        
        while(sc.hasNext()) {
            int year = sc.nextInt();
            String month = sc.next();
            double pressure = sc.nextDouble();
            double speed = sc.nextDouble();
            String name = sc.next();
            
            YearMonth time = YearMonth.of(year, Month.from(DTF_MONTH.parse(month)));
            
            list.add(new Hurricane(time,pressure, speed, name));
        }
        
        return new HurricaneDataset(list);
    } 
    
}
