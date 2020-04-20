/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comparing;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

/**
 *
 * @author Martin Motejlek
 */
public class ComparatorByFirstName implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        Collator col = Collator.getInstance(new Locale("cs", "CZ"));
        return col.compare(o1.getFirstName(), o2.getFirstName());
    }
    
}
