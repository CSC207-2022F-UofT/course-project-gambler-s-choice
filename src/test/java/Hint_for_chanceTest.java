import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Hint_for_chanceTest {

    @Test
    void get_win_rate() {
        Card da = new Card("DA");
        Card ha = new Card("HA");
        Card[] naa = {da, ha};
        Hint_for_chance playerLucky = new Hint_for_chance(naa);
        assertEquals(31.0, playerLucky.get_win_rate());
    }
}