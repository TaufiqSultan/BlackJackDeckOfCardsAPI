package model;

import java.util.List;

public class Card {
    private String value;
    private String suit;
    public Card(String value, String suit) {
        this.value = value;
        this.suit = suit;
    }
    public String getValue() {
        return value;
    }
    public String getSuit() {
        return suit;
    }
    public static List<String> getValues() {
        return List.of("ACE", "2", "3", "4", "5", "6", "7", "8", "9", "10", "JACK", "QUEEN", "KING");
    }
    public static List<String> getSuits() {
        return List.of("CLUBS", "DIAMONDS", "HEARTS", "SPADES");
    }
    @Override
    public String toString() {
        return value + " of " + suit;
    }
}