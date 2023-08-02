package ui.keylistenerui;

import javax.swing.*;
import java.awt.*;

// a rounded button that extends the features of JButton
public class RoundButton extends JButton {
    private int arcWidth = 20;
    private int arcHeight = 20;
    protected static Font FONT = new Font("Arial", Font.PLAIN, 14);

    // EFFECTS: constructs the rounded button
    public RoundButton(String label) {
        super(label);
        setFocusable(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setFont(FONT);
        setBackground(new Color(30, 140, 250));
        setForeground(Color.white);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (getModel().isArmed()) {
            g2d.setColor(Color.white);
        } else {
            g2d.setColor(getBackground());
        }
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);
        g2d.dispose();

        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(getForeground());
        g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arcWidth, arcHeight);
        g2d.dispose();
    }
}