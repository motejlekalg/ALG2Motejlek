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
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {       
        System.out.println("(A)");
        Calendar a = new Calendar(2020, 4, 15);
        System.out.println(a);
        System.out.println(a.display());
        a.prevDay();
        System.out.println(a.display());
        a.prevWeek();
        System.out.println(a.display());
        a.prevWeek();
        System.out.println(a.display());
        
        System.out.println("(B)");
        Calendar b = new Calendar(2020, 3, 31);
        System.out.println(b);
        System.out.println(b.display());
        b.changeDayBy(-5);
        System.out.println(b.display());
        b.changeDayBy(12);
        System.out.println(b.display());
        b.nextDay();
        System.out.println(b.display());
        b.nextWeek();
        System.out.println(b.display());
        
        System.out.println("(C)");
        Calendar c = new Calendar(2018, 12, 7);
        System.out.println(c);
        System.out.println(c.display());
        c.prevMonth();
        System.out.println(c.display());
        c.changeMonthBy(3);
        System.out.println(c.display());
        c.nextYear();
        System.out.println(c.display());
        c.nextYear();
        System.out.println(c.display());
        
        System.out.println("(D)");
        Calendar d = new Calendar(2000, 2, 29);
        System.out.println(d);
        System.out.println(d.display());
        d.setDate(2100, 2, 28);
        System.out.println(d.display());
        d.prevYear();
        System.out.println(d.display());
        d.nextMonth();
        System.out.println(d.display());
        d.changeMonthBy(-10);
        System.out.println(d.display());
        
        System.out.println("(E)");
        Calendar e = new Calendar(2020, 4, 15);
        System.out.println(e);
        System.out.println(e.display());
        e.changeMonthBy(24);
        System.out.println(e.display());
        e.changeMonthBy(-36);
        System.out.println(e.display());
        e.changeDayBy(-89);
        System.out.println(e.display());
        e.changeDayBy(366);
        System.out.println(e.display());
        
        System.out.println("(F)");
        Calendar f = new Calendar(2020, 6, 2);
        System.out.println(f);
        System.out.println(Calendar.daysInYear(f.getYear()));
        System.out.println(f.display());
        f.changeYearBy(-3);
        System.out.println(Calendar.daysInYear(f.getYear()));
        System.out.println(f.display());
        f.changeYearBy(19);
        System.out.println(Calendar.daysInYear(f.getYear()));
        System.out.println(f.display());
        f.setDate(1700, 7, 22);
        System.out.println(Calendar.daysInYear(f.getYear()));
        System.out.println(f.display());
        f.setDate(2000, 11, 30);
        System.out.println(Calendar.daysInYear(f.getYear()));
        System.out.println(f.display());
    }
    
}
