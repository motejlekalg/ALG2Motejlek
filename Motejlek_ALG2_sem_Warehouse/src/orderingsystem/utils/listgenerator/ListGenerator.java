package orderingsystem.utils.listgenerator;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

/**
 * A class for generating lists from collections.
 * 
 * @author Martin Motejlek
 */
public class ListGenerator<E> {

    List<ListModifier<E>> mods = new ArrayList<>();

    /**
     * Adds list modifiers.
     * @param mods one or more list modifiers to add
     * @return the instance of the ListGenerator
     */
    public ListGenerator add(ListModifier<E>... mods) {
        for (ListModifier<E> mod : mods) {
            if (mod == null) {
                throw new IllegalArgumentException("List modifier cannot be null.");
            }
            this.mods.add(mod);
        }
        return this;
    }

    /**
     * Creates a list from a collection and applies all added list modifiers to
     * it.
     * @param data a collection to process
     * @return a generated list
     */
    public List<E> generate(Collection<E> data) {
        ArrayList<E> list = new ArrayList<>(data);
        for (ListModifier mod : mods) {
            mod.applyTo(list);
        }
        return list;
    }

}
