package hurricane.utils;

import java.util.List;

/**
 *
 * @author Martin Motejlek
 */
public interface ListModifier<E> {
    
    public void applyTo(List<E> list);
    
}
