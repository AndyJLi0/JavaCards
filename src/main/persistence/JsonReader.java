package persistence;

import model.CardDeck;
import model.Decks;
import model.FlashCard;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class JsonReader {

    private String source;

    //EFFECTS: constructs reader with source file
    public JsonReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads decks from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Decks read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseDeck(jsonObject);
    }

    //EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    //EFFECTS: prepares decks from JSON object and returns it
    private Decks parseDeck(JSONObject jsonObject) {
        String name = jsonObject.getString("decksTitle");
        Decks decks = new Decks(name);
        addCardDecks(decks, jsonObject);
        return decks;
    }

    //MODIFIES: decks
    //EFFECTS: parses cardDeckList from JSON object and adds them to decks
    private void addCardDecks(Decks decks, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("cardDecks");
        for (Object json : jsonArray) {
            JSONObject nextDeck = (JSONObject) json;
            addCardDeck(decks, nextDeck);
        }
    }

    //EFFECTS: parses cardDeckList from JSON object and adds them to decks
    private void addCardDeck(Decks decks, JSONObject jsonObject) {
        String cardDeckName = jsonObject.getString("cardDeckName");
        CardDeck cardDeck = new CardDeck(cardDeckName);
        addFlashCards(cardDeck, jsonObject);
        decks.addDeckToDecks(cardDeck);
    }

    //MODIFIES: deck
    //EFFECTS: parses FlashCards from JSON object and adds them to CardDeck
    private void addFlashCards(CardDeck deck, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("flashCards");
        for (Object json : jsonArray) {
            JSONObject nextFlashCard = (JSONObject) json;
            addFlashCard(deck, nextFlashCard);
        }
    }

    //EFFECTS: parses FlashCard from JSON object and adds them to CardDeck
    private void addFlashCard(CardDeck deck, JSONObject jsonObject) {
        String frontSide = jsonObject.getString("frontSide");
        String backSide = jsonObject.getString("backSide");
        boolean isFrontShowing = jsonObject.getBoolean("isFrontShowing");
        FlashCard flashCard = new FlashCard(frontSide, backSide, isFrontShowing);
        deck.addCard(flashCard);
    }
}
