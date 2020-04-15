/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar;

import java.util.Scanner;

/**
 *
 * @author Martin Motejlek
 */
public class App {

    private static Scanner sc = new Scanner(System.in);
    private static Calendar c;

    /**
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Zadejte postupně číselně rok, měsíc a den:");
        int initYear = sc.nextInt();
        int initMonth = sc.nextInt();
        int initDay = sc.nextInt();
        sc.nextLine();
        c = new Calendar(initYear, initMonth, initDay);

        System.out.println();
        printMenu();

        boolean quitSignal;
        do {
            printCalendar();
            quitSignal = execute(readChoice());
        } while (!quitSignal);
    }

    private static void printMenu() {
        System.out.println("Seznam příkazů:");
        System.out.println("- predchozi rok/mesic/tyden/den");
        System.out.println("- dalsi rok/mesic/tyden/den");
        System.out.println("- posun o roky/mesice/dny");
        System.out.println("- nastavit");
        System.out.println("- pomoc");
        System.out.println("- konec");
    }

    private static void printCalendar() {
        System.out.println();
        System.out.println(c.display());
        System.out.println();
    }

    private static String readChoice() {
        System.out.print("> ");
        String input = sc.nextLine();
        System.out.println();
        return input;
    }
    
    private static boolean execute(String choice) {
        boolean quitSignal = false;
        switch (choice) {
            case "predchozi rok":
                c.prevYear();
                break;
            case "dalsi rok":
                c.nextYear();
                break;
            case "posun o roky":
                changeYearBy();
                break;
            case "predchozi mesic":
                c.prevMonth();
                break;
            case "dalsi mesic":
                c.nextMonth();
                break;
            case "posun o mesice":
                changeMonthBy();
                break;
            case "predchozi tyden":
                c.prevWeek();
                break;
            case "dalsi tyden":
                c.nextWeek();
                break;
            case "predchozi den":
                c.prevDay();
                break;
            case "dalsi den":
                c.nextDay();
                break;
            case "posun o dny":
                changeDayBy();
                break;
            case "nastavit":
                setDate();
                break;
            case "pomoc":
                printMenu();
                break;
            case "konec":
                quitSignal = true;
                break;
            default:
                System.out.println("Neplatný příkaz.");
        }
        return quitSignal;
    }

    private static void changeYearBy() {
        System.out.println("Zadejte počet let (záporný pro posun zpět):");
        int input = sc.nextInt();
        sc.nextLine();
        c.changeYearBy(input);
    }

    private static void changeMonthBy() {
        System.out.println("Zadejte počet měsíců (záporný pro posun zpět):");
        int input = sc.nextInt();
        sc.nextLine();
        c.changeMonthBy(input);
    }

    private static void changeDayBy() {
        System.out.println("Zadejte počet dní (záporný pro posun zpět):");
        int input = sc.nextInt();
        sc.nextLine();
        c.changeDayBy(input);
    }

    private static void setDate() {
        System.out.println("Zadejte postupně číselně rok, měsíc a den:");
        int year = sc.nextInt();
        int month = sc.nextInt();
        int day = sc.nextInt();
        sc.nextLine();
        c.setDate(year, month, day);
    }

}
