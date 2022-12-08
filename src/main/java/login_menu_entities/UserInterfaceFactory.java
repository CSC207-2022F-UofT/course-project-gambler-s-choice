package login_menu_entities;

/**
 * Interface of the factory to have the general outline of other login entity factories
 */
public interface UserInterfaceFactory {
    /**
     * Generates the necessary User and returns it
     * @param name name of the User
     * @param password password of the User's account
     * @param type type of the User's account
     * @param balance balance of the User's account
     * @return The values of the User as its respective entity
     */
    UserInterface create(String name, String password, String type, int balance);
}
