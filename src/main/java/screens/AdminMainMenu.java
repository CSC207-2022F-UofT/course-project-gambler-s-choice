package screens;
//THIS IS THE UI LAYER

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import admin_menu_use_case.*;

public class AdminMainMenu extends JPanel implements Menu{
    /**
     * Creates a main menu with an extra option to edit user balances
     * @param frame the frame to be modified
     */

    private final JFrame frame;
    private final AdminEditBalanceController controller;

    private boolean loggedIn = true;

    private boolean inGame = false;

    private boolean initiate = false;

    public AdminMainMenu(JFrame frame, AdminEditBalanceController controller){
    this.frame = frame;
    this.controller = controller;

        JLabel background = new JLabel();
        background.setSize(1000,800);
        scaleImage("images/menu2.jpg", background);//scales the image to the label

        JLabel helpWindow = new JLabel();
        helpWindow.setBounds(200,30, 557,700);
        scaleImage("images/poker hand rankings.jpg", helpWindow);// TEMP IMAGE I WILL CREATE AN IMAGE LATER

        helpWindow.setVisible(false);

        JButton helpButton = new JButton("Help");

        JButton logoutButton = new JButton("Log Out");
        JButton exitButton = new JButton("Exit game_entities.Game");
        JButton gameButton = new JButton("Play");
        JButton editButton = new JButton("Edit login_menu_entities.User");
        JLabel userLabel = new JLabel("Username");
        JLabel balanceLabel = new JLabel("Balance");
        JTextField username = new JTextField("");
        JTextField balance = new JTextField("");

        JButton buttons[] = {logoutButton,exitButton,gameButton,editButton};
        String userfile = "src/main/users.txt";
        for (JButton button: buttons) {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed (ActionEvent evt){
                    if (evt.getActionCommand().equals("Edit login_menu_entities.User")){
                        String userEntry = username.getText();
                        String balanceEntry = balance.getText();
                        username.setText("");
                        balance.setText("");
                        AdminEditGateway admin;
                        try{
                            admin = new AdminFileChecker(userfile);
                        } catch (IOException a){
                            throw new RuntimeException(a);
                        }
                        AdminEditPresenter presenter = new AdminEditResponseFormatter();
                        AdminEditBalanceInputBoundary inputBoundary = new AdminEditInteractor(admin, presenter);
                        AdminEditBalanceController balanceController = new AdminEditBalanceController(inputBoundary);
                        try {
                            balanceController.create(userEntry, Integer.parseInt(balanceEntry), userfile);
                        } catch (Exception a) {
                            ErrorPanel errorPanel = new ErrorPanel(a.getMessage());
                            JOptionPane.showMessageDialog(errorPanel, a.getMessage());
                        }
                    } else if (evt.getActionCommand().equals("Log Out")) {
                        loggedIn = !loggedIn;
                    } else if (evt.getActionCommand().equals("Exit game_entities.Game")) {
                        System.exit(0);
                    } else if (evt.getActionCommand().equals("Help")) {
                        helpWindow.setVisible(!helpWindow.isVisible());
                        username.setVisible(!username.isVisible());
                        userLabel.setVisible(!userLabel.isVisible());
                        balance.setVisible(!balance.isVisible());
                        balanceLabel.setVisible(!balanceLabel.isVisible());
                    } else if (evt.getActionCommand().equals("Play")) {
                        inGame = true;
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

    public boolean isInitiate(){
        return initiate;
    }

}
