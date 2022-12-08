package login_menu_use_casee;

public class UserLoginResponseModel {
    private String user;
    private String password;
    private String type;
    private final int balance;
    private boolean loggedIn;

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
    String getUser() {
        return user;
    }

    /**
     * Sets this UserLoginResponseModel's username
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Reports this UserLoginResponseModel's password
     * @return this UserLoginResponseModel's password
     */
    String getPassword() {
        return password;
    }

    /**
     * Sets this UserLoginResponseModel's password
     */
    public void setPassword(String password) {
        this.password = password;
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
