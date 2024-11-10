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

public class UnoGroupOfCards extends GroupOfCards {

    public UnoGroupOfCards(int size) {
        super(size);//group of cards constructor
        initializeDeck();
    }

    private void initializeDeck() {
        ArrayList<Card> cards = new ArrayList<>();
        String[] colors = {"Red", "Yellow", "Green", "Blue"};
        String[] values = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "Skip", "Reverse", "Draw Two"};

        for (String color : colors) {
            for (String value : values) {
                cards.add(new UnoCard(color, value));
                if (!value.equals("0")) {
                    cards.add(new UnoCard(color, value)); // Two of each card except 0
                }
            }
        }

        // Add special cards (Wild, Wild Draw Four)
        for (int i = 0; i < 4; i++) {
            cards.add(new UnoCard("Wild", "Wild"));
            cards.add(new UnoCard("Wild", "Wild Draw Four"));
        }

        setCards(cards); // Use the setter method to set the cards
    }

    // Additional methods specific to UNO can be added here
}

