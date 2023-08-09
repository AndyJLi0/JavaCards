package ui;

import model.Decks;
import model.Event;
import model.EventLog;
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
import java.util.Iterator;

// Class for frame and underlying structure of app
public class FlashCardsAppGUI {

    private JFrame frame;
    protected DecksGUI decksPanel;
    protected static MainMenuGUI mainMenuPanel;
    protected static CardDeckGUI cardDeckPanel;
    protected static PracticeGUI practicePanel;
    protected static CardLayout cardLayout;

    protected static Decks myDecks;
    private static final String JSON_STORE = "./data/myDecks.json";
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;
    protected static Boolean isDataLoaded;

    //EFFECTS: creates a new GUI
    public FlashCardsAppGUI() {
        myDecks = new Decks("myDecks");
        mainMenuPanel = new MainMenuGUI();
        decksPanel = new DecksGUI();
        cardDeckPanel = new CardDeckGUI();
        practicePanel = new PracticeGUI();
        cardLayout = new CardLayout();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        isDataLoaded = false;
        initSetup();
    }

    // MODIFIES: this
    // EFFECTS: sets initial application and pop-up
    public void initSetup() {
        frame = new JFrame();
        frame.setTitle("JavaCards");
        try {
            frame.setIconImage(ImageIO.read(new File("./data/images/flashCardAppIcon.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        initFrame();
        initPopup();
    }

    // MODIFIES: this
    // EFFECTS: asks if user wants to load previous data
    private void initPopup() {
        ImageIcon originalIcon = new ImageIcon("./data/images/colouredFlashCard.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(scaledImage);
        int response = JOptionPane.showConfirmDialog(frame,
                "Do you want to load your previous data?",
                "Load Data",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                icon);

        if (response == JOptionPane.YES_OPTION) {
            loadData();
            isDataLoaded = true;
        }
    }

    // MODIFIES: this
    // EFFECTS: sets up the initial frame for the GUI
    private void initFrame() {
        addAllCards();

        ImageIcon originalIcon = new ImageIcon("./data/images/saveIcon.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(scaledImage);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int response = JOptionPane.showConfirmDialog(frame,
                        "Do you want to save your data before exiting?",
                        "Exit",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        icon);
                if (response == JOptionPane.YES_OPTION) {
                    saveData();
                }
                printLog();
                frame.dispose();
            }
        });
    }


    // EFFECTS: prints the log on close
    private void printLog() {
        for (Event event :EventLog.getInstance()) {
            System.out.println(event.toString());
        }
    }

    // MODIFIES: this
    // EFFECTS: adds all panels to the card layout
    private void addAllCards() {
        frame.getContentPane().setLayout(cardLayout);
        frame.getContentPane().add(decksPanel, "Decks");
        frame.getContentPane().add(mainMenuPanel, "MainMenu");
        frame.getContentPane().add(cardDeckPanel, "CardDeck");
        frame.getContentPane().add(practicePanel, "Practice");
        cardLayout.show(frame.getContentPane(), "MainMenu");
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: loads previous user data
    private void loadData() {
        try {
            myDecks = jsonReader.read();
        } catch (IOException e) {
            // pass without loading anything
        }
    }

    // MODIFIES: this
    // EFFECTS: saves previous user data
    private void saveData() {
        try {
            jsonWriter.open();
            jsonWriter.write(myDecks);
            jsonWriter.close();

        } catch (FileNotFoundException e) {
            // exit without saving
        }
    }
}
