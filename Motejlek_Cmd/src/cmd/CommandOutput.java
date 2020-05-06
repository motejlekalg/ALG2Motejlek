/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmd;

import java.io.File;

/**
 *
 * @author Martin Motejlek
 */
public class CommandOutput {
    
    private String text = null;
    private File workingDir = null;
    private boolean stopSignal = false;
    
    public CommandOutput() {
    }
    
    public CommandOutput(String text) {
        this.text = text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public void setWorkingDir(File dir) {
        this.workingDir = dir;
    }

    public void setStopSignal(boolean stopSignal) {
        this.stopSignal = stopSignal;
    }

    public String getText() {
        return text;
    }

    public File getWorkingDir() {
        return workingDir;
    }
    
    public boolean getStopSignal() {
        return stopSignal;
    }
    
}
