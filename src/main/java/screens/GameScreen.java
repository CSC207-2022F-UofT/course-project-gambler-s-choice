package screens;

import game_use_case.ResponseModel;

import javax.swing.*;
import java.awt.*;

/**
 * The view/screen that is displayed when the game is displayed
 */
public class GameScreen extends JPanel implements Screen {

    int currentPlayer;
    int firstPlayer;
    int lastToBet;
    int[] playerBalance;
    String[] card1;
    String[] card2;
    String[] tableCard;
    String[] card1PNG;
    String[] card2PNG;
    String[] tableCardPNG;
    int currentBet;
    boolean[] isActive;
    int[] playerBets;
    String[] deck;
    boolean isInteract = false;
    boolean inGame = true;

    public boolean isInteract() {
        return isInteract;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public int getFirstPlayer() {
        return firstPlayer;
    }

    public int getLastToBet() {
        return lastToBet;
    }

    public int[] getPlayerBalance() {
        return playerBalance;
    }

    public String[] getCard1() {
        return card1;
    }

    public String[] getCard2() {
        return card2;
    }

    public String[] getTableCard() {
        return tableCard;
    }

    public String[] getCard1PNG() {
        return card1PNG;
    }

    public String[] getCard2PNG() {
        return card2PNG;
    }

    public String[] getTableCardPNG() {
        return tableCardPNG;
    }

    public int getCurrentBet() {
        return currentBet;
    }

    public boolean[] getIsActive() {
        return isActive;
    }

    public int[] getPlayerBets() {
        return playerBets;
    }

    public String[] getDeck() {
        return deck;
    }

    public boolean getInGame() {
        return this.inGame;
    }

    private final int CARD_WIDTH = 60;
    private final int CARD_HEIGHT = 100;
    private final JFrame frame;
    private final CheckController cController;
    private final BetController bController;
    private final FoldController fController;
    private final CallController aController;
    private final LeaveController lController;
    private final String user;

    private final JTextField betAmount = new JTextField();


    /**
     * Updates the current window to contain the necessary items in the game
     * @param frame The current main window being used
     * @param currentPlayer The player whose turn it currently is
     * @param firstPlayer The player who will start the round
     * @param lastToBet The player who will end the round
     * @param playerBalance The balance of the players
     * @param card1 The left card of the players
     * @param card2 The right card of the players
     * @param tableCard The cards that are displayed on the table at this state
     * @param card1PNG The image link of the left card of the player
     * @param card2PNG The image link of the right card of the player
     * @param tableCardPNG The image links of the cards on the table
     * @param currentBet The value the current bet is at
     * @param isActive Whether the turn is active or not to decide when to update the screen
     * @param playerBets The total bets from each player at the table
     * @param deck The cards that have not been used yet
     * @param cController The controller needed for the game to make decisions if the user checks
     * @param bController The controller needed for the game to make decisions if the user bets
     * @param aController The controller needed for the game to make decisions if the user calls
     * @param fController The controller needed for the game to make decisions if the user folds
     * @param lController The controller needed for the game to make decisions if the user leaves
     * @param user The user who is logged in
     */
    public GameScreen(JFrame frame,
                      int currentPlayer, int firstPlayer, int lastToBet, int[] playerBalance,
                      String[] card1, String[] card2, String[] tableCard, String[] card1PNG, String[] card2PNG,
                      String[] tableCardPNG, int currentBet, boolean[] isActive, int[] playerBets, String[] deck,
                      CheckController cController, BetController bController,
                      CallController aController, FoldController fController, LeaveController lController, String user) {

        this.frame = frame;
        this.cController = cController;
        this.bController = bController;
        this.aController = aController;
        this.fController = fController;
        this.lController = lController;

        this.currentPlayer = currentPlayer;
        this.firstPlayer = firstPlayer;
        this.lastToBet = lastToBet;
        this.playerBalance = playerBalance;
        this.card1 = card1;
        this.card2 = card2;
        this.tableCard = tableCard;
        this.card1PNG = card1PNG;
        this.card2PNG = card2PNG;
        this.tableCardPNG = tableCardPNG;
        this.currentBet = currentBet;
        this.isActive = isActive;
        this.playerBets = playerBets;
        this.deck = deck;
        this.user = user;

        this.setLayout(new BorderLayout());

        this.add(loadBackground());
        this.add(this.loadButtons(), BorderLayout.SOUTH);

    }

    /**
     * Initializes a default game screen on start of a new game
     * @param frame The current main window being used
     * @param cController The controller needed for the game to make decisions if the user checks
     * @param bController The controller needed for the game to make decisions if the user bets
     * @param aController The controller needed for the game to make decisions if the user calls
     * @param fController The controller needed for the game to make decisions if the user folds
     * @param lController The controller needed for the game to make decisions if the user leaves
     * @param user The user who is logged in
     */
    public GameScreen(JFrame frame,CheckController cController, BetController bController,
                      CallController aController, FoldController fController, NewGameController nController, LeaveController lController, String user) {

        this.frame = frame;
        this.cController = cController;
        this.bController = bController;
        this.aController = aController;
        this.fController = fController;
        this.lController = lController;

        int NUMBER_OF_PLAYERS = 4;

        ResponseModel response = nController.create(NUMBER_OF_PLAYERS);

        this.currentPlayer = response.getCurrentPlayer();
        this.firstPlayer = response.getFirstPlayer();
        this.lastToBet = response.getLastToBet();
        this.playerBalance = response.getPlayerBalance();
        this.card1 = response.getCard1();
        this.card2 = response.getCard2();
        this.tableCard = response.getTableCard();
        this.card1PNG = response.getCard1PNG();
        this.card2PNG = response.getCard2PNG();
        this.tableCardPNG = response.getTableCardPNG();
        this.currentBet = response.getCurrentBet();
        this.isActive = response.getIsActive();
        this.playerBets = response.getPlayerBets();
        this.deck = response.getDeck();
        this.user = user;

        this.setLayout(new BorderLayout());

        this.add(loadBackground());
        this.add(this.loadButtons(), BorderLayout.SOUTH);
    }

    /**
     * Creates all the cards that are visible to the player and puts them into an array
     *
     * @return An array of the images of cards that are visible to the player
     */
    private ImagePanel[] loadSeenCards() {
        String[] handCards = new String[]{
                this.card1PNG[currentPlayer], this.card2PNG[currentPlayer]
        };
        String[] tableCards = this.tableCardPNG;
        ImagePanel[] cards = new ImagePanel[2 + lenNotNull(tableCards)];

        for (int i = 0; i < lenNotNull(tableCards) + handCards.length; i++){
            if (i < 2) {
                cards[i] = new ImagePanel(handCards[i], 0, 0, CARD_WIDTH, CARD_HEIGHT);
                cards[i].setBounds(435 + 65 * i, 500, CARD_WIDTH, CARD_HEIGHT);
            }
            else {
                cards[i] = new ImagePanel(tableCards[i - 2], 0, 0, CARD_WIDTH, CARD_HEIGHT);
                cards[i].setBounds(335 + (i - 2)*65, 310, CARD_WIDTH, CARD_HEIGHT);
            }
        }

        return cards;
    }

    /**
     * Creates all the card backs for the hands of the opposing players
     *
     * @return An array of the images of the card backs for the opposing players
     */
    private ImagePanel[] loadHiddenCards(){
        int players = card1.length * 2 - 2;
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

    /**
     * Puts all the background elements into a JPanel for the frame to have
     * @return The JPanel with all the background elements
     */
    @Override
    public JPanel loadBackground(){
        JPanel backgroundPanel = new JPanel();
        backgroundPanel.setLayout(new BorderLayout());

        ImagePanel background = new ImagePanel("images/Poker Table.png", 0, 0, 1000, 800);
        ImagePanel[] cards = new ImagePanel[loadSeenCards().length + loadHiddenCards().length];
        ImagePanel[] seenCards = loadSeenCards();
        ImagePanel[] hiddenCards = loadHiddenCards();

        for (int i = 0; i <= seenCards.length + hiddenCards.length - 1; i++){
            if (i < hiddenCards.length){
                cards[i] = hiddenCards[i];
            }
            else{
                cards[i] = seenCards[i - hiddenCards.length];
            }
        }
        JLabel betPrompt = new JLabel("Bet amount:");
        JLabel balance = new JLabel("Balance: " + this.playerBalance[this.currentPlayer]);
        JLabel player = new JLabel("Player " + (this.currentPlayer + 1) + "'s turn");

        background.setBounds(0, 0, 1000, 800);
        betPrompt.setBounds(370, 640, 240, 40);
        betAmount.setBounds(440, 640, 110, 40); // x = 440, y = 640
        balance.setBounds(25, 0, 100, 50);
        player.setBounds(875, 0, 100, 50);

        backgroundPanel.add(betPrompt);
        backgroundPanel.add(betAmount);
        backgroundPanel.add(balance);
        backgroundPanel.add(player);
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
        buttons[0].addActionListener(e -> {
            try {
                ResponseModel response = cController.create(currentPlayer, firstPlayer, lastToBet, playerBalance,
                        card1, card2, tableCard, card1PNG, card2PNG, tableCardPNG, currentBet, isActive, playerBets,
                        deck, "0", user);
                this.currentPlayer = response.getCurrentPlayer();
                this.firstPlayer = response.getFirstPlayer();
                this.lastToBet = response.getLastToBet();
                this.playerBalance = response.getPlayerBalance();
                this.card1 = response.getCard1();
                this.card2 = response.getCard2();
                this.tableCard = response.getTableCard();
                this.card1PNG = response.getCard1PNG();
                this.card2PNG = response.getCard2PNG();
                this.tableCardPNG = response.getTableCardPNG();
                this.currentBet = response.getCurrentBet();
                this.isActive = response.getIsActive();
                this.playerBets = response.getPlayerBets();
                this.deck = response.getDeck();
                this.isInteract = response.isInteract();
            } catch (Exception ee) {
                JOptionPane.showMessageDialog(frame, ee.getMessage());
            }
        });
        buttons[1].addActionListener(e -> {
            try {
                ResponseModel response = bController.create(currentPlayer, firstPlayer, lastToBet, playerBalance,
                        card1, card2, tableCard, card1PNG, card2PNG, tableCardPNG, currentBet, isActive, playerBets,
                        deck, betAmount.getText(), user);
                this.currentPlayer = response.getCurrentPlayer();
                this.firstPlayer = response.getFirstPlayer();
                this.lastToBet = response.getLastToBet();
                this.playerBalance = response.getPlayerBalance();
                this.card1 = response.getCard1();
                this.card2 = response.getCard2();
                this.tableCard = response.getTableCard();
                this.card1PNG = response.getCard1PNG();
                this.card2PNG = response.getCard2PNG();
                this.tableCardPNG = response.getTableCardPNG();
                this.currentBet = response.getCurrentBet();
                this.isActive = response.getIsActive();
                this.playerBets = response.getPlayerBets();
                this.deck = response.getDeck();
                this.isInteract = response.isInteract();
            } catch (Exception ee) {
                JOptionPane.showMessageDialog(frame, ee.getMessage());
            }
        });
        buttons[2].addActionListener(e -> {
            try {
                ResponseModel response = aController.create(currentPlayer, firstPlayer, lastToBet, playerBalance,
                        card1, card2, tableCard, card1PNG, card2PNG, tableCardPNG, currentBet, isActive, playerBets,
                        deck, "0", user);
                this.currentPlayer = response.getCurrentPlayer();
                this.firstPlayer = response.getFirstPlayer();
                this.lastToBet = response.getLastToBet();
                this.playerBalance = response.getPlayerBalance();
                this.card1 = response.getCard1();
                this.card2 = response.getCard2();
                this.tableCard = response.getTableCard();
                this.card1PNG = response.getCard1PNG();
                this.card2PNG = response.getCard2PNG();
                this.tableCardPNG = response.getTableCardPNG();
                this.currentBet = response.getCurrentBet();
                this.isActive = response.getIsActive();
                this.playerBets = response.getPlayerBets();
                this.deck = response.getDeck();
                this.isInteract = response.isInteract();
            } catch (Exception ee) {
                JOptionPane.showMessageDialog(frame, ee.getMessage());
            }
        });
        buttons[3].addActionListener(e -> {
            try {
                ResponseModel response = fController.create(currentPlayer, firstPlayer, lastToBet, playerBalance,
                        card1, card2, tableCard, card1PNG, card2PNG, tableCardPNG, currentBet, isActive, playerBets,
                        deck, "0", user);
                this.currentPlayer = response.getCurrentPlayer();
                this.firstPlayer = response.getFirstPlayer();
                this.lastToBet = response.getLastToBet();
                this.playerBalance = response.getPlayerBalance();
                this.card1 = response.getCard1();
                this.card2 = response.getCard2();
                this.tableCard = response.getTableCard();
                this.card1PNG = response.getCard1PNG();
                this.card2PNG = response.getCard2PNG();
                this.tableCardPNG = response.getTableCardPNG();
                this.currentBet = response.getCurrentBet();
                this.isActive = response.getIsActive();
                this.playerBets = response.getPlayerBets();
                this.deck = response.getDeck();
                this.isInteract = response.isInteract();
            } catch (Exception ee) {
                JOptionPane.showMessageDialog(frame, ee.getMessage());
            }

        });
        buttons[4].addActionListener(e -> {
           try {
               boolean response = lController.create(currentPlayer, firstPlayer, lastToBet, playerBalance,
                       card1, card2, tableCard, card1PNG, card2PNG, tableCardPNG, currentBet, isActive, playerBets,
                       deck, null, user);
                this.inGame = response;
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

    /**
     * Returns the amount of not null indices in the array
     * All null indices must be at the end of the array
     * Arr must be length of 5
     * @param arr the Array to be measured
     * @return the number of non-null indices
     */
    private static int lenNotNull(String[] arr) {
        if (arr[0] == null) {
            return 0;
        }
        if (arr[1] == null) {
            return 1;
        }
        if (arr[2] == null) {
            return 2;
        }
        if (arr[3] == null) {
            return 3;
        }
        if (arr[4] == null) {
            return 4;
        }
        return 5;
    }
}