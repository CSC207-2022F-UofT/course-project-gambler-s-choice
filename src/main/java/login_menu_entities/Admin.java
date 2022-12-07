package login_menu_entities;

public class Admin extends User implements UserInterface {

    /**
     * Creates an Admin login_menu_entities.User by calling the login_menu_entities.User's Constructor and passing the params
     * @param name name of the user
     * @param password password of the user
     */
    public Admin(String name, String password){
        super(name, password, "admin");
    }

    public Admin(String name, String password, int balance){
        super(name, password, "admin", balance);
    }

}
