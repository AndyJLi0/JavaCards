package ui.keylistenerui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;


// class for constructing an animated toggle button
public class SwitchButton extends Component {

    private final Timer timer;
    private float location;
    private boolean selected;
    private boolean mouseOver;
    private final float speed = 0.1f;
    private final List<EventSwitchSelected> events;

    // EFFECTS: constructs a switch button with animation when pressed
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public SwitchButton() {
        setBackground(new Color(3, 140, 250));
        setPreferredSize(new Dimension(65, 25));
        setMinimumSize(new Dimension(65, 25));
        setMaximumSize(new Dimension(65, 25));
        setForeground(Color.WHITE);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        events = new ArrayList<>();
        location = 2;
        timer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (isSelected()) {
                    int endLocation = getWidth() - getHeight() + 2;
                    if (location < endLocation) {
                        location += speed;
                        repaint();
                    } else {
                        timer.stop();
                        location = endLocation;
                        repaint();
                    }
                } else {
                    int endLocation = 2;
                    if (location > endLocation) {
                        location -= speed;
                        repaint();
                    } else {
                        timer.stop();
                        location = endLocation;
                        repaint();
                    }
                }
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                mouseOver = true;
            }

            @Override
            public void mouseExited(MouseEvent me) {
                mouseOver = false;
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    if (mouseOver) {
                        selected = !selected;
                        timer.start();
                        runEvent();
                    }
                }
            }
        });
    }

    // EFFECTS: returns true if the toggle is toggled on
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void paint(Graphics graphics) {
        Graphics2D g2 = (Graphics2D) graphics;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int width = getWidth();
        int height = getHeight();
        float alpha = getAlpha();
        if (alpha < 1) {
            g2.setColor(Color.GRAY);
            g2.fillRoundRect(0, 0, width, height, 25, 25);
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, width, height, 25, 25);
        g2.setColor(getForeground());
        g2.setComposite(AlphaComposite.SrcOver);
        g2.fillOval((int) location, 2, height - 4, height - 4);
        super.paint(graphics);
    }

    // MODIFIES: this
    // EFFECTS: determines how far the sliding circle should move
    private float getAlpha() {
        float width = getWidth() - getHeight();
        float alpha = 0;
        if (width != 0) {
            alpha = (location - 2) / width;
        }
        if (alpha < 0) {
            alpha = 0;
        }
        if (alpha > 1) {
            alpha = 1;
        }
        return alpha;
    }

    // MODIFIES: this
    // EFFECTS: starts the animation
    private void runEvent() {
        for (EventSwitchSelected event : events) {
            event.onSelected(selected);
        }
    }

    // MODIFIES: this
    // EFFECTS: adds an event to the events list when the button is toggled
    public void addEventSelected(EventSwitchSelected event) {
        events.add(event);
    }

}
