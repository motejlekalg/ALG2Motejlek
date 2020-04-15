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
public interface ShapeInterface {
    
    // data
    final String NAME = "Geometric object";
    
    // methods
    public abstract double computeArea();
    
    default String getShapeName() {
        return this.getClass().getSimpleName();
    }
    
}