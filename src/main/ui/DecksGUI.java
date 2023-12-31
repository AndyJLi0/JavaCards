package ui;

import model.CardDeck;
import ui.keylistenerui.JListUI;
import ui.keylistenerui.RoundButton;
import ui.keylistenerui.RoundedBorder;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// GUI for the decks page, displays all decks and names; option to remove, rename, add
public class DecksGUI extends JPanel implements ActionListener {
    private static DefaultListModel<String> deckNameListModel;
    private JButton mainMenuButton;
    private JButton newDeckButton;
    private JButton removeDeckButton;
    private JButton renameDeckButton;
    private JButton viewDeckButton;
    private static JList<String> displayOfDecks;

    // EFFECTS: constructs a DECKS GUI
    public DecksGUI() {
        setLayout(new BorderLayout());

        this.setPreferredSize(new Dimension(495, 300));
        this.setBackground(Color.white);

        setupTitle();
        setupDisplayOfDecks();
        setupButtons();
        setupPage();
    }

    // MODIFIES: this
    // EFFECTS: constructs all the buttons for the GUI
    private void setupButtons() {
        mainMenuButton = makeButton("Main Menu");
        mainMenuButton.setPreferredSize(new Dimension(100, 35));
        mainMenuButton.setMargin(new Insets(3, 12, 3, 12));
        newDeckButton = makeButton("New Deck");
        newDeckButton.setMargin(new Insets(3, 28, 3, 28));
        viewDeckButton = makeButton("View Deck");
        viewDeckButton.setMargin(new Insets(3, 27, 3, 27));
        renameDeckButton = makeButton("Rename Deck");
        renameDeckButton.setMargin(new Insets(3, 12, 3, 19));
        removeDeckButton = makeButton("Remove Deck");
        removeDeckButton.setMargin(new Insets(3, 12, 3, 21));

    }

    // MODIFIES: this
    // EFFECTS: helper to create the buttons
    protected JButton makeButton(String buttonName) {
        JButton newButton = new RoundButton(buttonName);
        newButton.addActionListener(this);
        newButton.setPreferredSize(new Dimension(130, 35));
        return newButton;
    }

    // MODIFIES: this
    // EFFECTS: sets up the deck combo box
    private void setupDisplayOfDecks() {
        ArrayList<String> deckNames = FlashCardsAppGUI.myDecks.getDeckNamesFromDecks();

        deckNameListModel = new DefaultListModel<>();
        for (String name : deckNames) {
            deckNameListModel.addElement(name);
        }
        displayOfDecks = new JListUI(deckNameListModel);
    }

    // MODIFIES: this
    // EFFECTS: refreshes the deck display after user prompt for loading data
    public static void refreshDecksForLoad() {
        ArrayList<String> deckNames = FlashCardsAppGUI.myDecks.getDeckNamesFromDecks();

        for (String name : deckNames) {
            deckNameListModel.addElement(name);
        }
    }

    // MODIFIES: this
    // EFFECTS: sets up the title
    private void setupTitle() {
        JLabel titleLabel = new JLabel("JavaCard Decks", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(new Color(3, 140, 250));
        titleLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
        this.add(titleLabel, BorderLayout.NORTH);
    }

    // MODIFIES: this
    // EFFECTS: sets up the decks page
    private void setupPage() {
        renderRightSideButtons();
        renderMainMenuButton();
        renderScrollPane();
    }

    // MODIFIES: this
    // EFFECTS: separate method to create the main menu button
    private void renderMainMenuButton() {
        JPanel mainMenuButtonPanel = new JPanel();
        mainMenuButtonPanel.setOpaque(false);
        mainMenuButtonPanel.setBorder(new EmptyBorder(15, 25, 10, 25));
        mainMenuButtonPanel.add(mainMenuButton);
        renderScrollPane();
        this.add(mainMenuButtonPanel, BorderLayout.WEST);
    }

    // MODIFIES: this
    // EFFECTS: constructs the middle scroll pane that holds the list of Decks
    private void renderScrollPane() {
        JScrollPane scrollPane = new JScrollPane(displayOfDecks);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(100, 120));
        scrollPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
        scrollPane.setBorder(new RoundedBorder(10));

        JPanel scrollPanePanel = new JPanel();
        scrollPanePanel.setOpaque(false);
        scrollPanePanel.setLayout(new BoxLayout(scrollPanePanel, BoxLayout.PAGE_AXIS));
        scrollPanePanel.add(scrollPane);

        this.add(scrollPanePanel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: renders the right side column of buttons
    private void renderRightSideButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(new EmptyBorder(15, 25, 10, 25));
        buttonPanel.add(newDeckButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(viewDeckButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(renameDeckButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(removeDeckButton);
        this.add(buttonPanel, BorderLayout.EAST);
    }

    // MODIFIES: this
    // EFFECTS: creates a new deck
    private void createNewDeck() {
        JFrame frame = (JFrame) this.getRootPane().getParent();
        ImageIcon originalIcon = new ImageIcon("./data/images/colouredFlashCard.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(scaledImage);
        String deckName = (String) JOptionPane.showInputDialog(frame,
                "Enter the deck name:",
                "New Deck",
                JOptionPane.PLAIN_MESSAGE,
                icon,
                null, "");
        if (deckName != null && !deckName.trim().isEmpty()) {
            CardDeck newDeck = new CardDeck(deckName);

            FlashCardsAppGUI.myDecks.addDeckToDecks(newDeck);
            deckNameListModel.addElement(deckName);
        }
    }

    // MODIFIES: this
    // EFFECTS: removes a selected deck
    private void removeDeck(CardDeck deck) {
        deckNameListModel.removeElement(deck.getCardDeckName());
        FlashCardsAppGUI.myDecks.removeDeckFromDecks(deck);
    }

    // MODIFIES: this
    // EFFECTS: renames the selected deck
    private void renameDeck(CardDeck deck) {
        JFrame frame = (JFrame) this.getRootPane().getParent();
        ImageIcon originalIcon = new ImageIcon("./data/images/colouredFlashCard.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(scaledImage);
        String deckName = (String) JOptionPane.showInputDialog(frame,
                "Rename Deck To:",
                "Rename Deck",
                JOptionPane.PLAIN_MESSAGE,
                icon,
                null, "");
        if (deckName != null && !deckName.trim().isEmpty()) {
            deckNameListModel.removeElement(deck.getCardDeckName());
            deck.renameCardDeck(deckName);
            deckNameListModel.addElement(deckName);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = (JFrame) this.getRootPane().getParent();
        String cardDeckName = displayOfDecks.getSelectedValue();
        CardDeck selectedDeck = FlashCardsAppGUI.myDecks.getCardDeckFromName(cardDeckName);
        if (e.getSource() == mainMenuButton) {
            FlashCardsAppGUI.cardLayout.show(frame.getContentPane(), "MainMenu");
        } else if (e.getSource().equals(newDeckButton)) {
            createNewDeck();
        } else if (selectedDeck != null) {
            if (e.getSource().equals(removeDeckButton)) {
                removeDeck(selectedDeck);
            } else if (e.getSource().equals(renameDeckButton)) {
                renameDeck(selectedDeck);
            } else if (e.getSource().equals(viewDeckButton)) {
                FlashCardsAppGUI.cardDeckPanel.initGUI(selectedDeck);
                FlashCardsAppGUI.cardLayout.show(frame.getContentPane(), "CardDeck");
            }
        }
    }
}
