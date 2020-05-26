package orderingsystem.utils.listgenerator;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Martin Motejlek
 */
public class ListGenerator<E> {

    List<ListModifier> mods = new ArrayList<>();

    public ListGenerator add(ListModifier<E>... mods) {
        for (ListModifier<E> mod : mods) {
            if (mod == null) {
                throw new IllegalArgumentException("List modifier cannot be null.");
            }
            this.mods.add(mod);
        }
        return this;
    }

    public ArrayList<E> generate(Collection<E> data) {
        ArrayList<E> list = new ArrayList<>(data);
        for (ListModifier mod : mods) {
            mod.applyTo(list);
        }
        return list;
    }

}
