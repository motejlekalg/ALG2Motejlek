package hurricane.app;

import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author Martin Motejlek
 */
public class OutputGen {
    
    private static final int COL_YEAR = 4;
    private static final int COL_MONTH = 3;
    private static final int COL_PRESSURE = 4;
    private static final int COL_SPEED = 3;

    private static final String UNIT_PRESSURE = "mb";
    private static final String UNIT_SPEED = "km/h";

    private static final DateTimeFormatter DTF_YEAR
            = DateTimeFormatter.ofPattern("uuuu");

    private static final DateTimeFormatter DTF_MONTH
            = DateTimeFormatter.ofPattern("MMM");
    
    public static String speedCatString(Hurricane h) {
        return String.format(
                "Kategorie: %d%nRychlost: %.0f %s",
                h.getCategory(),
                h.getSpeedKmH(),
                UNIT_SPEED
        );
    }
    
    public static String genTable(List<Hurricane> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                sb.append(String.format("%n"));
            }
            sb.append(genTableLine(list.get(i)));
        }
        return sb.toString();
    }

    private static String genTableLine(Hurricane h) {
        return String.format(
                "%"
                + COL_YEAR + "s %"
                + COL_MONTH + "s  %"
                + COL_PRESSURE + ".0f %s  %"
                + COL_SPEED + ".0f %s  %s",
                h.getTime().format(DTF_YEAR),
                h.getTime().format(DTF_MONTH),
                h.getPressure(),
                UNIT_PRESSURE,
                h.getSpeedKmH(),
                UNIT_SPEED,
                h.getName()
        );
    }
    
}
