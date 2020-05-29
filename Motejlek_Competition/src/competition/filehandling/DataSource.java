package competition.filehandling;

import java.io.File;

/**
 *
 * @author Martin Motejlek
 */
public class DataSource {
    
    public static File DATA_DIR = new File(System.getProperty("user.dir"), "data");
    
    public static File convertPath(String path) {
        return new File(DATA_DIR, path);
    }
    
}
