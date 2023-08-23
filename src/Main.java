import api.DeckOfCards;
import interfaces.PlayerEventListener;
import model.Card;
import model.Deck;
import model.Player;
import service.GameManager;
import service.InputManager;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        InputManager inputManager = new InputManager();
        GameManager gameManager = new GameManager();

        PlayerEventListener playerEventListener = new PlayerEventListener() {
            @Override
            public void onPlayerEvent(String message) {
                System.out.println("Event received: " + message);
            }
        };

        gameManager.setPlayerEventListener(playerEventListener);

        while (true) {
            // Create a new deck and player for each game
            Deck deck = Deck.createNewDeck();
            Player player = new Player("Player");

            // Draw cards for the player
            try {
                player.dealCardsFromDeck(Deck.getDeckId(), 2); // Assuming you want to draw 2 cards
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("Player's dealt cards: " + player.getDealtCards());

            System.out.println("Do you want to play again? (y/n)");
            String answer = inputManager.getPlayerDecision();
            if (answer.equals("n")) {
                break;
            }
            player.clearDealtCards(); // Clear dealt cards for the next game
        }

        inputManager.closeScanner();
    }
}
