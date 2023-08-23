package model;

import java.util.ArrayList;

public class Player {
    // Data structure: Represents the player's name
    private final String name;
    // Data structure: List of cards dealt to the player
    private final ArrayList<Card> dealtCards = new ArrayList<>();

    public Player(String name) {
        this.name = name; // Initializing the player's name
    }

    // Method to get the player's name
    public String getName() {
        return name;
    }

    // Method to get the list of cards dealt to the player
    public ArrayList<Card> getDealtCards() {
        return dealtCards;
    }

    // Method to add a dealt card to the player's list
    public void addDealtCard(Card card) {
        dealtCards.add(card);
    }

    // Method to clear the list of dealt cards for the player
    public void clearDealtCards() {
        dealtCards.clear();
    }
}
