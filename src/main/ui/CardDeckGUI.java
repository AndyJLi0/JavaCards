package ui;

import model.CardDeck;
import model.FlashCard;
import ui.keylistenerui.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

// GUI for a CardDeck, displays practice option, flash cards,
// and option to add, edit, or remove cards
public class CardDeckGUI extends JPanel implements ActionListener, ItemListener {
    private static DefaultListModel<String> flashCardNamesListModel;
    protected CardDeck deckForPage;
    private JLabel title;
    private JButton backToDecksButton;
    private JButton practiceButton;
    private SwitchButton displayBackWhenOn;
    private JButton newFlashCardButton;
    private JButton editFlashCardButton;
    private JButton removeFlashCardButton;
    private JList<String> displayOfFlashCards;
    private ArrayList<String> flashCardNames;

    // EFFECTS: creates a new cardDeckGUI
    public CardDeckGUI() {
        deckForPage = new CardDeck("placeholder");
        this.title = new JLabel("placeholder", SwingConstants.CENTER);
        setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(495, 300));
        this.setBackground(Color.white);
        setDisplayOfFlashCards();
    }

    // MODIFIES: this
    // EFFECTS: displays info based on deck selected
    public void initGUI(CardDeck deck) {
        this.deckForPage = deck;
        this.title.setText(deck.getCardDeckName() + " Deck Page");
        //this.originalDeckIndex = FlashCardsAppGUI.myDecks.getIndexFromName(deck.getCardDeckName());
        placePage();

    }

    // MODIFIES: this
    // EFFECTS: places every component onto panel
    private void placePage() {
        setTitle();
        setButtons();
        updateFlashCardsDisplay();
    }

    // MODIFIES: this
    // EFFECTS: adds panels to page
    private void setDisplayOfFlashCards() {
        flashCardNames = deckForPage.getFlashCardListFront();
        flashCardNamesListModel = new DefaultListModel<>();
        for (String frontSide : flashCardNames) {
            flashCardNamesListModel.addElement(frontSide);
        }
        displayOfFlashCards = new JListUI(flashCardNamesListModel);

        JScrollPane scrollPane = new JScrollPane(displayOfFlashCards);
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
    // EFFECTS: places and declares buttons
    private void setButtons() {
        backToDecksButton = makeButton("Decks");
        practiceButton = makeButton("Practice");
        displayBackWhenOn = new SwitchButton();
        displayBackWhenOn.addEventSelected(new EventSwitchSelected() {
            @Override
            public void onSelected(boolean selected) {
                if (selected) {
                    updateFlashCardsDisplayWithBack();
                } else {
                    updateFlashCardsDisplayWithFront();
                }
            }
        });
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
        this.add(rightSideButtons, BorderLayout.EAST);
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
        JLabel toggleTheSideShowing = new JLabel("SHOW BACK: ");
        toggleTheSideShowing.setFont(new Font("Arial", Font.BOLD, 10));
        toggleTheSideShowing.setForeground(new Color(30, 140, 250));
        leftSideButtons.add(toggleTheSideShowing);
        toggleTheSideShowing.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftSideButtons.add(displayBackWhenOn);
        backToDecksButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(leftSideButtons, BorderLayout.WEST);
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
        JFrame frame = (JFrame) this.getRootPane().getParent();
        JTextField frontSideField = new JTextField();
        JTextField backSideField = new JTextField();
        Object[] message = {
                "Enter front side text:", frontSideField,
                "Enter back side text:", backSideField
        };

        int option = JOptionPane.showConfirmDialog(frame, message, "New FlashCard!!", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String flashCardFront = frontSideField.getText();
            String flashCardBack = backSideField.getText();
            if ((flashCardFront != null)
                    && !flashCardFront.trim().isEmpty()
                    && (flashCardBack != null)
                    && !flashCardBack.trim().isEmpty()) {
                FlashCard newFlashCard = new FlashCard(flashCardFront, flashCardBack, true);
                deckForPage.addCard(newFlashCard);
                updateFlashCardsDisplay();
            }
        }
    }


    // MODIFIES: this
    // EFFECTS: removes selected flashcard
    private void removeCard(FlashCard card) {
        deckForPage.removeCard(card);
        updateFlashCardsDisplay();
    }

    // MODIFIES: this
    // EFFECTS: edits selected flashcard, changing front and back
    private void editCard(FlashCard card) {
        JFrame frame = (JFrame) this.getRootPane().getParent();
        JTextField frontSideField = new JTextField();
        JTextField backSideField = new JTextField();
        Object[] message = {
                "Enter front side text:", frontSideField,
                "Enter back side text:", backSideField
        };

        int option = JOptionPane.showConfirmDialog(frame, message,
                "Editing " + card.getFrontSide() + "--" + card.getBackSide(), JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String flashCardFront = frontSideField.getText();
            String flashCardBack = backSideField.getText();
            if ((flashCardFront != null)
                    && !flashCardFront.trim().isEmpty()
                    && (flashCardBack != null)
                    && !flashCardBack.trim().isEmpty()) {
                card.setBackSide(flashCardBack);
                card.setFrontSide(flashCardFront);
                updateFlashCardsDisplay();
            }
        }
    }

    // Effects: handles the refresh choice of the dropdown
    private void updateFlashCardsDisplay() {
        this.flashCardNames = deckForPage.getFlashCardListFront();
        flashCardNamesListModel.removeAllElements();
        if (displayBackWhenOn.isSelected()) {
            updateFlashCardsDisplayWithBack();
        } else {
            updateFlashCardsDisplayWithFront();
        }
    }

    // MODIFIES: this
    // EFFECTS: refreshes drop down with front showing
    private void updateFlashCardsDisplayWithFront() {
        flashCardNamesListModel.removeAllElements();
        for (FlashCard fc : deckForPage.getFlashCardList()) {
            flashCardNamesListModel.addElement(fc.getFrontSide());
            displayOfFlashCards.revalidate();
        }
    }

    // MODIFIES: this
    // EFFECTS: refreshes drop down with back showing
    private void updateFlashCardsDisplayWithBack() {
        flashCardNamesListModel.removeAllElements();
        for (FlashCard fc : deckForPage.getFlashCardList()) {
            flashCardNamesListModel.addElement(fc.getBackSide());
            displayOfFlashCards.revalidate();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = (JFrame) this.getRootPane().getParent();
        String flashCardText = displayOfFlashCards.getSelectedValue();
        System.out.println("3");
        FlashCard flashCard;
        if (displayBackWhenOn.isSelected()) {
            flashCard = deckForPage.getFlashCardByBack(flashCardText);
        } else {
            flashCard = deckForPage.getFlashCardByFront(flashCardText);
        }
        if (e.getSource().equals(backToDecksButton)) {
            System.out.println("why the fuck");
            FlashCardsAppGUI.cardLayout.show(frame.getContentPane(), "Decks");
        } else if (e.getSource().equals(newFlashCardButton)) {
            System.out.println("this one works");
            addCard();
        } else if (flashCard != null) {
            System.out.println("bruh");
            if (e.getSource().equals(removeFlashCardButton)) {
                System.out.println("does this run");
                removeCard(flashCard);
            } else if (e.getSource().equals(editFlashCardButton)) {
                editCard(flashCard);
            } else if (e.getSource().equals(practiceButton)) {
                //todo: CREATE NEW PRACTICE FRAME!
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent ev) {
        flashCardNamesListModel.removeAllElements();
        if (ev.getStateChange() == ItemEvent.SELECTED) {
            updateFlashCardsDisplayWithBack();
        } else if (ev.getStateChange() == ItemEvent.DESELECTED) {
            updateFlashCardsDisplayWithFront();
        }
    }
}
