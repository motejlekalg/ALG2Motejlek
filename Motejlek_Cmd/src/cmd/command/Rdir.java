package cmd.command;

import cmd.Command;
import cmd.CommandOutput;
import java.io.File;

/**
 *
 * @author Martin Motejlek
 */
public class Rdir extends Command {

    private static String rdir(File dir, int level) {
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < level; i++) {
            sb.append("-");
        }
        sb.append(dir.getName());
        sb.append(String.format("%n"));
        
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                sb.append(rdir(file, level + 1));
            }
        }
        
        return sb.toString();
    }
    
    @Override
    public CommandOutput execute(File workingDir) {
        return new CommandOutput(rdir(workingDir, 0));
    }
    
}
