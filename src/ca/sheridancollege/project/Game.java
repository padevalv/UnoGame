package ca.sheridancollege.project;

import java.util.ArrayList;

/**
 * The class that models your game. You should create a more specific child of this class and instantiate the methods
 * given.
 *
 * @author dancye
 * @author Paul Bonenfant Jan 2020
 */
public abstract class Game {

    private final String name;//the title of the game
    private ArrayList<Player> players;// the players of the game

    public Game(String name) {
        this.name = name;
        players = new ArrayList();
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the players of this game
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * @param players the players of this game
     */
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    /**
     * Play the game. This might be one method or many method calls depending on your game.
     */
    public abstract void play();

    /**
     * When the game is over, use this method to declare and display a winning player.
     */
    public abstract void declareWinner();

}//end class

/**
 * A class representing an UNO game.
 */
class UnoGame extends Game {
    private UnoGroupOfCards deck;
    private ArrayList<UnoPlayer> players;

    public UnoGame(String name, UnoGroupOfCards deck, ArrayList<UnoPlayer> players) {
        super(name);
        this.deck = deck;
        this.players = players;
    }
    @Override
    public void declareWinner() {
        for (UnoPlayer player : players) {
            if (player.getHand().isEmpty()) {
                System.out.println("The winner is: " + player.getName());
                break;
            }
        }
    }

    @Override
    public void play() {
        deck.shuffle();
        // Deal 7 cards to each player
        for (UnoPlayer player : players) {
            for (int i = 0; i < 7; i++) {
                player.drawCard(deck.drawCardFromDeck());
            }
        }
        // Main game loop
        boolean gameWon = false;
        while (!gameWon) {
            for (UnoPlayer player : players) {
                player.play();
                if (player.getHand().isEmpty()) {
                    gameWon = true;
                    declareWinner();
                    break;
                }
            }
        }
    }   

}
