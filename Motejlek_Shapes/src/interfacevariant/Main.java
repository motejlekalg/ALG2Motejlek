/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacevariant;

import java.util.ArrayList;

/**
 *
 * @author Martin Motejlek
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Circle c1 = Circle.getInstanceR(1);
        Circle c2 = Circle.getInstanceD(4);
        Rectangle r1 = new Rectangle(2, 3);
        Square s1 = new Square(3);
        
        // 1
        double areaAll = c1.computeArea() + c2.computeArea() + r1.computeArea() + s1.computeArea();
        
        // 2
        ArrayList<ShapeInterface> shapes = new ArrayList();
        shapes.add(c1);
        shapes.add(c2);
        shapes.add(r1);
        shapes.add(s1);
        
        double areaAll1 = 0;
        for (int i = 0; i < shapes.size(); i++) {
            if (shapes.get(i) instanceof Circle) {
                areaAll1 += ((Circle)shapes.get(i)).computeArea();
            } else if (shapes.get(i) instanceof Rectangle) {
                areaAll1 += ((Rectangle)shapes.get(i)).computeArea();
            }
        }
        
        // 3
        ArrayList<ShapeInterface> shapes1 = new ArrayList();
        shapes1.add(c1);
        shapes1.add(c2);
        shapes1.add(r1);
        shapes1.add(s1);
        
        double areaAll2 = 0;
        for (ShapeInterface s : shapes1) {
            areaAll2 += s.computeArea(); // polymorphism
        }
        
        System.out.println(areaAll);
        System.out.println(areaAll1);
        System.out.println(areaAll2);
    }
    
}
