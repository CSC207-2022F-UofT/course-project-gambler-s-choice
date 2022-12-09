package game_entities;

/**
 * Interface for a game.
 * Represents the game object that will be built by the GameFactory
 */
public interface GameInterface {
    void dealCards();

    void nextPlayer();

    void nextRound();

    int[] findWinner(Player[] players, String[] flop);

    Player[] getPlayers();

    Pool getPool();

    Deck getDeck();

    Card[] getTableCards();

    int getCurrentWager();

    int getCurrentPlayer();

    int getFirstPlayer();

    int lastToBet();

    boolean[] getActive();

    void newGame();
}
