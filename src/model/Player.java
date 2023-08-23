package model;

import java.util.ArrayList;

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
}
