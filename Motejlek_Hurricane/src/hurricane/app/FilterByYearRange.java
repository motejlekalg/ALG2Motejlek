package hurricane.app;

import hurricane.utils.ListModifier;
import java.util.List;

/**
 *
 * @author Martin Motejlek
 */
public class FilterByYearRange implements ListModifier<Hurricane> {

    private final int yearStart;
    private final int yearEnd;

    public FilterByYearRange(int yearStart, int yearEnd) {
        this.yearStart = yearStart;
        this.yearEnd = yearEnd;
    }
    
    @Override
    public void applyTo(List<Hurricane> list) {
        list.removeIf((t) -> {
            return t.getTime().getYear() < yearStart || 
                    t.getTime().getYear() > yearEnd;
        });
    }
    
}
