package orderingsystem.utils.listgenerator;

import java.util.List;

/**
 * An interface for creating classes which process lists.
 * 
 * @author Martin Motejlek
 */
public interface ListModifier<E> {

    /**
     * Modifies a list.
     *
     * @param list the list to modify
     */
    void applyTo(List<E> list);

}
