/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmd.command;

import cmd.Command;
import cmd.CommandOutput;
import cmd.tools.FileTools;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Martin Motejlek
 */
public class Cd extends Command {

    private static final String HOME_PATH = System.getProperty("user.dir");
    
    private static CommandOutput changeDir(File workingDir, String path) {
        File newDir;
        
        try {
            newDir = FileTools.changePath(workingDir, path);
        } catch (IOException e) {
            return new CommandOutput(
                    String.format(
                            "\"%s\" is not a valid path.",
                            path
                    )
            );
        }
        
        if (!newDir.isDirectory()) {
            return new CommandOutput(
                    String.format(
                            "\"%s\" is not a directory.",
                            newDir.getPath()
                    )
            );
        }
        
        CommandOutput output = new CommandOutput();
        output.setWorkingDir(newDir);
        return output;
    }
    
    @Override
    public CommandOutput execute(File workingDir) {
        if (params.length == 1) {
            return changeDir(workingDir, HOME_PATH);
        } else if (params.length == 2) {
            return changeDir(workingDir, params[1]);
        } else {
            return new CommandOutput("Too many parameters.");
        }
    }

}
