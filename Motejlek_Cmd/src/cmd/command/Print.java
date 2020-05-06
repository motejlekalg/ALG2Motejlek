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
public class Print extends Command {

    @Override
    public CommandOutput execute(File workingDir) {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < params.length; i++) {
            sb.append(String.format("\"%s\"", params[i]));
            if (i < params.length - 1) {
                sb.append(String.format("%n"));
            }
        }
        
        return new CommandOutput(sb.toString());
    }
    
}
