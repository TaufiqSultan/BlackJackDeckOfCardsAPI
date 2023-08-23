package model;

import api.DeckOfCards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {
    private final String name;
    private final ArrayList<Card> dealtCards = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Card> getDealtCards() {
        return Collections.unmodifiableList(dealtCards); // Returning an unmodifiable view of the list
    }

    public void addDealtCard(Card card) {
        dealtCards.add(card);
    }

    public void clearDealtCards() {
        dealtCards.clear();
    }

    public void dealCardsFromDeck(String deckId, int num) {
        DeckOfCards deckOfCards = DeckOfCards.getInstance();

        for (int i = 0; i < num; i++) {
            Card drawnCard = Card.drawCardFromDeck(deckId);
            if (drawnCard != null) {
                addDealtCard(drawnCard);
            }
        }
    }
}
