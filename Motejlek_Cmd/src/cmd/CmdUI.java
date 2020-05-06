/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmd;

import java.util.Scanner;

/**
 *
 * @author Martin Motejlek
 */
public class CmdUI {

    private static Scanner sc = new Scanner(System.in);
    private static CmdInterface cmd = new CmdEditor();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String line;
        while(cmd.isRunning()) {
            System.out.print(getPrompt());
            line = sc.nextLine();
            String text = cmd.parseAndExecute(line);
            if (text != null) {
                System.out.println(text);
            }
        }
    }

    private static String getPrompt() {
        return cmd.getWorkingDir() + "> ";
    }
    
}
