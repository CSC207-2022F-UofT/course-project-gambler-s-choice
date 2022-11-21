import javax.swing.*;

public class GameScreenTest {

    public static void main(String[] args){

        Player player1 = new Player(new Card("5", "H"), new Card("7", "H"));
        Player player2 = new Player(new Card("K", "D"), new Card("9", "C"));

        JFrame aaaa = new JFrame();
        Player[] players = {player1, player2};
        Game pp = new Game(players);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GameScreen(aaaa, pp);
            }
        });

    }
}
