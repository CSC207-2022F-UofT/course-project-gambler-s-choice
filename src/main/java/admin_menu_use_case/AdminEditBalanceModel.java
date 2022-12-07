package admin_menu_use_case;

public class AdminEditBalanceModel {
    /**
     * The UserEditBalanceModel is just a data structure that stores a user and a balance for editing user balances
     */
    private final String user;
    private final String balance;
    private final String input;
    private final boolean rulesVisible;

    public AdminEditBalanceModel(String user, String balance, String input, boolean rulesVisible){
        this.user = user;
        this.balance = balance;
        this.input = input;
        this.rulesVisible = rulesVisible;
    }

    public String getUser(){
        return user;
    }

    public String getBalance() {
        return balance;
    }

    public String getInput(){
        return input;
    }

    public boolean isRulesVisible() {
        return rulesVisible;
    }
}
