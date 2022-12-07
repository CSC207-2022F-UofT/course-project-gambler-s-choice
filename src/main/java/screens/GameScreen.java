package screens;

import game_entities.Card;
import game_entities.Game;
import game_use_case.CheckResponseModel;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel implements Screen {

    private final int CARD_WIDTH = 60;
    private final int CARD_HEIGHT = 100;
    private final Game game;
    private final JFrame frame;
    private final CheckController cController;


    /**
     * Updates the current window to contain the necessary items in the game
     * @param frame The current main window being used
     * @param game The current game in play
     */
    public GameScreen(JFrame frame, Game game, CheckController cController) {

        this.game = game;
        this.frame = frame;
        this.cController = cController;

        this.setLayout(new BorderLayout());

        this.add(loadBackground());
        this.add(this.loadButtons(), BorderLayout.SOUTH);


    }

    /**
     * Creates all the cards that are visible to the player and puts them into an array
     * @param game The current game state to determine what the cards are
     * @return An array of the images of cards that are visible to the player
     */
    private ImagePanel[] loadSeenCards(Game game){
        Card[] handCards = game.getCurrPlayerObj().getCards();
        Card[] boardCards = game.getTableCards();
        ImagePanel[] cards = new ImagePanel[2 + boardCards.length];
        // TODO delete this
//        System.out.println("Cards" + boardCards);

//        for (Card card : boardCards) {
//            System.out.println(card);
//        }

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

    /**
     * Creates all the card backs for the hands of the opposing players
     * @param game The current game state to determine how many players there are
     * @return An array of the images of the card backs for the opposing players
     */
    private ImagePanel[] loadHiddenCards(Game game){
        int players = game.getPlayers().length * 2 - 2;
        String cardBack = "images/game_entities.Card Back.png";
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

    /**
     * Puts all the background elements into a JPanel for the frame to have
     * @return The JPanel with all the background elements
     */
    @Override
    public JPanel loadBackground(){
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
            //System.out.println(card);
            backgroundPanel.add(card);
        }
        backgroundPanel.add(background);

        return backgroundPanel;
    }

    /**
     * Creates all the necessary buttons for this screen and puts them into a JPanel
     * @return The JPanel with all the necessary button elements
     */
    @Override
    public JPanel loadButtons() {
        int BUTTON_WIDTH = 80;
        int BUTTON_HEIGHT = 60;
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JButton[] buttons = {new JButton("Check"), new JButton("Bet"), new JButton("Call"),
                new JButton("Fold"), new JButton("Menu")};
        //TODO ADD ACTION LISTENERS FOR BUtTONS TO CALL GAME CLASS DECISION MAKER METHOD
        buttons[0].addActionListener(e -> {
            System.out.println("Check");

            try {
                CheckResponseModel response = cController.create(game.getCurrentPlayerAsInt(), "");
            } catch (Exception ee) {
                JOptionPane.showMessageDialog(frame, ee.getMessage());
            }
        });
        for (JButton button: buttons){
            button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
            buttonPanel.add(button);
        }

        return buttonPanel;
    }
}
