package api;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DeckOfCards {
    private static final String API_BASE_URL = "https://deckofcardsapi.com/api/deck/";
    private static DeckOfCards instance;
    private DeckOfCards() {}

    public static DeckOfCards getInstance() {
        if (instance == null) {
            instance = new DeckOfCards();
        }
        return instance;
    }

    public String drawCards(String deckId, int count) throws IOException {
        String apiUrl = API_BASE_URL + deckId + "/draw/?count=" + count;
        return makeApiRequest(apiUrl);
    }

    private String makeApiRequest(String apiUrl) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setRequestProperty("Accept", "application/json");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } else {
            throw new IOException("API request failed with response code: " + responseCode);
        }
    }
}
