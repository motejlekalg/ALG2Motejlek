/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elevens.cli;

import elevens.app.ElevensGame;
import elevens.provided.BoardInterface;
import java.util.Scanner;

/**
 *
 * @author Martin Motejlek
 */
public class CLI {
    
    // static
    private final int COLS = 3;
    private final int IDX_SIZE = 1;
    private final int DESC_SIZE = 8;

    // data
    private Scanner sc = new Scanner(System.in);
    private BoardInterface game;
    private boolean[] selected;
    
    // methods
    public void run() {
        boolean end;
        boolean quit = false;
        while (!quit) {
            game = new ElevensGame();
            selected = new boolean[game.nCards()];
            
            end = false;
            while(!quit && !end) {
                displayBoard();
                if (checkWin()) {
                    end = true;
                    displayWin();
                } else if (checkLoss()) {
                    end = true;
                    displayLoss();
                } else {
                    displayHelp();
                    quit = executeInput();
                }
            }
            if (!quit) {
                quit = !askRepeat();
            }
        }
    }
    
    private void displayBoard() {
        StringBuilder board = new StringBuilder();
        board.append(String.format("%s%n", game.gameName()));
        int colCounter = 0;
        for (int i = 0; i < game.nCards(); i++) {
            board.append(String.format(
                    "%1s(%" + IDX_SIZE + "d) %-" + DESC_SIZE + "s%1s",
                    selected[i]? "[" : "",
                    i + 1,
                    game.getCardDescriptionAt(i),
                    selected[i]? "]" : ""
                    ));
            colCounter++;
            if (colCounter % COLS == 0 || i == game.nCards() - 1) {
                board.append(String.format("%n"));
            }
        }
        board.append(String.format("Počet zbývajících karet v balíku: %d",
                game.getDeckSize()));        
        System.out.println(board);
    }
    
    private void displayHelp() {
        System.out.println("Dostupné příkazy: vyber <pozice> (vybere pozici), zrus (zruší výběr všech pozic), ok (potvrdí výběr), konec.");
    }
    
    private void displayWin() {
        System.out.println("Vyhráli jste!");
    }
    
    private void displayLoss() {
        System.out.println("Prohráli jste!");
    }
    
    private boolean executeInput() {
        System.out.print("> ");
        String input = sc.next();
        boolean quitSignal = false;
        
        switch (input) {
            case "vyber":
                select();
                break;
            case "zrus":
                unselectAll();
                break;
            case "ok":
                confirm();
                break;
            case "konec":
                quitSignal = true;
                break;
            default:
                System.out.println("Neplatný příkaz.");
        }
        
        return quitSignal;
    }
    
    private boolean askRepeat() {
        System.out.println("Hrát znovu? (ano/ne)");
        String input = sc.next();
        if (input.equalsIgnoreCase("ano")) {
            return true;
        }
        return false;
    }

    private boolean checkWin() {
        return game.isWon();
    }
    
    private boolean checkLoss() {
        return !game.anotherPlayIsPossible();
    }
    
    private void select() {
        int i = sc.nextInt();
        i -= 1;
        if (i < 0 || i >= selected.length) {
            System.out.println("Neplatná pozice.");
        } else {
            selected[i] = !selected[i];
        }
    }
    
    private void unselectAll() {
        for (int i = 0; i < selected.length; i++) {
            selected[i] = false;
        }
    }
    
    private void confirm() {
        int count = 0;
        for (int i = 0; i < selected.length; i++) {
            if (selected[i]) count++;
        }
        int[] iSelected = new int[count];
        int j = 0;
        for (int i = 0; i < selected.length; i++) {
            if (selected[i]) {
                iSelected[j] = i;
                j++;
            }
        }
        game.playAndReplace(iSelected);
        unselectAll();
    }
    
}
