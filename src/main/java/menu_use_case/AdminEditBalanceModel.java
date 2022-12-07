package menu_use_case;

public class AdminEditBalanceModel {
    /**
     * The UserEditBalanceModel is just a data structure that stores a user and a balance for editing user balances
     */
    String user;
    int balance;

    public AdminEditBalanceModel(String user, int balance){
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
