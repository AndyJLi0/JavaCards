package ui;

import model.CardDeck;
import model.FlashCard;
import ui.keylistenerui.RoundButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

// Class for displaying the practice feature of the app
public class PracticeGUI extends JPanel implements ActionListener {
    private JLabel cardLabel;
    private int currentCardIndex = 0;
    private ArrayList<FlashCard> flashCards;
    private ArrayList<FlashCard> originalOrder;
    private CardDeck deckForPractice;

    // EFFECTS: constructs the panel when app launches; sets everything to null unless called from cardDeck page
    public PracticeGUI() {
        this.deckForPractice = null;
        this.flashCards = null;
        this.originalOrder = null;
        initUI();
        setTitle();
        setButtonAndText();
        setText();

    }

    // MODIFIES: this
    // EFFECTS:
    private void initUI() {
        setLayout(new BorderLayout());
        cardLabel = new JLabel();
        cardLabel.setHorizontalAlignment(JLabel.CENTER);
        setupKeyBinds();
    }

    // MODIFIES: this
    // EFFECTS: sets up the binds for practice
    private void setupKeyBinds() {
        InputMap inputMap = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "nextCard");
        actionMap.put("nextCard", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextCard();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("LEFT"), "prevCard");
        actionMap.put("prevCard", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prevCard();
            }
        });

        inputMap.put(KeyStroke.getKeyStroke("SPACE"), "flipCard");
        actionMap.put("flipCard", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flipCard();
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: Constructs and adds the title to the panel
    private void setTitle() {
        JLabel titleLabel = new JLabel("Practice Page", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(new Color(3, 140, 250));
        titleLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
        this.add(titleLabel, BorderLayout.NORTH);
    }

    // MODIFIES: this
    // EFFECTS: sets the text that shows from the flashcard
    private void setText() {
        cardLabel.setForeground(new Color(100, 172, 255));
        cardLabel.setFont(new Font("Arial", Font.BOLD, 24));
    }

    // MODIFIES: this
    // EFFECTS: adds backToDeckButton and flashcard text to panel and makes it visible
    private void setButtonAndText() {
        JButton backToDeckButton = new RoundButton("Exit");
        backToDeckButton.addActionListener(this);
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        centerPanel.add(cardLabel);
        cardLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 80)));
        centerPanel.add(backToDeckButton);
        backToDeckButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(centerPanel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: modifies the panel so that it now is updated based on deck it was called from
    public void whenCalled(CardDeck deck) {
        this.deckForPractice = deck;
        this.originalOrder = new ArrayList<>(deck.getFlashCardList());
        this.deckForPractice.shuffleDeck();
        this.flashCards = deckForPractice.getFlashCardList();
        updateCardDisplay();
    }

    // MODIFIES: this
    // EFFECTS: displays new card after pressing right arrow
    private void nextCard() {
        if (currentCardIndex < flashCards.size() - 1) {
            currentCardIndex++;
            flashCards.get(currentCardIndex).setIsFrontShowing(true);
            updateCardDisplay();
        }
    }

    // MODIFIES: this
    // EFFECTS: displays previous card after pressing left arrow
    private void prevCard() {
        if (currentCardIndex > 0) {
            currentCardIndex--;
            flashCards.get(currentCardIndex).setIsFrontShowing(true);
            updateCardDisplay();
        }
    }

    // MODIFIES: this
    // EFFECTS: flips card when space is pressed
    private void flipCard() {
        flashCards.get(currentCardIndex).setIsFrontShowing(!flashCards.get(currentCardIndex).getIsFrontShowing());
        updateCardDisplay();
    }

    // MODIFIES: this
    // EFFECTS: updates the display to either show front or backside of card
    private void updateCardDisplay() {
        FlashCard currentCard = flashCards.get(currentCardIndex);
        cardLabel.setText(currentCard.getIsFrontShowing() ? "Front: " + currentCard.getFrontSide()
                : "Back: " + currentCard.getBackSide());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = (JFrame) this.getRootPane().getParent();
        deckForPractice.resetDeck(originalOrder);
        FlashCardsAppGUI.cardDeckPanel.initGUI(deckForPractice);
        FlashCardsAppGUI.cardLayout.show(frame.getContentPane(), "CardDeck");
    }
}

