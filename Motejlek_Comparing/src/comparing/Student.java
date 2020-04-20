/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comparing;

import comparing.mycomparing.CompareInterface;
import java.util.Arrays;

/**
 *
 * @author Martin Motejlek
 */
public class Student implements CompareInterface, Comparable<Student> {
    
    private static final int MAX_MARKS = 10;
    
    private String firstName;
    private String lastName;
    private int age;
    private int studentNumber;
    private double[] marks = new double[MAX_MARKS];
    private int marksCount = 0;

    public Student(String firstName, String lastName, int age, int number) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.studentNumber = number;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public int getNumber() {
        return studentNumber;
    }
    
    public double[] getMarks() {
        return Arrays.copyOf(marks, marks.length);
    }
    
    public int getMarksCount() {
        return marksCount;
    }
    
    public boolean addMarks(double... marks) {
        if (marksCount + marks.length > MAX_MARKS) return false;
        for (double mark : marks) {
            this.marks[marksCount] = mark;
            marksCount++;
        }
        return true;
    }
    
    public double calcAvgMark() {
        double sum = 0;
        for (int i = 0; i < marksCount; i++) {
            sum += marks[i];
        }
        return sum / marksCount;
    }

    @Override
    public String toString() {
        return String.format("%-10s %-10s %2d %6d %1.2f %s", 
                firstName, lastName, age, studentNumber, calcAvgMark(), 
                Arrays.toString(marks));
    }

//    boolean startLater(Student student) {
//        return studentNumber > student.getNumber();
//    }
//    
//    boolean isOlder(Student student) {
//        return age > student.getAge();
//    }
    
    @Override
    public boolean isSmaller(CompareInterface o) {
        return this.studentNumber < ((Student)o).studentNumber;
    }
    
    @Override
    public int compareTo(Student s) {
        // sorting by number (largest first)
        // return ((Student)o).studentNumber - this.studentNumber;
        
        // sorting by average mark
        double EPS = 0.0001;
        double diff = calcAvgMark() - s.calcAvgMark();
        
        if (Math.abs(diff) < EPS) {
            return 0;
        } else if (diff > 0) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.studentNumber;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Student other = (Student) obj;
        if (this.studentNumber != other.studentNumber) {
            return false;
        }
        return true;
    }
    
}
