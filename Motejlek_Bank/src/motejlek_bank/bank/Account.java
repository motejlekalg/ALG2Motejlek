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
public class Account {
    
    // data
    private double balance;
    
    // constructors
    public Account(double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Zustatek nesmi byt zaporny.");
        }
        
        this.balance = balance;
    }
    
    public Account() {
        this(0);
    }
    
    // methods
    public double getBalance() {
        return balance;
    }
    
    public void deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Hodnota nesmi byt zaporna.");
        }
        
        balance += amount;
    }
    
    public void withdraw(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Hodnota nesmi byt zaporna.");
        }
        
        if (balance < amount) {
            throw new IllegalArgumentException("Hodnota nesmi byt vetsi nez zustatek na uctu.");
        }
        
        balance -= amount;
    }

    @Override
    public String toString() {
        return "Account{" + "balance=" + balance + '}';
    }
    
//    public static void main(String[] args) {
//        Account a = new Account();
//        Account b = new Account(545.40);
//        
//        System.out.println(a);
//        System.out.println(b);
//        
//        a.deposit(234.20);
//        a.withdraw(122.10);
//        
//        System.out.println(a);
//        System.out.println(a.getBalance());
//        
//        //b.withdraw(600);
//        new Account(-55.6);
//        //b.deposit(-2.3);
//        //b.withdraw(-10.1);
//    }
    
}
