package ui;

import ui.keylistenerui.RoundButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// GUI for the main menu, displays graphics and decks button
public class MainMenuGUI extends JPanel implements ActionListener {
    private JButton decksButton;
    private JPanel buttonPanel;
    private JLabel titleLabel;

    // EFFECTS: Constructs GUI for the main menu
    public MainMenuGUI() {
        setLayout(new BorderLayout());
        setTitle();
        setButton();
        setPage();
    }

    //MODIFIES: this
    //EFFECTS: sets up the title of the main menu
    private void setTitle() {
        titleLabel = new JLabel("Welcome to JavaCards!", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(new Color(30, 140, 250));
        titleLabel.setBorder(new EmptyBorder(20, 20, 20, 20));
    }

    //MODIFIES: this
    //EFFECTS: sets up the GUI of the main menu
    private void setPage() {
        this.setPreferredSize(new Dimension(495, 300));
        this.setBackground(Color.white);
        add(titleLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // MODIFIES: this
    //EFFECTS: sets up the "decks" button
    private void setButton() {
        decksButton = new RoundButton("Decks");
        decksButton.addActionListener(this);
        decksButton.setFont(new Font("Arial", Font.PLAIN, 20));
        decksButton.setMargin(new Insets(5, 20, 5, 20));

        buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(new EmptyBorder(10, 20, 30, 20));
        buttonPanel.add(decksButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = (JFrame) this.getRootPane().getParent();
        if (FlashCardsAppGUI.isDataLoaded) {
            DecksGUI.refreshDecksForLoad();
        }
        FlashCardsAppGUI.cardLayout.show(frame.getContentPane(), "Decks");
    }
}

