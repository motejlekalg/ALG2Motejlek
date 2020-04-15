/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polynomials;

import java.util.Arrays;

/**
 * Polynomial
 * @author Martin Motejlek
 */
public class Polynomial {
    // immutable class

    // data
    private final double[] coef;
    
    // constructor
    private Polynomial(double[] coef) { // defensive copy
        double[] coefTemp = new double[coef.length];
        System.arraycopy(coef, 0, coefTemp, 0, coef.length);
        this.coef = coefTemp;
    }
    
    // factory methods
    public static Polynomial getInstanceReverted(double...coef) {
        return new Polynomial(coef);
    }
    
    public static Polynomial getInstance(double...coef) {
        double[] coefTemp = new double[coef.length];
        for (int i = 0; i < coef.length; i++) {
            coefTemp[coef.length - 1 - i] = coef[i];
        }
        return new Polynomial(coefTemp);
    }
    
    //methods
    public int getDegree() {
        return coef.length - 1;
    }
    
    public double getCoefAt(int exponent) {
        return coef[exponent];
    }
    
    public double[] getCoef() {
        return Arrays.copyOf(coef, coef.length); // defensive copy
    }

    public Polynomial derivate() {
        double[] derivative = new double[coef.length - 1];
        for (int i = 0; i < derivative.length; i++) {
            derivative[i] = (i + 1) * coef[i + 1];
        }
        return new Polynomial(derivative);
    }
    
    public double computeValue(double x) {
        double value = 0;
        for (int i = coef.length - 1; i >= 0; i--) {
            value = value * x + coef[i];
        }
        return value;
    }
    
    public double integrate(double a, double b) {
        double[] integratedCoef = new double[coef.length + 1];
        
        for (int i = 1; i < integratedCoef.length; i++) {
            integratedCoef[i] = coef[i - 1] / i;
        }
        
        Polynomial integrated = new Polynomial(integratedCoef);
        
        return integrated.computeValue(b) - integrated.computeValue(a);
    }
    
    @Override
    public String toString() {
        String output = "";        
        for (int i = coef.length - 1; i >= 0; i--) {
            if (coef[i] != 0) {
                if (i < coef.length - 1) {
                    output += " ";
                }
                
                if (coef[i] < 0) {
                    output += "- ";
                } else if (i < coef.length - 1) {
                    output += "+ ";
                }
                
                if (i > 0) { 
                    output += String.format("%.2fx^%d", Math.abs(coef[i]), i);
                } else {
                    output += String.format("%.2f", Math.abs(coef[i]));
                }
            }
        }
        return output;
    }
    
//    public static void main(String[] args) {
//        Polynomial p = Polynomial.getInstance(3, 0, -2, 5);
//        System.out.println(p);
//        System.out.println(p.derivate());
//        System.out.println(p.integrate(-3, 1));
//        System.out.println(p.computeValue(2));
//    }
}
