package ui;

import model.CardDeck;
import model.Decks;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

// GUI for the decks page, displays all decks and names; option to remove, rename, add
public class DecksGUI extends JPanel implements ActionListener {
    private JButton mainMenuButton;
    private JButton newDeckButton;
    private JButton removeDeckButton;
    private JButton renameDeckButton;
    private JButton viewDeckButton;
    private JPanel buttonPanel;
    private JLabel titleLabel;
    private JComboBox deckDropDown;

    protected Decks myDecks;


    // EFFECTS: constructs a DECKS GUI
    public DecksGUI(Decks myDecks) {
        setLayout(new BorderLayout());

        this.setPreferredSize(new Dimension(825, 500));
        this.setBackground(Color.DARK_GRAY);

        setupTitle();
        setupDeckDropDown();
        setupButtons();
        setupPage();
        this.myDecks = myDecks;
        //myDecks.addDeckToDecks(new CardDeck("Deck1"));
    }

    private void setupButtons() {
        mainMenuButton = new JButton("Main Menu");
        mainMenuButton.addActionListener(this);
        mainMenuButton.setFont(new Font("Serif", Font.PLAIN, 20));
        mainMenuButton.setMargin(new Insets(10, 20, 10, 20));

        newDeckButton = new JButton("New Deck");
        newDeckButton.addActionListener(this);
        newDeckButton.setFont(new Font("Serif", Font.PLAIN, 20));
        newDeckButton.setMargin(new Insets(10, 20, 10, 20));

        removeDeckButton = new JButton("Remove Deck");
        removeDeckButton.addActionListener(this);
        removeDeckButton.setFont(new Font("Serif", Font.PLAIN, 20));
        removeDeckButton.setMargin(new Insets(10, 20, 10, 20));

        renameDeckButton = new JButton("Rename Deck");
        renameDeckButton.addActionListener(this);
        renameDeckButton.setFont(new Font("Serif", Font.PLAIN, 20));
        renameDeckButton.setMargin(new Insets(10, 20, 10, 20));

        viewDeckButton = new JButton("View Deck");
        viewDeckButton.addActionListener(this);
        viewDeckButton.setFont(new Font("Serif", Font.PLAIN, 20));
        viewDeckButton.setMargin(new Insets(10, 20, 10, 20));
    }

    private void setupDeckDropDown() {
        deckDropDown = new JComboBox<>(FlashCardsAppGUI.myDecks.getDeckList().toArray(new CardDeck[0]));
        deckDropDown.setMaximumSize(new Dimension(Integer.MAX_VALUE, deckDropDown.getPreferredSize().height));
    }

    private void setupTitle() {
        titleLabel = new JLabel("JavaCard Decks", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
    }


    // MODIFIES: this
    // EFFECTS: sets up the decks page
    private void setupPage() {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
        buttonPanel.add(viewDeckButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(renameDeckButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(removeDeckButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        buttonPanel.add(newDeckButton);

        JPanel mainMenuButtonPanel = new JPanel();
        mainMenuButtonPanel.setOpaque(false);
        mainMenuButtonPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
        mainMenuButtonPanel.add(mainMenuButton);

        JPanel deckDropDownPanel = new JPanel();
        deckDropDownPanel.setOpaque(false);
        deckDropDownPanel.setBorder(new EmptyBorder(10, 20, 10, 20));
        deckDropDownPanel.add(deckDropDown);

        this.add(titleLabel, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.EAST);
        this.add(mainMenuButtonPanel, BorderLayout.WEST);
        this.add(deckDropDownPanel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: creates a new deck
    private void newDeck() {

    }


    // MODIFIES: this
    // EFFECTS: removes a selected deck
    private void removeDeck() {

    }

    // MODIFIES: this
    // EFFECTS: renames the selected deck
    private void renameDeck() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
