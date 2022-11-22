import org.junit.jupiter.api.Test;
import tutorial.Game;

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


}
