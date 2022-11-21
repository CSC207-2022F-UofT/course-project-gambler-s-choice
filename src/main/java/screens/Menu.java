package screens;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

//FIXME I can probably make the menus extend jpanel if I really need to
public class Menu {

    public void scaleImage(String location, JLabel label){
        ImageIcon icon = new ImageIcon(location);
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        label.setIcon(scaledIcon);
    }

    public ArrayList<Rectangle> calcCoord(int x, int y, int num, int width, int height, int gap){
        ArrayList<Rectangle> coords = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            Rectangle rectangle = new Rectangle(x, y + i * (height + gap), width, height);
            coords.add(rectangle);
        }
        return coords;
    }

}
