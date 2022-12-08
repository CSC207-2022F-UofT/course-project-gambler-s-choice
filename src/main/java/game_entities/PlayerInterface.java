package game_entities;

/**
 * Interface for player objects, used for clean architecture
 */
public interface PlayerInterface {

    /**
     * Add money to the player
     * @param amount the amount of money
     */
    void addMoney(int amount);

    /**
     * Get the balanace of the player
     * @return the current balance of the player
     */
    int getBalance();
}
