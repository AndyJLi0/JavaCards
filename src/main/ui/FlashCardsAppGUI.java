package ui;

import model.Decks;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

// GUI for the main portion of the application
public class FlashCardsAppGUI {

    private JFrame frame;
    protected DecksGUI decksGUI;
    protected static MainMenuGUI mainMenuPanel;
    protected static CardDeckGUI cardDeckPanel;
    protected static CardLayout cardLayout;

    protected static Decks myDecks;
    private static final String JSON_STORE = "./data/myDecks.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //EFFECTS: creates a new GUI
    public FlashCardsAppGUI() {
        myDecks = new Decks("myDecks");
        mainMenuPanel = new MainMenuGUI();
        decksGUI = new DecksGUI();
        cardDeckPanel = new CardDeckGUI();
        cardLayout = new CardLayout();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        initSetup();
    }

    // MODIFIES: this
    // EFFECTS: sets initial application and pop-up
    public void initSetup() {
        frame = new JFrame();
        try {
            frame.setIconImage(ImageIO.read(new File("./data/images/flashCardAppIcon.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        initFrame();
        //initPopup();
    }

    // MODIFIES: this
    // EFFECTS: asks if user wants to load previous data
    private void initPopup() {
        int response = JOptionPane.showConfirmDialog(frame,
                "Do you want to load your previous data?",
                "Load Data",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.YES_OPTION) {
            loadData();
        }
    }

    // MODIFIES: this
    // EFFECTS: sets up the initial frame for the GUI
    private void initFrame() {
        frame.setTitle("JavaCards");
        frame.getContentPane().setLayout(cardLayout);
        frame.getContentPane().add(decksGUI, "Decks");
        frame.getContentPane().add(mainMenuPanel, "MainMenu");
        frame.getContentPane().add(cardDeckPanel, "CardDeck");
        cardLayout.show(frame.getContentPane(), "MainMenu");
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int response = JOptionPane.showConfirmDialog(frame,
                        "Do you want to save your data before exiting?",
                        "Exit",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    saveData();
                }
                frame.dispose();
            }
        });
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: loads previous user data
    private void loadData() {
        try {
            myDecks = jsonReader.read();
            System.out.println("loaded successfully!");
        } catch (IOException e) {
            System.out.println("unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: saves previous user data
    private void saveData() {
        try {
            jsonWriter.open();
            jsonWriter.write(myDecks);
            jsonWriter.close();
            System.out.println("Saved successfully!");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }
}
