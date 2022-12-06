import game_entities.Card;

import java.util.Arrays;
import java.util.Hashtable;

public class Hint_for_chance {
    private final Double win_rate;



    /**Construct a hint that takes in two cards, that is player's private hands. the win rate
     * is come from a hash-table that stores each initial card's win rate, calculated by millions of
     * simulations.
     *
     * @param player_hands two private cards of the player.
     */
    public Hint_for_chance(Card[] player_hands){
        assert player_hands.length == 2;
        this.win_rate = initial_win_rate(convertor(player_hands));
    }

    public Double get_win_rate(){
        return this.win_rate;
    }

    private Double initial_win_rate(String converted_hands) {
        Hashtable<String, Double> win_rate_table = new Hashtable<String, Double>();
        String[] starting_hand = {"NAA", "NKK", "NQQ", "NJJ", "NXX", "N99", "N88", "N77", "N66", "N55", "N44", "N33",
                "N22", "SKA", "SQA", "SQK", "SJA", "SJK", "SJQ", "SXA", "SXK", "SXQ", "SXJ", "S9A", "S9K", "S9Q", "S9J",
                "S9X", "S8A", "S8K", "S8Q", "S8J", "S8X", "S89", "S7A", "S7K", "S7Q", "S7J", "S7X", "S79", "S78", "S6A",
                "S6K", "S6Q", "S6J", "S6X", "S69", "S68", "S67", "S5A", "S5K", "S5Q", "S5J", "S5X", "S59", "S58", "S57",
                "S56", "S4A", "S4K", "S4Q", "S4J", "S4X", "S49", "S48", "S47", "S46", "S45", "S3A", "S3K", "S3Q", "S3J",
                "S3X", "S39", "S38", "S37", "S36", "S35", "S34", "S2A", "S2K", "S2Q", "S2J", "S2X", "S29", "S28", "S27",
                "S26", "S25", "S24", "S23", "NKA", "NQA", "NQK", "NJA", "NJK", "NJQ", "NXA", "NXK", "NXQ", "NXJ", "N9A",
                "N9K", "N9Q", "N9J", "N9X", "N8A", "N8K", "N8Q", "N8J", "N8X", "N89", "N7A", "N7K", "N7Q", "N7J", "N7X",
                "N79", "N78", "N6A", "N6K", "N6Q", "N6J", "N6X", "N69", "N68", "N67", "N5A", "N5K", "N5Q", "N5J", "N5X",
                "N59", "N58", "N57", "N56", "N4A", "N4K", "N4Q", "N4J", "N4X", "N49", "N48", "N47", "N46", "N45", "N3A",
                "N3K", "N3Q", "N3J", "N3X", "N39", "N38", "N37", "N36", "N35", "N34", "N2A", "N2K", "N2Q", "N2J", "N2X",
                "N29", "N28", "N27", "N26", "N25", "N24", "N23"};
        Double[] starting_win_rate ={31.0, 26.0, 22.0, 16.8, 15.3, 14.2, 13.4, 12.8, 12.2, 11.9, 11.9,
                11.9, 20.2, 18.7, 18.1, 17.5, 17.1, 16.6, 16.6, 16.1, 15.8, 15.8, 14.6, 14.2, 13.8, 13.8,
                14.1, 13.9, 12.8, 12.4, 12.5, 12.7, 12.6, 13.4, 12.2, 11.2, 11.1, 11.5, 11.7, 12.0, 13.0,
                11.8, 10.9, 10.1, 10.3, 10.7, 11.2, 11.5, 13.4, 11.6, 10.6, 9.9, 9.2, 9.6, 10.1, 10.7,
                11.1, 13.2, 11.4, 10.4, 9.7, 9.1, 8.7, 9.1, 9.7, 10.4, 10.9, 13.1, 11.3, 10.4, 9.6,
                9.1, 8.5, 8.2, 8.8, 9.5, 10.2, 9.8, 12.7, 11.3, 10.3, 9.5, 9.0, 8.5, 8.1, 7.9,
                8.5, 9.3, 9.0, 8.7, 16.7, 14.9, 14.4, 13.5, 13.2, 12.9, 12.4, 12.2, 12.0, 12.1, 10.2,
                9.9, 9.8, 10.0, 10.4, 9.4, 8.5, 8.3, 8.5, 8.9, 9.0, 8.8, 7.9, 7.0, 7.1, 7.5,
                8.0, 8.4, 8.4, 7.5, 6.6, 5.9, 6.3, 6.8, 7.4, 7.9, 8.9, 7.1, 6.3, 5.6, 5.2,
                5.6, 6.3, 7.0, 7.6, 8.7, 7.0, 6.1, 5.5, 5.0, 4.7, 5.3, 6.0, 6.8, 7.4, 8.5,
                6.9, 6.1, 5.4, 5.0, 4.5, 4.3, 4.9, 5.7, 6.6, 6.2, 8.2, 6.8, 6.0, 5.3, 4.9,
                4.5, 4.2, 4.0, 4.7, 5.6, 5.4, 5.0};

        for (int index = 0; index < starting_hand.length; index++) {
            win_rate_table.put(starting_hand[index],starting_win_rate[index]);
        }
        return win_rate_table.get(converted_hands);
    }

    /**Convert two cards to a string form that is easier to be read by the hashmap.
     *
     * @param two_cards - player_hands two private cards of the player.
     * @return S means suited, N means non-suited, for example, {"HA","HK"} will return "SKA".
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

