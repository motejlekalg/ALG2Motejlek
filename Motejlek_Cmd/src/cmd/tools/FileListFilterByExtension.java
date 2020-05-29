package cmd.tools;

import java.io.File;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Martin Motejlek
 */
public class FileListFilterByExtension implements FileListManipulator {

    private final String extension;
    
    public FileListFilterByExtension(String extension) {
        this.extension = extension;
    }
    
    @Override
    public void execute(List<File> files) {
        String regexp = ".*\\." + extension;
        Iterator<File> iter = files.iterator();
        while (iter.hasNext()) {
            File file = iter.next();
            if (file.isDirectory() || !file.getName().matches(regexp)) {
                iter.remove();
            }
        }
    }
    
}
