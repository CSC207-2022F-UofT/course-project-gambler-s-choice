package menu_use_case;

public class UserEditBalanceModel {

    String user;
    int balance;

    public UserEditBalanceModel(String user, int balance){
        this.user = user;
        this.balance = balance;

    }

    public String getUser(){
        return user;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
    public void setUser(String user) {
        this.user = user;
    }
}
