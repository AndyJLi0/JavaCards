package ui;

import model.CardDeck;
import ui.keylistenerui.RoundButton;
import ui.keylistenerui.RoundComboBoxUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// GUI for the decks page, displays all decks and names; option to remove, rename, add
public class DecksGUI extends JPanel implements ActionListener {
    private JButton mainMenuButton;
    private JButton newDeckButton;
    private JButton removeDeckButton;
    private JButton renameDeckButton;
    private JButton viewDeckButton;
    private JPanel buttonPanel;
    private JLabel titleLabel;
    private JComboBox<String> deckDropDown;

    // EFFECTS: constructs a DECKS GUI
    public DecksGUI() {
        setLayout(new BorderLayout());

        this.setPreferredSize(new Dimension(495, 300));
        this.setBackground(Color.white);

        setupTitle();
        setupDeckDropDown();
        setupButtons();
        setupPage();
        //myDecks.addDeckToDecks(new CardDeck("Deck1"));
    }

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

    private JButton makeButton(String buttonName) {
        JButton newButton = new RoundButton(buttonName);
        newButton.addActionListener(this);
        newButton.setPreferredSize(new Dimension(130, 35));
        return newButton;
    }

    private void setupDeckDropDown() {
        ArrayList<String> deckNames = FlashCardsAppGUI.myDecks.getDeckNamesFromDecks();
        deckDropDown = new JComboBox<>(deckNames.toArray(new String[0]));
        deckDropDown.setUI(new RoundComboBoxUI());
        deckDropDown.setFont(new Font("Arial", Font.PLAIN, 14));
        deckDropDown.setBounds(50, 50, 90, 20);
        deckDropDown.setMaximumSize(new Dimension(Integer.MAX_VALUE, deckDropDown.getPreferredSize().height));
        deckDropDown.setPreferredSize(new Dimension(100, deckDropDown.getPreferredSize().height));
    }

    private void setupTitle() {
        titleLabel = new JLabel("JavaCard Decks", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(new Color(3, 140, 250));
        titleLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
    }


    // MODIFIES: this
    // EFFECTS: sets up the decks page
    private void setupPage() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(new EmptyBorder(15, 20, 10, 40));
        buttonPanel.add(newDeckButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(viewDeckButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(renameDeckButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(removeDeckButton);

        JPanel mainMenuButtonPanel = new JPanel();
        mainMenuButtonPanel.setOpaque(false);
        mainMenuButtonPanel.setBorder(new EmptyBorder(15, 40, 10, 20));
        mainMenuButtonPanel.add(mainMenuButton);

        JPanel deckDropDownPanel = new JPanel();
        deckDropDownPanel.setOpaque(false);
        deckDropDownPanel.setBorder(new EmptyBorder(15, 0, 10, 0));
        deckDropDownPanel.add(deckDropDown);

        this.add(titleLabel, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.EAST);
        this.add(mainMenuButtonPanel, BorderLayout.WEST);
        this.add(deckDropDownPanel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: creates a new deck
    private void createNewDeck() {
        JFrame frame = (JFrame) this.getRootPane().getParent();
        String deckName = JOptionPane.showInputDialog(frame, "Enter the deck name:");
        if (deckName != null && !deckName.trim().isEmpty()) {
            CardDeck newDeck = new CardDeck(deckName);
            FlashCardsAppGUI.myDecks.addDeckToDecks(newDeck);
            deckDropDown.addItem(deckName);
        }
    }


    // MODIFIES: this
    // EFFECTS: removes a selected deck
    private void removeDeck(CardDeck deck) {
        FlashCardsAppGUI.myDecks.removeDeckFromDecks(deck);
        this.refreshDropDown();
    }

    // MODIFIES: this
    // EFFECTS: renames the selected deck
    private void renameDeck(CardDeck deck) {
        JFrame frame = (JFrame) this.getRootPane().getParent();
        String deckName = JOptionPane.showInputDialog(frame, "Rename deck to:");
        // Check if the user clicked "OK" (i.e., deckName is not null) and the input is not empty
        if (deckName != null && !deckName.trim().isEmpty()) {
            deck.renameCardDeck(deckName);
        }
        this.refreshDropDown();
    }

    private void refreshDropDown() {
        deckDropDown.removeAllItems();
        for (CardDeck cardDeck : FlashCardsAppGUI.myDecks.getDeckList()) {
            deckDropDown.addItem(cardDeck.getCardDeckName());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = (JFrame) this.getRootPane().getParent();
        String cardDeckName = (String) deckDropDown.getSelectedItem();
        CardDeck selectedDeck = FlashCardsAppGUI.myDecks.getCardDeckFromName(cardDeckName);
        if (e.getSource() == mainMenuButton) {
            FlashCardsAppGUI.cardLayout.show(frame.getContentPane(), "MainMenu");
        } else if (e.getSource() == newDeckButton) {
            createNewDeck();
        } else if (e.getSource() == removeDeckButton) {
            removeDeck(selectedDeck);
        } else if (e.getSource() == renameDeckButton) {
            renameDeck(selectedDeck);
        } else if (e.getSource() == viewDeckButton) {
            //TOdo: MAYBE IMPLEMENT EXCEPTION
            FlashCardsAppGUI.cardDeckPanel.initGUI(selectedDeck);
            FlashCardsAppGUI.cardLayout.show(frame.getContentPane(), "CardDeck");
        }
    }
}
