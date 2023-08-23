package model;

import api.DeckOfCards;

import java.util.ArrayList;
import java.io.IOException;

public class Player {
    private final String name;
    private final ArrayList<Card> dealtCards = new ArrayList<>();

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getDealtCards() {
        return dealtCards;
    }

    public void addDealtCard(Card card) {
        dealtCards.add(card);
    }

    public void clearDealtCards() {
        dealtCards.clear();
    }

    public void dealCardsFromDeck(String deckId, int num) {
        for (int i = 0; i < num; i++) {
            Card drawnCard = Card.drawCardFromDeck(deckId); // Draw a card using deckId
            if (drawnCard != null) {
                addDealtCard(drawnCard); // Add the drawn card to the player's dealt cards
            }
        }
    }
}
