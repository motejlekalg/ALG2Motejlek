package competition.app;

import java.time.LocalTime;

/**
 *
 * @author Martin Motejlek
 */
public class FinishStat {
        
    private final int number;
    private final LocalTime finishTime;

    public FinishStat(int number, LocalTime finishTime) {
        this.number = number;
        this.finishTime = finishTime;
    }

    public int getNumber() {
        return number;
    }

    public LocalTime getFinishTime() {
        return finishTime;
    }
        
}
