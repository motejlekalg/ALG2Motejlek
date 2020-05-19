package competition.filehandling;

import competition.app.Runner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Martin Motejlek
 */
public abstract class ReaderStart {
    
    public abstract List<Runner> loadData(String startFilepath) throws FileNotFoundException, IOException;
    
}
