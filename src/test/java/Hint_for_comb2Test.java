import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Hint_for_comb2Test {
    Card da = new Card("DA");
    Card ha = new Card("HA");
    Card sa = new Card("SA");
    Card CSeven = new Card("C7");
    Card STwo = new Card("S2");
    Card dk = new Card("DK");
    Card dq = new Card("DQ");
    Card dj = new Card("DJ");
    Card dx = new Card("10", "D");
    Card dNine = new Card("D9");
    Card[] ThreeOfAKind = {da, sa, ha, CSeven, STwo};
    Card[] Straight = {ha, dk, dq, dj, dx};
    Card[] APair = {da, ha, dk, dq, dx};
    Card[] Flush = {da, dk, dq, dj, dNine};
    Card[] RoyalFlush = {da, dk, dq, dj, dx};
    Card[] RoyalFlushDeck = {da, dk, dq, dj, dx, dNine, sa};
    Card[] RoyalFlushAtTurn = {da, dk, dq, dj, dx, sa};
    Card[] FlushDeck = {da, ha, dq, dj, dx, dNine, sa};
    Card[] ThreeOfAKindDeck = {da, ha, dq, dj, dx, STwo, sa};
    Hint_for_comb2 for_test = new Hint_for_comb2(RoyalFlush);

    int[] gotRoyalFlushAtTurn = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 46};

    @Test
    void find_best_comb() {
        assertEquals(9, for_test.find_best_comb(RoyalFlushDeck));
        assertEquals(5, for_test.find_best_comb(FlushDeck));
        assertEquals(3, for_test.find_best_comb(ThreeOfAKindDeck));
    }

    @Test
    void Hint_at_Turn(){
        assertEquals(gotRoyalFlushAtTurn, for_test.Hint_at_Turn(RoyalFlushAtTurn));
    }

    @Test
    void Hint_at_flop(){
        assertEquals(gotRoyalFlushAtTurn, for_test.Hint_at_flop(APair));
    }
}