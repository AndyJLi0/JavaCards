package ui;

import ui.keylistenerui.RoundButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

// GUI for the main menu, displays graphics and decks button
public class MainMenuGUI extends JPanel implements ActionListener {
    private JPanel buttonPanel;
    private JLabel titleLabel;

    // EFFECTS: Constructs GUI for the main menu
    public MainMenuGUI() {
        setLayout(new BorderLayout());
        setTitle();
        setButton();
        setImage();
        setPage();
    }

    // MODIFIES: this
    // EFFECTS: sets the image of the java flashcard image
    private void setImage() {
        try {
            Image image = ImageIO.read(new File("./data/images/javaFlashCard.png"));

            JPanel imagePanel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    int x = (getWidth() - image.getWidth(this)) / 2;
                    int y = (getHeight() - image.getHeight(this)) / 2;
                    g.drawImage(image, x, y, this);
                }
            };
            imagePanel.setBackground(Color.white);
            add(imagePanel, BorderLayout.CENTER);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
        JButton decksButton = new RoundButton("Decks");
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

