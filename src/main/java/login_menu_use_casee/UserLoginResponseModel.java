package login_menu_use_casee;

public class UserLoginResponseModel {
    private String user;
    private String password;
    private String type;
    private final int balance;

    public UserLoginResponseModel(String user, String password, String type, int balance) {
        this.user = user;
        this.password = password;
        this.type = type;
        this.balance = balance;
    }

    String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getBalance() {
        return balance;
    }
}
