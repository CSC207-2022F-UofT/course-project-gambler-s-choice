package screens;
//THIS IS THE UI LAYER

import menu_use_case.MenuResponseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class MainMenu extends JPanel implements Menu {
    /**
     * creates a main menu screen
     * This class does not work as of right now since we have yet to implement use cases, so there will be popup messages
     * that will display when corresponding buttons are clicked that denote what the button is supposed to do
     * @param frame The frame that MainMenu modifies
     */
    private final JFrame frame;

    private boolean loggedIn = true;
    private boolean inGame = false;

    public MainMenu(JFrame frame, MenuController controller, String user){
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

        JButton[] buttons = {logoutButton,exitButton,gameButton, helpButton};

        for (JButton button: buttons) {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed (ActionEvent evt){
                    if (evt.getActionCommand().equals("Log Out")) {
                        MenuResponseModel response = controller.create(user, "Log out", helpWindow.isVisible());
                        loggedIn = response.isLoggedIn();
                    } else if (evt.getActionCommand().equals("Exit Game")) {
                        System.exit(0);
                    } else if (evt.getActionCommand().equals("Help")) {
                        MenuResponseModel response = controller.create(user, "Help", helpWindow.isVisible());
                        helpWindow.setVisible(response.isRulesVisible());
                    } else if (evt.getActionCommand().equals("Play")) {
                        try {
                            MenuResponseModel response = controller.create(user, "Play", helpWindow.isVisible());
                            inGame = response.isInGame();
                        }catch (Exception e){
                            JOptionPane.showMessageDialog(frame, e.getMessage());
                        }
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

        this.add(background);
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public boolean isInGame() {
        return inGame;
    }

}
