import javax.swing.*;
import java.awt.*;

public class LoginScreen implements Screen{

    /**
     * Updates the current window to contain the necessary items in the game
     * @param frame The current main window being used
     */
    public LoginScreen(JFrame frame){
        frame.setLayout(new BorderLayout());
        Container container = frame.getContentPane();

        container.add(loadBackground());
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
        Font font = new Font("", Font.PLAIN, 24);
        ImagePanel background = new ImagePanel("images/Login Background.jpg", 0, 0, 1000, 800);

        JLabel usernamePrompt = new JLabel("Username:");
        usernamePrompt.setFont(font);
        JTextField usernameField = new JTextField();
        usernameField.setFont(font);
        JLabel passwordPrompt = new JLabel("Password:");
        passwordPrompt.setFont(font);
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setFont(font);

        background.setBounds(0, 0, 1000, 800);
        usernamePrompt.setBounds(350,0, 150, 150);
        usernameField.setBounds(500, 65, 200, 25);
        passwordPrompt.setBounds(355, 30, 150, 150);
        passwordField.setBounds(500, 95, 200, 25);

        backgroundPanel.add(usernamePrompt);
        backgroundPanel.add(usernameField);
        backgroundPanel.add(passwordPrompt);
        backgroundPanel.add(passwordField);
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
            button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
            buttonPanel.add(button);
        }

        return buttonPanel;
    }

}
