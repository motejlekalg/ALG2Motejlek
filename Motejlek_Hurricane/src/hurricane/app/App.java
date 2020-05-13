package hurricane.app;

import java.io.FileNotFoundException;

/**
 *
 * @author Martin Motejlek
 */
public class App {

    HurricaneDataset data;
    
    public App(String path) throws FileNotFoundException {
        data = DataLoader.load(path);
    }
    
    public String viewListInTimeRange(int yearStart, int yearEnd) {
        return OutputGen.genTable(data.generateList(
                new FilterByYearRange(yearStart, yearEnd),
                new SortByTime()
        ));
    }
    
    public String viewSortedBySpeed() {
        return OutputGen.genTable(data.generateList(
                new SortBySpeed()
        ));
    }
    
    public String viewSpeedCatOf(String name) {
        Hurricane h = data.getEntryByName(name);
        if (h != null) {
            return OutputGen.speedCatString(h);
        } else {
            return "Hurikán nenalezen.";
        }
    }

}
