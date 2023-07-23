package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Class that represents the multiple card decks a user can create
public class Decks implements Writable {
    private final ArrayList<CardDeck> deckList;
    private final String decksTitle;

    //EFFECTS: creates a Deck list with a name,
    public Decks(String name) {
        decksTitle = name;
        deckList = new ArrayList<>();
    }

    //MODIFIES: this
    //EFFECTS: adds a deck to the deck list
    public void addDeckToDecks(CardDeck deck) {
        deckList.add(deck);
    }

    //MODIFIES: this
    //EFFECTS: removes given deck from deck list (assumed index valid from user handling)
    public void removeDeckFromDecks(int index) {
        deckList.remove(index);
    }

    //EFFECTS: returns the of given index, assume that index is within bounds
    //         (assumed index valid from user handling)
    public CardDeck fetchDeckFromDecks(int index) {
        return deckList.get(index);
    }

    //EFFECTS: returns the title of the list of decks
    public String getDecksTitle() {
        return decksTitle;
    }

    //EFFECTS: returns the list of all the decks
    public ArrayList<CardDeck> getDeckList() {
        return deckList;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("decksTitle", decksTitle);
        json.put("cardDecks", deckListToJson());
        return json;
    }

    //EFFECTS: returns deckList in Decks as a JSON array
    private JSONArray deckListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (CardDeck deck : deckList) {
            jsonArray.put(deck.toJson());
        }
        return jsonArray;
    }

}
