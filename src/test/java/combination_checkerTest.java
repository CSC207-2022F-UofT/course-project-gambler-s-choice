import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class combination_checkerTest {
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

    @Test
    void get_compare_ID() {
        assertEquals(5, combination_checker.get_compare_ID(Flush));
        assertEquals(9, combination_checker.get_compare_ID(RoyalFlush));
    }

    @Test
    void compareTo() {
    }

    @Test
    void is_one_pairs() {
        Arrays.sort(Straight);
        assertTrue(combination_checker.is_one_pairs(APair));
    }

    @Test
    void is_two_pairs() {
    }

    @Test
    void is_three_of_one_kind() {
        Arrays.sort(ThreeOfAKind);
        assertTrue(combination_checker.is_three_of_one_kind(ThreeOfAKind));
    }

    @Test
    void is_full_house() {
    }

    @Test
    void is_four_of_one_kind() {
    }

    @Test
    void isFlush() {
    }

    @Test
    void isStraight2() {
        Arrays.sort(Straight);
        assertTrue(combination_checker.isStraight2(Straight));
    }
}