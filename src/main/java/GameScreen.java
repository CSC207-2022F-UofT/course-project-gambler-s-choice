import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameScreen implements Screen{

    private final int CARD_WIDTH = 60;
    private final int CARD_HEIGHT = 100;


    public GameScreen(JFrame frame, Game game) {
        this.clearScreen(frame);

        frame.setLayout(new BorderLayout());
        Container container = frame.getContentPane();

        container.add(loadBackground(game));
        container.add(this.loadButtons(), BorderLayout.SOUTH);

        frame.setSize(1000,800);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private ImagePanel[] loadSeenCards(Player player){
        Card[] cards = player.getCards();
        ImagePanel[] handCards = new ImagePanel[2];

        for (int i = 0; i <= 1; i++){
            handCards[i] = new ImagePanel(cards[i].getPNG(), 0, 0, CARD_WIDTH, CARD_HEIGHT);
            handCards[i].setBounds(435 + 65*i, 500, CARD_WIDTH, CARD_HEIGHT);
        }

        return handCards;
    }

    private ImagePanel[] loadHiddenCards(Game game){
        int players = game.getPlayers().length * 2 - 2;
        String cardBack = "images/Card Back.png";
        ImagePanel[] oppCards = new ImagePanel[players];
        for (int i = 0; i <= players; i+=2){
            oppCards[i] = new ImagePanel(cardBack, 0, 0, CARD_WIDTH, CARD_HEIGHT);
            oppCards[i + 1] = new ImagePanel(cardBack, 0, 0, CARD_WIDTH, CARD_HEIGHT);
            if (i == 2){
                oppCards[i].setBounds(435, 125, CARD_WIDTH, CARD_HEIGHT);
                oppCards[i + 1].setBounds(500, 125, CARD_WIDTH, CARD_HEIGHT);
            }
            else{
                oppCards[i].setBounds((int)(185 + i*(128.75)), 150 + (315 * i/6), CARD_WIDTH, CARD_HEIGHT);
                oppCards[i + 1].setBounds((int)(250 + i*(128.75)), 150 + (315 * i/6), CARD_WIDTH, CARD_HEIGHT);
            }
        }

        return oppCards;
    }

    private JPanel loadBackground(Game game){
        JPanel backgroundPanel = new JPanel();

        ImagePanel background = new ImagePanel("images/Poker Table.png", 0, 0, 1000, 800);
        ImagePanel[] cards = loadHiddenCards(game) + loadSeenCards(game.currentPlayer());
        JLabel betPrompt = new JLabel("Bet amount:");
        JTextField betAmount = new JTextField();


        background.setBounds(0, 0, 1000, 800);
        betPrompt.setBounds(370, 640, 240, 40);
        betAmount.setBounds(440, 640, 110, 40);

        for (ImagePanel card: cards){
            backgroundPanel.add(card);
        }
        backgroundPanel.add(betPrompt);
        backgroundPanel.add(betAmount);

        backgroundPanel.add(background);

        return backgroundPanel;
    }

    @Override
    public JPanel loadButtons() {
        int BUTTON_WIDTH = 80;
        int BUTTON_HEIGHT = 60;
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JButton[] buttons = {new JButton("Check"), new JButton("Bet"), new JButton("Call"),
                new JButton("Fold"), new JButton("Menu")};

        for (JButton button: buttons){
            button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
            buttonPanel.add(button);
        }

        return buttonPanel;
    }

    public static void main(String[] args){

        JFrame aaaa = new JFrame();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameScreen(aaaa);
            }
        });

    }
}
