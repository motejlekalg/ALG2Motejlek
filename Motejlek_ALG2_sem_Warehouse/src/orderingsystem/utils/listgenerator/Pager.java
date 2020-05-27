package orderingsystem.utils.listgenerator;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Martin Motejlek
 */
public class Pager implements ListModifier {

    private int pageSize;
    private int pageNumber;

    public Pager(int pageSize, int page) {
        if (pageSize <= 0) {
            throw new IllegalArgumentException("Page size must be positive.");
        }
        if (pageSize <= 0) {
            throw new IllegalArgumentException("Page number cannot be negative.");
        }
        this.pageSize = pageSize;
        this.pageNumber = page;
    }
    
    @Override
    public void applyTo(List list) {
        List page = new ArrayList();
        for (int i = pageSize * pageNumber; i < list.size() && i < pageSize * (pageNumber + 1); i++) {
            page.add(list.get(i));
        }
        list.clear();
        list.addAll(page);
    }
    
}
