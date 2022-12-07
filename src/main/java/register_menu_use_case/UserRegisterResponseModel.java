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

    public String getUser() {
        return user;
    }
    String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public int getBalance() {
        return balance;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }
}
