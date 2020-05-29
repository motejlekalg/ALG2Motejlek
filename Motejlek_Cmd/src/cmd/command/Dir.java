/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmd.command;

import cmd.Command;
import cmd.CommandOutput;
import cmd.tools.FileListFilterByExtension;
import cmd.tools.FileListFilterByLength;
import cmd.tools.FileListManipulator;
import cmd.tools.FileListOrderByName;
import java.io.File;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Martin Motejlek
 */
public class Dir extends Command {
    
    private static final int LAST_MODIFIED_COL_WIDTH = 19;
    private static final int SIZE_COL_WIDTH = 12;
    private static final String LIST_FORMAT =
            "%n%1s %-" + LAST_MODIFIED_COL_WIDTH + "s %" + SIZE_COL_WIDTH + "s %s";
    
    private static final DateTimeFormatter DATE_FORMATTER
            = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    private static CommandOutput dir(
            File workingDir, List<FileListManipulator> f) {
        if (!workingDir.isDirectory()) {
            return new CommandOutput(
                    String.format(
                            "\"%s\" is not a directory.",
                            workingDir.getPath()
                    )
            );
        }
        
        File[] files = workingDir.listFiles();
        
        if (files == null) {
            return new CommandOutput(
                    String.format(
                            "Contents of \"%s\" cannot be shown.",
                            workingDir.getPath()
                    )
            );
        }
        
        files = modifyList(files, f);
        
        return new CommandOutput(createListString(workingDir.getPath(), files));
    }
    
    private static File[] modifyList(
            File[] filesArray, List<FileListManipulator> f) {
        if (!f.isEmpty()) {
            ArrayList<File> files = new ArrayList<>();
            Collections.addAll(files, filesArray);
            for (FileListManipulator i : f) {
                i.execute(files);
            }
            return files.toArray(new File[files.size()]);
        }
        return filesArray;
    }
    
    private static String createListString(String dirName, File[] files) {
        StringBuilder sb = new StringBuilder("");
        sb.append(String.format("Directory: %s", dirName));
        sb.append(String.format(
                    LIST_FORMAT,
                    "T", "LAST MODIFIED", "SIZE", "NAME"));
        for (File file : files) {
            sb.append(String.format(
                    LIST_FORMAT,
                    file.isDirectory() ? "d" : "-",
                    LocalDateTime.ofInstant(
                            Instant.ofEpochMilli(file.lastModified()),
                            ZoneId.systemDefault()
                    ).format(DATE_FORMATTER),
                    file.isDirectory() ? "-" : file.length(),
                    file.getName()
                    ));
        }
        return sb.toString();
    }
    
    @Override
    public CommandOutput execute(File workingDir) {
        ArrayList<FileListManipulator> f = new ArrayList<>();
        
        boolean invalidParameters = false;
        if (params.length == 2) {
            switch (params[1]) {
                case "-o":
                    f.add(new FileListOrderByName());
                    break;
                default:
                    invalidParameters = true;
            }
        } else if (params.length == 3) {
            switch (params[1]) {
                case "-e":
                    f.add(new FileListFilterByExtension(params[2]));
                    break;
                case "-s":
                    try {
                        f.add(new FileListFilterByLength(
                                Integer.parseInt(params[2]),
                                true, true));
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
        
        return dir(workingDir, f);
    }
    
}
