package hurricane.app;

import hurricane.utils.ListModifier;
import java.util.Collections;
//import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Martin Motejlek
 */
public class SortByTime implements ListModifier<Hurricane> {
    
//    @Override
//    public void applyTo(List<Hurricane> list) {
//        Collections.sort(list, Comparator.comparing(Hurricane::getTime));
//    }
    
    @Override
    public void applyTo(List<Hurricane> list) {
        Collections.sort(list, (o1, o2) -> o1.getTime().compareTo(o2.getTime()));
    }
    
}
