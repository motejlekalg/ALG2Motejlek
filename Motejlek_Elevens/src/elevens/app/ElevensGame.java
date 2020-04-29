/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elevens.app;

import elevens.app.generic.Card;
import elevens.app.generic.CardDeck;
import elevens.app.generic.CardTable;
import elevens.provided.BoardInterface;

/**
 *
 * @author Martin Motejlek
 */
public class ElevensGame implements BoardInterface {

    // static
    private static final String GAME_NAME = "Elevens";
    private static final int TABLE_SIZE = 9;

    private static CardDeck genFullDeck() {
        CardDeck deck = new CardDeck();
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                deck.addToBottom(new ElevensCard(suit, rank));
            }
        }
        return deck;
    }

    // data
    private CardDeck deck;
    private CardTable table;

    // constructor
    public ElevensGame() {
        deck = genFullDeck();
        deck.shuffle();

        table = new CardTable(TABLE_SIZE);
        for (int i = 0; i < table.size(); i++) {
            table.place(i, deck.deal());
        }
    }

    // methods
    private void replaceCards(int[] positions) {
        for (int i : positions) {
            table.remove(i);
            table.place(i, deck.deal());
        }
    }
    
    @Override
    public String gameName() {
        return GAME_NAME;
    }

    @Override
    public int nCards() {
        return TABLE_SIZE;
    }

    @Override
    public int getDeckSize() {
        return deck.size();
    }

    @Override
    public String getCardDescriptionAt(int index) {
        if (!table.isEmpty(index)) {
            return ((ElevensCard)table.get(index)).getDescription();
        }
        return "";
    }

    @Override
    public boolean anotherPlayIsPossible() {
        boolean hasJ = false, hasQ = false, hasK = false;
        for (int i = 0; i < table.size(); i++) {
            if (!table.isEmpty(i)) {
                switch (table.get(i).getRank()) {
                    case J:
                        hasJ = true;
                        break;
                    case Q:
                        hasQ = true;
                        break;
                    case K:
                        hasK = true;
                        break;
                }
            }
        }
        if (hasJ && hasQ && hasK) {
            return true;
        }
        
        int sum;
        for (int i = 0; i < table.size() - 1; i++) {
            if (!table.isEmpty(i)) {
                for (int j = i + 1; j < table.size(); j++) {
                    if (!table.isEmpty(j)) {
                        sum = 0;
                        sum += ((ElevensCard)table.get(i)).getValue();
                        sum += ((ElevensCard)table.get(j)).getValue();
                        if (sum == 11) {
                            return true;
                        }
                    }
                }
            }
        }
        
        return false;
    }

    @Override
    public boolean playAndReplace(int[] iSelectedCards) {
        for (int i : iSelectedCards) {
            if (table.isEmpty(i)) {
                return false;
            }
        }
        
        boolean success = false;
        if (iSelectedCards.length == 2) {
            int sum = 0;
            for (int i : iSelectedCards) {
                sum += ((ElevensCard)table.get(i)).getValue();
            }
            if (sum == 11) {
                success = true;
            }
        } else if (iSelectedCards.length == 3) {
            boolean hasJ = false, hasQ = false, hasK = false;
            for (int i : iSelectedCards) {
                switch (table.get(i).getRank()) {
                    case J:
                        hasJ = true;
                        break;
                    case Q:
                        hasQ = true;
                        break;
                    case K:
                        hasK = true;
                        break;
                }
            }
            if (hasJ && hasQ && hasK) {
                success = true;
            }            
        }
        if (success) {
            replaceCards(iSelectedCards);
        }
        return success;
    }

    @Override
    public boolean isWon() {
        return deck.size() == 0 && table.isEmpty();
    }

}
