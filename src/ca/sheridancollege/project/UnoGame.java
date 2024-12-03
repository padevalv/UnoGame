package ca.sheridancollege.project;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class representing the Uno game.
 */
public class UnoGame extends Game {

    private UnoDeck deck;
    private ArrayList<UnoPlayer> players;
    private UnoCard topCard;
    private int currentPlayerIndex;
    private boolean isReverse;
    private int drawStack = 0;

    public UnoGame(String name) {
        super(name);
        this.players = new ArrayList<>();
        this.isReverse = false;
    }

    @Override
    public void play() {
        deck = new UnoDeck();
        topCard = deck.drawCard();
        deck.addToDiscardPile(topCard);

        System.out.println("Starting the game. Top card is: " + topCard);

        boolean gameWon = false;
        Scanner scanner = new Scanner(System.in);

        while (!gameWon) {
            UnoPlayer currentPlayer = players.get(currentPlayerIndex);
            System.out.println("\n" + currentPlayer.getName() + "'s turn.");
            System.out.println("Top card on the discard pile: " + topCard);
            System.out.println("Your hand: " + currentPlayer.getHand());

            boolean validMove = false;
            while (!validMove) {
                System.out.println("Choose an action:");
                System.out.println("1. Play a card");
                System.out.println("2. Draw a card");
                System.out.println("3. View hand");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        System.out.println("Select a card to play (index):");
                        for (int i = 0; i < currentPlayer.getHand().size(); i++) {
                            System.out.println(i + ": " + currentPlayer.getHand().get(i));
                        }
                        int cardIndex = scanner.nextInt();
                        if (cardIndex >= 0 && cardIndex < currentPlayer.getHand().size()) {
                            UnoCard chosenCard = currentPlayer.getHand().get(cardIndex);
                            if (isValidPlay(chosenCard)) {
                                currentPlayer.playCard(chosenCard);
                                deck.addToDiscardPile(chosenCard);
                                topCard = chosenCard;

                                // Handle special cards
                                if (chosenCard.getValue() == UnoCard.Value.WILD_DRAW_FOUR) {
                                    System.out.println("Choose a color for the next player (RED, GREEN, BLUE, YELLOW): ");
                                    Scanner colorScanner = new Scanner(System.in);
                                    String chosenColor = colorScanner.nextLine().toUpperCase();

                                    try {
                                        UnoCard.Color newColor = UnoCard.Color.valueOf(chosenColor);
                                        topCard = new UnoCard(newColor, UnoCard.Value.WILD_DRAW_FOUR);
                                        deck.addToDiscardPile(chosenCard);
                                        System.out.println(currentPlayer.getName() + " played WILD DRAW FOUR and chose " + chosenColor);
                                        validMove = true;
                                    } catch (IllegalArgumentException e) {
                                        System.out.println("Invalid color. Please choose a valid color (RED, GREEN, BLUE, YELLOW).");
                                    }
                                } else if (chosenCard.getValue() == UnoCard.Value.DRAW_TWO) {
                                    drawStack += 2;
                                } else if (chosenCard.getValue() == UnoCard.Value.SKIP) {
                                    skipNextPlayer();
                                } else if (chosenCard.getValue() == UnoCard.Value.REVERSE) {
                                    reverseOrder();
                                }

                                validMove = true;
                                System.out.println(currentPlayer.getName() + " played: " + chosenCard);
                            } else {
                                System.out.println("Invalid move. The card does not match the top card. Try again.");
                            }
                        } else {
                            System.out.println("Invalid selection. Try again.");
                        }
                        break;
                    case 2:
                        if (drawStack > 0) {
                            for (int i = 0; i < drawStack; i++) {
                                try {
                                    UnoCard drawnCard = deck.drawCard();
                                    currentPlayer.drawCard(drawnCard);
                                    System.out.println(currentPlayer.getName() + " drew a card: " + drawnCard);
                                } catch (IllegalStateException e) {
                                    System.out.println("No cards left to draw. The game will continue.");
                                }
                            }
                            drawStack = 0;
                            validMove = true; // End the turn after drawing
                        } else {
                            try {
                                UnoCard drawnCard = deck.drawCard();
                                currentPlayer.drawCard(drawnCard);
                                System.out.println(currentPlayer.getName() + " drew a card: " + drawnCard);
                                validMove = true; // End the turn after drawing
                            } catch (IllegalStateException e) {
                                System.out.println("No cards left to draw. The game will continue.");
                            }
                        }
                        break;
                    case 3:
                        System.out.println("Your hand: " + currentPlayer.getHand());
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }

            if (currentPlayer.getHand().isEmpty()) {
                declareWinner(currentPlayer);
                gameWon = true;
                break;
            }

            moveToNextPlayer();
        }
        scanner.close();
    }

    @Override
    public void declareWinner() {
        System.out.println("Game over. A winner has been declared.");
    }

    public void declareWinner(UnoPlayer winner) {
        System.out.println("Game over! The winner is " + winner.getName() + "!");
    }

    public void addPlayer(UnoPlayer player) {
        players.add(player);
    }

    private boolean isValidPlay(UnoCard card) {
        return card.getColor() == topCard.getColor()
                || card.getValue() == topCard.getValue()
                || card.getValue() == UnoCard.Value.WILD_DRAW_FOUR;
    }

    private void skipNextPlayer() {
        if (players.size() == 2) {
            return;
        }
        moveToNextPlayer();
    }

    private void reverseOrder() {
        isReverse = !isReverse;
    }

    private void moveToNextPlayer() {
        if (isReverse) {
            currentPlayerIndex = (currentPlayerIndex - 1 + players.size()) % players.size();
        } else {
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }
    }

    public static void main(String[] args) {
        UnoGame unoGame = new UnoGame("Uno");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of players (2-4): ");
        int playerCount = scanner.nextInt();

        while (playerCount < 2 || playerCount > 4) {
            System.out.println("Invalid number of players. Please enter a number between 2 and 4: ");
            playerCount = scanner.nextInt();
        }

        for (int i = 1; i <= playerCount; i++) {
            System.out.println("Enter the name for Player " + i + ": ");
            String name = scanner.next();
            UnoPlayer player = new UnoPlayer(name);
            unoGame.addPlayer(player);
        }

        // Initialize the deck
        unoGame.deck = new UnoDeck();

        // Distribute initial cards
        System.out.println("Distributing cards...");
        for (UnoPlayer player : unoGame.players) {
            for (int i = 0; i < 7; i++) {
                UnoCard card;
                do {
                    card = unoGame.deck.drawCard();
                } while (card.getValue() == UnoCard.Value.WILD_DRAW_FOUR);
                player.drawCard(card);
            }
        }
        System.out.println("Cards distributed. Starting the game!");

        unoGame.play();
    }
}
