/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motejlek_bank.bank;

import java.util.ArrayList;

/**
 *
 * @author Martin Motejlek
 */
public abstract class Client {
    
    // data
    String name;
    ArrayList<Account> accounts = new ArrayList<>();
    
    // constructor
    public Client(String name) {
        if (name.length() == 0) {
            throw new IllegalArgumentException("Jmeno nesmi byt prazdny retezec.");
        }
        
        this.name = name;
    }
    
    // methods
    public double getTotal() {
        double total = 0;
        for (Account account : accounts) {
            total += account.getBalance();
        }
        return total;
    }
    
    public abstract String getName();
    
    public void addAccount(Account account) {
        accounts.add(account);
    }

    @Override
    public String toString() {
        return "Client{" + "name=" + name + ", accounts=" + accounts + '}';
    }
    
}