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

public class UnoPlayer extends Player {
    private ArrayList<UnoCard> hand;

    public UnoPlayer(String name) {
        super(name);
        this.hand = new ArrayList<>();
    }
    

    @Override
    public void play() {
        // Implement the logic for a player to play their turn in UNO
        // Delegate tasks to other classes/methods
    }

    public void drawCard(UnoCard card) {
        hand.add(card);
    }

    public void playCard(UnoCard card) {
        hand.remove(card);
    }

    // Additional methods to manage the player's hand
}

