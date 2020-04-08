/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package polynomials;

/**
 * Polynomials
 * @author Martin Motejlek
 */
public class Polynomials {
    
    private Polynomials() {} // private constructor
    
    public static Polynomial sum(Polynomial a, Polynomial b) {
        Polynomial max = a.getDegree() > b.getDegree() ? a : b;
        Polynomial min = a.getDegree() > b.getDegree() ? b : a;
        
        double[] sum = max.getCoef();
        
        for (int i = 0; i < min.getDegree() + 1; i++) {
            sum[i] += min.getCoefAt(i);
        }
        
        return Polynomial.getInstanceReverted(sum);
    }
    
    public static Polynomial multiply(Polynomial a, Polynomial b) {
        double[] product = new double[a.getDegree() + b.getDegree() + 1];
        
        for (int i = 0; i < a.getDegree() + 1; i++) {
            for (int j = 0; j < b.getDegree() + 1; j++) {
                product[i + j] += a.getCoefAt(i) * b.getCoefAt(j);
            }
        }
        
        return Polynomial.getInstanceReverted(product);
    }
    
//    public static void main(String[] args) {
//        Polynomial p1 = Polynomial.getInstance(3, 0, -2, 5);
//        Polynomial p2 = Polynomial.getInstance(-2, 1, 4);
//        System.out.println(sum(p1, p2));
//        System.out.println(multiply(p1, p2));
//    }
}