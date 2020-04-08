/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fractions;

/**
 * Fractions
 * @author Martin Motejlek
 */
public class FractionsCalculator {
    
    private FractionsCalculator() {}
    
    public static Fraction add(Fraction a, Fraction b) {
        Fraction aSimple = a.simplify();
        Fraction bSimple = b.simplify();
        
        Fraction sum = Fraction.getInstance(
                aSimple.getNominator() * bSimple.getDenominator() +
                        bSimple.getNominator() * aSimple.getDenominator(),
                aSimple.getDenominator() * bSimple.getDenominator());
        
        return sum.simplify();
    }
    
    public static Fraction subtract(Fraction a, Fraction b) {
        Fraction minusB = Fraction.getInstance(
                -b.getNominator(),
                b.getDenominator());
        
        return add(a, minusB);
    }
    
    public static Fraction multiply(Fraction a, Fraction b) {
        return Fraction.getInstance(
                a.getNominator() * b.getNominator(),
                a.getDenominator() * b.getDenominator());
    }
    
    public static Fraction divide(Fraction a, Fraction b) {
        return Fraction.getInstance(
                a.getNominator() * b.getDenominator(),
                a.getDenominator() * b.getNominator());
    }
    
//    public static void main(String[] args) {
//        Fraction a = Fraction.getInstance(3, 4);
//        Fraction b = Fraction.getInstance(0, 1);
//        Fraction c = Fraction.getInstance("-3/8");
//        Fraction d = Fraction.getInstance("-1/20");
//        Fraction e = Fraction.getInstance(5, 2);
//        
//        System.out.println(add(a, b));
//        System.out.println(add(a, c));
//        System.out.println(subtract(e, a));
//        System.out.println(multiply(c, a));
//        System.out.println(multiply(b, d));
//        System.out.println(divide(e, a));
//    }
    
}