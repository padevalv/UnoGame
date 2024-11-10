/**
 * SYST 17796 Project Base code.
 * Students can modify and extend to implement their game.
 * Add your name as an author and the date!
 */
package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A concrete class that represents any grouping of cards for a Game. HINT, you might want to subclass this more than
 * once. The group of cards has a maximum size attribute which is flexible for reuse.
 *
 * @author Deval Keenan Yesha November 9th 2024
 */
public class GroupOfCards {

    //The group of cards, stored in an ArrayList
    private ArrayList<Card> cards;
    private int size;//the size of the grouping

    public GroupOfCards(int size) {
        this.size = size;
        this.cards = new ArrayList<>();
        
    }

    /**
     * A method that will get the group of cards as an ArrayList
     *
     * @return the group of cards.
     */
    public ArrayList<Card> getCards() {
        return cards;
    }
     public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * @return the size of the group of cards
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the max size for the group of cards
     */
    public void setSize(int size) {
        this.size = size;
    }

}//end class

class UnoGroupOfCards extends GroupOfCards {

    public UnoGroupOfCards(int size) {
        super(size);//group of cards constructor
        initializeDeck();
    }
    public UnoCard drawCardFromDeck() {
        if (getCards().isEmpty()) {
            //when the deck is empty
            return null;
        }
        return (UnoCard) getCards().remove(0);
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

        setCards(cards); 
    }

   
}


