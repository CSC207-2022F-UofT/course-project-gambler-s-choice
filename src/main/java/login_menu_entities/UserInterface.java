package login_menu_entities;

/**
 * General User method outline
 */
public interface UserInterface {

    /**
     * Reports the username
     * @return the username
     */
    String getName();

    /**
     * Reports the password
     * @return the password
     */
    String getPassword();

    /**
     * Reports the type
     * @return the type
     */
    String getType();

    /**
     * Reports the balance
     * @return the balance
     */
    int getBalance();
}
