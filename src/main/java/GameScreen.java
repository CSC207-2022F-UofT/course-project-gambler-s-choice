import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameScreen extends JFrame {

    public GameScreen() {
        super("Gambler's Choice");
        setLayout(new BorderLayout());
        Container container = getContentPane();

        ImagePanel background = new ImagePanel("images/Poker Table.png", 0, 0, 1000, 800);

        JButton checkButton = new JButton("Check");
        JButton betButton = new JButton("Bet");
        JButton callButton = new JButton("Call");
        JButton foldButton = new JButton("Fold");
        JButton menuButton = new JButton("Menu");

        JLabel betPrompt = new JLabel("Bet amount:");
        JTextField betAmount = new JTextField();

        //TODO Replace with whatever we are using to determine card in hand (add image link directly to the card class)
        ImagePanel handCard1 = new ImagePanel("images/Playing Cards/3_of_hearts.png", 0, 0, 60, 100);
        ImagePanel handCard2 = new ImagePanel("images/Playing Cards/4_of_hearts.png", 0, 0, 60, 100);

        //TODO Same as above except with deck cards
        ImagePanel boardCard1 = new ImagePanel("images/Playing Cards/5_of_hearts.png", 0, 0, 60, 100);
        ImagePanel boardCard2 = new ImagePanel("images/Playing Cards/6_of_hearts.png", 0, 0, 60, 100);
        ImagePanel boardCard3 = new ImagePanel("images/Playing Cards/7_of_hearts.png", 0, 0, 60, 100);
        ImagePanel boardCard4 = new ImagePanel("images/Playing Cards/8_of_hearts.png", 0, 0, 60, 100);
        ImagePanel boardCard5 = new ImagePanel("images/Playing Cards/9_of_hearts.png", 0, 0, 60, 100);


        ImagePanel oppCard1 = new ImagePanel("images/Card Back.png", 0, 0, 60, 100);
        ImagePanel oppCard2 = new ImagePanel("images/Card Back.png", 0, 0, 60, 100);
        ImagePanel oppCard3 = new ImagePanel("images/Card Back.png", 0, 0, 60, 100);
        ImagePanel oppCard4 = new ImagePanel("images/Card Back.png", 0, 0, 60, 100);
        ImagePanel oppCard5 = new ImagePanel("images/Card Back.png", 0, 0, 60, 100);
        ImagePanel oppCard6 = new ImagePanel("images/Card Back.png", 0, 0, 60, 100);
        ImagePanel oppCard7 = new ImagePanel("images/Card Back.png", 0, 0, 60, 100);
        ImagePanel oppCard8 = new ImagePanel("images/Card Back.png", 0, 0, 60, 100);
        ImagePanel oppCard9 = new ImagePanel("images/Card Back.png", 0, 0, 60, 100);
        ImagePanel oppCard10 = new ImagePanel("images/Card Back.png", 0, 0, 60, 100);

        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());


        //TODO have cards come out only when needed
        boardCard1.setBounds(335, 310, 60, 100);
        boardCard2.setBounds(400, 310, 60, 100);
        boardCard3.setBounds(465, 310, 60, 100);
        boardCard4.setBounds(530, 310, 60, 100);
        boardCard5.setBounds(595, 310, 60, 100);

        background.setBounds(0, 0, 1000, 800);
        handCard1.setBounds(435, 500, 60, 100);
        handCard2.setBounds(500, 500, 60, 100);
        oppCard1.setBounds(185, 150, 60, 100);
        oppCard2.setBounds(250, 150, 60, 100);
        oppCard3.setBounds(435, 125, 60, 100);
        oppCard4.setBounds(500, 125, 60, 100);
        oppCard5.setBounds(700, 150, 60, 100);
        oppCard6.setBounds(765, 150, 60, 100);
        oppCard7.setBounds(185, 465, 60, 100);
        oppCard8.setBounds(250, 465, 60, 100);
        oppCard9.setBounds(700, 465, 60, 100);
        oppCard10.setBounds(765, 465, 60, 100);
        betPrompt.setBounds(370, 640, 240, 40);
        betAmount.setBounds(440, 640, 110, 40);
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
        backgroundPanel.add(betPrompt);
        backgroundPanel.add(betAmount);
        backgroundPanel.add(oppCard1);
        backgroundPanel.add(oppCard2);
        backgroundPanel.add(oppCard3);
        backgroundPanel.add(oppCard4);
        backgroundPanel.add(oppCard5);
        backgroundPanel.add(oppCard6);
        backgroundPanel.add(oppCard7);
        backgroundPanel.add(oppCard8);
        backgroundPanel.add(oppCard9);
        backgroundPanel.add(oppCard10);
        backgroundPanel.add(boardCard1);
        backgroundPanel.add(boardCard2);
        backgroundPanel.add(boardCard3);
        backgroundPanel.add(boardCard4);
        backgroundPanel.add(boardCard5);
        backgroundPanel.add(handCard1);
        backgroundPanel.add(handCard2);
        backgroundPanel.add(background);
        container.add(backgroundPanel);
        container.add(buttonPanel, BorderLayout.SOUTH);

        setSize(1000,800);
        setResizable(false);
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
