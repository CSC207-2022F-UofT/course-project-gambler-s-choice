package login_menu_entities;

public class Admin extends User implements UserInterface {

    /**
     * Creates an Admin User by calling the User's Constructor and passing the params
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
