package ui;

import model.Decks;
import model.FlashCard;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;

// GUI for the main portion of the application
public class FlashCardsAppGUI {

    private JFrame frame;
    private DecksGUI decksPanel;
    private MainMenuGUI mainMenuPanel;
    private Decks myDecks;
    private static final String JSON_STORE = "./data/myDecks.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: creates a new GUI
    public FlashCardsAppGUI() {
        myDecks = new Decks("myDecks");
        mainMenuPanel = new MainMenuGUI();
        decksPanel = new DecksGUI();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        initSetup();
    }

    // MODIFIES: this
    // EFFECTS: sets initial application and pop-up
    public void initSetup() {
        initFrame();
        initPopup();
    }

    // MODIFIES: this
    // EFFECTS: asks if user wants to load previous data
    private void initPopup() {

    }


    // MODIFIES: this
    // EFFECTS: sets up the initial frame for the GUI
    private void initFrame() {

    }

    // MODIFIES: this
    // EFFECTS: loads previous user data
    private void loadData() {

    }

    // MODIFIES: this
    // EFFECTS: saves previous user data
    private void saveData() {

    }

}
