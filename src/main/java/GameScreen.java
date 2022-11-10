import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameScreen extends JFrame {

    private Container container;
    private JButton checkButton;
    private JButton betButton;
    private JButton callButton;
    private JButton foldButton;
    private JButton menuButton;


    public GameScreen() {
        super("Gambler's Choice");
        setLayout(new BorderLayout());
        container = getContentPane();

        ImagePanel background = new ImagePanel("images/Poker Table.png", 0, 0, 1000, 800);

        checkButton = new JButton("Check");
        betButton = new JButton("Bet");
        callButton = new JButton("Call");
        foldButton = new JButton("Fold");
        menuButton = new JButton("Menu");

        ImagePanel card1 = new ImagePanel("images/Playing Cards/3_of_hearts.png", 0, 0, 60, 100);
        ImagePanel card2 = new ImagePanel("images/Playing Cards/4_of_hearts.png", 0, 0, 60, 100);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());


        background.setBounds(0, 0, 1000, 800);
        card1.setBounds(500, 500, 60, 100);
        card2.setBounds(435, 500, 60, 100);
        checkButton.setPreferredSize(new Dimension(80,60));
        betButton.setPreferredSize(new Dimension(80,60));
        callButton.setPreferredSize(new Dimension(80,60));
        foldButton.setPreferredSize(new Dimension(80,60));
        menuButton.setPreferredSize(new Dimension(80,60));


        buttonPanel.add(checkButton);
        buttonPanel.add(betButton);
        buttonPanel.add(callButton);
        buttonPanel.add(foldButton);
        buttonPanel.add(menuButton);
        container.add(card1);
        container.add(card2);
        container.add(background);
        container.add(buttonPanel, BorderLayout.SOUTH);

        setSize(1000,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    public static void main(String[] args){

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameScreen();
            }
        });

    }
}
