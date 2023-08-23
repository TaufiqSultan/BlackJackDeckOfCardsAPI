package model;


import java.io.IOException;

import java.util.ArrayList;

import java.util.List;


public class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
    }

    public static Deck createNewDeck() throws IOException {
        Deck deck = new Deck();
        for (String suit : Card.getSuits()) {
            for (String value : Card.getValues()) {
                deck.cards.add(new Card(value, suit));
            }
        }
        return deck;
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

}