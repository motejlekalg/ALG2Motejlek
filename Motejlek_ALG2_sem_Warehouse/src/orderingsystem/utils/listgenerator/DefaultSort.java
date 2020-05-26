package orderingsystem.utils.listgenerator;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author Martin Motejlek
 */
public class DefaultSort implements ListModifier {

    @Override
    public void applyTo(List list) {
        Collections.sort(list);
    }
    
}
