package login_menu_use_case;

/**
 * The response model which holds any data needed in response of an action
 */
public class UserLoginResponseModel {
    private String user;
    private String password;
    private String type;
    private final int balance;
    private boolean loggedIn;

    /**
     * Constructor of the response model which assigns the neceessary values
     * @param user The username of the User
     * @param password The password of the User
     * @param type The type of the User
     * @param balance the balance of the User
     * @param loggedIn If the User is successfully logged in
     */
    public UserLoginResponseModel(String user, String password, String type, int balance, boolean loggedIn) {
        this.user = user;
        this.password = password;
        this.type = type;
        this.balance = balance;
        this.loggedIn = loggedIn;
    }

    /**
     * Reports this UserLoginResponseModel's username
     * @return this UserLoginResponseModel's username
     */
    public String getUser() {
        return user;
    }

    /**
     * Reports this UserLoginResponseModel's password
     * @return this UserLoginResponseModel's password
     */
    String getPassword() {
        return password;
    }

    /**
     * Reports this UserLoginResponseModel's type
     * @return this UserLoginResponseModel's type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets this UserLoginResponseModel's balance
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Reports the loggedin status of this UserLoginResponseModel
     * @return true iff this UserLoginResponseModel's loggedin status is true
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }
}
