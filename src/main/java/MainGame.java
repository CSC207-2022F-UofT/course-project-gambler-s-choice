import javax.swing.*;

import game_entities.Card;
import game_entities.Game;
import game_entities.Player;
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
        Game pp = new Game(players);

        CheckPresenter checkPresenter = new CheckResponseFormatter();
        CheckInputBoundary checkInputBoundary = new CheckInteractor(checkPresenter, pp);
        CheckController checkController = new CheckController(checkInputBoundary);

        JFrame application = new JFrame("Gambler's Choice");
        CardLayout cardLayout = new CardLayout();
        JPanel screens = new JPanel(cardLayout);
        application.add(screens);

        GameScreen gameScreen = new GameScreen(application, pp, checkController);

        screens.add(gameScreen, "Game");
        application.pack();
        application.setSize(1000,700);
        application.setResizable(false);
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.setVisible(true);


        while (application.isActive()) {
            gameScreen = new GameScreen(application, pp, checkController);
            screens.add(gameScreen, "Game");
            cardLayout.show(screens, "Game");
        }
    }

}
