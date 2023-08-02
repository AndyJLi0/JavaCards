package ui;

import model.CardDeck;
import model.FlashCard;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

// GUI for a CardDeck, displays practice option, flash cards,
// and option to add, edit, or remove cards
public class CardDeckGUI extends JPanel {
    protected CardDeck deckForPage;
    private String title;
    private List<FlashCard> flashCardList;

    // EFFECTS: creates a new cardDeckGUI
    public CardDeckGUI() {
        deckForPage = new CardDeck("placeholder");
        title = "placeholder";
        flashCardList = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: displays info based on deck selected
    public void initGUI(CardDeck deck) {
        deckForPage = deck;
        List<FlashCard> flashCardList = deck.getFlashCardList();
        title = deck.getCardDeckName() + " Deck Page";

    }

    // MODIFIES: this
    // EFFECTS: adds a new flashcard to the deck
    private void addCard() {

    }

    // MODIFIES: this
    // EFFECTS: removes selected flashcard
    private void removeCard() {

    }

    // MODIFIES: this
    // EFFECTS: edits selected flashcard, changing front and back
    private void editCard() {

    }

}
