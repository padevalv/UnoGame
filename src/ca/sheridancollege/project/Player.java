/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 * A class that models each Player in the game. Players have an identifier, which should be unique.
 *
 * @author dancye
 * @author Paul Bonenfant Jan 2020
 */
public abstract class Player {

    private String name; //the unique name for this player

    /**
     * A constructor that allows you to set the player's unique ID
     *
     * @param name the unique ID to assign to this player.
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * @return the player name
     */
    public String getName() {
        return name;
    }

    /**
     * Ensure that the playerID is unique
     *
     * @param name the player name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * The method to be overridden when you subclass the Player class with your specific type of Player and filled in
     * with logic to play your game.
     */
    public abstract void play();

}

 class UnoPlayer extends Player {
    private ArrayList<UnoCard> hand;

    public UnoPlayer(String name) {
        super(name);
        this.hand = new ArrayList<>();
    }
    

    @Override
    public void play() {
      //Show the player's hand
        System.out.println(getName() + "'s turn. Your hand: " + hand);

        // Get the top card of the discard pile 
        UnoCard topCard = getTopCardOfDiscardPile();

        //Check if the player can play a card
        UnoCard cardToPlay = null;
        for (UnoCard card : hand) {
            if (canPlayCard(card, topCard)) {
                cardToPlay = card;
                break;
            }
        }

        if (cardToPlay != null) {
            //plays card
            playCard(cardToPlay);
            System.out.println(getName() + " played: " + cardToPlay);
        } else {
            //Draw a card if no player can play a card
            UnoCard drawnCard = drawCardFromDeck();
            hand.add(drawnCard);
            System.out.println(getName() + " drew a card: " + drawnCard);
        }
    }
    private UnoCard getTopCardOfDiscardPile() {
        // gets a fake card CHANGE THIS LATER
        return new UnoCard("Red", "5");
    }
    private boolean canPlayCard(UnoCard card, UnoCard topCard) {
        // Check if the card can be played on the top card of the discard pile
        return card.getColor().equals(topCard.getColor()) || card.getValue().equals(topCard.getValue()) || card.getColor().equals("Wild");
    }
    private UnoCard drawCardFromDeck() {
        //gets a fake card CHANGE THIS LATER
        return new UnoCard("Blue", "2");
    }

    public void drawCard(UnoCard card) {
        hand.add(card);
    }

    public void playCard(UnoCard card) {
        hand.remove(card);
    }
    public ArrayList<UnoCard> getHand() {
    return hand;
}


}


