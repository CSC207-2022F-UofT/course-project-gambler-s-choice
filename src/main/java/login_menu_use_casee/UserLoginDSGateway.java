package login_menu_use_casee;


public interface UserLoginDSGateway {

    /**
     * Checks if the given username exists in the database.
     * @param name the given username
     */
    boolean existsByName(String name);

    /**
     * Checks if the given password matches with the password corresponding to the username in the database
     * @param user the given username
     * @param pass the given password
     */
    boolean matchingPass(String user, String pass);

    /**
     * Reports the type and balance of the user with the given name and password
     * @param name the given name
     * @param pass the given password
     */
    String[] getAccountInfo(String name, String pass);
}
