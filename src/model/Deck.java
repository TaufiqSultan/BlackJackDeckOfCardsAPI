package model;

import api.DeckOfCards;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Deck {
    private List<Card> cards;

    private Deck(List<Card> cards) {
        this.cards = cards;
    }

    /**
     * Create a new deck of cards by fetching a new deck from the API and populating it.
     * @throws IOException if there's an issue with the API request.
     */
    public static Deck createNewDeck() throws IOException {
        DeckOfCards deckOfCards = DeckOfCards.getInstance();
        String deckId = deckOfCards.fetchNewDeck();

        List<Card> cards = new ArrayList<>();

        for (int i = 0; i < 52; i++) {
            Card drawnCard = Card.drawCardFromDeck(deckId);
            if (drawnCard != null) {
                cards.add(drawnCard);
            }
        }

        return new Deck(cards);
    }

    /**
     * Shuffle the cards in the deck using the Fisher-Yates shuffle algorithm.
     */
    public void shuffle() {
        for (int i = cards.size() - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            Card temp = cards.get(i);
            cards.set(i, cards.get(j));
            cards.set(j, temp);
        }
    }

    /**
     * Draw a specified number of cards from the top of the deck.
     * @param num The number of cards to draw.
     * @return The drawn card.
     * @throws IllegalArgumentException if there are not enough cards in the deck.
     */
    public Card drawCards(int num) {
        if (num > cards.size()) {
            throw new IllegalArgumentException("Not enough cards in the deck. Available: " + cards.size());
        }
        Card card = cards.remove(cards.size() - 1); // Remove and return the top card
        return card;
    }

    /**
     * Return the list of remaining cards in the deck.
     * @return The list of remaining cards.
     */
    public List<Card> getCards() {
        return cards;
    }

    // Getter for the deck ID (currently not used in the provided code)
    public static String getDeckId() {
        return null; // Return the deck ID if needed
    }
}
