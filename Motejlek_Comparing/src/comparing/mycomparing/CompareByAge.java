/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comparing.mycomparing;

import comparing.Student;

/**
 *
 * @author Martin Motejlek
 */
public class CompareByAge implements ComparatorInterface {
    
    @Override
    public boolean bigger(Object o1, Object o2) {
        return ((Student)o1).getAge() > ((Student)o2).getAge();
    }
    
}
