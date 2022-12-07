package screens;
//THIS IS THE UI LAYER

import menu_use_case.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;


public class MainMenu implements Menu {
    /**
     * creates a main menu screen
     * This class does not work as of right now since we have yet to implement use cases, so there will be popup messages
     * that will display when corresponding buttons are clicked that denote what the button is supposed to do
     * @param frame The frame that MainMenu modifies
     */
    private final JFrame frame;

    private boolean loggedIn = true;

    private boolean inGame = false;

    public MainMenu(JFrame frame){
        this.frame = frame;

        JLabel background = new JLabel();
        background.setSize(1000,800);
        scaleImage("images/menu2.jpg", background);

        JLabel helpWindow = new JLabel();
        helpWindow.setBounds(200,30, 557,700);

        scaleImage("images/poker hand rankings.jpg", helpWindow);

        helpWindow.setVisible(false);

        JButton helpButton = new JButton("Help");

        JButton logoutButton = new JButton("Log Out");

        JButton exitButton = new JButton("Exit Game");

        JButton gameButton = new JButton("Play");

        JButton buttons[] = {logoutButton,exitButton,gameButton};

        for (JButton button: buttons) {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed (ActionEvent evt){
                    if (evt.getActionCommand().equals("Log Out")) {
                        loggedIn = !loggedIn;
                    } else if (evt.getActionCommand().equals("Exit Game")) {
                        System.exit(0);
                    } else if (evt.getActionCommand().equals("Help")) {
                        helpWindow.setVisible(!helpWindow.isVisible());
                    } else if (evt.getActionCommand().equals("Play")) {
                        inGame = true;
                    }
                }
            });
        }




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

    }
}
