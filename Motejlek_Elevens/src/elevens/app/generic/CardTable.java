/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elevens.app.generic;

import java.util.Arrays;

/**
 *
 * @author Martin Motejlek
 */
public class CardTable {

    // data
    private Card[] table;

    public CardTable(int size) {
        table = new Card[size];
    }

    // methods
    public int size() {
        return table.length;
    }
    
    public Card get(int pos) {
        checkPos(pos);
        return table[pos];
    }
    
    public void place(int pos, Card card) {
        checkPos(pos);
        if (table[pos] != null) {
            throw new IllegalArgumentException("Position must be empty.");
        }        
        table[pos] = card;
    }
    
    public Card remove(int pos) {
        checkPos(pos);
        Card temp = table[pos];
        table[pos] = null;
        return temp;
    }

    public boolean isEmpty() {
        for (Card i : table) {
            if (i != null) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isEmpty(int pos) {
        checkPos(pos);
        return table[pos] == null;
    }
    
    private void checkPos(int pos) {
        if (pos < 0 || pos >= table.length) {
            throw new IllegalArgumentException("Position must be empty.");
        }
    }

    @Override
    public String toString() {
        return "CardTable{" + "table=" + Arrays.toString(table) + '}';
    }
    
}
