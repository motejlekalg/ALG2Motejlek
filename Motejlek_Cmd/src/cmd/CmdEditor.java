/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmd;

import cmd.tools.InvalidCommandException;
import cmd.tools.MalformedCommandException;
import java.io.File;

/**
 *
 * @author Martin Motejlek
 */
public class CmdEditor implements CmdInterface {
    
    private static final String HOME_PATH = System.getProperty("user.dir");
    
    private static String createErrorMessage(
            String generalMessage, String errorDescription) {
        return String.format("%s%n%s", generalMessage, errorDescription);
    }
    
    private File workingDir;
    private boolean isRunning;
    
    public CmdEditor() {
        isRunning = true;
        workingDir = new File(HOME_PATH);
    }

    @Override
    public String getWorkingDir() {
        return workingDir.getAbsolutePath();
    }
    
    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public String parseAndExecute(String line) {
        Command command;
        try {
            command = Parser.parse(line);
        } catch (MalformedCommandException e) {
            return createErrorMessage("Cannot parse command.", e.toString());
        } catch (InvalidCommandException e) {
            return createErrorMessage("Invalid command.", e.toString());
        }
        
        if (command == null) {
            return null;
        }
        
        CommandOutput output;
        
        output = command.execute(workingDir);
        
        if (output.getWorkingDir() != null) {
            workingDir = output.getWorkingDir();
        }
        if (output.getStopSignal()) {
            isRunning = false;
        }
        return output.getText();
    }
    
}
