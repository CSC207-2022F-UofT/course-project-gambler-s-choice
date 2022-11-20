import java.util.Arrays;
import java.util.Hashtable;

public class Hint_for_chance {
    private Double win_rate;
    private


    /**Construct a hint that takes in two cards, that is player's private hands. the win rate
     * is come from a hash-table that stores each initial card's win rate, calculated by millions of
     * simulations.
     *
     * @param player_hands two private cards of the player.
     */
    public Hint_for_chance(Card[] player_hands){
        this.win_rate = initial_win_rate(convertor(player_hands));
    }

    private Double initial_win_rate(String converted_hands) {
        Hashtable<String, Double> win_rate_table = new Hashtable<String, Double>();
        String[] starting_hand = {"NAA",
        win_rate_table.put("SAK", 80.00);
        // SAA is example, going to implement actual win rate later.
    }

    /**Convert two cards to a string form that is easier to be read by the hashmap.
     *
     * @param two_cards - player_hands two private cards of the player.
     * @return S means suited, N means non-suited, for example, {"HA","HK"} will return "SAK".
     */
    private String convertor(Card[] two_cards){
        Arrays.sort(two_cards);
        boolean suited = two_cards[0].sameSuit(two_cards[1]);
        String ranks = two_cards[0].getRank() + two_cards[1].getRank();
        if (suited){
            return "S" + ranks;
        }
        else{
            return "N" + ranks;}
        }
    }

