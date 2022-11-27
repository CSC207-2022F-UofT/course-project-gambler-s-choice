import org.junit.jupiter.api.Test;
import tutorial.Game;
import tutorial.Player;

public class WinRoundTest {


    @Test
    void calculateHandTestRoyalFlush() {
        Game g = new Game();
        String[] hand = {"HJ", "CJ"};
        String[] river = {"HQ", "HK", "H1", "HT", "C9"};
        assert g.calculateHand(hand, river) == 130;
    }

    @Test
    void calculateHandTestFullHouse() {
        Game g = new Game();
        String[] hand = {"H2", "C3"};
        String[] river = {"D3", "S3", "C2", "DT", "SJ"};
        assert g.calculateHand(hand, river) == 80;
    }

    @Test
    void calculateHandTestStraight() {
        Game g = new Game();
        String[] hand = {"H1", "H2"};
        String[] river = {"H5", "ST", "S3", "D4", "C2"};
        assert g.calculateHand(hand, river) == 55;
    }

    @Test
    void calculateHandTestFlush() {
        Game g = new Game();
        String[] hand = {"H1", "HQ"};
        String[] river = {"HJ", "HT", "SK", "H4", "C2"};
        assert g.calculateHand(hand, river) == 78;
    }

    @Test
    void winRoundTest() {
        Game g = new Game();
        Player[] players = new Player[]{
                new Player(1, new String[]{"H1", "C1"}),
                new Player(2, new String[]{"H2", "C7"}),
                new Player(3, new String[]{"D6", "S8"}),
                new Player(4, new String[]{"C4", "D1"}),
                new Player(5, new String[]{"H1", "C1"})};
        String[] flop = {"S1", "H3", "C3", "D4", "D5"};
        int[] results = g.findWinner(players, flop);
        assert(results[0] == 1);
        assert(results[1] == 3);
        assert(results[2] == 5);
        assert(results[3] == 4);
        assert(results[4] == 1);
    }

}
