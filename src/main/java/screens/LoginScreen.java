package screens;

import login_menu_entities.UserFactory;
import login_menu_entities.UserInterfaceFactory;
import login_menu_use_casee.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;

public class LoginScreen implements Screen {

    private final Font font = new Font("", Font.PLAIN, 24);
    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JPasswordField passwordField2;

    /**
     * Updates the current window to contain the necessary items in the game
     * @param frame The current main window being used
     */
    public LoginScreen(JFrame frame){
        frame.setLayout(new BorderLayout());
        Container container = frame.getContentPane();


        JPanel backgroundPanel = loadBackground();
        usernameField = new JTextField();
        usernameField.setFont(font);
        usernameField.setBounds(500, 65, 200, 25);
        passwordField = new JPasswordField(20);
        passwordField.setFont(font);
        passwordField.setBounds(500, 95, 200, 25);
        passwordField2 = new JPasswordField(20);
        passwordField2.setFont(font);
        passwordField2.setBounds(500, 125, 200, 25);
        backgroundPanel.add(usernameField);
        backgroundPanel.add(passwordField);
        backgroundPanel.add(passwordField2);
        container.add(backgroundPanel);

        container.add(this.loadButtons(), BorderLayout.SOUTH);

        frame.setSize(1000,800);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Creates all the necessary elements for the background and puts them into a JPanel
     * @return The JPanel with all the background elements
     */
    @Override
    public JPanel loadBackground() {
        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setLayout(new BorderLayout());

        ImagePanel background = new ImagePanel("images/Login Background.jpg", 0, 0, 1000, 800);

        JLabel usernamePrompt = new JLabel("Username:");
        usernamePrompt.setFont(font);

        JLabel passwordPrompt = new JLabel("Password:");
        passwordPrompt.setFont(font);

        background.setBounds(0, 0, 1000, 800);
        usernamePrompt.setBounds(350,0, 150, 150);
        passwordPrompt.setBounds(355, 30, 150, 150);

        backgroundPanel.add(usernamePrompt);
        backgroundPanel.add(passwordPrompt);
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
                        UserLoginDSGateway user;
                        try{
                            user = new FileChecker("./users.txt");
                        } catch (IOException e){
                            throw new RuntimeException("File could not be created");
                        }
                        UserLoginPresenter presenter = new UserLoginResponseFormatter();
                        UserInterfaceFactory userFactory = new UserFactory();

                        UserLoginInputBoundary inputBoundary = new UserLoginInteractor(user, presenter, userFactory);
                        LoginController controller = new LoginController(inputBoundary);

                        try{
                            controller.create(usernameField.getText(),
                                    Arrays.toString(passwordField.getPassword()));
                        } catch (Exception e) {
                            ErrorPanel errorPanel = new ErrorPanel(e.getMessage());
                            JOptionPane.showMessageDialog(errorPanel, e.getMessage());
                        }
                    }
                    else if (evt.getActionCommand().equals("Register")){

                        UserInterfaceFactory userFactory = new UserFactory();

                       // UserRegisterInputBoundary inputBoundary = new UserRegisterInteractor();
                      //  RegisterController controller = new RegisterController(inputBoundary);
                    }
                }
            });
            button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
            buttonPanel.add(button);
        }

        return buttonPanel;
    }

}
