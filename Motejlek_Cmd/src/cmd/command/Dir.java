/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmd.command;

import cmd.Command;
import cmd.CommandOutput;
import cmd.tools.ComparatorFilesByName;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Martin Motejlek
 */
public class Dir extends Command {
    
    private static final int SIZE_COL_WIDTH = 12;
    
    private static final SimpleDateFormat dateFormatter = 
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    // purely a container, no encapsulation
    private static class Parameters {
        boolean orderByName = false;

        boolean filterExtension = false;
        String extension;

        boolean filterLarger = false;
        int size;
    }
    
    private static CommandOutput dir(File workingDir, Parameters p) {
        if (!workingDir.isDirectory()) {
            return new CommandOutput(
                    String.format(
                            "\"%s\" is not a directory.",
                            workingDir.getPath()
                    )
            );
        }
        
        File[] filesArray = workingDir.listFiles();
        
        if (filesArray == null) {
            return new CommandOutput(
                    String.format(
                            "Contents of \"%s\" cannot be shown.",
                            workingDir.getPath()
                    )
            );
        }
        
        LinkedList<File> files = new LinkedList();
        Collections.addAll(files, filesArray);
        
        if (p.filterExtension) {
            filterExtension(files, p.extension);
        }
        
        if (p.filterLarger) {
            filterLarger(files, p.size);
        }
        
        if (p.orderByName) {
            orderByName(files);
        }
        
        File[] filesArray2 = new File[files.size()];
        files.toArray(filesArray2);
        return new CommandOutput(
                createList(workingDir.getPath(), filesArray2)
        );
    }
    
    private static void filterExtension(List<File> files, String extension) {
        String regexp = ".*\\." + extension;
        for (int i = files.size() - 1; i >= 0; i--) {
            if (files.get(i).isDirectory() ||
                    !files.get(i).getName().matches(regexp)) {
                files.remove(i);
            }
        }
    }
    
    private static void filterLarger(List<File> files, int size) {
        for (int i = files.size() - 1; i >= 0; i--) {
            if (files.get(i).isDirectory() || files.get(i).length() < size) {
                files.remove(i);
            }
        }
    }
    
    private static void orderByName(List files) {
        Collections.sort(files, new ComparatorFilesByName());
    }
    
    private static String createList(String dirName, File[] files) {
        StringBuilder sb = new StringBuilder("");
        sb.append(String.format("Directory: %s", dirName));
        for (File file : files) {            
            sb.append(String.format(
                    "%n%1s %s %" + SIZE_COL_WIDTH + "s %s",
                    file.isDirectory() ? "d" : "-",
                    dateFormatter.format(new Date(file.lastModified())),
                    file.isDirectory() ? "-" : file.length(),
                    file.getName()
                    ));
        }
        return sb.toString();
    }
    
    @Override
    public CommandOutput execute(File workingDir) {
        Parameters p = new Parameters();
        
        boolean invalidParameters = false;
        if (params.length == 2) {
            switch (params[1]) {
                case "-o":
                    p.orderByName = true;
                    break;
                default:
                    invalidParameters = true;
            }
        } else if (params.length == 3) {
            switch (params[1]) {
                case "-e":
                    p.filterExtension = true;
                    p.extension = params[2];
                    break;
                case "-s":
                    p.filterLarger = true;
                    try {
                        p.size = Integer.parseInt(params[2]);
                    } catch (NumberFormatException e) {
                        invalidParameters = true;
                    }
                    break;
                default:
                    invalidParameters = true;
            }            
        } else if (params.length > 3) {
            invalidParameters = true;
        }
        
        if (invalidParameters) {
            return new CommandOutput("Invalid parameters.");
        }
        
        return dir(workingDir, p);
    }
    
}
