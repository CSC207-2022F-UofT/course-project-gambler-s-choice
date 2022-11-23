import org.junit.jupiter.api.Test;

public class WinRoundTest {


    @Test
    void calculateHandTestRoyalFlush() {
        Game g = new Game(new Player[]{});
        String[] hand = {"HJ", "CJ"};
        String[] river = {"HQ", "HK", "H1", "HT", "C9"};
        assert g.calculateHand(hand, river) == 130;
    }

    @Test
    void calculateHandTestFullHouse() {
        Game g = new Game(new Player[]{});
        String[] hand = {"H2", "C3"};
        String[] river = {"D3", "S3", "C2", "DT", "SJ"};
        assert g.calculateHand(hand, river) == 80;
    }

    @Test
    void calculateHandTestStraight() {
        Game g = new Game(new Player[]{});
        String[] hand = {"H1", "H2"};
        String[] river = {"H5", "ST", "S3", "D4", "C2"};
        assert g.calculateHand(hand, river) == 55;
    }

    @Test
    void calculateHandTestFlush() {
        Game g = new Game(new Player[]{});
        String[] hand = {"H1", "HQ"};
        String[] river = {"HJ", "HT", "SK", "H4", "C2"};
        assert g.calculateHand(hand, river) == 78;
    }

    @Test
    void winRoundTest() {

        Player[] players = new Player[]{
                new Player(new Card("H", "1"), new Card("C", "1")),
                new Player(new Card("H", "2"), new Card("C","7")),
                new Player(new Card("D", "6"), new Card("S", "8")),
                new Player(new Card("C", "4"), new Card("D", "1")),
                new Player(new Card("H", "1"), new Card("C", "1"))};
        Game g = new Game(players);
        String[] flop = {"S1", "H3", "C3", "D4", "D5"};
        int[] results = g.findWinner(players, flop);
        assert(results[0] == 1);
        assert(results[1] == 3);
        assert(results[2] == 5);
        assert(results[3] == 4);
        assert(results[4] == 1);
    }

}
