package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * A class representing the Uno deck.
 */
class UnoDeck extends GroupOfCards {

    private ArrayList<UnoCard> discardPile;

    public UnoDeck() {
        super(108);
        cards = new ArrayList<>();
        discardPile = new ArrayList<>();
        initializeDeck();
    }

    private void initializeDeck() {
        for (UnoCard.Color color : UnoCard.Color.values()) {
            if (color == UnoCard.Color.WILD) {
                for (int i = 0; i < 4; i++) {
                    cards.add(new UnoCard(color, UnoCard.Value.WILD_DRAW_FOUR));
                }
            } else {
                for (UnoCard.Value value : UnoCard.Value.values()) {
                    if (value != UnoCard.Value.WILD_DRAW_FOUR) {
                        cards.add(new UnoCard(color, value));
                        if (value != UnoCard.Value.ZERO) {
                            cards.add(new UnoCard(color, value));
                        }
                    }
                }
            }
        }
        shuffle();
    }

    public UnoCard drawCard() {
        if (cards.isEmpty()) {
            reshuffleDiscardPile();
        }
        if (!cards.isEmpty()) {
            return (UnoCard) cards.remove(0);
        }
        throw new IllegalStateException("No cards left to draw, and discard pile is empty!");
    }

    public void addToDiscardPile(UnoCard card) {
        discardPile.add(card);
    }

    private void reshuffleDiscardPile() {
        if (discardPile.isEmpty()) {
            throw new IllegalStateException("No cards left in discard pile to reshuffle!");
        }
        cards.addAll(discardPile);
        discardPile.clear();
        shuffle();
    }

    public UnoCard getTopCard() {
        return discardPile.isEmpty() ? null : discardPile.get(discardPile.size() - 1);
    }
}
