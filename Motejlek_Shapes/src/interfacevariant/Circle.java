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
public class Circle implements ShapeInterface {

    // data
    private final double r;
    
    // constructor
    private Circle(double r) {
        if (r < 0) {
            throw new IllegalArgumentException("Radius cannot be negative.");
        }
        
        this.r = r;
    }
    
    // factory methods
    public static Circle getInstanceR(double r) {
        return new Circle(r);
    }
    
    public static Circle getInstanceD(double d) {
        return new Circle(d/2);
    }

    // methods
    public double getR() {
        return r;
    }
    
    @Override
    public double computeArea() {
        return Math.PI * r * r;
    }

    @Override
    public String toString() {
        return "Circle{" + "r=" + r + '}';
    }
    
}
