package hurricane.utils;

import java.util.Set;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 *
 * @author Martin Motejlek
 */
public class Dataset<T> {

    protected final Set<T> data;
    
    public Dataset(List data) {
        this.data = new HashSet(data);
    }
    
    public ArrayList<T> generateList() {
        return new ArrayList<>(data);
    }
    
    public ArrayList<T> generateList(ListModifier<T>... mods) {
        return generateList(Arrays.asList(mods));
    }
    
    public ArrayList<T> generateList(List<ListModifier<T>> mods) {
        if (mods != null && !mods.isEmpty()) {
            LinkedList<T> list = new LinkedList<>(data);
            for (ListModifier mod : mods) mod.applyTo(list);
            return new ArrayList<>(list);
        }
        return generateList();
    }
    
}