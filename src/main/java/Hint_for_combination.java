public class Hint_for_combination{
    public Hint_for_chance(Card[] player_hand, Card common_card1, Card common_card2, Card common_card3){
        Deck remaining_cards = new Deck();
        remaining_cards.remove(common_card1);
        remaining_cards.remove(common_card2);
        remaining_cards.remove(common_card3);
        remaining_cards.remove(player_hand[0]);
        remaining_cards.remove(player_hand[1]);

    }

}
