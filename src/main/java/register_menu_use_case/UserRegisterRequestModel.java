package register_menu_use_case;

public class UserRegisterRequestModel {

    /**
     * The UserLoginRequestModel is a data structure that stores the username and the password for registering.
     */

    private final String user;
    private final String password;
    private final String password2;

    public UserRegisterRequestModel(String user, String password, String password2){
        this.user = user;
        this.password = password;
        this.password2 = password2;
    }

    /**
     * Reports the username of the user
     * @return the username of the user
     */
    public String getUser() {
        return user;
    }

    /**
     * Reports the first password entered by the user
     * @return the first password entered by the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Reports the second password entered by the user
     * @return the second password entered by the user
     */
    public String getPassword2() {
        return password2;
    }
}
