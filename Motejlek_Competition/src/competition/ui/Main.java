/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package competition.ui;

import competition.app.Competition;
import competition.utils.IllegalFilenameException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Martin Motejlek
 */
public class Main {

    public static Scanner sc = new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Competition c = new Competition();
        System.out.println("Zadejte jména vstupních souborů:");
        try {
            while(true) {
                try {
                    String startFile = sc.next();
                    String finishFile = sc.next();
                    c.load(startFile, finishFile);
                    break;
                } catch (FileNotFoundException e) {
                    System.out.println("Zadaný soubor neexistuje. Zadejte znovu.");
                    System.out.println(e.getMessage());
                } catch (IllegalFilenameException e) {
                    System.out.println("Neplatný název souboru. Zadejte znovu.");
                    System.out.println(e.getMessage());
                }
            }
            System.out.println(c.getResults());

            System.out.println("Zadejte jméno výsledného souboru:");
            while(true) {
                try {
                    String resultFile = sc.next();
                    c.saveResults(resultFile);
                    break;
                } catch (IllegalFilenameException e) {
                    System.out.println("Neplatný název souboru. Zadejte znovu.");
                    System.out.println(e.getMessage());
                }
            }
            System.out.println("Data byla uložena.");
        } catch (IOException e) {
            System.out.println("Chyba pri cteni a zapisu.");
        }
    }
    
}
