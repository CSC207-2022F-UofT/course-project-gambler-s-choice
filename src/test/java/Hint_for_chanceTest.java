import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Hint_for_chanceTest {
    Card da = new Card("DA");
    Card ha = new Card("HA");
    Card CSeven = new Card("C7");
    Card STwo = new Card("S2");
    Card[] naa = {da, ha};
    Card[] SevenTwo = {CSeven,STwo};
    Hint_for_chance playerLucky = new Hint_for_chance(naa);
    Hint_for_chance playerLowest = new Hint_for_chance(SevenTwo);

    @Test
    void get_win_rate() {
        assertEquals(31.0, playerLucky.get_win_rate());
        assertEquals(4.0, playerLowest.get_win_rate());
    }
}