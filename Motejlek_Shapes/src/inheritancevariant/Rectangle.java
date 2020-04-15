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
public class Rectangle extends Shape {
    
    // data
    protected final double a;
    protected final double b;

    // constructor
    public Rectangle(double a, double b) {
        if (a < 0 || b < 0) {
            throw new IllegalArgumentException("Dimensions cannot be negative.");
        }
        
        this.a = a;
        this.b = b;
    }

    // methods
    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }
    
    @Override
    public double computeArea() {
        return a * b;
    }

    @Override
    public String toString() {
        // return "Rectangle{" + "a=" + a + ", b=" + b + '}';
        return super.toString() + String.format(" a = %.2f, b = %.2f", a, b);
    }    
    
}
