import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pool {

    // players and bets will be parallel arrays
    private final Player[] players;
    private int[] bets;

    /**
     * Class constructor for Pool
     *
     * @param players   the players who are playing this game
     */
    public Pool(Player[] players) {
        this.players = players;
        this.bets = new int[players.length];
        this.resetBets();
    }

    /**
     * Adds the money into the pool when a player makes a bet
     *
     * @param player    the player making the bet
     * @param amount    the amount the player is betting
     */
    public void addMoney(Player player, int amount) {
        int index = Arrays.asList(this.players).indexOf(player); // Find the index of player
        this.bets[index] += amount; // Add this money to the same index as players for bets array
    }

    private void resetBets() {
        for(int i = 0; i < this.bets.length; i++) {
            this.bets[i] = 0;
        }
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

    public static void main(String[] args) {
        Player player1 = new Player();
        Player player2 = new Player();
        Player player3 = new Player();
        Player[] players = new Player[] {player1, player2, player3};
        Pool pool = new Pool(players);

        // Placing bets
        pool.addMoney(player1, 5);
        pool.addMoney(player2, 10);
        pool.addMoney(player3, 15);

        // Calculate winnings
        pool.calculateWinnings(new int[]{1, 2, 3});
        System.out.println(player1.money);
        System.out.println(player2.money);
        System.out.println(player3.money);

        System.out.println();
        System.out.println(pool.bets[0]);
        System.out.println(pool.bets[1]);
        System.out.println(pool.bets[2]);
    }
}
