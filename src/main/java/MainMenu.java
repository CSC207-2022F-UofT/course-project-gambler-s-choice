import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainMenu{

    public MainMenu(JFrame frame){

        JLabel background = new JLabel();
        background.setSize(1000,800);
        scaleImage("images/Menu.jpg", background);//scales the image to the label

        JLabel helpWindow = new JLabel();
        helpWindow.setBounds(200,30, 557,700);
        scaleImage("images/poker hand rankings.jpg", helpWindow);// TEMP IMAGE I WILL CREATE AN IMAGE LATER

        helpWindow.setVisible(false);

        JButton helpButton = new JButton("Help");

        helpButton.addActionListener(e -> {
            helpWindow.setVisible(!helpWindow.isVisible());
        });

        JButton logoutButton = new JButton("Log Out");

        logoutButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null,"(TEMP MESSAGE) YOU LOGGED OUT");
        });

        JButton exitButton = new JButton("Exit Game");

        exitButton.addActionListener(e -> {
            System.exit(0);
        });

        JButton gameButton = new JButton("Play Game");

        gameButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null,"(TEMP MESSAGE) YOU ARE BACK IN THE GAME");
        });


        ArrayList <Rectangle> coords = calcCoord(10,10, 4, 100, 60, 10);

        gameButton.setBounds(coords.get(0));
        helpButton.setBounds(coords.get(1));
        logoutButton.setBounds(coords.get(2));
        exitButton.setBounds(coords.get(3));


        background.add(gameButton);

        background.add(helpButton);

        background.add(logoutButton);

        background.add(exitButton);
        background.add(helpWindow);


        frame.add(background);

        frame.setSize(1000,800);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);




    }
    //THIS IS TEMPORARY AS I DON'T KNOW WHERE ELSE TO PUT THIS METHOD
    private void scaleImage(String location, JLabel label){
        ImageIcon icon = new ImageIcon(location);
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        label.setIcon(scaledIcon);
    }

    private ArrayList <Rectangle> calcCoord(int x, int y, int num, int width, int height, int gap){
        ArrayList<Rectangle> coords = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            Rectangle rectangle = new Rectangle(x, y + i * (height + gap), width, height);
            coords.add(rectangle);
        }
        return coords;
    }

    public static void main(String[] args){
        JFrame frame = new JFrame("Main Menu");

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainMenu(frame);
            }
        });

    }
}
