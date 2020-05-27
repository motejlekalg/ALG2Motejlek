package orderingsystem.utils.listgenerator;

import java.util.Collections;
import java.util.List;
import orderingsystem.app.Item;

/**
 *
 * @author Martin Motejlek
 */
public class SortItemsByQuantity implements ListModifier<Item> {

    @Override
    public void applyTo(List<Item> list) {
        Collections.sort(list, (o1, o2) -> {
            return o1.getQuantity() - o2.getQuantity();
        });
    }
    
}
