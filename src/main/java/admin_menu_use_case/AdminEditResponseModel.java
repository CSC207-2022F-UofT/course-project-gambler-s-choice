package admin_menu_use_case;

public class AdminEditResponseModel {
    private String user;
    private final int balance;

    public AdminEditResponseModel(String user, int balance){
        this.user = user;
        this.balance = balance;
    }
    String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getBalance() {
        return balance;
    }
}
