package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Class for representing a deck of flash cards
public class CardDeck implements Writable {
    private String cardDeckName;
    private final ArrayList<FlashCard> flashCardList;

    //EFFECTS: creates a card deck with a name and no flashcards
    public CardDeck(String cardDeckName) {
        flashCardList = new ArrayList<>();
        this.cardDeckName = cardDeckName;
    }

    //MODIFIES: this
    //EFFECTS: adds a card to the card deck
    public void addCard(FlashCard card) {
        flashCardList.add(card);
    }

    //MODIFIES: this
    //EFFECTS: removes the given card from the card deck (assumed index valid from user handling)
    public void removeCard(Integer index) {
        FlashCard card = getFlashCard(index);
        flashCardList.remove(card);
    }

    // MODIFIES: this
    // EFFECTS: removes given card from card deck; implicit assumption of card existing
    public void removeCard(FlashCard card) {
        flashCardList.remove(card);
    }

    //MODIFIES: this
    //EFFECTS: renames the flashcard deck name
    public void renameCardDeck(String name) {
        this.cardDeckName = name;
    }

    //MODIFIES: this
    //EFFECTS: shuffles the flashcard list using Fisher Yates algorithm
    public void shuffleDeck() {
        int n = flashCardList.size();
        for (int i = n - 1; i > 0; i--) {
            int j = (int) (Math.random() * (i + 1));
            FlashCard temp = flashCardList.get(i);
            flashCardList.set(i, flashCardList.get(j));
            flashCardList.set(j, temp);
        }
    }

    //MODIFIES: this
    //EFFECTS: resets deck back to original flashCard order
    public void resetDeck(ArrayList<FlashCard> original) {
        flashCardList.clear();
        flashCardList.addAll(original);
    }

    //EFFECTS: returns the flashcards in the deck
    public ArrayList<FlashCard> getFlashCardList() {
        return flashCardList;
    }

    // EFFECTS: returns list of all flashCard front sides
    public ArrayList<String> getFlashCardListFront() {
        ArrayList<String> allFrontShowing = new ArrayList<>();
        for (FlashCard fc : flashCardList) {
            allFrontShowing.add(fc.getFrontSide());
        }
        return allFrontShowing;
    }

    // EFFECTS: returns list of all flashCard back sides
    public ArrayList<String> getFlashCardListBack() {
        ArrayList<String> allBackShowing = new ArrayList<>();
        for (FlashCard fc : flashCardList) {
            allBackShowing.add(fc.getBackSide());
        }
        return allBackShowing;
    }

    //EFFECTS: returns the flashcard given the index
    public FlashCard getFlashCard(int index) {
        return flashCardList.get(index);
    }

    //EFFECTS: returns the number of flashCards in the deck
    public int getSizeOfDeck() {
        return flashCardList.size();
    }

    //EFFECTS: returns card deck name
    public String getCardDeckName() {
        return cardDeckName;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("cardDeckName", cardDeckName);
        json.put("flashCards", flashCardsToJson());
        return json;
    }

    //EFFECTS: returns flashCards in deck as a JSON array
    private JSONArray flashCardsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (FlashCard flashCard : flashCardList) {
            jsonArray.put(flashCard.toJson());
        }
        return jsonArray;
    }


    // EFFECTS: returns the flashcard given the backside of text, null if it doesn't exist
    public FlashCard getFlashCardByBack(String flashCardText) {
        for (FlashCard fc: flashCardList) {
            if (flashCardText == fc.getBackSide()) {
                return fc;
            }
        }
        return null;
    }

    // EFFECTS: returns the flashcard given the front-side of text, null if it doesn't exist
    public FlashCard getFlashCardByFront(String flashCardText) {
        for (FlashCard fc: flashCardList) {
            if (flashCardText == fc.getFrontSide()) {
                return fc;
            }
        }
        return null;
    }
}
