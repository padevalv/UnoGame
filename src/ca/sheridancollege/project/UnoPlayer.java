package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * A class representing a player in the Uno game.
 */
class UnoPlayer extends Player {

    private ArrayList<UnoCard> hand;

    public UnoPlayer(String name) {
        super(name);
        this.hand = new ArrayList<>();
    }

    public ArrayList<UnoCard> getHand() {
        return hand;
    }

    public void drawCard(UnoCard card) {
        hand.add(card);
    }

    public void playCard(UnoCard card) {
        hand.remove(card);
    }

    @Override
    public void play() {
        // Logic for the player to play a card (to be implemented)
        System.out.println(getName() + " is playing their turn.");
    }
}