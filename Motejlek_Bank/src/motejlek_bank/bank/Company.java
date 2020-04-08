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
public class Company extends Client {
    
    // constructor
    public Company(String name) {
        super(name);
    }
    
    // methods
    @Override
    public String getName() {
        return "firma " + name;
    }
    
}
