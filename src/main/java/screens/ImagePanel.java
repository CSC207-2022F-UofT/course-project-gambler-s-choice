package screens;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagePanel extends JPanel{

    private BufferedImage image;
    private final int x;
    private final int y;
    private final int width;
    private final int height;

    /**
     * screens.ImagePanel constructor to make Image objects to put into a JFrame
     * @param path The path to the image file as a string
     * @param x The x coordinate on the frame for the image to start from
     * @param y The y coordinate on the frame for the image to start from
     * @param width The width of the image to be drawn as
     * @param height The height of the image to be drawn as
     */
    public ImagePanel(String path, int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        try {
            image = ImageIO.read(new File(path));
        } catch (IOException noPath) {
            return;
        }
    }

    /**
     * Draws the image
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, x, y, width, height, this);
    }
}
