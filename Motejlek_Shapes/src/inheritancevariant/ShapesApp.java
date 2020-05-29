/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inheritancevariant;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Martin Motejlek
 */
public class ShapesApp {
    
    public static Scanner sc = new Scanner(System.in);
    public static ArrayList<Shape> shapes = new ArrayList<>();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int choice;
        
        do {
            displayMenu();
            choice = readChoice();
            switch (choice) {
                case 0:
                    break;
                case 1:
                    clearObjects();
                    break;
                case 2:
                    addSquare();
                    break;
                case 3:
                    addRectangle();
                    break;
                case 4:
                    addCircle();
                    break;
                case 5:
                    printObjects();
                    break;
                case 6:
                    computeArea();
                    break;
                case 7:
                    findWithMaxArea();
                    break;
                case 8:
                    getObjectInfo();
                    break;
                case 9: 
                    sortByArea();
                    break;
                default:
                    System.out.println("Chybná volba.");
            }
        } while (choice != 0);
    }

    private static void displayMenu() {
        System.out.println("");
        System.out.println("1. Nová sada");
        System.out.println("2. Přidej čtverec");
        System.out.println("3. Přidej obdélník");
        System.out.println("4. Přidej kruh");
        System.out.println("5. Vypiš geometrické objekty");
        System.out.println("6. Vypočti celkovou plochu");
        System.out.println("7. Vypiš objekt s největší plochou");
        System.out.println("8. Vypiš plochu objektu");
        System.out.println("9. Seřaď objekty podle obsahu plochy");
        System.out.println("0. Konec programu");
    }

    private static int readChoice() {
        System.out.print("Zadejte volbu: ");
        int choice = sc.nextInt();
        System.out.println("");
        return choice;
    }

    private static void clearObjects() {
        shapes.clear();
        System.out.println("Všechny geometrické útvary byly vymazány.");
    }

    private static void addSquare() {
        System.out.println("Zadejte rozměr strany čtverce:");
        double a = sc.nextDouble();
        shapes.add(new Square(a));
    }

    private static void addRectangle() {
        System.out.println("Zadejte rozměr první strany obdélníku:");
        double a = sc.nextDouble();
        System.out.println("Zadejte rozměr druhé strany obdélníku:");
        double b = sc.nextDouble();
        shapes.add(new Rectangle(a, b));
    }

    private static void addCircle() {
        System.out.println("1. Poloměr");
        System.out.println("2. Průměr");
        int choice = readChoice();
        
        switch (choice) {
            case 1:
                System.out.println("Zadejte poloměr kruhu:");
                double r = sc.nextDouble();
                shapes.add(Circle.getInstanceR(r));
                break;
            case 2:
                System.out.println("Zadejte průměr kruhu:");
                double d = sc.nextDouble();
                shapes.add(Circle.getInstanceD(d));
                break;
            default:
                System.out.println("Neplatná volba.");
        }
    }

    private static void printObjects() {
        for (Shape shape : shapes) {
            printWithArea(shape);
        }
    }

    private static void computeArea() {
        double area = 0;
        for (Shape shape : shapes) {
            area += shape.computeArea();
        }
        System.out.printf("Celková plocha geometrických útvarů je %.2f.%n", area);
    }

    private static void findWithMaxArea() {
        if (checkEmpty()) return;
        
        Shape max_shape = shapes.get(0);
        for (int i = 0; i < shapes.size(); i++) {
            if (shapes.get(i).computeArea() > max_shape.computeArea()){
                max_shape = shapes.get(i);
            }
        }
        
        printWithArea(max_shape);
    }

    private static void getObjectInfo() {
        if (checkEmpty()) return;
        
        for (int i = 0; i < shapes.size(); i++) {
            System.out.println((i + 1) + ". " + shapes.get(i));
        }
        
        int choice = readChoice();
        if (choice > 0 && choice <= shapes.size()) {
            printWithArea(shapes.get(choice - 1));
        } else {
            System.out.println("Neplatná volba.");
        }
    }
    
    private static void sortByArea() {
        if (checkEmpty()) return;
        
        Collections.sort(shapes);
        System.out.println("Objekty byly seřazeny podle obsahu plochy.");
    }
    
    private static void printWithArea(Shape shape) {
        System.out.println(shape + 
                String.format(", area = %.2f", shape.computeArea()));
    }
    
    private static boolean checkEmpty() {
        if (shapes.isEmpty()) {
            System.out.println("Seznam objektů je prázdný.");
            return true;
        }
        return false;
    }
    
}
