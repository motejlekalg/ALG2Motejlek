/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmd.tools;

/**
 *
 * @author Martin Motejlek
 */
public class MalformedCommandException extends Exception {
    
    public MalformedCommandException(String message) {
        super(message);
    }
    
}
