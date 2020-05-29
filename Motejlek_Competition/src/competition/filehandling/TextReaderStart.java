package competition.filehandling;

import competition.app.Runner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Martin Motejlek
 */
public class TextReaderStart extends ReaderStart {

    @Override
    public List<Runner> loadData(String startFilepath) throws FileNotFoundException, IOException {
        // nacitani pomoci Scanneru
        List<Runner> runners = new ArrayList<>();
        
        File startFile = DataSource.convertPath(startFilepath);
        Scanner inStart = new Scanner(startFile);
        
        while(inStart.hasNext()) {
            int number = inStart.nextInt();
            String firstName = inStart.next();
            String lastName = inStart.next();
            String startTime = inStart.next();
            Runner r = new Runner(firstName, lastName, number);
            r.setStartTime(startTime);
            runners.add(r);
        }
        
        return runners;
    }
    
}
