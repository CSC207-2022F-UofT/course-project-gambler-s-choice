import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainMenu{
    private JFrame frame;
    private JLabel label;
    public MainMenu(){

        this.frame = new JFrame("Main Menu");
        /*
        setLayout(new BorderLayout());
        Container container = getContentPane();

        BackgroundPanel background = new BackgroundPanel("images/Poker Table.jpg", 1000, 800);

        JButton helpButton = new JButton("Help");
        JButton logoutButton = new JButton("Log Out");
        JButton exitButton = new JButton("Exit Game");
        JButton backButton = new JButton("Go back");

        helpButton.setSize(new Dimension(80,60));
        logoutButton.setSize(new Dimension(80,60));
        exitButton.setSize(new Dimension(80,60));
        backButton.setSize(new Dimension(80,60));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));



        buttonPanel.add(backButton);


        buttonPanel.add(helpButton);

        buttonPanel.add(logoutButton);

        buttonPanel.add(exitButton);

        background.add(buttonPanel);
        container.add(buttonPanel, BorderLayout.WEST);

        container.add(background);





        setSize(1000,800);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);*/
        this.label = new JLabel();
        this.label.setSize(1000,800);
        scaleImage("images/Menu.jpg", this.label);//scales the image to the label



        JButton helpButton = new JButton("Help");

        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showInputDialog(null, "This is the message", "This is the default text");
            }
        });

        JButton logoutButton = new JButton("Log Out");
        JButton exitButton = new JButton("Exit Game");
        JButton backButton = new JButton("Go back");

        ArrayList <Rectangle> coords = calcCoord(10,10, 4, 100, 60, 10);


        helpButton.setBounds(coords.get(0));
        logoutButton.setBounds(coords.get(1));
        exitButton.setBounds(coords.get(2));
        backButton.setBounds(coords.get(3));

        this.label.add(backButton);

        this.label.add(helpButton);

        this.label.add(logoutButton);

        this.label.add(exitButton);




        frame.add(this.label);
        frame.setSize(1000,800);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);




    }
    //THIS IS TEMPORARY AS I DON'T KNOW WHERE ELSE TO PUT THIS METHOD
    private void scaleImage(String location, JLabel label){
        ImageIcon icon = new ImageIcon(location);
        Image img = icon.getImage();
        Image imgScale = img.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        label.setIcon(scaledIcon);
    }

    private ArrayList <Rectangle> calcCoord(int x, int y, int num, int width, int height, int gap){
        ArrayList<Rectangle> coords = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            Rectangle rectangle = new Rectangle(x, y + i * (height + gap), width, height);
            coords.add(rectangle);
        }
        return coords;
    }

    public static void main(String[] args){

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainMenu();
            }
        });

    }
}
