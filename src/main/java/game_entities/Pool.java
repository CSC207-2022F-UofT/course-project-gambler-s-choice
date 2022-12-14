package game_entities;

import game_entities.PlayerInterface;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Pool class used to manage the bet of the players and assign winnings
 */
public class Pool {

    // players and bets will be parallel arrays
    private final PlayerInterface[] players;
    private int[] bets;

    /**
     * Class constructor for game_entities.Pool
     *
     * @param players   the players who are playing this game
     */
    public Pool(PlayerInterface[] players) {
        this.players = players;
        this.bets = new int[players.length];
        this.resetBets();
    }

    public Pool(PlayerInterface[] players, int[] bets) {
        this.players = players;
        this.bets = bets;

    }

    /**
     * Returns the total amount in the pool
     *
     * @return the sum of values in the bets array
     */
    public int totalBets() {
        int total = 0;
        for (int i : this.bets) {
            total += i;
        }
        return total;
    }

    /**
     * Adds the money into the pool when a player makes a bet
     *
     * @param player    the player making the bet
     * @param amount    the amount the player is betting
     */
    public void addMoney(PlayerInterface player, int amount) {
        int index = Arrays.asList(this.players).indexOf(player); // Find the index of player
        this.bets[index] += amount; // Add this money to the same index as players for bets array
    }

    private void resetBets() {
        Arrays.fill(this.bets, 0);
    }

    /**
     * Calculates the winnings for the players
     * The index of the input array corresponds to the players
     * So ranking[0] is the rank of players[0], ranking[1] is the rank of players[1], etc.
     * The number within the array should range from 1 to the length of the array
     * Ex. If the array length is 6, the numbers should range from 1 to 6
     * This corresponds to the fact that you can't have a 7th place player in a 6 player game
     * This method will add money to the player's balance and then empty the pool
     *
     * @param ranking   an array of the same length as the number of players denoting the ranking
     *                  of each player
     */
    public void calculateWinnings(int[] ranking) {
        // Loop from 1 to length of ranking
        for (int i = 1; i <= ranking.length; i++) {
            int[] ithPlace = indexEqualTo(i, ranking);
            int[] betSublist = new int[ithPlace.length];
            for (int j = 0; j < ithPlace.length; j++) {
                betSublist[j] = this.bets[ithPlace[j]];
            }
            this.calculateWinningsHelper(ithPlace, betSublist);
        }

        this.resetBets(); // Redundant since calculateWinnings should do this
    }

    private void calculateWinningsHelper(int[] ithPlace, int[] betSublist) {
        ArrayList<Integer> winners = new ArrayList<>();
        for (int i = 0; i < ithPlace.length; i++) {
            winners.add(ithPlace[i]);
        }

        int index = smallestIndexNonZero(betSublist);
        while (index != -1) {
            int moneyPool = 0;
            int amount = betSublist[index];

            // Subtract from bet sublist
            for (int i = 0; i < betSublist.length; i++) {
                if (betSublist[i] != 0) {
                    betSublist[i] -= amount;
                }
            }

            // Subtract money from actual bet list
            for (int i = 0; i < this.bets.length; i++) {
                if (this.bets[i] < amount) {
                    moneyPool += this.bets[i];
                    this.bets[i] = 0;
                } else {
                    this.bets[i] -= amount;
                    moneyPool += amount;
                }
            }

            // Add everyone's money to this pool
            this.distributeWinnings(winners, moneyPool);

            // Since that player has their winnings, remove them from list
            winners.remove(Integer.valueOf(ithPlace[index]));

            index = smallestIndexNonZero(betSublist);
        }
    }

    private void distributeWinnings(ArrayList<Integer> winners, int amount) {
        if (winners.size() > 0) { // Only run when winners in non-empty
            int winnings = amount / winners.size();
            int remainder = amount % winners.size();

            /*
            Divides evenly the money between players
            Slightly favours first player
            Example; if 9 dollars is the amount, first player wins 5 dollars, 2nd player wins 4
             */
            for (int winner: winners) {
                this.players[winner].addMoney(winnings);
                if (remainder != 0) {
                    this.players[winner].addMoney(1);
                    remainder -= 1;
                }
            }
        }
    }

    private static int[] indexEqualTo(int value, int[] intArray) {
        ArrayList<Integer> toReturn = new ArrayList<>();
        for (int i = 0; i < intArray.length; i++) {
            if (intArray[i] == value) {
                toReturn.add(i);
            }
        }
        return toReturn.stream().mapToInt(i -> i).toArray(); // Converts from ArrayList<Integer> to int[]
    }

    /**
     * Returns the smallest non-zero index of the array
     * Returns -1 if whole array is 0
     *
     * @param intArray  non-empty array of all non-negative integers less than 1,000,000,000
     * @return  index of the array with the smallest value
     */
    private static int smallestIndexNonZero(int[] intArray) {
        int smallest = 1000000000; // A player should not have a bet greater than 1,000,000,000
        int smallestIndex = -1;

        for (int i = 0; i < intArray.length; i++) {
            if (intArray[i] < smallest && intArray[i] != 0) {
                smallestIndex = i;
                smallest = intArray[i];
            }
        }

        return smallestIndex;
    }

    /**
     * Getter for bets
     *
     * @return the list of bets players have made
     */
    public int[] getBets() {
        return bets;
    }

    /**
     * To string method
     * Return the array of bets contained in pool
     * @return array of bets as a string
     */
    @Override
    public String toString() {
        String toReturn = "[";
        for (int i : this.bets) {
            toReturn = toReturn + " " + i;
        }
        return toReturn + " ]";
    }
}
