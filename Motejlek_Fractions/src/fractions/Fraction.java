/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fractions;

/**
 * Fraction
 * @author Martin Motejlek
 */
public class Fraction {

    private final int nominator;
    private final int denominator;
    
    private Fraction(int nominator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Jmenovatel nesmí být 0.");
        }
        
        // make sign consistently stick with nominator
        this.nominator = nominator * Integer.signum(denominator);
        this.denominator = Math.abs(denominator);
    }
    
    public static Fraction getInstance(int nominator, int denominator) {
        return new Fraction(nominator, denominator);
    }
    
    public static Fraction getInstance(String fraction) {
        String[] numbers = fraction.split("/");
        
        if (numbers.length != 2) {
            throw new IllegalArgumentException("Špatně zapsaný zlomek.");
        }
        
        return new Fraction(
                Integer.parseInt(numbers[0]), 
                Integer.parseInt(numbers[1]));
    }
    
    public int getNominator() {
        return nominator;
    }
    
    public int getDenominator() {
        return denominator;
    }
    
    public Fraction simplify() {
        // Euclidean algorithm
        int u = Math.abs(nominator);
        int w = denominator;
        int r;
        
        while (w != 0) {
            r = u % w;
            u = w;
            w = r;
        }
        
        return new Fraction(nominator / u, denominator / u);
    }
    
    @Override
    public String toString() {
        return (nominator < 0 ? "-" : "") 
                + Math.abs(nominator) + "/" + Math.abs(denominator);
    }
    
//    public static void main(String[] args) {
//        Fraction a = Fraction.getInstance(-3, 4);
//        //Fraction b = Fraction.getInstance(0, 1);
//        Fraction b = Fraction.getInstance(0, -1);
//        Fraction c = Fraction.getInstance("-54/-72");
//        //Fraction d = Fraction.getInstance("-1/20");
//        Fraction d = Fraction.getInstance("1/-20");
//        
//        System.out.println(a);
//        System.out.println(b);
//        System.out.println(c);
//        System.out.println(d);
//        System.out.println(c.simplify());
//        System.out.println(d.simplify());
//        System.out.println(a.getNominator());
//        System.out.println(d.getDenominator());
//        System.out.println(Fraction.getInstance(d.toString()).getNominator());
//        
//        //Fraction.getInstance("-20/0");
//        //Fraction.getInstance(4, 0);
//    }
    
}
