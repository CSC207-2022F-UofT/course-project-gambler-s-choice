import login_menu_entities.User;

// Admin class
//TODO FIX IMPLEMENTATION TO FOLLOW CLEAN ARCHITECTURE
public class Admin extends User {

    /**
     * Creates an Admin login_menu_entities.User by calling the login_menu_entities.User's Constructor and passing the params
     * @param name name of the user
     * @param password password of the user
     */
    public Admin(String name, String password){
        super(name, password, "admin");
    }

    /**
     * Edits the balance of a user
     * @param user The user whose balance is being edited
     * @param amount The amount being added to their balance
     */
    public void editBalance(User user, int amount){
        user.addBalance(amount);
        //TODO EDIT FILE TO UPDATE BALANCE AS WELL
    }

}

