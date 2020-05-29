package competition.filehandling;

import competition.app.FinishStat;
import competition.app.Runner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Martin Motejlek
 */
public class TextReaderFinish extends ReaderFinish {

    @Override
    public List<FinishStat> loadData(String finishFilepath) throws FileNotFoundException, IOException {
        ArrayList<FinishStat> stats = new ArrayList<>();
        
        // nacitani pomoci BufferedReader
        try (BufferedReader inFinish = new BufferedReader(new FileReader(
                DataSource.convertPath(finishFilepath)))) {
            String line;
            while ((line = inFinish.readLine()) != null) {
                String[] parts = line.split("[ ]+");
                int number = Integer.parseInt(parts[0]);
                LocalTime finishTime = LocalTime.parse(parts[1], Runner.dtfFinish);
                stats.add(new FinishStat(number, finishTime));
            }
        }
        
        return stats;
    }
    
}
