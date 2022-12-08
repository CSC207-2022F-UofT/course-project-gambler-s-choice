package screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import admin_menu_use_case.*;

public class AdminMainMenu extends JPanel implements Menu{

    private final JFrame frame;
    private boolean loggedIn = true;
    private boolean inGame = false;
    private String user;

    /**
     * Creates a main menu with an extra option to edit user balances
     * @param frame the frame to be modified
     */
    public AdminMainMenu(JFrame frame, AdminEditBalanceController controller, String user){
    this.user = user;
    this.frame = frame;

        JLabel background = new JLabel();
        background.setSize(1000,800);
        scaleImage("images/menu2.jpg", background);//scales the image to the label

        JLabel helpWindow = new JLabel();
        helpWindow.setBounds(200,30, 557,700);
        scaleImage("images/poker hand rankings.jpg", helpWindow);

        helpWindow.setVisible(false);

        JButton helpButton = new JButton("Help");

        JButton logoutButton = new JButton("Log Out");
        JButton exitButton = new JButton("Exit Game");
        JButton gameButton = new JButton("Play");
        JButton editButton = new JButton("Edit User");
        JLabel userLabel = new JLabel("Username");
        JLabel balanceLabel = new JLabel("Balance");
        JTextField username = new JTextField("");
        JTextField balance = new JTextField("");

        JButton[] buttons= {logoutButton,exitButton,gameButton,editButton, helpButton};
        for (JButton button: buttons) {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed (ActionEvent evt){
                    if (evt.getActionCommand().equals("Edit User")){
                        String userEntry = username.getText();
                        String balanceEntry = balance.getText();
                        username.setText("");
                        balance.setText("");
                        try {
                            AdminEditResponseModel response = controller.create(userEntry, balanceEntry, "Edit User", helpWindow.isVisible());
                            loggedIn = response.isLoggedIn();
                        } catch (Exception a) {
                            JOptionPane.showMessageDialog(frame, a.getMessage());
                        }
                    } else if (evt.getActionCommand().equals("Log Out")) {
                        AdminEditResponseModel response = controller.create(user, null, "Log Out", helpWindow.isVisible());
                        loggedIn = response.isLoggedIn();
                    } else if (evt.getActionCommand().equals("Exit Game")) {
                        System.exit(0);
                    } else if (evt.getActionCommand().equals("Help")) {
                        AdminEditResponseModel response = controller.create(user, null, "Help", helpWindow.isVisible());
                        helpWindow.setVisible(response.isRulesVisible());
                        username.setVisible(!response.isRulesVisible());
                        userLabel.setVisible(!response.isRulesVisible());
                        balance.setVisible(!response.isRulesVisible());
                        balanceLabel.setVisible(!response.isRulesVisible());
                    } else if (evt.getActionCommand().equals("Play")) {
                        try {
                            AdminEditResponseModel response = controller.create(user, null, "Play", helpWindow.isVisible());
                            inGame = response.isInGame();
                        } catch (Exception e){
                            JOptionPane.showMessageDialog(frame, e.getMessage());
                        }
                    }
                }
            });
        }

        ArrayList <Rectangle> coords = calcCoord(10,10, 5, 100, 60, 10);

        gameButton.setBounds(coords.get(0));
        helpButton.setBounds(coords.get(1));
        logoutButton.setBounds(coords.get(2));
        editButton.setBounds(coords.get(3));
        exitButton.setBounds(coords.get(4));

        //locations of the user and balance text fields
        Rectangle userCoord = new Rectangle(130, 240, 100, 40);
        Rectangle balanceCoord = new Rectangle(240, 240, 100, 40);
        Rectangle userLabelCoord = new Rectangle(130, 200, 100, 60);
        Rectangle balanceLabelCoord = new Rectangle(240, 200, 100, 60);

        userLabel.setBounds(userLabelCoord);
        balanceLabel.setBounds(balanceLabelCoord);

        username.setBounds(userCoord);
        balance.setBounds(balanceCoord);



        background.add(gameButton);

        background.add(helpButton);

        background.add(logoutButton);

        background.add(editButton);
        background.add(username);
        background.add(balance);
        background.add(exitButton);
        background.add(helpWindow);
        background.add(userLabel);
        background.add(balanceLabel);


        this.add(background);

    }
    public boolean isLoggedIn() {
        return loggedIn;
    }
    public boolean isInGame(){ return inGame;}

}
