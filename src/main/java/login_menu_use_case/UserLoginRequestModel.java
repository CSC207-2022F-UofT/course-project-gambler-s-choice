package login_menu_use_case;

/**
 * Request model to hold all the values of the inputs
 */
public class UserLoginRequestModel {

    /**
     * The UserLoginRequestModel is a data structure that stores the username and the password for logging in.
     */

    private final String user;
    private final String password;

    /**
     * Constructor of the requestmodel which assigns the necessary data
     * @param user
     * @param password
     */
    public UserLoginRequestModel(String user, String password){
        this.user = user;
        this.password = password;
    }

    /**
     * Reports the username of the user
     * @return the username of the user
     */
    public String getUser() {
        return user;
    }

    /**
     * Reports the password of the user
     * @return the password of the user
     */
    public String getPassword() {
        return password;
    }
}
