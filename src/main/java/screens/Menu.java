package screens;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public interface Menu {
/**
 * Scales the given image at the location to the label (background) and assigns image to label
 * @param location the path of the image to be scaled
 * @param label the label that the image is assigned to
 */
    public default void scaleImage(String location, JLabel label){
        ImageIcon icon = new ImageIcon(location);
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        label.setIcon(scaledIcon);
    }

    /** Calculates an arraylist of rectangles starting at (x,y) and spaced apart vertically by gap
     *
     * @param x x location
     * @param y y location
     * @param num number of rectangles to be generated
     * @param width width of each rectangle
     * @param height height of each rectangle
     * @param gap the gap between each square
     * @return the arraylist of rectangles
     */
    public default ArrayList<Rectangle> calcCoord(int x, int y, int num, int width, int height, int gap){
        ArrayList<Rectangle> coords = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            Rectangle rectangle = new Rectangle(x, y + i * (height + gap), width, height);
            coords.add(rectangle);
        }
        return coords;
    }

}
