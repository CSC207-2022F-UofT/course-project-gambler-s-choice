import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HintForComb2 {
    public int[] Hint_at_flop(Card[] existing_card) {
        assert existing_card.length == 5;
        int[] flop_result_list = new int[10];
        List<Card> possible_draw = get_remaining_deck(existing_card);
        for (Card c : possible_draw) {
            Card[] new_set = Arrays.copyOf(existing_card, 6);
            new_set[6] = c;
            int[] result_of_turn = Hint_at_Turn(new_set);
            for (int i = 0; i < 10; i++) {
                flop_result_list[i] += result_of_turn[i];
            }
        }
        return flop_result_list;
    }

    private int[] Hint_at_Turn(Card[] existing_cards) {
        assert existing_cards.length == 6;
        List<Card> possible_draw = get_remaining_deck(existing_cards);
        int[] result_list = new int[10];
        Card[] old_set = Arrays.copyOf(existing_cards, 7);
        for (Card c : possible_draw) {
            old_set[7] = c;
            int winner = find_best_comb(old_set);
            result_list[winner]++;
        }
        return result_list;
    }

    public List<Card> get_remaining_deck(Card[] existing_cards) {
        Deck deck = new Deck();
        List<Card> remaining_cards = new ArrayList<>();
        while (!deck.isEmpty()) {
            Card drawing_card = deck.getCard();
            if (have_not_draw(existing_cards, drawing_card))
                remaining_cards.add(drawing_card);
        }
        return remaining_cards;
    }


    public boolean have_not_draw(Card[] exist_card, Card new_card) {
        for (Card c : exist_card) {
            if (c.sameRank(new_card) && c.sameSuit(new_card)) {
                return false;
            }
        }
        return true;
    }


    /**
     * Find the best combinations of the current all cards, which is 2 private card and 5 public cards.
     *
     * @param all_cards card[] of 7 cards.
     * @return card[] of 5 cards that forms the best combination.
     */
    public int find_best_comb(Card[] all_cards) {
        assert all_cards.length == 7;
        int comb_count=0;
        Card[][] possible_comb = new Card[21][5];
        for(int i=0; i < all_cards.length; i++){
            for (int j=i+1; j < all_cards.length; j++){
                int added_cards = 0;
                for (int k = 0; k<all_cards.length; k++){
                    if(k != i && k != j){
                        possible_comb[comb_count][added_cards++]=all_cards[k];
                    }
                }
                comb_count++;
            }
        }
        Arrays.sort(possible_comb);
        return CombinationChecker.get_compare_ID(possible_comb[20]);
    }
}