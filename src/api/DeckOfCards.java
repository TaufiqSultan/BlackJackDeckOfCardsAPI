package api;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class DeckOfCards {
    private static final String API_BASE_URL = "https://deckofcardsapi.com/api/deck/";

    private static DeckOfCards instance;
    private static String currentDeckId;

    private DeckOfCards() {
        // Private constructor voor singleton pattern
    }

    public static DeckOfCards getInstance() {
        if (instance == null) {
            instance = new DeckOfCards();
        }
        return instance;
    }

    public static String fetchNewDeck() throws IOException {
        String apiUrl = API_BASE_URL + "new";
        String response = makeApiRequest(apiUrl);
        currentDeckId = parseDeckId(response);
        return currentDeckId;
    }

    public String drawCards(String deckId, int count) throws IOException {
        String apiUrl = API_BASE_URL + currentDeckId + "/draw/?count=" + count;
        return makeApiRequest(apiUrl);
    }

    private static String parseDeckId(String response) {
        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
        return jsonObject.get("deck_id").getAsString();
    }

    private static String makeApiRequest(String apiUrl) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setRequestProperty("Accept", "application/json");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String inputLine;
                    StringBuilder response = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    return response.toString();
                }
            } else {
                throw new ApiRequestFailedException("API request failed with response code: " + responseCode);
            }
        } catch (ApiRequestFailedException e) {
            throw new RuntimeException(e);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
