package hint_system_case_tests;

import future_features.HintForChance;
import game_entities.Card;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HintForChanceTest {
    Card da = new Card("DA");
    Card ha = new Card("HA");
    Card CSeven = new Card("C7");
    Card STwo = new Card("S2");
    Card[] naa = {da, ha};
    Card[] SevenTwo = {CSeven,STwo};
    HintForChance playerLucky = new HintForChance(naa);
    HintForChance playerLowest = new HintForChance(SevenTwo);

    @Test
    void get_win_rate() {
        assertEquals("Your initial win rate for the starting hand is 31.0%", playerLucky.GetHint());
        assertEquals("Your initial win rate for the starting hand is 4.0%", playerLowest.GetHint());
    }
}