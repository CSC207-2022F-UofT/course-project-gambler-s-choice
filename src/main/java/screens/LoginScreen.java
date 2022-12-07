package screens;

import login_menu_entities.UserFactory;
import login_menu_entities.UserInterfaceFactory;
import login_menu_use_casee.*;
import register_menu_use_case.UserRegisterResponseModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;

public class LoginScreen extends JPanel implements Screen {
    private final JTextField usernameField = new JTextField();
    private final JPasswordField passwordField = new JPasswordField(20);
    private final JPasswordField passwordField2 = new JPasswordField(20);
    private final JFrame frame;
    private final LoginController lcontroller;
    private final RegisterController rcontroller;
    private boolean loggedIn = false;
    private String user = null;
    private String type = null;

    /**
     * Updates the current window to contain the necessary items in the game
     * @param
     */
    public LoginScreen(JFrame frame, LoginController lcontroller, RegisterController rcontroller){
        this.frame = frame;
        this.lcontroller = lcontroller;
        this.rcontroller = rcontroller;
        this.setLayout(new BorderLayout());

        JPanel backgroundPanel = loadBackground();
        this.add(backgroundPanel);

        usernameField.setText("");
        passwordField.setText(""); //TODO FIGURE OUT HOW TO CLEAR JTEXTFIELD
        passwordField2.setText("");

        this.add(this.loadButtons(), BorderLayout.SOUTH);
    }

    /**
     * Creates all the necessary elements for the background and puts them into a JPanel
     * @return The JPanel with all the background elements
     */
    @Override
    public JPanel loadBackground() {

        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setLayout(new BorderLayout());
        Font font = new Font("", Font.PLAIN, 24);

        ImagePanel background = new ImagePanel("images/Login Background.jpg", 0, 0, 1000, 800);
        JLabel usernamePrompt = new JLabel("Username:");
        JLabel passwordPrompt = new JLabel("Password:");
        JLabel repeatPasswordPrompt = new JLabel("Repeat Password:");


        usernamePrompt.setForeground(Color.CYAN);
        passwordPrompt.setForeground(Color.CYAN);
        repeatPasswordPrompt.setForeground(Color.CYAN);
        usernamePrompt.setFont(font);
        passwordPrompt.setFont(font);
        repeatPasswordPrompt.setFont(font);
        usernameField.setFont(font);
        passwordField.setFont(font);
        passwordField2.setFont(font);

        background.setBounds(0, 0, 1000, 800);
        usernamePrompt.setBounds(350,0, 150, 150);
        passwordPrompt.setBounds(355, 30, 150, 150);
        repeatPasswordPrompt.setBounds(270, 60, 200, 150);
        usernameField.setBounds(500, 65, 200, 25);
        passwordField.setBounds(500, 95, 200, 25);
        passwordField2.setBounds(500, 125, 200, 25);

        backgroundPanel.add(usernamePrompt);
        backgroundPanel.add(usernameField);
        backgroundPanel.add(passwordPrompt);
        backgroundPanel.add(passwordField);
        backgroundPanel.add(repeatPasswordPrompt);
        backgroundPanel.add(passwordField2);
        backgroundPanel.add(background);

        return backgroundPanel;
    }

    /**
     * Creates all the necessary button elements and puts them into a JPanel
     * @return The JPanel with all the button elements
     */
    @Override
    public JPanel loadButtons() {
        int BUTTON_WIDTH = 100;
        int BUTTON_HEIGHT = 60;

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JButton[] buttons = {new JButton("Login"), new JButton("Register")};

        for (JButton button: buttons){
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    if (evt.getActionCommand().equals("Login")){
                        try{
                            UserLoginResponseModel response = lcontroller.create(usernameField.getText(),
                                    new String(passwordField.getPassword()));
                            loggedIn = response.isLoggedIn();
                            type = response.getType();
                            user = response.getUser();
                            } catch (Exception e) {
                            JOptionPane.showMessageDialog(frame, e.getMessage());

                        }
                    }
                    else if (evt.getActionCommand().equals("Register")){
                        try {
                            UserRegisterResponseModel response = rcontroller.create(usernameField.getText(),
                                    new String(passwordField.getPassword()),
                                    new String(passwordField2.getPassword()));
                            loggedIn = response.isLoggedIn();
                            type = response.getType();
                            user = response.getUser();
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(frame, e.getMessage());
                        }

                    }
                }
            });
            button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
            buttonPanel.add(button);
        }

        return buttonPanel;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public String getUser() {
        return user;
    }

    public String getType() {
        return type;
    }

}
