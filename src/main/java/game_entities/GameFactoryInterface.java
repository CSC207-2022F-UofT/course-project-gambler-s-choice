package game_entities;

/**
 * Represents a gamefactory to create a gamefactory.
 * Used by the use case interactors for clean architecture purposes.
 */
public interface GameFactoryInterface {

    /**
     * Given the input parameters, create a new game object with the appropriate values
     * @param currentPlayer the current player
     * @param firstPlayer the first player of the round
     * @param lastToBet the first one to bet (last to call)
     * @param playerBalance the balance of all the players as an array
     * @param card1 the first card of every player as an array
     * @param card2 the second card of every player as an array
     * @param tableCard the cards on the table
     * @param currentBet the current bet
     * @param isActive an array of active players
     * @param playerBets an array of player bets
     * @param deck the deck as strings
     * @return a game object
     */
    GameInterface create(int currentPlayer, int firstPlayer, int lastToBet, int[] playerBalance,
                         String[] card1, String[] card2, String[] tableCard, int currentBet,
                         boolean[] isActive, int[] playerBets, String deck[]);
}
