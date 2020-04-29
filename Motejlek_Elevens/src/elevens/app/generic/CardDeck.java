/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elevens.app.generic;

import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author Martin Motejlek
 */
public class CardDeck {
    
    // data
    private Random rng;
    private LinkedList<Card> deck;
    
    // constructor
    public CardDeck() {
        rng = new Random();
        deck = new LinkedList<>();
    }
    
    // methods
    public int size() {
        return deck.size();
    }
    
    public void addToBottom(Card card) {
        deck.add(card);
    }
    
    public Card deal() {
        return deck.pollFirst();
    }
    
    public void shuffle() {
        int j;
        Card temp;
        for (int i = deck.size() - 1; i > 0; i--) {
            j = rng.nextInt(i);
            temp = deck.get(i);
            deck.set(i, deck.get(j));
            deck.set(j, temp);
        }
    }

    @Override
    public String toString() {
        return "CardDeck{" + "deck=" + deck + '}';
    }
    
}
