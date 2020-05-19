package competition.filehandling;

import competition.app.FinishStat;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Martin Motejlek
 */
public class BinaryReaderFinish extends ReaderFinish {

    @Override
    public List<FinishStat> loadData(String finishFilepath) throws FileNotFoundException, IOException {
        ArrayList<FinishStat> stats = new ArrayList<>();
        
        try (DataInputStream dis = new DataInputStream(new FileInputStream(finishFilepath))) {
            boolean isEnd = false;
            while(!isEnd) {
                try {
                    int number = dis.readInt();
                    LocalTime finishTime = LocalTime.ofNanoOfDay(dis.readLong());
                    stats.add(new FinishStat(number, finishTime));
                } catch (EOFException e) {
                    isEnd = true;
                }
            }
        }
        
        return stats;
    }
    
}
