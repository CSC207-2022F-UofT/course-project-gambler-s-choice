import game_entities.Card;
import game_entities.Game;
import game_entities.Player;
import game_use_case.CheckInputBoundary;
import game_use_case.CheckInteractor;
import game_use_case.CheckPresenter;
import game_use_case.CheckResponseFormatter;
import screens.CheckController;
import screens.GameScreen;

import javax.swing.*;
import java.awt.*;

public class GameScreenTest {

    public static void main(String[] args){

        Player player1 = new Player(new Card("5", "H"), new Card("7", "H"));
        Player player2 = new Player(new Card("K", "D"), new Card("9", "C"));

        Player[] players = {player1, player2};
        Game pp = new Game(players);

        JFrame aaaa = new JFrame();
        CardLayout cardLayout = new CardLayout();
        JPanel screens = new JPanel(cardLayout);
        aaaa.add(screens);
        CheckPresenter checkPresenter = new CheckResponseFormatter();
        CheckInputBoundary checkInputBoundary = new CheckInteractor(checkPresenter, pp);
        CheckController checkController = new CheckController(checkInputBoundary);
        screens.add(new GameScreen(aaaa, pp, checkController), "Game");

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
