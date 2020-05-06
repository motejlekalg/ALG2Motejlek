/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmd.command;

import cmd.Command;
import cmd.CommandOutput;
import java.io.File;

/**
 *
 * @author Martin Motejlek
 */
public class Help extends Command {

    private static final int COMMAND_COL_WIDTH = 32;
    private static final String[][] CONTENTS = {
        {"cd", "Set working directory to the directory which this program was run from"},
        {"cd [path]", "Change working directory"},
        {"dir", "Display contents of the working directory"},
        {"dir [-o]", "Order the displayed contents"},
        {"dir [-e] [file extension]", "Display files with a specified extension"},
        {"dir [-s] [size]", "Display files bigger than the specified size (in bytes)"},
        {"mkdir", "Create a directory"},
        {"print", "Prints all passed command parameters"},
        {"rename", "Rename a file or a directory"},
        {"help", "Display help"},
        {"exit", "Exit cmd"}
    };
    
    @Override
    public CommandOutput execute(File workingDir) {
        StringBuilder sb = new StringBuilder();
        sb.append("HELP");
        for (String[] entry : CONTENTS) {
            sb.append(String.format(
                    "%n%-" + COMMAND_COL_WIDTH + "s %s",
                    entry[0], entry[1]));
        }
        return new CommandOutput(sb.toString());
    }
    
}
