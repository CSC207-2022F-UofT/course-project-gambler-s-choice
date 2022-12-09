package screens;
//THIS IS THE UI LAYER

import menu_use_case.MenuResponseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * The view for regular User Main Menu
 */
public class MainMenu extends JPanel implements Menu {
    private final JFrame frame;

    private boolean loggedIn = true;
    private boolean inGame = false;

    /**
     * Creates a normal main menu on the frame
     * @param frame The frame to be modified
     * @param controller The controller for the menu use case
     * @param user The user that is logged in
     */
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
