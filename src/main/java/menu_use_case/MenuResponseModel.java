package menu_use_case;

public class MenuResponseModel {

    private boolean inGame;
    private String user;
    private int balance;
    private boolean loggedIn;
    private boolean rulesVisible;

    public MenuResponseModel(String user, int balance, boolean inGame, boolean loggedIn, boolean rulesVisible){
        this.inGame = inGame;
        this.user = user;
        this.balance = balance;
        this.loggedIn = loggedIn;
        this.rulesVisible = rulesVisible;
    }

    /**
     * Returns whether the user is in the game
     * @return true iff the user is in the game
     */
    public boolean isInGame() {
        return inGame;
    }

    /**
     * Reports the username of the user
     * @return the username of the user
     */
    public String getUser() {
        return user;
    }

    /**
     * Reports the user's balance
     * @return the user's balance
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Returns whether the user is logged in
     * @return true iff the usewr is logged in
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * Returns whether the rules are visible
     * @return true iff the rules are visible
     */
    public boolean isRulesVisible() {
        return rulesVisible;
    }
}
