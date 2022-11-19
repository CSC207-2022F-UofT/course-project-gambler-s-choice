import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pool {
    private ArrayList<PartPool> pools;
    private int poolCounter;

    // players and bets will be parallel arrays
    private final Player[] players;
    private ArrayList<Player> playersEntitled;
    private int[] bets;

    public Pool(Player[] players) {
        this.players = players;
        this.bets = new int[players.length];
        this.resetBets();

        this.pools = new ArrayList<>();
        PartPool mainPool = new PartPool(players);
        this.pools.add(mainPool);
        this.poolCounter = 0;

        this.playersEntitled = new ArrayList<>();
        this.playersEntitled.addAll(List.of(players));
    }

    public void addMoney(Player player, int amount) {
        int index = Arrays.asList(this.players).indexOf(player); // Find the index of player
        this.bets[index] += amount; // Add this money to the same index as players for bets array
    }

    private void resetBets() {
        for(int i = 0; i < this.bets.length; i++) {
            this.bets[i] = 0;
        }
    }

    public void endRound() {
        /* Deal with folding
        For any players who have folded, add their bets into the current pool

        Heuristic: keep looping until whole bets array is 0
        Find the smallest value
        3 cases:
        If the player has not gone all in, add the money to the pool, and set bet[i] to 0
            Sub-case 1: player folded: remove player entitlement from current pool
            Sub-case 2: player not folded: do nothing
        If the player has gone all in:
            Add their money to the pool
            Add everyone else's money to the pool by that players amount
            Remove that player from entitlement
            Create a new pool with only entitled players
         */

        // TODO Refactor this method so that its not a large nested loop
        int index = smallestIndexNonZero(this.bets);
        while (index != -1) {
            // Player not gone all in
            if (!this.players[index].getAllIn()) {
                this.pools.get(this.poolCounter).addMoney(this.bets[index]);
                this.bets[index] = 0;

                // If player is folded
                if (this.players[index].getFold()) {

                    // Remove player from entitlement
                    this.playersEntitled.remove(this.players[index]);
                    this.pools.get(this.poolCounter).removePlayer(this.players[index]);
                }
            } else { // Player has gone all in
                int amount = this.bets[index];
                // Loop through every bet, and subtract this amount from the bet
                for (int i = 0; i < this.bets.length; i++) {
                    if (this.bets[i] != 0) {
                        this.bets[i] -= amount;
                        this.pools.get(this.poolCounter).addMoney(amount);
                    }
                }

                // Remove player from entitlement
                this.playersEntitled.remove(this.players[index]);
                // Create new pool
                this.poolCounter++;
                PartPool sidePool = new PartPool((ArrayList<Player>) this.playersEntitled.clone());
                // Add this pool to the list of pools
                this.pools.add(sidePool);
            }

            index = smallestIndexNonZero(this.bets);
            /*
            Note: this loop should terminate
            Every iteration, a player's bet is set to 0
            It will exit once every player's bet is set to 0
             */
        }


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

        player1.allIn = true;
        player2.allIn = true;
        System.out.println(pool.bets[0]);
        System.out.println(pool.bets[1]);
        System.out.println(pool.bets[2]);
        System.out.println();

        // Seeing what happens when we end the round
        pool.endRound();

        System.out.println(pool.bets[0]);
        System.out.println(pool.bets[1]);
        System.out.println(pool.bets[2]);
        System.out.println(pool.pools.get(1).getAmount());
        System.out.println(pool.pools.get(1).getPlayersEntitled());
    }
}
