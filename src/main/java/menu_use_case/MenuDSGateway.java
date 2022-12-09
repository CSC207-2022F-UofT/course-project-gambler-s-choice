package menu_use_case;

public interface MenuDSGateway {

    /**
     * Checks if a user has sufficient balance in the database
     * @param user the given username
     * @return true iff the user has sufficient balance
     */
    boolean sufficientBalance(String user);

    /**
     * Gets the balance of a user from the database
     * @param user the name of the user to get the balance from
     * @return how much balance this user has
     */
    int getBalance(String user);
}
