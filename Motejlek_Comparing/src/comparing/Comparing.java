/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comparing;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Martin Motejlek
 */
public class Comparing {
    
    public static void main(String[] args) {
        System.out.println("- ARRAY -");
        Student[] students = Datasource.loadDataAsArray();
        print(students);
        System.out.println("Sort by average mark");
        Arrays.sort(students); //students musi byt typove kompatibilni s interface Comparable
        print(students);
        
        System.out.println("- LIST -");
        List<Student> students1 = Datasource.loadDataAsList();
        print(students1);
        System.out.println("Sort by average mark");
        Collections.sort(students1);
        print(students1);
    }
    
    public static void print(Object[] array) {
        for (Object o : array) {
            System.out.println(o);
        }
    }
    
    public static void print(List list) {
        for (Object o : list) {
            System.out.println(o);
        }
    }
    
}
