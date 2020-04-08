/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motejlek_bank.bank;

/**
 *
 * @author Martin Motejlek
 */
public class Person extends Client {
    
    // constructor
    public Person(String name) {
        super(name);
    }
    
    // methods
    @Override
    public String getName() {
        return "pan" + (name.matches(".*ová") ? "í" : "") + " " + name;
    }
    
//    public static void main(String[] args) {
//        //new Person("");
//        Person a = new Person("Hjk");
//        Person b = new Person("asdfgová");
//        System.out.println(a);
//        a.addAccount(new Account(4));
//        b.addAccount(new Account());
//        b.addAccount(new Account(100.4));
//        b.addAccount(new Account(20.3));
//        System.out.println(a);
//        System.out.println(b);
//        System.out.println(b.getTotal());
//        System.out.println(a.getName());
//        System.out.println(b.getName());
//    }
    
}
