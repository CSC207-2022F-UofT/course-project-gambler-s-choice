package screens;
//THIS IS THE UI LAYER

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class MainMenu implements Menu{
    /**
     * creates a main menu screen
     * @param frame The frame that MainMenu modifies
     */
    public MainMenu(JFrame frame){

        JLabel background = new JLabel();
        background.setSize(1000,800);
        scaleImage("images/menu2.jpg", background);//scales the image to the label

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
}
