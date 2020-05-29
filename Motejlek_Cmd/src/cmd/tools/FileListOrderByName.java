package cmd.tools;

import java.io.File;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Martin Motejlek
 */
public class FileListOrderByName implements FileListManipulator {

    @Override
    public void execute(List<File> files) {
        Collections.sort(files, (o1, o2) -> {
            return o1.getName().compareTo(o2.getName());
        });
    }
    
}
