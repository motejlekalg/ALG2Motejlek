/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elevens.app.generic;

/**
 *
 * @author Martin Motejlek
 */
public abstract class Card {
    
    // estatic
    public static enum Suit {
        HEARTS, DIAMONDS, CLUBS, SPADES
    }
    
    public static enum Rank {
        A, N2, N3, N4, N5, N6, N7, N8, N9, N10, J, Q, K
    }
    
    private static final int SUIT_FORMAT_SIZE = 5;
    private static final int RANK_FORMAT_SIZE = 2;
    
    private static String suitFormat(Suit suit) {
        switch (suit) {
            case HEARTS:
                return "srdce";
            case DIAMONDS:
                return "kára";
            case CLUBS:
                return "kříže";
            case SPADES:
                return "piky";
        }
        // never happens
        throw new Error("Suit does not have assigned value.");
    }
    
    private static String rankFormat(Rank rank) {
        switch (rank) {
            case A:
                return "A";
            case N2:
                return "2";
            case N3:
                return "3";
            case N4:
                return "4";
            case N5:
                return "5";
            case N6:
                return "6";
            case N7:
                return "7";
            case N8:
                return "8";
            case N9:
                return "9";
            case N10:
                return "10";
            case J:
                return "J";
            case Q:
                return "Q";
            case K:
                return "K";
        }
        // never happens
        throw new Error("Rank does not have assigned value.");
    }
    
    // data
    protected final Suit suit;
    protected final Rank rank;
    
    // constructor
    protected Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    // methods
    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Card other = (Card) obj;
        if (this.suit != other.suit) {
            return false;
        }
        if (this.rank != other.rank) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Card{" + "suit=" + suit + ", rank=" + rank + '}';
    }
    
    public String getDescription() {
        return String.format(
                "%" + SUIT_FORMAT_SIZE + "s %" + RANK_FORMAT_SIZE + "s", 
                suitFormat(suit), rankFormat(rank));
    }
        
}
