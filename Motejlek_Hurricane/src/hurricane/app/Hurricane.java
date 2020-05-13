package hurricane.app;

import java.time.YearMonth;

/**
 *
 * @author Martin Motejlek
 */
public class Hurricane {
    
    private static double knToKmH(double kn) {
        return kn * 1.825;
    }
    
    private static int kmHToCategory(double kmH) {
        int category;
        if (kmH < 119) {
            category = 0;
        } else if (kmH < 154) {
            category = 1;
        } else if (kmH < 178) {
            category = 2;
        } else if (kmH < 209) {
            category = 3;
        } else if (kmH < 252) {
            category = 4;
        } else {
            category = 5;
        }
        return category;
    }
    
    private final YearMonth time;
    private final double pressure; //mb
    private final double speed; //kn
    private final String name;

    public Hurricane(YearMonth time, double pressure, double speed, String name) {
        if (time == null) {
            throw new IllegalArgumentException("Time cannot be null.");
        }
        if (pressure < 0) {
            throw new IllegalArgumentException("Pressure cannot be negative.");
        }
        if (speed < 0) {
            throw new IllegalArgumentException("Speed cannot be negative.");
        }
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null.");
        }
        if (name.length() == 0) {
            throw new IllegalArgumentException("Name cannot be an empty string.");
        }
        this.time = time;
        this.pressure = pressure;
        this.speed = speed;
        this.name = name;
    }
    
    public YearMonth getTime() {
        return time;
    }

    public double getPressure() {
        return pressure;
    }

    public double getSpeed() {
        return speed;
    }

    public String getName() {
        return name;
    }
    
    public double getSpeedKmH() {
        return knToKmH(speed);
    }
    
    public int getCategory() {
        return kmHToCategory(getSpeedKmH());
    }
    
}
