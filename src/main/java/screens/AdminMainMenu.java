package screens;
//THIS IS THE UI LAYER

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import menu_use_case.UserEditBalanceModel;

public class AdminMainMenu implements Menu{
    /**
     * Creates a main menu with an extra option to edit user balances
     * @param frame the frame to be modified
     */
    public AdminMainMenu(JFrame frame){

        MenuController m = new MenuController();

        JLabel background = new JLabel();
        background.setSize(1000,800);
        scaleImage("images/menu2.jpg", background);//scales the image to the label

        JLabel helpWindow = new JLabel();
        helpWindow.setBounds(200,30, 557,700);
        scaleImage("images/poker hand rankings.jpg", helpWindow);// TEMP IMAGE I WILL CREATE AN IMAGE LATER

        helpWindow.setVisible(false);

        JButton helpButton = new JButton("Help");

        JButton logoutButton = new JButton("Log Out");
        JButton exitButton = new JButton("Exit Game");
        JButton gameButton = new JButton("Play");
        JButton editButton = new JButton("Edit login_menu_entities.User");
        JLabel userLabel = new JLabel("Username");
        JLabel balanceLabel = new JLabel("Balance");
        JTextField username = new JTextField("");
        JTextField balance = new JTextField("");


        helpButton.addActionListener(e -> {
            helpWindow.setVisible(!helpWindow.isVisible());
            username.setVisible(!username.isVisible());
            userLabel.setVisible(!userLabel.isVisible());
            balance.setVisible(!balance.isVisible());
            balanceLabel.setVisible(!balanceLabel.isVisible());

        });
        logoutButton.addActionListener(m);

        //Also this just exits the system, don't think I would ever change this, so I think this should be fine to leave here.
        exitButton.addActionListener(e -> {
            System.exit(0);
        });


        gameButton.addActionListener(m);



        editButton.addActionListener(e -> {
            String userEntry = username.getText();
            String balanceEntry = balance.getText();
            username.setText("");
            balance.setText("");
            //FIXME this just extracts the text fields
            JOptionPane.showMessageDialog(null,"Username: "+ userEntry + " New Balance: "+ balanceEntry);
        });

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


        frame.add(background);

        frame.setSize(1000,800);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);




    }
}
