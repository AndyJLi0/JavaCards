package ui.keylistenerui;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.*;
import javax.swing.border.AbstractBorder;

public class RoundComboBoxUI extends BasicComboBoxUI {
    Color bgColor = new Color(30, 140, 250);

    protected void installDefaults() {
        super.installDefaults();
        comboBox.setOpaque(true);
        comboBox.setBackground(Color.white);
        comboBox.setForeground(bgColor);
        comboBox.setBorder(new SimpleRoundBorder());
    }

    protected JButton createArrowButton() {
        JButton button = new BasicArrowButton(BasicArrowButton.SOUTH, bgColor, bgColor, Color.white, bgColor);
        button.setUI(new BorderlessButtonUI());
        return button;
    }

    class SimpleRoundBorder extends AbstractBorder {
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            ((Graphics2D) g).setColor(bgColor);
            ((Graphics2D) g).drawRoundRect(x, y, width - 2, height - 2, 12, 12);
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(3, 6, 3, 3);
        }

        public Insets getBorderInsets(Component c, Insets insets) {
            insets.top = insets.left = insets.bottom = insets.right = 3;
            return insets;
        }
    }
}