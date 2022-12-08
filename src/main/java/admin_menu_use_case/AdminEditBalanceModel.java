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

    /**
     * Reports the username of the user
     * @return the username of the user
     */
    public String getUser(){
        return user;
    }

    /**
     * Reports the balance of the user
     * @return the balance of the user
     */
    public String getBalance() {
        return balance;
    }

    /**
     * Reports the input to the system
     * @return the input to the system
     */
    public String getInput(){
        return input;
    }

    /**
     * Returns whether the rules are visible
     * @return true iff the rules are visible
     */
    public boolean isRulesVisible() {
        return rulesVisible;
    }
}
