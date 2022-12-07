package game_entities;

public interface GameInterface {
    void dealCards();

    void nextPlayer();

    void nextRound();

    int[] findWinner(Player[] players, String[] flop);
}
