import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameScreen implements Screen{

    private final int CARD_WIDTH = 60;
    private final int CARD_HEIGHT = 100;


    public GameScreen(JFrame frame, Game game) {

        frame.setLayout(new BorderLayout());
        Container container = frame.getContentPane();

        container.add(loadBackground(game));
        container.add(this.loadButtons(), BorderLayout.SOUTH);

        frame.setSize(1000,800);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private ImagePanel[] loadSeenCards(Game game){
        Card[] handCards = game.getCurrPlayer().getCards();
        Card[] boardCards = game.getBoardCards();
        ImagePanel[] cards = new ImagePanel[2 + boardCards.length];

        for (int i = 0; i < boardCards.length + handCards.length; i++){
            if (i < 2) {
                cards[i] = new ImagePanel(handCards[i].getPNG(), 0, 0, CARD_WIDTH, CARD_HEIGHT);
                cards[i].setBounds(435 + 65 * i, 500, CARD_WIDTH, CARD_HEIGHT);
            }
            else {
                cards[i] = new ImagePanel(boardCards[i - 2].getPNG(), 0, 0, CARD_WIDTH, CARD_HEIGHT);
                cards[i].setBounds(335 + (i - 2)*65, 310, CARD_WIDTH, CARD_HEIGHT);
            }
        }

        return cards;
    }

    private ImagePanel[] loadHiddenCards(Game game){
        int players = game.getPlayers().length * 2 - 2;
        String cardBack = "images/Card Back.png";
        ImagePanel[] oppCards = new ImagePanel[players];
        for (int i = 0; i < players; i+=2){
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
        backgroundPanel.setLayout(new BorderLayout());

        ImagePanel background = new ImagePanel("images/Poker Table.png", 0, 0, 1000, 800);
        ImagePanel[] cards = new ImagePanel[loadSeenCards(game).length + loadHiddenCards(game).length];
        ImagePanel[] seenCards = loadSeenCards(game);
        ImagePanel[] hiddenCards = loadHiddenCards(game);

        for (int i = 0; i <= seenCards.length + hiddenCards.length - 1; i++){
            if (i < hiddenCards.length){
                cards[i] = hiddenCards[i];
            }
            else{
                cards[i] = seenCards[i - hiddenCards.length];
            }
        }
        JLabel betPrompt = new JLabel("Bet amount:");
        JTextField betAmount = new JTextField();

        background.setBounds(0, 0, 1000, 800);
        betPrompt.setBounds(370, 640, 240, 40);
        betAmount.setBounds(440, 640, 110, 40);

        backgroundPanel.add(betPrompt);
        backgroundPanel.add(betAmount);
        for (ImagePanel card: cards){
            System.out.println(card);
            backgroundPanel.add(card);
        }
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
        //TODO ADD ACTION LISTENERS FOR BUtTONS TO CALL GAME CLASS DECISION MAKER METHOD
        for (JButton button: buttons){
            button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
            buttonPanel.add(button);
        }

        return buttonPanel;
    }
}
