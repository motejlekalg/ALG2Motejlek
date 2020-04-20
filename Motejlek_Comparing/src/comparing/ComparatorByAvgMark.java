/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comparing;

import java.util.Comparator;

/**
 *
 * @author Martin Motejlek
 */
public class ComparatorByAvgMark implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        double EPS = 0.0001;
        double diff = o1.calcAvgMark() - o2.calcAvgMark();
        
        if (Math.abs(diff) < EPS) {
            return 0;
        } else if (diff > 0) {
            return 1;
        } else {
            return -1;
        }
    }
    
}
