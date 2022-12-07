package game_entities;

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
}
