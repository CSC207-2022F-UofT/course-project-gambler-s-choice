package login_menu_entities;

/**
 * Admin Entity that stores the data of the Admin User
 */
public class Admin extends User implements UserInterface {

    /**
     * Creates an Admin User by calling the User's Constructor and passing the params
     * @param name name of the user
     * @param password password for the account of the user
     */
    public Admin(String name, String password){
        super(name, password, "admin");
    }

    /**
     * Creates an Admin User with a specific balance by calling the User's Constructor and passing the params
     * @param name name of the admin user
     * @param password password for the account of the user
     * @param balance balance of the user
     */
    public Admin(String name, String password, int balance){
        super(name, password, "admin", balance);
    }

}
