package game_entities;

public interface GameFactoryInterface {
    GameInterface create(int currentPlayer, int firstPlayer, int lastToBet, int[] playerBalance,
                         String[] card1, String[] card2, String[] tableCard, int currentBet,
                         boolean[] isActive, int[] playerBets, String deck[]);
}
