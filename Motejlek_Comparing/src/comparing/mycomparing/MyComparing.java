/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comparing.mycomparing;

import comparing.Datasource;
import comparing.Student;

/**
 *
 * @author Martin Motejlek
 */
public class MyComparing {
    
    public static void main(String[] args) {
        Student[] students = Datasource.loadDataAsArray();
        print(students);
//        System.out.println("Sort by age");
//        sortByAge(students);
//        print(students);
//        System.out.println("Sort by number");
//        sort(students);
//        print(students);
        System.out.println("Sort by first name");
        sort(students, new CompareByFirstName());
        print(students);
        System.out.println("Sort by last name");
        sort(students, new CompareByLastName());
        print(students);
        System.out.println("Sort by age");
        sort(students, new CompareByAge());
        print(students);
        System.out.println("Sort by number");
        sort(students, new CompareByNumber());
        print(students);
    }
    
//    private static void sortByAge(Student[] array) {
//        // bubble sort
//        for (int i = 0; i < array.length - 1; i++) {
//            for (int j = 1; j < array.length - i; j++) {
//                if (array[j - 1].isOlder(array[j])) {
//                    Student temp = array[j];
//                    array[j] = array[j - 1];
//                    array[j - 1] = temp;
//                }
//            }
//        }
//    }
    
//    private static void sort(CompareInterface[] array) {
//        // bubble sort
//        for (int i = 0; i < array.length - 1; i++) {
//            for (int j = 1; j < array.length - i; j++) {
//                if (array[j - 1].isSmaller(array[j])) {
//                    CompareInterface temp = array[j];
//                    array[j] = array[j - 1];
//                    array[j - 1] = temp;
//                }
//            }
//        }
//    }
    
    private static void sort(Object[] array, ComparatorInterface comparator) {
        // bubble sort
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 1; j < array.length - i; j++) {
                if (comparator.bigger(array[j - 1], array[j])) {
                    Object temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
        }
    }
    
    public static void print(CompareInterface[] array) {
        for (CompareInterface i : array) {
            System.out.println(i);
        }
    }
    
}
