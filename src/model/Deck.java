package model;

import api.DeckOfCards;
import model.Card;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Deck {
    private List<Card> cards;

    private Deck(List<Card> cards) {
        this.cards = cards;
    }

    public static Deck createNewDeck() throws IOException {
        DeckOfCards deckOfCards = DeckOfCards.getInstance();
        String deckId = DeckOfCards.fetchNewDeck(); // Fetch a new deck using the API

        List<Card> cards = new ArrayList<>();

        for (String suit : Card.getPossibleSuits()) {
            for (String value : Card.getPossibleValues()) {
                cards.add(new Card(value, suit));
            }
        }

        return new Deck(cards);
    }


    public void shuffle() {
        for (int i = cards.size() - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            Card temp = cards.get(i);
            cards.set(i, cards.get(j));
            cards.set(j, temp);
        }
    }

    public Card drawCards(int num) {
        if (num > cards.size()) {
            throw new IllegalArgumentException("Not enough cards in the deck.");
        }
        Card card = cards.remove(cards.size() - 1);
        return card;
    }

    public List<Card> getCards() {
        return cards;
    }

    public static String getDeckId() {
        String deckId = null;
        return null; // Getter for the deck ID
    }
}
