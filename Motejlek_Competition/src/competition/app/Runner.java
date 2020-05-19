/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package competition.app;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Martin Motejlek
 */
public class Runner implements Comparable<Runner> {
    
    public static final DateTimeFormatter dtfStart
            = DateTimeFormatter.ofPattern("HH:mm:ss");
    public static final DateTimeFormatter dtfFinish
            = DateTimeFormatter.ofPattern("HH:mm:ss:SSS");
    
    private String firstName;
    private String lastName;
    private int number;
    private LocalTime startTime;
    private LocalTime finishTime;

    public Runner(String firstName, String lastName, int number) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getNumber() {
        return number;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getFinishTime() {
        return finishTime;
    }
    
    public String getStartTimeString() {
        return startTime.format(dtfStart);
    }
    
    public String getFinishTimeString() {
        return finishTime.format(dtfFinish);
    }
    
    public void setStartTime(String startTime) {
        this.startTime = LocalTime.parse(startTime, dtfStart);
    }
    
    public void setStartTime(int startTime) {
        this.startTime = LocalTime.ofSecondOfDay(startTime);
    }
    
    public void setFinishTime(String finishTime) {
        this.finishTime = LocalTime.parse(finishTime, dtfFinish);
    }
    
    public void setFinishTime(LocalTime finishTime) {
        this.finishTime = finishTime;
    }
    
    public void setFinishTime(int finishTime) {
        this.finishTime = LocalTime.ofNanoOfDay(finishTime);
    }
    
    public LocalTime runningTime() {
        return finishTime.minusNanos(startTime.toNanoOfDay());
    }
    
    public String getRunningTimeString() {
        return runningTime().format(dtfFinish);
    }
    
    @Override
    public String toString() {
        return String.format(
                "%4d %-10s %-10s %s %s %s",
                number,
                firstName,
                lastName,
                getStartTimeString(),
                getFinishTimeString(),
                getRunningTimeString()
        );
    }

    @Override
    public int compareTo(Runner o) {
        return this.runningTime().compareTo(o.runningTime());
    }
    
//    public static void main(String[] args) {
//        Runner r = new Runner("Alice", "Mala", 23);
//        r.setStartTime("09:00:00");
//        r.setFinishTime("10:20:10:019");
//        System.out.println(r);
//    }
    
}
