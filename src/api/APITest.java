package api;

import api.DeckOfCards;

import java.io.IOException;

public class APITest {
    public static void main(String[] args) {
        try {
            DeckOfCards deckOfCards = DeckOfCards.getInstance();

            // Fetch a new deck
            String deckId = deckOfCards.fetchNewDeck();
            System.out.println("Fetched new deck with ID: " + deckId);

            // Draw cards from the deck
            String drawnCards = deckOfCards.drawCards(deckId, 5);
            System.out.println("Drawn cards: " + drawnCards);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
