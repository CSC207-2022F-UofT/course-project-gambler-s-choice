package hint_system_case_tests;

import future_features.CombinationChecker;
import game_entities.Card;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;


class CombinationCheckerTest {
    Card da = new Card("DA");
    Card ha = new Card("HA");
    Card sa = new Card("SA");
    Card ca = new Card("CA");
    Card CSeven = new Card("C7");
    Card STwo = new Card("S2");
    Card dk = new Card("DK");
    Card dq = new Card("DQ");
    Card dj = new Card("DJ");
    Card dx = new Card("10", "D");
    Card dNine = new Card("D9");
    Card[] ThreeOfAKind = {da, sa, ha, CSeven, STwo};
    Card[] FourOfAKind = {STwo,da, sa, ha, ca};
    Card[] Straight = {ha, dk, dq, dj, dx};
    Card[] APair = {da, ha, dk, dq, dx};
    Card[] Flush = {da, dk, dq, dj, dNine};
    Card[] RoyalFlush = {da, dk, dq, dj, dx};

    @Test
    void get_compare_ID() {
        Assertions.assertEquals(5, CombinationChecker.getCompareID(Flush));
        Assertions.assertEquals(9, CombinationChecker.getCompareID(RoyalFlush));
    }

    @Test
    void is_one_pairs() {
        Arrays.sort(Straight);
        Assertions.assertTrue(CombinationChecker.is_one_pairs(APair));
    }


    @Test
    void is_three_of_one_kind() {
        Arrays.sort(ThreeOfAKind);
        Assertions.assertTrue(CombinationChecker.is_three_of_one_kind(ThreeOfAKind));
    }

    @Test
    void is_four_of_one_kind() {
        Assertions.assertTrue(CombinationChecker.is_four_of_one_kind(FourOfAKind));
    }


    @Test
    void isStraight2() {
        Arrays.sort(Straight);
        Assertions.assertTrue(CombinationChecker.isStraight2(Straight));
    }
}