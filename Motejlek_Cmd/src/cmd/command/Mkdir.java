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
public class Mkdir extends Command {
    
    private static CommandOutput createDir(File workingDir, String path) {
        File dir;
        try {
            dir = FileTools.changePath(workingDir, path);
        } catch (IOException e) {
            return new CommandOutput(
                    String.format(
                            "\"%s\" is not a valid path.",
                            path
                    )
            );
        }
        
        if (dir.exists()) {
            return new CommandOutput(
                    String.format(
                            "\"%s\" already exists.",
                            dir.getPath()
                    )
            );
        }
        
        if (!dir.mkdir()) {
            return new CommandOutput(
                    String.format(
                            "Creating folder \"%s\" failed.",
                            dir.getPath()
                    )
            );
        }
        
        return new CommandOutput();
    }
    
    @Override
    public CommandOutput execute(File workingDir) {
        if (params.length < 2) {
            return new CommandOutput("Chybí parametr umístění.");
        } else if (params.length == 2) {
            return createDir(workingDir, params[1]);
        } else {
            return new CommandOutput("Příliš mnoho parametrů.");
        }
    }
}
