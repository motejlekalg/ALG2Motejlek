package hurricane.app;

import hurricane.utils.ListModifier;
import java.util.Collections;
//import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Martin Motejlek
 */
public class SortBySpeed implements ListModifier<Hurricane> {
    
//    @Override
//    public void applyTo(List<Hurricane> list) {
//        Collections.sort(list, Comparator.comparing(Hurricane::getSpeed));
//    }
    
    private static final double EPS = 0.0001;

    @Override
    public void applyTo(List<Hurricane> list) {
        Collections.sort(list, (o1, o2) -> {
            double diff = o1.getSpeed() - o2.getSpeed();
            if (Math.abs(diff) < EPS) {
                return 0;
            } else if (diff > 0) {
                return 1;
            } else {
                return -1;
            }
        });
    }
    
}
