import game_entities.*;
import game_use_case.*;
import screens.BetController;
import screens.CheckController;
import screens.FoldController;
import screens.GameScreen;

import javax.swing.*;
import java.awt.*;

public class GameScreenTest {

    public static void main(String[] args){

        Player player1 = new Player(new Card("5", "H"), new Card("7", "H"));
        Player player2 = new Player(new Card("K", "D"), new Card("9", "C"));

        Player[] players = {player1, player2};
        Game game = new Game(players);

        int length = game.getPlayers().length;
        String[] card1 = new String[length];
        String[] card2 = new String[length];
        String[] tableCard = new String[5];
        String[] card1PNG = new String[length];
        String[] card2PNG = new String[length];
        String[] tableCardPNG = new String[5];
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
            playerBalance[i] = 0;
            playerBets[i] = 0;
            isActive[i] = true;
            // System.out.println(card1[i] + " " + card2[i] + " " + card1PNG[i] + " " + card2PNG[i]);
        }
        for (int i = 0; i < 5; i++) {
            tableCard[i] = game.getTableCards()[i].toString();
            tableCardPNG[i] = game.getTableCards()[i].getPNG();
            // System.out.println(tableCard[i] + " " + tableCardPNG[i]);
        }
        String[] deck = {"DA", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "DX", "DJ", "DQ", "DK",
                "CA", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "CX", "CJ", "CQ", "CK",
                "HA", "H2", "H3", "H4", "H5", "H6", "H7", "H8", "H9", "HX", "HJ", "HQ", "HK",
                "SA", "S2", "S3", "S4", "S5", "S6", "S7", "S8", "S9", "SX", "SJ", "SQ", "SK"};

        JFrame aaaa = new JFrame();
        CardLayout cardLayout = new CardLayout();
        JPanel screens = new JPanel(cardLayout);
        aaaa.add(screens);
        CheckPresenter checkPresenter = new CheckResponseFormatter();
        GameFactoryInterface gameFactory = new GameFactory();
        CheckInputBoundary checkInputBoundary = new CheckInteractor(checkPresenter, gameFactory);
        CheckController checkController = new CheckController(checkInputBoundary);
        BetPresenter betPresenter = new BetResponseFormatter();
        BetInputBoundary betInputBoundary = new BetInteractor(betPresenter, gameFactory);
        BetController betController = new BetController(betInputBoundary);

        FoldPresenter foldPresenter = new FoldResponseFormatter();
        FoldInputBoundary foldInputBoundary = new FoldInteractor(foldPresenter, gameFactory);
        FoldController foldController = new FoldController(foldInputBoundary);

        screens.add(new GameScreen(aaaa,
                currentPlayer, firstPlayer, lastToBet, playerBalance,
                card1, card2, tableCard, card1PNG, card2PNG,
                tableCardPNG, currentBet, isActive, playerBets, deck,
                checkController, betController, foldController), "Game");

        aaaa.pack();
        aaaa.setSize(1000,800);
        aaaa.setResizable(false);
        aaaa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        aaaa.setVisible(true);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //while (aaaa.isActive()) {
                    cardLayout.show(screens, "Game");
                //}
            }
        });

    }
}
