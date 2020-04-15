/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inheritancevariant;

/**
 *
 * @author Martin Motejlek
 */
//public class Square extends Rectangle {
//    
//    // constructor
//    public Square(double a) {
//        super(a, a);
//    }
//    
//    // methods
//    @Override
//    public String toString() {
//        return "Square{" + "a=" + a + '}';
//    }
//    
//}

public class Square extends Shape {
    
    // data
    private final double a;

    // constructor
    public Square(double a) {
        if (a < 0) {
            throw new IllegalArgumentException("Dimensions cannot be negative.");
        }
        
        this.a = a;
    }

    // methods
    public double getA() {
        return a;
    }
    
    @Override
    public double computeArea() {
        return a * a;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" a = %.2f", a);
    }
    
}
