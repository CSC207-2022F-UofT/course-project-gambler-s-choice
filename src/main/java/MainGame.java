import javax.swing.*;

import game_entities.*;
import game_use_case.CheckInputBoundary;
import game_use_case.CheckInteractor;
import game_use_case.CheckPresenter;
import game_use_case.CheckResponseFormatter;
import screens.CheckController;
import screens.GameScreen;

import java.awt.*;

public class MainGame {
    public static void main(String[] args) {
        Player player1 = new Player(new Card("5", "H"), new Card("7", "H"));
        Player player2 = new Player(new Card("K", "D"), new Card("9", "C"));

        Player[] players = {player1, player2};
        Game game = new Game(players);

        int length = game.getPlayers().length;
        String[] card1 = new String[length];
        String[] card2 = new String[length];
        String[] boardCard = new String[5];
        String[] card1PNG = new String[length];
        String[] card2PNG = new String[length];
        String[] boardCardPNG = new String[5];
        int[] playerBalance = new int[length];
        int[] playerBets = new int[length];
        boolean[] isActive = new boolean[length];

        int currentPlayer = 0;
        int firstPlayer = 0;
        int lastToBet = 0;
        int currentBet = 0;

        for (int i = 0; i < length; i++) {
            card1[i] = game.getPlayers()[i].getCards()[0].toString();
            card2[i] = game.getPlayers()[i].getCards()[1].toString();
            card1PNG[i] = game.getPlayers()[i].getCards()[0].getPNG();
            card2PNG[i] = game.getPlayers()[i].getCards()[1].getPNG();
            playerBalance[i] = 3 * i;
            playerBets[i] = 0;
            isActive[i] = true;
            // System.out.println(card1[i] + " " + card2[i] + " " + card1PNG[i] + " " + card2PNG[i]);
        }
        for (int i = 0; i < 5; i++) {
            boardCard[i] = game.getBoardCards()[i].toString();
            boardCardPNG[i] = game.getBoardCards()[i].getPNG();
            // System.out.println(boardCard[i] + " " + boardCardPNG[i]);
        }
        String[] deck = {"DA", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "DX", "DJ", "DQ", "DK",
                "CA", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "CX", "CJ", "CQ", "CK",
                "HA", "H2", "H3", "H4", "H5", "H6", "H7", "H8", "H9", "HX", "HJ", "HQ", "HK",
                "SA", "S2", "S3", "S4", "S5", "S6", "S7", "S8", "S9", "SX", "SJ", "SQ", "SK"};


        CheckPresenter checkPresenter = new CheckResponseFormatter();
        GameFactoryInterface gameFactory = new GameFactory();
        CheckInputBoundary checkInputBoundary = new CheckInteractor(checkPresenter, gameFactory);
        CheckController checkController = new CheckController(checkInputBoundary);

        JFrame application = new JFrame("Gambler's Choice");
        CardLayout cardLayout = new CardLayout();
        JPanel screens = new JPanel(cardLayout);
        application.add(screens);

        GameScreen gameScreen = new GameScreen(application,
                currentPlayer, firstPlayer, lastToBet, playerBalance,
                card1, card2, boardCard, card1PNG, card2PNG,
                boardCardPNG, currentBet, isActive, playerBets, deck,
                checkController);

        screens.add(gameScreen, "Game");
        application.pack();
        application.setSize(1000,800);
        application.setResizable(false);
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.setVisible(true);

        while (true) {
            if (gameScreen.isInteract()) {
                currentPlayer = gameScreen.getCurrentPlayer();
                firstPlayer = gameScreen.getFirstPlayer();
                lastToBet = gameScreen.getLastToBet();
                playerBalance = gameScreen.getPlayerBalance();
                card1 = gameScreen.getCard1();
                card2 = gameScreen.getCard2();
                boardCard = gameScreen.getBoardCard();
                card1PNG = gameScreen.getCard1PNG();
                card2PNG = gameScreen.getCard2PNG();
                boardCardPNG = gameScreen.getBoardCardPNG();
                currentBet = gameScreen.getCurrentBet();
                isActive = gameScreen.getIsActive();
                playerBets = gameScreen.getPlayerBets();
                deck = gameScreen.getDeck();
                gameScreen = new GameScreen(application,
                        currentPlayer, firstPlayer, lastToBet, playerBalance,
                        card1, card2, boardCard, card1PNG, card2PNG,
                        boardCardPNG, currentBet, isActive, playerBets, deck,
                        checkController);
                screens.add(gameScreen, "Game");
            }

            cardLayout.show(screens, "Game");
        }
    }

}
