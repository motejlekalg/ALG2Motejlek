/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmd;

import cmd.tools.InvalidCommandException;
import cmd.tools.MalformedCommandException;
import java.util.ArrayList;

/**
 *
 * @author Martin Motejlek
 */
public class Parser {
    
    public static Command parse(String line)
            throws InvalidCommandException, MalformedCommandException {
        if (line.length() == 0) {
            return null;
        }
        
        String[] params = process(line);
        
        String name = Command.COMMAND_PKG + "." + capitaliseFirst(params[0]);
        try {
            Class c = Class.forName(name);
            Command command = (Command) c.newInstance();
            command.setParams(params);
            return command;
        } catch(Exception e) {
            throw new InvalidCommandException(e.toString());
        }
    }
    
    private static String capitaliseFirst(String string) {
        char first = Character.toUpperCase(string.charAt(0));
        return first + string.substring(1);
    }
    
    private static String[] process(String line)
            throws MalformedCommandException {
        ArrayList<String> params = new ArrayList<>();
        StringBuilder param = new StringBuilder();
        char c;
        
        boolean ignoreChar = false;
        boolean allowEmptyParam = false;
        boolean addParam = false;
        
        boolean escapeNext = false;
        boolean groupStrings = false;
        char groupStringsEnd = '\0';
        
        for (int i = 0; i < line.length(); i++) {
            c = line.charAt(i);
            
            if (c == '\\') {
                ignoreChar = true;
                escapeNext = true;
            } else {
                if (groupStrings) {
                    if (!escapeNext
                            && c == groupStringsEnd) {
                        ignoreChar = true;
                        groupStrings = false;
                    }
                } else {
                    if (Character.getType(c) == Character.SPACE_SEPARATOR) {
                        ignoreChar = true;
                        addParam = true;
                    } else if (!escapeNext && (c == '"' | c == '\'')) {
                        ignoreChar = true;
                        groupStrings = true;
                        groupStringsEnd = c;
                        allowEmptyParam = true;
                    }
                }
            }
            
            if (i == line.length() - 1) {
                addParam = true;
            }
            
            if (!ignoreChar) {
                param.append(c);
            } else {
                ignoreChar = false;
            }
            
            if (addParam) {
                if (param.length() > 0 || allowEmptyParam) {
                    params.add(param.toString());
                    param = new StringBuilder();
                    allowEmptyParam = false;
                    addParam = false;
                }
            }
            
        }
        
        if (groupStrings) {
            throw new MalformedCommandException("Missing closing quotation marks.");
        }
        
        return params.toArray(new String[params.size()]);
    }
    
    private Parser() {};
    
}
