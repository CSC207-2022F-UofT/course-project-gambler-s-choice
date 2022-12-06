package screens;
//THIS IS THE UI LAYER

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class MainMenu extends JPanel implements Menu {
    /**
     * creates a main menu screen
     * This class does not work as of right now since we have yet to implement use cases, so there will be popup messages
     * that will display when corresponding buttons are clicked that denote what the button is supposed to do
     * @param frame The frame that MainMenu modifies
     */
    public MainMenu(JFrame frame){

        //Creates a new MenuController object
        MenuController m = new MenuController();

        JLabel background = new JLabel();
        background.setSize(1000,800);
        scaleImage("images/menu2.jpg", background);

        JLabel helpWindow = new JLabel();
        helpWindow.setBounds(200,30, 557,700);

        scaleImage("images/poker hand rankings.jpg", helpWindow);

        helpWindow.setVisible(false);

        JButton helpButton = new JButton("Help");

        helpButton.addActionListener(e -> {
            helpWindow.setVisible(!helpWindow.isVisible());
        });

        JButton logoutButton = new JButton("Log Out");

        logoutButton.addActionListener(m);

        JButton exitButton = new JButton("Exit game_entities.Game");

        exitButton.addActionListener(e -> {
            System.exit(0);
        });

        JButton gameButton = new JButton("Play");

        gameButton.addActionListener(m);




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


        this.add(background);


    }
}
