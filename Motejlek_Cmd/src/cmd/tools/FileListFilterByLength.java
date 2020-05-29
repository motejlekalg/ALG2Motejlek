package cmd.tools;

import java.io.File;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Martin Motejlek
 */
public class FileListFilterByLength implements FileListManipulator {

    private final int length;
    private final boolean greater;
    private final boolean equal;

    public FileListFilterByLength(int length, boolean greater, boolean equal) {
        this.length = length;
        this.greater = greater;
        this.equal = equal;
    }

    @Override
    public void execute(List<File> files) {
        Iterator<File> iter = files.iterator();
        while (iter.hasNext()) {
            File file = iter.next();
            if (file.isDirectory()
                    || greater && file.length() < length
                    || !greater && file.length() > length
                    || !equal && file.length() == length) {
                iter.remove();
            }
        }
    }

}
