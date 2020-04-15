/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar;

/**
 *
 * @author Martin Motejlek
 */
public class Calendar {

    // static data
    private static final int COL_WIDTH = 2;

    private static final int MONTHS_IN_YEAR = 12;

    private static final int[] STANDARD_DAYS_IN_MONTH = {
        31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };

    private static final String[] MONTH_NAMES = {
        "Leden", "Únor", "Březen", "Duben", "Květen", "Červen",
        "Červenec", "Srpen", "Září", "Říjen", "Listopad", "Prosinec"
    };

    private static final int DAYS_IN_WEEK = 7;

    private static final String[] DAY_SHORT_NAMES = {
        "Po", "Út", "St", "Čt", "Pá", "So", "Ne"
    };

    // static data sanity check
    static {
        if (MONTHS_IN_YEAR != STANDARD_DAYS_IN_MONTH.length) {
            throw new AssertionError(
                    "The length of STANDARD_DAYS_IN_MONTH does not equal MONTHS_IN_YEAR.");
        }

        if (MONTHS_IN_YEAR != MONTH_NAMES.length) {
            throw new AssertionError(
                    "The length of MONTH_NAMES does not equal MONTHS_IN_YEAR.");
        }

        if (DAYS_IN_WEEK != DAY_SHORT_NAMES.length) {
            throw new AssertionError(
                    "The length of DAY_SHORT_NAMES does not equal DAYS_IN_WEEK.");
        }

    }

    // static methods
    private static void checkMonthValidity(int month) {
        if (month < 1 || month > MONTHS_IN_YEAR) {
            throw new IllegalArgumentException("The month does not exist.");
        }
    }

    private static void checkDayValidity(int year, int month, int day) {
        if (day < 1 || day > daysInMonth(year, month)) {
            throw new IllegalArgumentException("The day does not exist.");
        }
    }
    
    private static void checkDayOfWeekValidity(int dayOfWeek) {
        if (dayOfWeek < 1 || dayOfWeek > DAYS_IN_WEEK) {
            throw new IllegalArgumentException("The day of week does not exist.");
        }
    }

    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && ((year % 100 != 0) || (year % 400 == 0));
    }

    public static int daysInYear(int year) {
        int daysInYear = 0;
        for (int i = 1; i <= MONTHS_IN_YEAR; i++) {
            daysInYear += daysInMonth(year, i);
        }
        return daysInYear;
    }

    public static int daysInMonth(int year, int month) {
        checkMonthValidity(month);

        int daysOfMonth = STANDARD_DAYS_IN_MONTH[month - 1];
        if (isLeapYear(year) && month == 2) { // Leap year February
            daysOfMonth += 1;
        }
        return daysOfMonth;
    }

    private static int zeller(int q, int m, int K, int J) {
        return (q + (13 * (m + 1) / 5) + K + (K / 4) + (J / 4) + (5 * J)) % 7;
    }

    public static int dayOfWeek(int year, int month, int day) {
        checkMonthValidity(month);
        checkDayValidity(year, month, day);

        int zMonth = month;
        int zYear = year;
        if (month <= 2) {
            zMonth += MONTHS_IN_YEAR;
            zYear--;
        }
        int zeller = zeller(day, zMonth, zYear % 100, zYear / 100);
        return (zeller + 5) % 7 + 1;
    }

    public static String isoDate(int year, int month, int day) {
        checkMonthValidity(month);
        checkDayValidity(year, month, day);

        return String.format("%04d-%02d-%02d", year, month, day);
    }

    public static String nameOfMonth(int month) {
        checkMonthValidity(month);

        return MONTH_NAMES[month - 1];
    }

    public static String shortNameOfDay(int dayOfWeek) {
        checkDayOfWeekValidity(dayOfWeek);

        return DAY_SHORT_NAMES[dayOfWeek - 1];
    }

    // data
    private int year;
    private int month;
    private int day;

    // constructor
    public Calendar(int year, int month, int day) {
        checkMonthValidity(month);
        checkDayValidity(year, month, day);

        this.year = year;
        this.month = month;
        this.day = day;
    }

    // methods
    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getDayOfWeek() {
        return dayOfWeek(year, month, day);
    }

    public void setDate(int year, int month, int day) {
        checkMonthValidity(month);
        checkDayValidity(year, month, day);

        this.year = year;
        this.month = month;
        this.day = day;
    }

    private void alterMonth(int n) {
        // !!! doesn't change day !!!
        int m = month + n - 1;
        year += m / MONTHS_IN_YEAR + ((m < 0)? -1 : 0);
        month = ((m % MONTHS_IN_YEAR) + MONTHS_IN_YEAR) % MONTHS_IN_YEAR + 1;
    }

    public void changeYearBy(int n) {
        year += n;
        day = 1;
    }

    public void changeMonthBy(int n) {
        alterMonth(n);
        day = 1;
    }

    public void changeDayBy(int n) {
        int d = day + n;
        while (d < 1) {
            alterMonth(-1);
            d += daysInMonth(year, month);
        }
        while (d > daysInMonth(year, month)) {
            d -= daysInMonth(year, month);
            alterMonth(1);
        }
        day = d;
    }

    public void prevYear() {
        changeYearBy(-1);
    }

    public void nextYear() {
        changeYearBy(1);
    }

    public void prevMonth() {
        changeMonthBy(-1);
    }

    public void nextMonth() {
        changeMonthBy(1);
    }

    public void prevWeek() {
        changeDayBy(-7);
    }

    public void nextWeek() {
        changeDayBy(7);
    }

    public void prevDay() {
        changeDayBy(-1);
    }

    public void nextDay() {
        changeDayBy(1);
    }

    public String display() {
        StringBuilder output = new StringBuilder();

        // title
        output.append(String.format("%s %d", nameOfMonth(month), year));

        // display heading
        output.append(String.format("%n"));
        for (int i = 1; i <= DAYS_IN_WEEK; i++) {
            output.append(
                    String.format(" %" + COL_WIDTH + "s ", shortNameOfDay(i)));
        }

        // display
        boolean beforeMonthRange = true;
        boolean afterMonthRange = false;
        int dayCounter = 0;
        boolean currentDay;
        while (!afterMonthRange) {
            output.append(String.format("%n"));
            for (int i = 1; i <= DAYS_IN_WEEK; i++) {
                if (i == dayOfWeek(year, month, 1)) {
                    beforeMonthRange = false;
                }

                if (!beforeMonthRange && !afterMonthRange) {
                    dayCounter++;
                    currentDay = dayCounter == day;
                    output.append(currentDay ? "[" : " ");
                    output.append(
                            String.format("%" + COL_WIDTH + "d", dayCounter));
                    output.append(currentDay ? "]" : " ");
                } else {
                    output.append(String.format(" %" + COL_WIDTH + "s ", ""));
                }
                
                if (dayCounter == daysInMonth(year, month)) {
                    afterMonthRange = true;
                }
            }
        }

        return output.toString();
    }

    @Override
    public String toString() {
        return "Calendar{" + isoDate(year, month, day) + "}";
    }
    
}
