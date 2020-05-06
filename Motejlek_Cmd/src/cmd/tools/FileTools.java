/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmd.tools;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author Martin Motejlek
 */
public class FileTools {
    
    public static File changePath(File workingDir, String path)
            throws IOException {
        File newFile;
        if (new File(path).isAbsolute()) {
            newFile = new File(path);
        } else {
            newFile = new File(workingDir, path);
        }
        newFile = newFile.getCanonicalFile();
        return newFile;
    }
    
    private FileTools(){};
    
}