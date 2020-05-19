package competition.filehandling;

import competition.app.FinishStat;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Martin Motejlek
 */
public abstract class ReaderFinish {
    
    public abstract List<FinishStat> loadData(String finishFilepath) throws FileNotFoundException, IOException;
    
}
