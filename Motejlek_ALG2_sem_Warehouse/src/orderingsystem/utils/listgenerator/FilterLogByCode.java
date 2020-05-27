package orderingsystem.utils.listgenerator;

import java.util.List;
import orderingsystem.app.LogEntry;

/**
 * A list modifier which filters a list of log entries by item code.
 * 
 * @author Martin Motejlek
 */
public class FilterLogByCode implements ListModifier<LogEntry> {

    private String code;

    /**
     * FilterLogByCode initializer.
     * @param code the item code to filter
     */
    public FilterLogByCode(String code) {
        this.code = code;
    }
    
    @Override
    public void applyTo(List<LogEntry> list) {
        list.removeIf((t) -> {
            return !t.getCode().equals(code);
        });
    }
    
}
