package service;

import interfaces.PlayerEventListener;
import model.Deck;
import model.Player;

import java.io.IOException;

public class GameManager implements PlayerEventListener {
    private Deck deck;
    private Player player;
    private Player dealer;
    private PlayerEventListener playerEventListener;

    public void setPlayerEventListener(PlayerEventListener listener) {
        this.playerEventListener = listener;
    }

    public void startGame() {
        System.out.println("Welcome to Blackjack!");

        try {
            deck = Deck.createNewDeck();
            deck.shuffle();

            player = new Player("Player");
            dealer = new Player("Dealer");

            player.dealCardsFromDeck(deck.getDeckId(), 2);
            dealer.dealCardsFromDeck(deck.getDeckId(), 2);

            System.out.println("Player's cards: " + player.getDealtCards());
            if (!dealer.getDealtCards().isEmpty()) {
                System.out.println("Dealer's visible card: " + dealer.getDealtCards().get(0));
            } else {
                System.out.println("Dealer has no visible cards.");
            }


            while (true) {
                String decision = InputManager.getPlayerDecision(); // Using the Strategy pattern for input

                if (decision.equals("hit")) {
                    player.dealCardsFromDeck(deck.getDeckId(), 1);
                    System.out.println("Player's cards: " + player.getDealtCards());

                    if (calculatePoints(player) > 21) {
                        System.out.println("Player busts! Dealer wins.");
                        break;
                    }
                } else if (decision.equals("stand")) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter 'hit' or 'stand'.");
                }
            }

            while (calculatePoints(dealer) < 17) {
                dealer.dealCardsFromDeck(deck.getDeckId(), 1);
            }
            System.out.println("Dealer's cards: " + dealer.getDealtCards());

            int playerPoints = calculatePoints(player);
            int dealerPoints = calculatePoints(dealer);

            if (playerPoints > 21) {
                System.out.println("Dealer wins!");
            } else if (dealerPoints > 21 || playerPoints > dealerPoints) {
                System.out.println("Player wins!");
            } else if (playerPoints < dealerPoints) {
                System.out.println("Dealer wins!");
            } else {
                System.out.println("It's a tie!");
            }

        } catch (IOException e) {
            System.out.println("Error connecting to the API: " + e.getMessage());
        }
    }

    private int calculatePoints(Player player) {
        int points = 0;
        int numberOfAces = 0;

        for (model.Card card : player.getDealtCards()) {
            String cardValue = card.getValue();

            if ("KING QUEEN JACK".contains(cardValue)) {
                points += 10;
            } else if (cardValue.equals("ACE")) {
                numberOfAces++;
                points += 11;
            } else {
                points += Integer.parseInt(cardValue);
            }
        }

        while (numberOfAces > 0 && points > 21) {
            points -= 10;
            numberOfAces--;
        }

        if (points >= 15) {
            notifyPlayerEvent("Consider standing, your total is " + points);
        }

        return points;
    }

    private void notifyPlayerEvent(String message) {
        if (playerEventListener != null) {
            playerEventListener.onPlayerEvent(message);
        }
    }

    @Override
    public void onPlayerEvent(String message) {
        System.out.println(message);
    }

    public static void main(String[] args) {
        GameManager gameManager = new GameManager();
        gameManager.startGame();
    }
}
