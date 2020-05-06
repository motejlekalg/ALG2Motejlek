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
public class Exit extends Command {

    @Override
    public CommandOutput execute(File workingDir) {
        CommandOutput output = new CommandOutput();
        output.setStopSignal(true);
        return output;
    }
    
}
