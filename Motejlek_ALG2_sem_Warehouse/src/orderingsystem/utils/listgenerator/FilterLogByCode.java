package orderingsystem.utils.listgenerator;

import java.util.List;
import orderingsystem.app.LogEntry;

/**
 *
 * @author Martin Motejlek
 */
public class FilterLogByCode implements ListModifier<LogEntry> {

    private String code;

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
