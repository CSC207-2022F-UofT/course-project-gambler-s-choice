import java.util.Arrays;

public class combination_checker implements Comparable<combination_checker>{
    private final int Unique_compare_ID;

    /**Set a unique ID of comparing for the item, ID will be 11 digit.
     * first int represent the combination it is:
     * 9 : Royal flush, 8: Straight flush, 7: Full house, ..., 0 : high card.
     * the next ten-digits is the cord's rank, but rank in a special descending order,
     * such that a pair is prior to single card.
     * For example, "2AA22" will have ID: 71414140202.
     * one more example,"AK447" will have ID: 20404141307. where the first index "2"
     * means this is a pair combination, with 4 as the top pair.
     *
     *
     * @param cards An arrays of 5 cards.
     */
    public combination_checker(Card[] cards){
        Arrays.sort(cards);
        this.Unique_compare_ID = get_ID(cards);
    }

    /**Compare two set of 5 cards, which one is stronger in the poker rule.
     *
     * @param other_hands the object to be compared.
     * @return positive number if this hands is bigger than other,
     * negative if this is smaller, 0 if it is a tie.
     */
    @Override
    public int compareTo(combination_checker other_hands){
        return this.Unique_compare_ID - other_hands.Unique_compare_ID;
    }

    /**Return whether the card has combination of one pair.
     *
     * @param cards
     * @return
     */
    public boolean is_one_pairs(Card[] cards) {
        int[] rep_list = get_Repeated_counting(cards);
        boolean a = (rep_list[0] == 0);
        boolean b = (rep_list[1] == 1);
        boolean c = (rep_list[2] == 1);
        boolean d = (rep_list[3] == 1);
        boolean e = (rep_list[4] == 2);
        return a && b && c && d;
    }

    /**Return whether the card has combination of two pairs.
     *
     * @param cards
     * @return
     */
    public boolean is_two_pairs(Card[] cards) {
        int[] rep_list = get_Repeated_counting(cards);
        boolean a = (rep_list[0] == 0);
        boolean b = (rep_list[1] == 0);
        boolean c = (rep_list[2] == 1);
        boolean d = (rep_list[3] == 2);
        boolean e = (rep_list[4] == 2);
        return a && b && c && d;
    }

    /**Return whether the card has combination of three of one kind.
     *
     * @param cards
     * @return
     */
    public boolean is_three_of_one_kind(Card[] cards) {
        int[] rep_list = get_Repeated_counting(cards);
        boolean a = (rep_list[0] == 0);
        boolean b = (rep_list[1] == 0);
        boolean c = (rep_list[2] == 1);
        boolean d = (rep_list[3] == 1);
        boolean e = (rep_list[4] == 3);
        return a && b && c && d && e;
    }

    /**Return whether the card has combination of full house.
     *
     * @param cards
     * @return
     */
    public boolean is_fullhouse(Card[] cards) {
        int[] rep_list = get_Repeated_counting(cards);
        boolean a = (rep_list[0] == 0);
        boolean b = (rep_list[1] == 0);
        boolean c = (rep_list[2] == 0);
        boolean d = (rep_list[3] == 2);
        boolean e = (rep_list[4] == 3);
        return a && b && c && d && e;
    }

    /**Return whether the card has combination of full house.
     *
     * note: either {0,0,0,4} and {0,0,1,4} are four of one kind.
     * @param cards
     * @return
     */
    public boolean is_four_of_one_kind(Card[] cards) {
        int[] rep_list = get_Repeated_counting(cards);
        boolean d = (rep_list[3] == 4);
        return d;
    }

    /**Help method, that shows how many pair or card with same rank as an array. so it is easy
     * to determine pair or other same rank's combination.
     *
     * For example, cards with rank "225KA", will be return an array of {2,1,1,1}
     * so we know it is a pair.
     *
     * @param cards
     * @return
     */
    private int[] get_Repeated_counting(Card[] cards){
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
        Repeated_counting[5] = temp;
        Arrays.sort(Repeated_counting);
        return Repeated_counting;
    }

    /**
     * Check if the five cards form a combination of Flush.
     *
     * @param five_card
     * @return Whether the cards form a Flush.
     */
    public boolean isFlush(Card[] five_card) {
        Card temp = five_card[0];
        for (Card c : five_card) {
            if (temp.sameSuit(c)) {
                return false;
            }
            temp = c;
        }
        return true;
    }

    /**Check if the five cards form a combination of Straight.
     *
     * @param sorted_cards
     * @return Whether the cards form a Flush.
     *
     */
    public boolean isStraight2(Card[] sorted_cards) {
        boolean a = sorted_cards[0].getRankAsInt() == sorted_cards[1].getRankAsInt() + 1;
        boolean b = sorted_cards[1].getRankAsInt() == sorted_cards[2].getRankAsInt() + 1;
        boolean c = sorted_cards[2].getRankAsInt() == sorted_cards[3].getRankAsInt() + 1;
        boolean d = (sorted_cards[3].getRankAsInt() == sorted_cards[4].getRankAsInt() + 1
                    || sorted_cards[3].getRankAsInt() == sorted_cards[4].getRankAsInt() + 9);
        // last condition on d is for straight of rank 'A2345'
        return a && b && c && d;
    }
}
