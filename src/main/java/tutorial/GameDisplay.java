package tutorial;
import java.awt.*;
import javax.swing.*;

public class GameDisplay extends JFrame{

    private Container con = getContentPane();
    public GameDisplay(JPanel gameState) {
        //initialization
        super("Poker");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        con.setLayout(new BorderLayout());

        Container displayCon = gameState; //pass in the games current state into here

        Container movesCon = new JPanel();
        movesCon.setLayout(new FlowLayout());
        movesCon.add(new JButton("Check"));
        movesCon.add(new JButton("Bet"));
        movesCon.add(new JButton("Fold"));
        movesCon.add(new JButton("Menu"));

        con.add(displayCon, BorderLayout.PAGE_START);
        con.add(movesCon, BorderLayout.PAGE_END);

        pack();
        setVisible(true); //comment this line out if need be
    }
}
