package deprecated;

import javax.swing.*;

import game_entities.*;
import game_use_case.*;
import screens.*;

import java.awt.*;

@Deprecated
public class MainGame {
    public static void main(String[] args) {

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
        LeaveResponseFormatter leaveResponseFormatter = new LeaveResponseFormatter();
        LeaveInteractor leaveInteractor = new LeaveInteractor(leaveResponseFormatter);
        LeaveController leaveController = new LeaveController(leaveInteractor);

        JFrame application = new JFrame("Gambler's Choice");
        CardLayout cardLayout = new CardLayout();
        JPanel screens = new JPanel(cardLayout);
        application.add(screens);

        GameScreen gameScreen = new GameScreen(application,
                checkController, betController, callController, foldController, newGameController, leaveController, "AAA");

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
                        checkController, betController, callController, foldController, leaveController, "AA");
                screens.add(gameScreen, "Game");
            }

            cardLayout.show(screens, "Game");

        }
    }
}
