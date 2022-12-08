import javax.swing.*;

import game_entities.*;
import game_use_case.*;
import screens.*;

import java.awt.*;

public class MainGame {
    public static void main(String[] args) {
//        Player player1 = new Player(new Card("5", "H"), new Card("7", "H"));
//        Player player2 = new Player(new Card("K", "D"), new Card("9", "C"));
//
//        Player[] players = {player1, player2};
//        Game game = new Game(players);
//
//        int length = game.getPlayers().length;
//        String[] card1 = new String[length];
//        String[] card2 = new String[length];
//        String[] tableCard = new String[5];
//        String[] card1PNG = new String[length];
//        String[] card2PNG = new String[length];
//        String[] tableCardPNG = new String[5];
//        int[] playerBalance = new int[length];
//        int[] playerBets = new int[length];
//        boolean[] isActive = new boolean[length];
//
//        int currentPlayer = 0;
//        int firstPlayer = 0;
//        int lastToBet = 0;
//        int currentBet = 0;
//
//        for (int i = 0; i < length; i++) {
//            card1[i] = game.getPlayers()[i].getCards()[0].toString();
//            card2[i] = game.getPlayers()[i].getCards()[1].toString();
//            card1PNG[i] = game.getPlayers()[i].getCards()[0].getPNG();
//            card2PNG[i] = game.getPlayers()[i].getCards()[1].getPNG();
//            playerBalance[i] = 100;
//            playerBets[i] = 0;
//            isActive[i] = true;
//            // System.out.println(card1[i] + " " + card2[i] + " " + card1PNG[i] + " " + card2PNG[i]);
//        }
//        for (int i = 0; i < lenNotNull(game.getTableCards()); i++) {
//            tableCard[i] = game.getTableCards()[i].toString();
//            tableCardPNG[i] = game.getTableCards()[i].getPNG();
//            // System.out.println(tableCard[i] + " " + tableCardPNG[i]);
//        }
//        String[] deck = {"DA", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "D10", "DJ", "DQ", "DK",
//                "CA", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10", "CJ", "CQ", "CK",
//                "HA", "H2", "H3", "H4", "H5", "H6", "H7", "H8", "H9", "H10", "HJ", "HQ", "HK",
//                "SA", "S2", "S3", "S4", "S5", "S6", "S7", "S8", "S9", "S10", "SJ", "SQ", "SK"};


        GameFactoryInterface gameFactory = new GameFactory();
        CheckPresenter checkPresenter = new CheckResponseFormatter();
        CheckInputBoundary checkInputBoundary = new CheckInteractor(checkPresenter, gameFactory);
        CheckController checkController = new CheckController(checkInputBoundary);
        BetPresenter betPresenter = new BetResponseFormatter();
        BetInputBoundary betInputBoundary = new BetInteractor(betPresenter, gameFactory);
        BetController betController = new BetController(betInputBoundary);
        CallPresenter callPresenter = new CallResponseFormatter();
        CallInputBoundary callInputBoundary = new CallInteractor(callPresenter, gameFactory);
        CallController callController = new CallController(callInputBoundary);
        FoldPresenter foldPresenter = new FoldResponseFormatter();
        FoldInputBoundary foldInputBoundary = new FoldInteractor(foldPresenter, gameFactory);
        FoldController foldController = new FoldController(foldInputBoundary);
        NewGamePresenter newGamePresenter = new NewGameResponseFormatter();
        NewGameInputBoundary newGameInputBoundary = new NewGameInteractor(newGamePresenter, gameFactory);
        NewGameController newGameController = new NewGameController(newGameInputBoundary);

        JFrame application = new JFrame("Gambler's Choice");
        CardLayout cardLayout = new CardLayout();
        JPanel screens = new JPanel(cardLayout);
        application.add(screens);

        GameScreen gameScreen = new GameScreen(application,
                checkController, betController, callController, foldController, newGameController);

        screens.add(gameScreen, "Game");
        application.pack();
        application.setSize(1000,800); // TODO reset height to 800
        application.setResizable(false);
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.setVisible(true);

        while (true) {
            if (gameScreen.isInteract()) {
                int currentPlayer = gameScreen.getCurrentPlayer();
                int firstPlayer = gameScreen.getFirstPlayer();
                int lastToBet = gameScreen.getLastToBet();
                int[] playerBalance = gameScreen.getPlayerBalance();
                String[] card1 = gameScreen.getCard1();
                String[] card2 = gameScreen.getCard2();
                String[] tableCard = gameScreen.getTableCard();
                String[] card1PNG = gameScreen.getCard1PNG();
                String[] card2PNG = gameScreen.getCard2PNG();
                String[] tableCardPNG = gameScreen.getTableCardPNG();
                int currentBet = gameScreen.getCurrentBet();
                boolean[] isActive = gameScreen.getIsActive();
                int[] playerBets = gameScreen.getPlayerBets();
                String[] deck = gameScreen.getDeck();
                gameScreen = new GameScreen(application,
                        currentPlayer, firstPlayer, lastToBet, playerBalance,
                        card1, card2, tableCard, card1PNG, card2PNG,
                        tableCardPNG, currentBet, isActive, playerBets, deck,
                        checkController, betController, callController, foldController);
                screens.add(gameScreen, "Game");
            }

            cardLayout.show(screens, "Game");
        }
    }

    private static int lenNotNull(Card[] arr) {
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
