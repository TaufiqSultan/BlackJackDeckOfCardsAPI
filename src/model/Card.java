package model;

import api.DeckOfCards;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Arrays;
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

    @Override
    public String toString() {
        return value + " of " + suit;
    }

    // Static method to draw a card from the deck
    public static Card drawCardFromDeck(String deckId) {
        try {
            DeckOfCards deckOfCards = DeckOfCards.getInstance();
            String drawnCardResponse = deckOfCards.drawCards(deckId, 1);

            // Parse the response to get the card details
            JsonObject jsonResponse = JsonParser.parseString(drawnCardResponse).getAsJsonObject();
            JsonArray cardsArray = jsonResponse.getAsJsonArray("cards");

            if (cardsArray.size() > 0) {
                JsonObject cardObject = cardsArray.get(0).getAsJsonObject();
                String value = cardObject.get("value").getAsString();
                String suit = cardObject.get("suit").getAsString();
                return new Card(value, suit);
            } else {
                System.out.println("No cards were drawn.");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Static methods for possible values and suits
    public static List<String> getPossibleValues() {
        return Arrays.asList("ACE", "2", "3", "4", "5", "6", "7", "8", "9", "10", "JACK", "QUEEN", "KING");
    }

    public static List<String> getPossibleSuits() {
        return Arrays.asList("CLUBS", "DIAMONDS", "HEARTS", "SPADES");
    }
}
