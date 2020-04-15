/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacevariant;

/**
 *
 * @author Martin Motejlek
 */
public class Square extends Rectangle {
    
    // constructor
    public Square(double a) {
        super(a, a);
    }
    
    // methods
    @Override
    public String toString() {
        return "Square{" + "a=" + a + '}';
    }
    
}
