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
        System.out.println("Sort by first name");
        Arrays.sort(students, new ComparatorByFirstName());
        print(students);
        System.out.println("Sort by last name");
        Arrays.sort(students, new ComparatorByLastName());
        print(students);
        System.out.println("Sort by age");
        Arrays.sort(students, new ComparatorByAge());
        print(students);
        System.out.println("Sort by number");
        Arrays.sort(students, new ComparatorByNumber());
        print(students);
        System.out.println("Sort by average mark");
        Arrays.sort(students, new ComparatorByAvgMark());
        print(students);
        
        System.out.println("Sort by average mark (Comparable)");
        Arrays.sort(students); //students musi byt typove kompatibilni s interface Comparable
        print(students);
        
        System.out.println("- LIST -");
        List<Student> students1 = Datasource.loadDataAsList();
        print(students1);
        System.out.println("Sort by first name");
        Collections.sort(students1, new ComparatorByFirstName());
        print(students1);
        System.out.println("Sort by last name");
        Collections.sort(students1, new ComparatorByLastName());
        print(students1);
        System.out.println("Sort by age");
        Collections.sort(students1, new ComparatorByAge());
        print(students1);
        System.out.println("Sort by number");
        Collections.sort(students1, new ComparatorByNumber());
        print(students1);
        System.out.println("Sort by average mark");
        Collections.sort(students1, new ComparatorByAvgMark());
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
