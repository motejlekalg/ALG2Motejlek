/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elevens.app;

import elevens.app.generic.Card;

/**
 *
 * @author Martin Motejlek
 */
public class ElevensCard extends Card {

    // static
    public static int compValue(Rank rank) {
        switch (rank) {
            case A:
                return 1;
            case N2:
                return 2;
            case N3:
                return 3;
            case N4:
                return 4;
            case N5:
                return 5;
            case N6:
                return 6;
            case N7:
                return 7;
            case N8:
                return 8;
            case N9:
                return 9;
            case N10:
                return 10;
            case J:
                return 11;
            case Q:
                return 12;
            case K:
                return 13;
        }
        // never happens
        throw new Error("Rank does not have assigned value.");
    }

    // data
    private final int value;

    // constructor
    public ElevensCard(Suit suit, Rank rank) {
        super(suit, rank);
        value = compValue(rank);
    }

    // methods
    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "ElevenCard{" + "suit=" + suit + ", rank=" + rank
                + ", pointValue=" + value + '}';
    }

}
