package future_features;

import game_entities.Card;

import java.util.Arrays;

public class CombinationChecker implements Comparable<CombinationChecker>{
    public final int compareID;

    /**
     * For a set of 5 cards, identify the best combination it could form and initial an ID to represent it.
     * The ID for Royal flush = 9, Straight flush = 8,  four of a kind = 7, full house = 6,
     * flush = 5, Straight = 4, three of a kind = 3, two pair = 2, pair = 1, High card = 0.
     * @param cards The set of cards that we would to check.
     */
    public CombinationChecker(Card[] cards){
        if (cards.length != 5){
            throw new IllegalArgumentException("Checking combination takes exactly 5 cards, please check how many" +
                    "Cards been put in");
        }
        this.compareID = getCompareID(cards);
    }

    /**Compare two set of 5 cards by their rank on combination.
     * for example, two pair is greater than one pair. However, we only compare the
     * combination, no card rank comparing.
     *
     * @param other_hands the object to be compared.
     * @return negative means this has smaller rank than the param hand, positive otherwise, zero if it is even.
     */
    @Override
    public int compareTo(CombinationChecker other_hands) {
        return this.compareID - other_hands.compareID;
    }

    /**
     * Identity and return the card's best combination.
     * @param cards A set of 5 cards.
     * @return an ID that represent the type of combination of the set.
     */
    public static int getCompareID(Card[] cards){
        Arrays.sort(cards);
        if(isStraight2(cards) && isFlush(cards) && (cards[0].getRankAsInt() == 10)) {
            return 9;
        } else if (isFlush(cards) && isStraight2(cards)) {
            return 8;
        } else if (is_four_of_one_kind(cards)) {
            return 7;
        } else if (is_full_house(cards)) {
            return 6;
        } else if (isFlush(cards)) {
            return 5;
        } else if (isStraight2(cards)) {
            return 4;
        } else if (is_three_of_one_kind(cards)) {
            return 3;
        } else if (is_two_pairs(cards)) {
            return 2;
        } else if (is_one_pairs(cards)) {
            return 1;
        } else { return 0;}
    }


    /**Return whether the card has combination of one pair.
     *
     * @param cards a set of 5 cards
     * @return whether the best combination is pair.
     */
    public static boolean is_one_pairs(Card[] cards) {
        int[] rep_list = get_Repeated_counting(cards);
        boolean a = (rep_list[0] == 0);
        boolean b = (rep_list[1] == 1);
        boolean c = (rep_list[2] == 1);
        boolean d = (rep_list[3] == 1);
        boolean e = (rep_list[4] == 2);
        //The highest repeated rank in the set of card is one pair and the rest of three cards are
        //different with each other.
        return a && b && c && d && e;
    }


    public static boolean is_two_pairs(Card[] cards) {
        int[] rep_list = get_Repeated_counting(cards);
        boolean a = (rep_list[0] == 0);
        boolean b = (rep_list[1] == 0);
        boolean c = (rep_list[2] == 1);
        boolean d = (rep_list[3] == 2);
        return a && b && c && d;
    }


    public static boolean is_three_of_one_kind(Card[] cards) {
        int[] rep_list = get_Repeated_counting(cards);
        boolean a = (rep_list[0] == 0);
        boolean b = (rep_list[1] == 0);
        boolean c = (rep_list[2] == 1);
        boolean d = (rep_list[3] == 1);
        boolean e = (rep_list[4] == 3);
        return a && b && c && d && e;
    }


    public static boolean is_full_house(Card[] cards) {
        int[] rep_list = get_Repeated_counting(cards);
        boolean a = (rep_list[0] == 0);
        boolean b = (rep_list[1] == 0);
        boolean c = (rep_list[2] == 0);
        boolean d = (rep_list[3] == 2);
        boolean e = (rep_list[4] == 3);
        return a && b && c && d && e;
    }

    /**Return whether the card has combination of full house.
     * note: either {0,0,0,0,4} and {0,0,0,1,4} are four of one kind.
     * @param cards a set of 5 cards.
     * @return Whether the set has 4 cards with same rank.
     */
    public static boolean is_four_of_one_kind(Card[] cards) {
        int[] rep_list = get_Repeated_counting(cards);
        return rep_list[4] == 4;
    }

    /**Help method, that shows how many pair or card with same rank as an array. so it is easy
     * to determine pair or other same rank's combination.
     * For example, cards with rank "225KA", will be return an array of {2,1,1,1,0}
     * so we know it is a pair.
     *
     * @param cards A set of 5 cards.
     * @return an array of int that represent the times of a rank was repeated in the cards.
     */
    private static int[] get_Repeated_counting(Card[] cards){
        int[] Repeated_counting = new int[5];
        int index_counting = 0;
        int temp = 1;
        for(int i = 1; i < 5; i++) {
            if (cards[i - 1].sameRank(cards[i])) {
                temp++;
            } else {
                Repeated_counting[index_counting] = temp;
                index_counting++;
                temp = 1;
            }
        }
        Repeated_counting[4] = temp;
        Arrays.sort(Repeated_counting);
        return Repeated_counting;
    }

    /**
     * Check if the five cards form a combination of Flush.
     *
     * @param five_card a set of 5 cards.
     * @return Whether the cards form a Flush.
     */
    public static boolean isFlush(Card[] five_card) {
        Card temp = five_card[0];
        for (Card c : five_card) {
            if (!temp.sameSuit(c)) {
                return false;
            }
            temp = c;
        }
        return true;
    }

    /**Check if the five cards form a combination of Straight.
     *
     * @param sorted_cards a set of 5 cards that is sorted by ranks.
     * @return Whether the cards form a Flush.
     *
     */
    public static boolean isStraight2(Card[] sorted_cards) {
        boolean a = sorted_cards[1].getRankAsInt() == sorted_cards[0].getRankAsInt() + 1;
        boolean b = sorted_cards[2].getRankAsInt() == sorted_cards[1].getRankAsInt() + 1;
        boolean c = sorted_cards[3].getRankAsInt() == sorted_cards[2].getRankAsInt() + 1;
        boolean d = (sorted_cards[4].getRankAsInt() == sorted_cards[3].getRankAsInt() + 1
                || sorted_cards[3].getRankAsInt() == sorted_cards[4].getRankAsInt() + 9);
        // last condition on d is for straight of rank 'A2345'
        return a && b && c && d;
    }
}
