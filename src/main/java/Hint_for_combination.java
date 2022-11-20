import java.util.ArrayList;
import java.util.List;

public class Hint_for_combination {
    public int[][] Hint_at_flop(Card[] player_hand, Card common_card1,Card common_card2, Card common_card3){
        int[][] flop_result_list = new int[10][0];
        Card[] existing_cards = {player_hand[0], player_hand[1], common_card1, common_card2,
                common_card3};
        List<Card> possible_draw = get_remaining_deck(existing_cards);
        for (Card c: possible_draw) {
            int[][] result_of_turn = Hint_at_Turn(player_hand, common_card1,common_card2,common_card3,c);
            for(int i = 0; i < 10; i++){
                flop_result_list[i][0] += result_of_turn[i][0];
            }
        }return flop_result_list;
    }

    public int[][] Hint_at_Turn(Card[] player_hand, Card common_card1, Card common_card2, Card common_card3,
                                Card common_card4) {
        Card[] existing_cards = {player_hand[0], player_hand[1], common_card1, common_card2,
                common_card3, common_card4};
        List<Card> possible_draw = get_remaining_deck(existing_cards);
        int[][] result_list = new int[10][0];
        Card [] Current_best_hand = {player_hand[0],player_hand[1], common_card1,common_card2,
                common_card3,common_card4, possible_draw.get(0)};
        for (Card c : possible_draw) {
            Card[] other_hand = {c, player_hand[0], player_hand[1], common_card1, common_card2, common_card3, common_card4};
            winner = find_winner(Current_best_hand, other_hand);
            int comb_ID = combination_checker(winner);
            result_list[comb_ID][0] ++;}
        return result_list;
    }



    public List<Card> get_remaining_deck(Card[] existing_cards){
        Deck deck = new Deck();
        List<Card> remaining_cards = new ArrayList<>();
        while (!deck.isEmpty()) {
            Card drawing_card = deck.getCard();
            if(have_not_draw(existing_cards, drawing_card))
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
}