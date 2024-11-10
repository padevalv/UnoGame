/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ca.sheridancollege.project;

/**
 *
 * @author diask
 */


import java.util.ArrayList;

public class UnoGame extends Game {
    private UnoGroupOfCards deck;
    private ArrayList<UnoPlayer> players;

    public UnoGame(String name, UnoGroupOfCards deck, ArrayList<UnoPlayer> players) {
        super(name);
        this.deck = deck;
        this.players = players;
    }

    @Override
    public void play() {
        // Implement the logic to play UNO
        // Delegate tasks to other classes/methods
    }

    @Override
    public void declareWinner() {
        // Implement the logic to declare the winner
        // Delegate tasks to other classes/methods
    }
}

