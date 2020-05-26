package orderingsystem.utils.listgenerator;

import java.util.List;

/**
 *
 * @author Martin Motejlek
 */
public interface ListModifier<E> {
    
    void applyTo(List<E> list);
    
}
