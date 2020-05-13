package hurricane.app;

import hurricane.utils.ListModifier;
import java.util.List;

/**
 *
 * @author Martin Motejlek
 */
public class FilterByName implements ListModifier<Hurricane> {

    private final String name;

    public FilterByName(String name) {
        this.name = name;
    }
    
    @Override
    public void applyTo(List<Hurricane> list) {
        list.removeIf((t) -> {
            return !t.getName().equals(name);
        });
    }
    
}
