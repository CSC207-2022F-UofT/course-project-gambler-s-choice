package register_menu_use_case;

public class UserRegisterResponseModel {

    private String user;
    private String password;
    private String type;
    private final int balance;
    private boolean loggedIn;

    public UserRegisterResponseModel(String user, String password, String type, int balance, boolean loggedIn) {
        this.user = user;
        this.password = password;
        this.type = type;
        this.balance = balance;
        this.loggedIn = loggedIn;
    }

    /**
     * Reports this UserRegisterResponseModel's username
     * @return this UserRegisterResponseModel's username
     */
    String getUser() {
        return user;
    }

    /**
     * Sets this UserRegisterResponseModel's username
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * Reports this UserRegisterResponseModel's password
     * @return this UserRegisterResponseModel's password
     */
    String getPassword() {
        return password;
    }

    /**
     * Sets this UserRegisterResponseModel's password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Reports this UserRegisterResponseModel's type
     * @return this UserRegisterResponseModel's type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets this UserRegisterResponseModel's balance
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Reports the loggedin status of this UserRegisterResponseModel
     * @return true iff this UserRegisterResponseModel's loggedin status is true
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

}
