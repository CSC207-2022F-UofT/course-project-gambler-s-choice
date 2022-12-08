import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class hintForCombination implements HintInterface{
    private int[] CombinationCounts;
    private int BestCombinationID;

    /**
     * Construct the hint system, and generate the possible outcomes and store it in the private arguments.
     * @param cards Player's hand and the public cards been draw.
     */
    public hintForCombination(Card[] cards){
        if (cards.length == 5){
            CombinationCounts = Hint_at_flop(cards);
        } else if (cards.length == 6) {
            CombinationCounts = Hint_at_Turn(cards);
        } else if (cards.length == 7) {
            BestCombinationID = find_best_comb(cards);
        }
    }

    /**
     * Show the Chance of the set of card form each of the combinations.
     * If the set is seven card, just show the best combination it forms.
     * @return the string that tells the best combination or chance for each combination.
     */
    @Override
    public String GetHint() {
        String[] CombinationByRank = {"High Card\n", "Pair\n", "Two Pair\n", "Three of a kind\n", "Straight\n",
                "Flush\n", "Full House\n", "Four of a kind\n", "Straight Flush\n", "Royal Flush"};
        if (this.CombinationCounts == null) {
            return "The best combination is " + CombinationByRank[this.BestCombinationID];
        } else {
            double TotalOutcomes = Arrays.stream(CombinationCounts).sum();
            StringBuilder ResultHint = new StringBuilder("The chance for each combinations are: ");
            int[] CombCounts = this.CombinationCounts;
            for (int i = 0; i < 10; i++) {
                if (CombCounts[i] != 0) {
                    double possibility = CombCounts[i] * 100 / TotalOutcomes;
                    ResultHint.append(possibility).append("% ").append(CombinationByRank[i]);
                }
            }
            return ResultHint.toString();
        }
    }
    /**
     * Provide hint that chance of player end up with each of the combinations in the flop phase,
     * which when there are 3 public cards reveled.
     *
     * @param existing_card A set of 5 Cards, which includes 2 cards from the player and 3 of the common cards.
     * @return Return a list of int that represent how many outcomes for each combination - out of all the possible
     * outcomes.   For example: [10][9][8][7][6][5][4][3][2][1], means it has 10 outcome is high card, 9 outcomes
     * are pair,..., 1 outcome of royal flush.
     */
    public int[] Hint_at_flop(Card[] existing_card) {
        assert existing_card.length == 5;
        int[] flop_result_list = new int[10];
        List<Card> possible_draw = get_remaining_deck(existing_card);
        // We are going to call the Hint at turn method, the logic is that we take all the possible card as the
        // next public card, then put it into our Hint at turn function, and sum up all the possible outcomes, so we
        // get all the possible outcome we would have at flop.
        for (Card c : possible_draw) {
            Card[] new_set = Arrays.copyOf(existing_card, 6);
            new_set[5] = c;
            int[] result_of_turn = Hint_at_Turn(new_set);
            for (int i = 0; i < 10; i++) {
                flop_result_list[i] += result_of_turn[i];
            }
        }
        return flop_result_list;
    }

    /**
     * Provide hint that chance of player end up with each of the combinations in the turn phase,
     * which when there are 4 public cards reveled.
     *
     * @param existing_cards A set of 6 Cards, which includes 2 cards from the player and 4 of the common cards.
     * @return Return a list of int that represent how many outcomes for each combination
     */
    public int[] Hint_at_Turn(Card[] existing_cards) {
        assert existing_cards.length == 6;
        List<Card> possible_draw = get_remaining_deck(existing_cards);
        int[] result_list = new int[10];
        Card[] old_set = Arrays.copyOf(existing_cards, 7);
        // As we have 6 cards known, the next thing is that we going to try all the rest of cards and
        // identify what's the best combination it forms and count it into the array of int.
        for (Card c : possible_draw) {
            old_set[6] = c;
            int winner = find_best_comb(old_set);
            result_list[winner]++;
        }
        return result_list;
    }

    /**
     * Get a deck of the remaining cards.
     * @param existing_cards cards that is already on the table.
     * @return An array of Cards that could possibly be the next draw.
     */
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

    /**
     * Determine whether this card has been draw.
     * @param exist_card A set of card that is already on the table.
     * @param new_card the card we want to check if it has been draw.
     * @return whether the deck already exist or not.
     */
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
     * methods are public for test purpose.
     * @param all_cards card[] of 7 cards.
     * @return card[] of 5 cards that forms the best combination.
     */
    public int find_best_comb(Card[] all_cards) {
        assert all_cards.length == 7;
        int bestCombCount=0;
        Card[] possible_comb = new Card[5];
        for(int i=0; i < all_cards.length; i++){
            for (int j=i+1; j < all_cards.length; j++){
                int added_cards = 0;
                for (int k = 0; k<all_cards.length; k++){
                    if(k != i && k != j){
                        possible_comb[added_cards++]=all_cards[k];
                    }
                }
                bestCombCount = Math.max(bestCombCount, combinationChecker.getCompareID(possible_comb));
            }
        }
        return bestCombCount;
    }
}