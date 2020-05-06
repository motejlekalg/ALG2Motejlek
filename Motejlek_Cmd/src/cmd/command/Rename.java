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
public class Rename extends Command {
    
    private static CommandOutput rename(
            File workingDir, String path, String newPath) {
        File file;
        try {
            file = FileTools.changePath(workingDir, path);
        } catch (IOException e) {
            return new CommandOutput(
                    String.format(
                            "\"%s\" is not a valid path.",
                            path
                    )
            );
        }
        if (!file.exists()) {
            return new CommandOutput(
                    String.format(
                            "\"%s\" does not exist.",
                            file.getPath()
                    )
            );
        }

        File newFile;
        try {
            newFile = FileTools.changePath(workingDir, newPath);
        } catch (IOException e) {
            return new CommandOutput(
                    String.format(
                            "\"%s\" is not a valid path.",
                            newPath
                    )
            );
        }
        if (newFile.exists()) {
            return new CommandOutput(
                    String.format(
                            "\"%s\" already exists.",
                            newFile.getPath()
                    )
            );
        }

        if (!file.renameTo(newFile)) {
            return new CommandOutput(
                    String.format(
                            "Renaming \"%s\" to \"%s\" failed.",
                            file.getPath(),
                            newFile.getPath()
                    )
            );
        }
        
        return new CommandOutput();
    }
    
    @Override
    public CommandOutput execute(File workingDir) {
        if (params.length < 3) {
            return new CommandOutput("Not enough parameters.");
        } else if (params.length == 3) {
            return rename(workingDir, params[1], params[2]);
        } else {
            return new CommandOutput("Too many parameters.");
        }
    }
    
}
