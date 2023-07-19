package model;

import java.util.ArrayList;

// Class for representing a deck of flash cards
public class CardDeck {
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
        for (int i = 0; i < getSizeOfDeck(); i++) {
            if (index == i) {
                flashCardList.remove(getFlashCard(i));
                break;
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: renames the flashcard deck name
    public void renameCardDeck(String name) {
        this.cardDeckName = name;
    }

    //EFFECTS: returns the flashcards in the deck
    public ArrayList<FlashCard> getFlashCardList() {
        return flashCardList;
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
}
