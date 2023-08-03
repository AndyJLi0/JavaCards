package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
// class for handling and creating images to display
public class ImagePanel extends JPanel {

    private Image image;

    // MODIFIES: this
    // EFFECTS: takes image path and creates a Swing component of the image
    public ImagePanel(String imagePath, int width, int height) {
        try {
            BufferedImage originalImage = ImageIO.read(new File(imagePath));
            image = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters
    }
}
