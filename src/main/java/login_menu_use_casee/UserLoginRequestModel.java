package login_menu_use_casee;

public class UserLoginRequestModel {

    /**
     * The UserLoginRequestModel is a data structure that stores the username and the password for logging in.
     */

    private final String user;
    private final String password;

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
