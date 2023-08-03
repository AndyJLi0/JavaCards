package ui;

import model.CardDeck;
import model.FlashCard;
import ui.keylistenerui.RoundButton;
import ui.keylistenerui.RoundComboBoxUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// GUI for a CardDeck, displays practice option, flash cards,
// and option to add, edit, or remove cards
public class CardDeckGUI extends JPanel implements ActionListener {
    private int originalDeckIndex;
    protected CardDeck deckForPage;
    private JLabel title;
    private JButton backToDecksButton;
    private JButton practiceButton;
    private JToggleButton displayFrontOrBack;
    private JButton newFlashCardButton;
    private JButton editFlashCardButton;
    private JButton removeFlashCardButton;
    private JComboBox flashCardDropDown;
    private List<FlashCard> flashCardList;

    // EFFECTS: creates a new cardDeckGUI
    public CardDeckGUI() {
        deckForPage = new CardDeck("placeholder");
        this.title = new JLabel("placeholder", SwingConstants.CENTER);
        flashCardList = new ArrayList<>();
        setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(495, 300));
        this.setBackground(Color.white);
    }

    // MODIFIES: this
    // EFFECTS: displays info based on deck selected
    public void initGUI(CardDeck deck) {
        this.deckForPage = deck;
        this.flashCardList = deck.getFlashCardList();
        this.title.setText(deck.getCardDeckName() + " Deck Page");
        this.originalDeckIndex = FlashCardsAppGUI.myDecks.getIndexFromName(deck.getCardDeckName());
        placePage();

    }

    // MODIFIES: this
    // EFFECTS: places every component onto panel
    private void placePage() {
        setTitle();
        setButtons();
        setDropDown();
    }

    // MODIFIES: this
    // EFFECTS: adds panels to page
    private void setDropDown() {
        ArrayList<String> flashCardNames = deckForPage.getFlashCardListFront();
        flashCardDropDown = new JComboBox<>(flashCardNames.toArray(new String[0]));
        flashCardDropDown.setUI(new RoundComboBoxUI());
        flashCardDropDown.setFont(new Font("Arial", Font.PLAIN, 14));
        flashCardDropDown.setBounds(50, 50, 90, 20);
        flashCardDropDown.setMaximumSize(new Dimension(Integer.MAX_VALUE, flashCardDropDown.getPreferredSize().height));
        flashCardDropDown.setPreferredSize(new Dimension(100, flashCardDropDown.getPreferredSize().height));
        this.add(flashCardDropDown, BorderLayout.CENTER);

        JPanel flashCardDropDownPanel = new JPanel();
        flashCardDropDownPanel.setOpaque(false);
        flashCardDropDownPanel.setBorder(new EmptyBorder(15, 0, 10, 0));
        flashCardDropDownPanel.add(this.flashCardDropDown);
        this.add(flashCardDropDownPanel,BorderLayout.CENTER);
    }


    // MODIFIES: this
    // EFFECTS: places and declares buttons
    private void setButtons() {
        backToDecksButton = makeButton("Decks");
        practiceButton = makeButton("Practice");
        displayFrontOrBack = new JToggleButton("Show Back");
        newFlashCardButton = makeButton("Create");
        editFlashCardButton = makeButton("Edit");
        removeFlashCardButton = makeButton("Remove");
        addLeftSide();
        addRightSide();
    }

    // MODIFIES: this
    // EFFECTS: sets up right column of buttons
    private void addRightSide() {
        JPanel rightSideButtons = new JPanel();
        rightSideButtons.setLayout(new BoxLayout(rightSideButtons, BoxLayout.Y_AXIS));
        rightSideButtons.setOpaque(false);
        rightSideButtons.setBorder(new EmptyBorder(15, 20, 10, 40));
        rightSideButtons.add(newFlashCardButton);
        rightSideButtons.add(Box.createRigidArea(new Dimension(0, 10)));
        rightSideButtons.add(editFlashCardButton);
        rightSideButtons.add(Box.createRigidArea(new Dimension(0, 10)));
        rightSideButtons.add(removeFlashCardButton);
        rightSideButtons.add(Box.createRigidArea(new Dimension(0, 10)));
        rightSideButtons.add(practiceButton);
        this.add(rightSideButtons,BorderLayout.EAST);
    }


    // MODIFIES: this
    // EFFECTS: sets up left column of buttons
    private void addLeftSide() {
        JPanel leftSideButtons = new JPanel();
        leftSideButtons.setLayout(new BoxLayout(leftSideButtons, BoxLayout.Y_AXIS));
        leftSideButtons.setOpaque(false);
        leftSideButtons.setBorder(new EmptyBorder(15, 40, 10, 20));
        leftSideButtons.add(backToDecksButton);
        leftSideButtons.add(Box.createRigidArea(new Dimension(0, 10)));
        leftSideButtons.add(displayFrontOrBack);
        this.add(leftSideButtons,BorderLayout.WEST);
    }


    // MODIFIES: this
    // EFFECTS: actually creates the buttons
    private JButton makeButton(String flashCardButtonName) {
        JButton newFlashCardButton = new RoundButton(flashCardButtonName);
        newFlashCardButton.addActionListener(this);
        newFlashCardButton.setPreferredSize(new Dimension(130, 35));
        return newFlashCardButton;
    }


    private void setTitle() {
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(new Color(3, 140, 250));
        title.setBorder(new EmptyBorder(20, 20, 20, 20));

        this.add(title, BorderLayout.NORTH);
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

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
