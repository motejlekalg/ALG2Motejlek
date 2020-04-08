/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package motejlek_bank.app;

import motejlek_bank.bank.*;

/**
 *
 * @author Martin Motejlek
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Client[] clients = new Client[3];
        
        clients[0] = new Person("Pekař");
        clients[1] = new Person("Švecová");
        clients[2] = new Company("Škoda");
        
        clients[0].addAccount(new Account(1000));
        clients[0].addAccount(new Account(500));
        clients[1].addAccount(new Account(1200));
        clients[2].addAccount(new Account(120));
        
        for (Client client : clients) {
            System.out.printf("%s má v bance uloženo celkem %.2f Kč%n",
                    client.getName(), client.getTotal());
        }
    }
    
}