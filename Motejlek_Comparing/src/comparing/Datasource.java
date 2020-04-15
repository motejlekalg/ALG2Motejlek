/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comparing;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Martin Motejlek
 */
public class Datasource {
    
    private static Student[] data = {
        new Student("Jan", "Maly", 20, 1234),
        new Student("Alice", "Velka", 19, 1436),
        new Student("Bob", "Pech", 18, 1954)
    };
    
    static {
        data[0].addMarks();
        data[0].addMarks(3, 3, 3, 3, 3, 1.5, 2, 3.5, 1, 2.5);
        data[1].addMarks(4, 4.5, 2.5, 5, 3, 1, 5);
        data[2].addMarks(2.5);
        data[2].addMarks(1, 1.5, 2);
        data[2].addMarks(1, 1.5);
    }
    
    public static Student[] loadDataAsArray() {
        return Arrays.copyOf(data, data.length);
    }
    
    public static List<Student> loadDataAsList() {
        return Arrays.asList(data);
    }
    
}