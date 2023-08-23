package model;

import api.DeckOfCards;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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

    public static Card drawCardFromDeck(String deckId) {
        try {
            DeckOfCards deckOfCards = DeckOfCards.getInstance();
            String drawnCardResponse = deckOfCards.drawCards(deckId, 1);

            JsonObject jsonResponse = JsonParser.parseString(drawnCardResponse).getAsJsonObject();
            JsonArray cardsArray = jsonResponse.getAsJsonArray("cards");

            if (!cardsArray.isEmpty()) {
                String value = cardsArray.get(0).getAsJsonObject().get("value").getAsString();
                String suit = cardsArray.get(0).getAsJsonObject().get("suit").getAsString();
                return new Card(value, suit);
            } else {
                System.out.println("No cards were drawn from the deck.");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
