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
public abstract class Shape implements Comparable<Shape> {
    
    // data
    protected final String NAME = "Geometric object";
    
    // methods
    public abstract double computeArea();
    
    public String getShapeName() {
        return this.getClass().getSimpleName();
    }
    
    @Override
    public String toString() {
        return NAME + ": " + getShapeName();
    }
    
    @Override
    public int compareTo(Shape s) {
        double EPS = 0.0001;
        double diff = computeArea() - s.computeArea();
        
        if (Math.abs(diff) < EPS) {
            return 0;
        } else if (diff > 0) {
            return 1;
        } else {
            return -1;
        }
    }
    
}