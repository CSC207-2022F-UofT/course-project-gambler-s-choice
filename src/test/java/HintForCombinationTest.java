import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HintForCombinationTest {
    Card da = new Card("DA");
    Card ha = new Card("HA");
    Card sa = new Card("SA");
    Card STwo = new Card("S2");
    Card dk = new Card("DK");
    Card dq = new Card("DQ");
    Card dj = new Card("DJ");
    Card dx = new Card("10", "D");
    Card dNine = new Card("D9");

    Card[] RoyalFlush = {da, dk, dq, dj, dx};
    Card[] RoyalFlushDeck = {da, dk, dq, dj, dx, dNine, sa};
    Card[] ThreeOfAKindAtTurn = {da, ha, dq, dj, STwo, sa};
    Card[] FlushDeck = {da, ha, dq, dj, dx, dNine, sa};
    Card[] ThreeOfAKindDeck = {da, ha, dq, dj, dx, STwo, sa};
    hintForCombination RoyalFlushSet = new hintForCombination(RoyalFlush);
    hintForCombination FlushSet = new hintForCombination(FlushDeck);
    hintForCombination ThreeOfAKind = new hintForCombination(ThreeOfAKindAtTurn);


    @Test
    void find_best_comb() {
        assertEquals(9, RoyalFlushSet.find_best_comb(RoyalFlushDeck));
        assertEquals(5, FlushSet.find_best_comb(FlushDeck));
        assertEquals(3, ThreeOfAKind.find_best_comb(ThreeOfAKindDeck));
    }

    @Test
    void Hint_at_Turn(){
        assertEquals("The chance for each combinations are: 100.0% Royal Flush", RoyalFlushSet.GetHint());
    }

    @Test
    void Hint_at_flop(){
        assertEquals("The chance for each combinations are: 78.26086956521739% Three of a kind\n" +
                "19.565217391304348% Full House\n" +
                "2.1739130434782608% Four of a kind\n", ThreeOfAKind.GetHint());
    }

    @Test
    void BestCombination(){
        assertEquals("The best combination is Flush\n", FlushSet.GetHint());
    }
}