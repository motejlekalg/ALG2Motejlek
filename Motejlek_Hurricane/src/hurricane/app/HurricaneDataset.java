package hurricane.app;

import hurricane.utils.Dataset;
import java.util.List;

/**
 *
 * @author Martin Motejlek
 */
public class HurricaneDataset extends Dataset<Hurricane> {
    
    public HurricaneDataset(List data) {
        super(data);
    }
    
    public Hurricane getEntryByName(String name) {
        for (Hurricane h : data) {
            if (h.getName().equals(name)) {
                return h;
            }
        }
        return null;
    }
    
}
